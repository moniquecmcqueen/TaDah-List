import tadahSound from '../src/assets/tadahSound.mp3';
import popupGif from '../src/assets/tadahPopup.gif';

// Get the childUsername parameter from the URL
const urlParams = new URLSearchParams(window.location.search);
const childUsername = urlParams.get('childUsername');
const parentUsername = urlParams.get('parentUsername');

// Update the heading with the child's username
const tasklistHeading = document.getElementById('tasklist-heading');
tasklistHeading.textContent = `${childUsername}'s Task List`;

document.addEventListener('DOMContentLoaded', function() {
    // Fetch tasks after the DOM content is loaded
    fetchTasks();

    // Show or hide elements based on parentUsername presence
    showHideElements();
});

// List of inspirational quotes for children
const inspirationalQuotes = [
    "Believe you can and you're halfway there. - Theodore Roosevelt",
    "You are braver than you believe, stronger than you seem, and smarter than you think. - A.A. Milne",
    "The future belongs to those who believe in the beauty of their dreams. - Eleanor Roosevelt",
    "You have within you right now, everything you need to deal with whatever the world can throw at you. - Brian Tracy",
    "The only limit to our realization of tomorrow will be our doubts of today. - Franklin D. Roosevelt",
    "In a world where you can be anything, be kind. - Unknown",
    "Success is not the key to happiness. Happiness is the key to success. If you love what you are doing, you will be successful. - Albert Schweitzer",
    "You're off to great places! Today is your day! Your mountain is waiting, so... get on your way! - Dr. Seuss",
    "The more that you read, the more things you will know. The more that you learn, the more places you'll go. - Dr. Seuss",
    "Do what you can, with what you have, where you are. - Theodore Roosevelt"
];

// Function to get a random quote from the list
function getRandomQuote() {
    const randomIndex = Math.floor(Math.random() * inspirationalQuotes.length);
    return inspirationalQuotes[randomIndex];
}

// Function to display the quote of the day
function displayQuoteOfTheDay() {
    const quoteContainer = document.getElementById('quote-container');
    const quote = getRandomQuote();

    quoteContainer.textContent = quote;
}

// Call the function to display the quote of the day
displayQuoteOfTheDay();



function fetchTasks() {
    fetch(`/tasks/${childUsername}`)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('An error occurred while fetching the tasks.');
            }
        })
        .then(data => {
            // Check if the response data is not empty
            if (data && data.length) {
                renderTasks(data);
            } else {
                console.log('No tasks found.');
                // You can perform any additional action when there are no tasks available
            }
        })
        .catch(error => {
            console.error(error);
        });
}

function deleteTask(taskId) {
    fetch(`/tasks/${taskId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                console.log('Task deleted successfully');
                fetchTasks(); // Fetch updated tasks after deleting a task
            } else {
                throw new Error('An error occurred while deleting the task.');
            }
        })
        .catch(error => {
            console.error(error);
        });
}

function renderTasks(tasks) {
    const parentTable = document.getElementById('task-table');
    const childTable = document.getElementById('child-table');

    if (parentUsername) {
        parentTable.style.display = 'table';
        childTable.style.display = 'none';
    } else {
        parentTable.style.display = 'none';
        childTable.style.display = 'table';
    }

    const parentTableBody = document.getElementById('task-table-body');
    parentTableBody.innerHTML = '';

    const childTableBody = document.getElementById('child-table-body');
    childTableBody.innerHTML = '';

    for (const task of tasks) {
        if (parentUsername) {
            // Render in parent table
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
                updateTaskCompletionStatus(task.taskId, true, parentUsername, task.taskTitle);
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

            parentTableBody.appendChild(row);
        } else {
            // Render in child table
            if (task.childUsername === childUsername) {
                const row = document.createElement('tr');
                row.setAttribute('data-username', task.childUsername);

                const taskTitleCell = document.createElement('td');
                taskTitleCell.textContent = task.taskTitle;
                row.appendChild(taskTitleCell);

                const isCompletedCell = document.createElement('td');
                isCompletedCell.textContent = task.isCompleted ? 'Complete' : 'Incomplete';
                row.appendChild(isCompletedCell);

                const actionsCell = document.createElement('td');

                const completeButton = document.createElement('button');
                completeButton.classList.add('complete-button');
                completeButton.textContent = 'Complete';
                completeButton.addEventListener('click', () => {
                    markTaskCompleteChild(task.taskId, task.isCompleted, childUsername, task.taskTitle, task.parentUsername);
                });
                actionsCell.appendChild(completeButton);

                row.appendChild(actionsCell);

                childTableBody.appendChild(row);
            }
        }
    }
}
function showModalPopup(title, content) {
    const modal = new bootstrap.Modal(document.getElementById('completionModal'));
    const modalTitle = document.getElementById('completionModalLabel');
    const modalContent = document.querySelector('#completionModal .modal-body');
    const modalGif = document.getElementById('completion-gif');

    modalTitle.textContent = title;
    modalContent.textContent = content;
    modalGif.style.zIndex = '9999'; // Set a high z-index to bring the GIF to the foreground

    modal.show();
}

function markTaskCompleteChild(taskId, isCompleted, childUsername, taskTitle, parentUsername) {
    const tableBody = document.getElementById('child-table-body');
    const rows = tableBody.getElementsByTagName('tr');

    for (const row of rows) {
        const taskTitleCell = row.querySelector('td:nth-child(1)'); // Assuming task title is in the 1st column

        if (taskTitleCell.textContent === taskTitle) {
            const isCompletedCell = row.querySelector('td:nth-child(2)'); // Assuming isCompleted is in the 2nd column

            const currentStatus = isCompletedCell.textContent === 'Complete';
            const updatedStatus = !currentStatus;

            const taskUpdateRequest = {
                taskId: taskId,
                parentUsername: parentUsername,
                childUsername: childUsername,
                isCompleted: updatedStatus,
                taskTitle: taskTitle
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
                    isCompletedCell.textContent = updatedTask.isCompleted ? 'Complete' : 'Incomplete'; // Update the cell content

                    if (updatedTask.isCompleted) {
                        showModalPopup('Task Completed!', 'TaDah, you completed a task! Good Job!');
                    }
                })
                .catch(error => {
                    console.error(error);
                });

            break; // Exit the loop after updating the task
        }
    }
}



function updateTaskCompletionStatus(taskId, isCompleted, taskTitle) {
    const tableBody = document.getElementById('task-table-body');
    const rows = tableBody.getElementsByTagName('tr');

    for (const row of rows) {
        const taskIdCell = row.querySelector('td:nth-child(1)'); // Assuming taskId is in the 1st column

        if (taskIdCell.textContent === taskId) {
            const isCompletedCell = row.querySelector('td:nth-child(5)'); // Assuming isCompleted is in the 5th column

            const currentStatus = isCompletedCell.textContent === 'Complete';
            const updatedStatus = !currentStatus;

            console.log('currentStatus:', currentStatus);
            console.log('updatedStatus:', updatedStatus);

            const taskUpdateRequest = {
                taskId: taskId,
                parentUsername: parentUsername,
                childUsername: childUsername,
                isCompleted: updatedStatus,
                taskTitle: taskTitle
            };

            console.log('taskUpdateRequest:', taskUpdateRequest);

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
                    isCompletedCell.textContent = updatedTask.isCompleted ? 'Complete' : 'Incomplete'; // Update the cell content
                })
                .catch(error => {
                    console.error(error);
                });

            break; // Exit the loop after updating the task
        }
    }
}



const addTaskButton = document.getElementById('add-task-button');
const taskInput = document.getElementById('task-input');

addTaskButton.addEventListener('click', () => {
    const taskTitle = taskInput.value.trim();
    if (taskTitle) {
        addTask(taskTitle);
        taskInput.value = '';
    }
});


function addTask(taskTitle) {
    const taskCreateRequest = {
        childUsername,
        taskTitle,
        isCompleted: false,
        parentUsername
    };

    fetch('/tasks', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(taskCreateRequest)
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('An error occurred while adding the task.');
            }
        })
        .then(newTask => {
            console.log('Added Task:', newTask);
            fetchTasks(); // Fetch updated tasks after adding a new task
        })
        .catch(error => {
            console.error(error);
        });
}

// Function to show or hide elements based on parentUsername presence
function showHideElements() {
    // Get the childUsername and parentUsername parameters from the URL
    const urlParams = new URLSearchParams(window.location.search);
    const childUsername = urlParams.get('childUsername');
    const parentUsername = urlParams.get('parentUsername');

    // Hide the add task button and task input if there is no parentUsername
    const addTaskButton = document.getElementById('add-task-button');
    const taskInput = document.getElementById('task-input');

    if (!parentUsername) {
        addTaskButton.style.display = 'none';
        taskInput.style.display = 'none';

    }

    // Show or hide the delete buttons based on the presence of parentUsername
    const deleteButtons = document.getElementsByClassName('delete-button');
    for (const deleteButton of deleteButtons) {
        if (!parentUsername) {
            deleteButton.style.display = 'none';
        } else {
            deleteButton.style.display = 'block';
        }
    }

    // Show or hide the task ID, parentUsername, childUsername columns based on user type
    const taskTable = document.getElementById('task-table');
    const taskIdColumn = taskTable.getElementsByClassName('task-id-column');
    const parentUsernameColumn = taskTable.getElementsByClassName('parent-username-column');
    const childUsernameColumn = taskTable.getElementsByClassName('child-username-column');

    if (childUsername) {
        for (const column of taskIdColumn) {
            column.style.display = 'none';
        }
        for (const column of parentUsernameColumn) {
            column.style.display = 'none';
        }
        for (const column of childUsernameColumn) {
            column.style.display = 'none';
        }
    } else {
        for (const column of taskIdColumn) {
            column.style.display = 'table-cell';
        }
        for (const column of parentUsernameColumn) {
            column.style.display = 'table-cell';
        }
        for (const column of childUsernameColumn) {
            column.style.display = 'table-cell';
        }
    }
}



// Call the showHideElements function when the DOM content is loaded
document.addEventListener('DOMContentLoaded', showHideElements);


fetchTasks();
