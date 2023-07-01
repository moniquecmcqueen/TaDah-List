// UsernameCheck.js

function checkUsernameExists(username) {
    // Make API calls to check if the username exists in both parent and child controllers
    var parentUrl = "/parents/parentUsername/" + username;
    var childUrl = "/children/childUsername/" + username;

    var parentPromise = fetch(parentUrl).then(function(response) {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error("Error checking parent username");
        }
    }).then(function(data) {
        return data;
    }).catch(function(error) {
        console.error(error);
        return false;
    });

    var childPromise = fetch(childUrl).then(function(response) {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error("Error checking child username");
        }
    }).then(function(data) {
        return data;
    }).catch(function(error) {
        console.error(error);
        return false;
    });

    // Return a Promise that resolves to true if the username exists in both controllers, false otherwise
    return Promise.all([parentPromise, childPromise]).then(function(results) {
        var parentExists = results[0];
        var childExists = results[1];
        return parentExists && childExists;
    });
}
