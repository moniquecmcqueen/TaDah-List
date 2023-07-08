const CHECK_PARENT_USERNAME_URL = '/parents/'; // Update with the correct endpoint URL for parent username check

document.getElementById('login-form').addEventListener('submit', checkUsername);

function checkUsername(event) {
    event.preventDefault(); // Prevent form submission

    const enteredUsername = document.getElementById('username').value;

    // Remove event listener to prevent multiple submissions
    document.getElementById('login-form').removeEventListener('submit', checkUsername);

    // Make fetch request to check if parent username exists
    fetch(CHECK_PARENT_USERNAME_URL + enteredUsername)
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
            const parentUsername = enteredUsername;
            const childUsername = data.childUsername;

            const responseContainer = document.getElementById('response-container');
            responseContainer.textContent = `Username exists in parent records! You can proceed with the login as a parent. Child Username: ${childUsername}`;

            // Perform parent login logic or redirect to another page
        })
        .catch(error => {
            const responseContainer = document.getElementById('response-container');
            responseContainer.textContent = error.message;
            // Clear the input field or perform any other desired action
        });
}
