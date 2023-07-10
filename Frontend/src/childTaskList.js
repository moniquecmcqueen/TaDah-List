document.addEventListener('DOMContentLoaded', function() {

    // Update the heading with the child's username
    const childUsername =document.getElementById('child-username').value;

    const tasklistHeading = document.getElementById('tasklist-heading');
    tasklistHeading.textContent = `${childUsername}'s Task List`;

});


function fetchChildTasks(childUsername) {
    fetch(`/tasks/${childUsername}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else if (response.status === 204) {
                throw new Error('No tasks found for the specified child username.');
            } else {
                throw new Error('An error occurred while retrieving tasks. Please try again later.');
            }
        })
        .then(data => {
            // Map the JSON response to an array of Task objects
            const tasks = data.map(taskData => {
                return new Task(
                    taskData.taskId,
                    taskData.isCompleted,
                    taskData.taskTitle,
                    taskData.parentUsername,
                    taskData.childUsername
                );
            });

            // Handle the tasks data as needed
            console.log(tasks);

            // Redirect to childTaskList.html with the childUsername and tasks in the URL
            const queryParams = `childUsername=${childUsername}&tasks=${JSON.stringify(tasks)}`;
            window.location.href = `childTaskList.html?${queryParams}`;
        })
        .catch(error => {
            const responseContainer = document.getElementById('response-container');
            responseContainer.textContent = error.message;
            // Clear the input field or perform any other desired action
        });
}

function renderTasks(tasks) {
    const tableBody = document.getElementById('task-table-body');
    tableBody.innerHTML = '';

    for (const task of tasks) {
        const row = document.createElement('tr');

        const taskIdCell = document.createElement('td');
        taskIdCell.textContent = task.taskId;
        row.appendChild(taskIdCell);

        const taskTitleCell = document.createElement('td');
        taskTitleCell.textContent = task.taskTitle;
        row.appendChild(taskTitleCell);

        const parentUsernameCell = document.createElement('td');
        parentUsernameCell.textContent = task.parentUsername;
        row.appendChild(parentUsernameCell);

        const childUsernameCell = document.createElement('td');
        childUsernameCell.textContent = task.childUsername;
        row.appendChild(childUsernameCell);

        const isCompletedCell = document.createElement('td');
        isCompletedCell.textContent = task.isCompleted ? 'Complete' : 'Incomplete';
        row.appendChild(isCompletedCell);

        const actionsCell = document.createElement('td');

        const completeButton = document.createElement('button');
        completeButton.classList.add('complete-button');
        completeButton.textContent = 'Complete';
        completeButton.addEventListener('click', () => {
            updateChildTaskCompletionStatus(task.taskId, true);
        });
        actionsCell.appendChild(completeButton);

        const deleteButton = document.createElement('button');
        deleteButton.classList.add('delete-button');
        deleteButton.textContent = 'Delete';
        deleteButton.addEventListener('click', () => {
            deleteTask(task.taskId);
        });
        actionsCell.appendChild(deleteButton);

        row.appendChild(actionsCell);

        tableBody.appendChild(row);
    }
}

function updateChildTaskCompletionStatus(taskId, isCompleted) {
    // Find the task row with the matching taskId
    const tableBody = document.getElementById('task-table-body');
    const rows = tableBody.getElementsByTagName('tr');
    for (const row of rows) {
        const taskIdCell = row.querySelector('td:nth-child(1)'); // Assuming taskId is in the 1st column
        if (taskIdCell.textContent === taskId) {
            // Retrieve the data from the row cells
            const taskTitleCell = row.querySelector('td:nth-child(2)'); // Assuming taskTitle is in the 2nd column
            const parentUsernameCell = row.querySelector('td:nth-child(3)'); // Assuming parentUsername is in the 3rd column
            const childUsernameCell = row.querySelector('td:nth-child(4)'); // Assuming childUsername is in the 4th column

            // Get the current completion status from the frontend table
            const isCompletedCell = row.querySelector('td:nth-child(5)'); // Assuming isCompleted is in the 5th column
            const currentStatus = isCompletedCell.textContent === 'Complete';

            // Toggle the completion status
            isCompleted = !currentStatus;
            isCompletedCell.textContent = isCompleted ? 'Complete' : 'Incomplete';

            // Create the task update request object
            const taskUpdateRequest = {
                taskId: taskId,
                parentUsername: parentUsernameCell.textContent,
                childUsername: childUsernameCell.textContent,
                isCompleted: isCompleted,
                taskTitle: taskTitleCell.textContent
            };

            // Perform the PUT request to update the task in the backend
            fetch(`/tasks`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(taskUpdateRequest)
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('An error occurred while updating the task.');
                    }
                })
                .then(updatedTask => {
                    console.log('Updated Task:', updatedTask);
                })
                .catch(error => {
                    console.error(error);
                });

            break; // Exit the loop after updating the task
        }
    }
}

fetchChildTasks();