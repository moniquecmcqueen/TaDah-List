let PARENT_USERNAME_URL = '/parents/';
const childUsername =document.getElementById('child-username').value;
// Function to check if parent username exists
function handleChildUsernameSelection(parentUsername, childUsername) {
    // Redirect to tasklist.html with the parentUsername and childUsername in the URL
    window.location.href = `tasklist.html?parentUsername=${parentUsername}&childUsername=${childUsername}`;
}

// Function to check if parent username exists
function checkParentUsername(parentUsername) {
    // Make fetch request to check if parent username exists
    fetch(PARENT_USERNAME_URL + parentUsername)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else if (response.status === 404) {
                throw new Error('Username does not exist! Please try again.');
            } else {
                throw new Error('An error occurred while checking the username. Please try again later.');
            }
        })
        .then(data => {
            const childUsername = data.childUsername; // Access childUsername value from the response
            const responseContainer = document.getElementById('response-container');
            responseContainer.textContent = ''; // Clear the response container

            const childButtonContainer = document.getElementById('child-button-container');
            childButtonContainer.textContent = '';

            if (childUsername) {
                const button = document.createElement('button');
                button.textContent = childUsername;
                button.addEventListener('click', () => {
                    // Handle button click event
                    handleChildUsernameSelection(parentUsername, childUsername);
                });

                childButtonContainer.appendChild(button);
            }

            const parentButtonContainer = document.querySelector('#parent-login-button').parentElement;
            parentButtonContainer.appendChild(createAccountButton);

            // Perform child login logic or redirect to another page
            if (childUsername) {
                // Only redirect when the child button is clicked
                button.addEventListener('click', () => {
                    window.location.href = `tasklist.html?parentUsername=${parentUsername}&childUsername=${childUsername}`;
                });
            }
        })
        .catch(error => {
            const responseContainer = document.getElementById('response-container');
            console.log(responseContainer);
            // Clear the input field or perform any other desired action
        });
}

function checkChildUsername() {
    // Make fetch request to retrieve tasks based on child username
    const childUsername =document.getElementById('child-username').value;

    fetch(`/tasks/${(childUsername)}`)
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
            // Handle the tasks data as needed
            console.log(data);

            // Redirect to tasklist.html with the childUsername in the URL
            window.location.href = `childTaskList.html?childUsername=${(childUsername)}`;
        })
        .catch(error => {
            const responseContainer = document.getElementById('response-container');
            console.log(responseContainer);
            // Clear the input field or perform any other desired action
        });
}



// Function to handle parent login
function handleParentLogin() {
    const parentUsername = document.getElementById('parent-username').value;
    console.log('Parent Username:', parentUsername);
    checkParentUsername(parentUsername);
}

// Function to handle create account button
function handleCreateAccount(parentUsername) {
    // Redirect to create account page with the parentUsername in the URL
    window.location.href = `signup.html`;
}

function createAccount() {
    const parentUsername = document.getElementById('new-parent-account-input').value;
    const childUsername = document.getElementById('new-child-account-input').value;
    console.log('New Parent Account:', parentUsername);
    console.log('New Child Account:', childUsername);

    // Create an object with the data to be sent in the request body
    const data = {
        parentUsername: parentUsername,
        childUsername: childUsername
    };

    // Create a PUT request to the parents/ endpoint
    fetch(PARENT_USERNAME_URL, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                console.log('Account created successfully!');
                // Perform any further actions after creating the account
            } else {
                throw new Error('An error occurred while creating the account.');
            }
        })
        .catch(error => {
            console.error(error);
            // Handle the error
        });
}

document.getElementById('parent-login-button').addEventListener('click', handleParentLogin);
document.getElementById('child-login-button').addEventListener('click', checkChildUsername);
document.getElementById('signup-create-account-button').addEventListener('click', createAccount);
