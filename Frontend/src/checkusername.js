
let PARENT_PARENT_USERNAME_URL = '/parents/parent/';
let CHILD_USERNAME_URL = '/parents/child/';
let PARENT_USERNAME_URL = '/parents';

function addLogMessage(message, logContainerId) {
    const logContainer = document.getElementById(logContainerId);
    if (logContainer) {
        const logMessage = document.createElement('p');
        logMessage.textContent = message;
        logContainer.appendChild(logMessage);
    } else {
        console.error('Log container not found:', logContainerId);
    }
}



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

            const childButtonContainer = document.getElementById('child-button-container');
            childButtonContainer.textContent = '';

            const childSelectionText = document.getElementById('child-selection-text');
            if (childUsername) {
                childSelectionText.style.display = 'block'; // Show the child selection text
                const childButton = document.createElement('button');
                childButton.textContent = childUsername;
                childButton.addEventListener('click', () => {
                    // Handle button click event
                    handleChildUsernameSelection(parentUsername, childUsername);
                });

                childButtonContainer.appendChild(childButton);
            } else {
                childSelectionText.style.display = 'none'; // Hide the child selection text
            }

            const parentLoginForm = document.getElementById('parent-login-form');
            parentLoginForm.addEventListener('submit', (event) => {
                event.preventDefault(); // Prevent form submission
                window.location.href = `tasklist.html?parentUsername=${parentUsername}&childUsername=${childUsername}`;
            });
        })
        .catch(error => {
            console.log(error);
            // Clear the input field or perform any other desired action
            addLogMessage(error, 'log-container-parent');
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
            addLogMessage(error, 'log-container-child');

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
            addLogMessage(error, 'log-container-signup');

            // Handle the error
        });
}
function selectUserType(userType) {
    const parentTab = document.getElementById('parent-tab');
    const childTab = document.getElementById('child-tab');
    const signupTab = document.getElementById('signup-tab');

    // Hide all tabs
    parentTab.classList.remove('show', 'active');
    childTab.classList.remove('show', 'active');
    signupTab.classList.remove('show', 'active');

    // Show the selected tab
    if (userType === 'parent') {
        parentTab.classList.add('show', 'active');
    } else if (userType === 'child') {
        childTab.classList.add('show', 'active');
    } else if (userType === 'signup') {
        signupTab.classList.add('show', 'active');
    }
}


document.getElementById('parent-login-button').addEventListener('click', handleParentLogin);
document.getElementById('child-login-button').addEventListener('click', () => {
    const childUsername = document.getElementById('child-username').value;
    console.log('Child Username:', childUsername);
    checkChildUsername(childUsername);
});

document.getElementById('signup-create-account-button').addEventListener('click', createAccount);
