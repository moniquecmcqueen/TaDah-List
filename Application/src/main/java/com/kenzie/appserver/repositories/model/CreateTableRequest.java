package com.kenzie.appserver.repositories.model;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.model.*;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.TableDescription;
import com.kenzie.appserver.Application.*;
import java.util.Arrays;
import java.util.List;

public class CreateTableRequest extends com.amazonaws.services.dynamodbv2.model.CreateTableRequest {

    public static void main(String[] args) {
        // Create an instance of the DynamoDB client
        AmazonDynamoDB dynamoDB = AmazonDynamoDBClientBuilder.defaultClient();

        // Define the list of record classes
        List<Class<?>> recordClasses = Arrays.asList(
                ChildRecord.class,
                ParentRecord.class,
                TaskRecord.class
        );

        // Iterate through the record classes and create tables
        for (Class<?> recordClass : recordClasses) {
            DynamoDBTable tableAnnotation = recordClass.getAnnotation(DynamoDBTable.class);
            if (tableAnnotation != null) {
                String tableName = tableAnnotation.tableName();
                createTable(dynamoDB, recordClass, tableName);
            }
        }
    }

    private static void createTable(AmazonDynamoDB dynamoDB, Class<?> recordClass, String tableName) {
        // Define the table schema
        AttributeDefinition partitionKey = new AttributeDefinition()
                .withAttributeName("id")
                .withAttributeType("S");

        KeySchemaElement partitionKeySchema = new KeySchemaElement()
                .withAttributeName("id")
                .withKeyType(KeyType.HASH);

        CreateTableRequest createTableRequest = (CreateTableRequest) new CreateTableRequest()
                .withTableName(tableName)
                .withAttributeDefinitions(partitionKey)
                .withKeySchema(partitionKeySchema)
                .withProvisionedThroughput(new ProvisionedThroughput()
                        .withReadCapacityUnits(10L)
                        .withWriteCapacityUnits(10L));

        // Add GSI if it exists for ChildRecord
        if (recordClass.equals(ChildRecord.class)) {
            AttributeDefinition gsiPartitionKey = new AttributeDefinition()
                    .withAttributeName("gsiKey")
                    .withAttributeType("S");

            KeySchemaElement gsiPartitionKeySchema = new KeySchemaElement()
                    .withAttributeName("gsiKey")
                    .withKeyType(KeyType.HASH);

            GlobalSecondaryIndex gsi = new GlobalSecondaryIndex()
                    .withIndexName("ChildGSI")
                    .withProvisionedThroughput(new ProvisionedThroughput()
                            .withReadCapacityUnits(10L)
                            .withWriteCapacityUnits(10L))
                    .withProjection(new Projection().withProjectionType(ProjectionType.ALL))
                    .withKeySchema(gsiPartitionKeySchema);

            createTableRequest.withGlobalSecondaryIndexes(gsi);
            createTableRequest.withAttributeDefinitions(gsiPartitionKey);
        }

        // Add GSI if it exists for ParentRecord
        if (recordClass.equals(ParentRecord.class)) {
            AttributeDefinition gsiPartitionKey = new AttributeDefinition()
                    .withAttributeName("gsiKey")
                    .withAttributeType("S");

            KeySchemaElement gsiPartitionKeySchema = new KeySchemaElement()
                    .withAttributeName("gsiKey")
                    .withKeyType(KeyType.HASH);

            GlobalSecondaryIndex gsi = new GlobalSecondaryIndex()
                    .withIndexName("ParentGSI")
                    .withProvisionedThroughput(new ProvisionedThroughput()
                            .withReadCapacityUnits(10L)
                            .withWriteCapacityUnits(10L))
                    .withProjection(new Projection().withProjectionType(ProjectionType.ALL))
                    .withKeySchema(gsiPartitionKeySchema);

            createTableRequest.withGlobalSecondaryIndexes(gsi);
            createTableRequest.withAttributeDefinitions(gsiPartitionKey);
        }

        // Create the table
        CreateTableResult createTableResult = dynamoDB.createTable(createTableRequest);

        // Access the table description
        TableDescription tableDescription = createTableResult.getTableDescription();
        System.out.println("Table created: " + tableDescription.getTableName() + " (" + recordClass.getSimpleName() + ")");
    }


}

