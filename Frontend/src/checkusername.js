let PARENT_PARENT_USERNAME_URL = '/parents/parent/';
let CHILD_USERNAME_URL = '/parents/child/';
let PARENT_USERNAME_URL = '/parents';


// Function to check if parent username exists
function handleChildUsernameSelection(parentUsername, childUsername) {
    // Redirect to tasklist.html with the parentUsername and childUsername in the URL
    window.location.href = `tasklist.html?parentUsername=${parentUsername}&childUsername=${childUsername}`;
}

// Function to check if parent username exists
function checkParentUsername(parentUsername) {
    // Make fetch request to check if parent username exists
    fetch(PARENT_PARENT_USERNAME_URL + parentUsername)
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

            const childSelectionText = document.getElementById('child-selection-text');
            if (childUsername) {
                childSelectionText.style.display = 'block'; // Show the child selection text
                const button = document.createElement('button');
                button.textContent = childUsername;
                button.addEventListener('click', () => {
                    // Handle button click event
                    handleChildUsernameSelection(parentUsername, childUsername);
                });

                childButtonContainer.appendChild(button);
            } else {
                childSelectionText.style.display = 'none'; // Hide the child selection text
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

function checkChildUsername(childUsername) {
    // Make fetch request to check if child username exists
    fetch(CHILD_USERNAME_URL + childUsername)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else if (response.status === 404) {
                throw new Error('Child username does not exist! Please try again.');
            } else {
                throw new Error('An error occurred while checking the child username. Please try again later.');
            }
        })
        .then(data => {
            const childUsername = data.childUsername; // Access childUsername value from the response

            // Perform child login logic or redirect to another page
            if (childUsername) {
                const tasklistUrl2 = `tasklist.html?childUsername=${childUsername}`;
                window.location.href = tasklistUrl2;
            }
        })
        .catch(error => {
            const responseContainer = document.getElementById('response-container');
            responseContainer.textContent = error.message;
            // Clear the input field or perform any other desired action
        });
}

// Function to handle child login
function handleChildLogin() {
    const childUsernameInput = document.getElementById('child-username');
    const childUsername = childUsernameInput.value;
    console.log('Child Username:', childUsername);
    checkChildUsername(childUsername);
}



// Function to handle parent login
function handleParentLogin() {
    const parentUsername = document.getElementById('parent-username').value;
    console.log('Parent Username:', parentUsername);
    checkParentUsername(parentUsername);
}

// Function to handle create account button
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

    // Create a POST request to the parents/ endpoint
    fetch(PARENT_USERNAME_URL, {
        method: 'POST',
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
document.getElementById('child-login-button').addEventListener('click', () => {
    const childUsername = document.getElementById('child-username').value;
    console.log('Child Username:', childUsername);
    checkChildUsername(childUsername);
});

document.getElementById('signup-create-account-button').addEventListener('click', createAccount);
