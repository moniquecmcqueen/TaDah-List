import { checkUsernameExists } from "./UsernameCheck";

function checkUsername() {
    const inputElement = document.getElementById('username-input');
    const submitButton = document.getElementById('login-btn');

    submitButton.addEventListener('click', function () {
        const username = inputElement.value;

        // Call the checkUsernameExists function
        checkUsernameExists(username)
            .then(function (result) {
                if (result) {
                    if (result.type === 'parent') {
                        const parentData = result.data;
                        // Handle parent data
                        console.log('Parent data:', parentData);
                    } else if (result.type === 'child') {
                        const childData = result.data;
                        // Handle child data
                        console.log('Child data:', childData);
                    }
                } else {
                    console.log('Username does not exist');
                    // Show an error message or take appropriate action
                }
            })
            .catch(function (error) {
                console.error(error);
                // Handle the error appropriately
            });
    });

    }

// Call the checkUsername function when the login button is clicked
document.addEventListener('DOMContentLoaded', function () {
    const submitButton = document.getElementById('login-btn');
    submitButton.addEventListener('click', checkUsername);
});

// Get the error-related elements
const errorElement = document.getElementById('error');
const errorMessageElement = document.getElementById('errorMessage');

function showCreateUsernameOption(username) {
    errorMessageElement.textContent = `Username '${username}' not found. Do you want to create it?`;
    errorMessageElement.style.display = 'block';

    const createButton = document.createElement('button');
    createButton.setAttribute('id', 'createUsername');
    createButton.textContent = 'Create Username';
    errorMessageElement.appendChild(createButton);

    createButton.addEventListener('click', () => {
        createUsername(username);
    });
}

// Function to create the username
function createUsername(username) {
    console.log(`Username created: ${username}`);
    errorMessageElement.style.display = 'none';
    // Perform any necessary actions to create the username in the backend
    // Refresh the page or perform any other necessary updates
    location.reload();
}

// Login button click event listener
document.getElementById('login-btn').addEventListener('click', () => {
    checkUsername();
});

// Create New Account button click event listener
document.getElementById('create-account-btn').addEventListener('click', () => {
    document.getElementById('initial-view').style.display = 'none';
    document.getElementById('create-account-view').style.display = 'block';
});


// Home button click event listener
document.getElementById('home-btn').addEventListener('click', () => {
    document.getElementById('create-account-view').style.display = 'none';
    document.getElementById('initial-view').style.display = 'block';

    const additionalChildrenSection = document.getElementById('additional-children-section');
    additionalChildrenSection.innerHTML = '';
});
// Add additional child event listener
        // Define the childCount variable before the event listener
        var childCount = 1;

        document.getElementById('add-child-btn').addEventListener('click', function () {
            // Create a new child input field
            var newChildInputField = document.createElement('input');
            newChildInputField.type = 'text';
            newChildInputField.placeholder = "Child's Username";
            newChildInputField.name = 'child-username-' + childCount; // Unique name attribute
            newChildInputField.id = 'child-username-' + childCount; // Unique id attribute
            newChildInputField.classList.add('additional-child-input');

            // Get the container where you want to append the new child input field
            var container = document.getElementById('additional-children-section');

            // Append the new child input field to the container
            container.appendChild(newChildInputField);

            childCount++; // Increment the child count for the next input field

            // Update the event listener for the new child input field
            newChildInputField.addEventListener('change', function () {
                // Handle the change event for the new child input field
                var childUsername = newChildInputField.value;
                console.log('Child Username:', childUsername);
            });
        });



// Minus button click event listener
document.getElementById('remove-child-btn').addEventListener('click', () => {
    const additionalChildrenSection = document.getElementById('additional-children-section');
    const lastAdditionalChildContainer = additionalChildrenSection.lastElementChild;

    if (lastAdditionalChildContainer) {
        additionalChildrenSection.removeChild(lastAdditionalChildContainer);
    }
});

// Create Account button click event listener
document.getElementById('create-account-submit-btn').addEventListener('click', () => {
    const username = document.getElementById('create-username-input').value;
    const childUsername = document.getElementById('child-username-input').value;

    const additionalChildInputs = document.getElementsByClassName('additional-child-input');
    const additionalChildrenUsernames = Array.from(additionalChildInputs).map(input => input.value);

    console.log('Username:', username);
    console.log('Child Username:', childUsername);
    console.log('Additional Children Usernames:', additionalChildrenUsernames);

    // Perform the necessary actions with the obtained data
    // For example, send it to the backend for account creation

    // Show the account creation confirmation section
    document.getElementById('create-account-view').style.display = 'none';
    document.getElementById('account-confirmation-section').style.display = 'block';

    // Update the confirmation username
    document.getElementById('confirmation-username').textContent = username;

    // Create child buttons
    const childButtonsContainer = document.getElementById('child-buttons-container');
    childButtonsContainer.innerHTML = '';

    const createChildButton = (childUsername) => {
        const button = document.createElement('button');
        button.textContent = childUsername;
        button.addEventListener('click', () => {
            console.log('Child button clicked:', childUsername);
            // Redirect to tasklist.html with the selected child's username
            window.location.href = `tasklist.html?child=${childUsername}`;
        });
        return button;
    };

    const childUsernames = [childUsername, ...additionalChildrenUsernames];
    childUsernames.forEach(childUsername => {
        const button = createChildButton(childUsername);
        childButtonsContainer.appendChild(button);
    });

    const createTask = (taskName, completed) => {
        const taskItem = document.createElement('div');
        taskItem.classList.add('task-item');

        const taskNameElement = document.createElement('span');
        taskNameElement.textContent = taskName;
        taskItem.appendChild(taskNameElement);

        if (completed) {
            taskItem.classList.add('completed');
        }

        taskItem.addEventListener('click', () => {
            taskItem.classList.toggle('completed');
            console.log('Task clicked:', taskName);
            // Perform the necessary actions when a task is clicked
        });

        return taskItem;
    };

    const tasks = [
        { name: 'Task 1', completed: false },
        { name: 'Task 2', completed: true },
        { name: 'Task 3', completed: false }
    ];

    tasks.forEach(task => {
        const taskItem = createTask(task.name, task.completed);
        tasklistContainer.appendChild(taskItem);
    });
});
