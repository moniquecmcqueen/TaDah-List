import checkUsernameExists from '../api/UsernameCheck';

// Get the form element
const form = document.querySelector('form');

// Get the error message element
const errorMessage = document.getElementById('error');

// Get the username input element
const usernameInput = document.getElementById('usernameInput');

// Function to check the username
function checkUsername() {
    const username = usernameInput.value;
    if (username.trim() === '') {
        errorMessage.textContent = 'Please enter a username.';
        errorMessage.style.display = 'block';
    } else {
        checkUsernameExists(username);
    }
}

// Add event listener to the username input
usernameInput.addEventListener('input', checkUsername);

// Function to show the option to create a username
function showCreateUsernameOption(username) {
    errorMessage.textContent = `Username '${username}' not found. Do you want to create it?`;
    errorMessage.style.display = 'block';

    const createButton = document.createElement('button');
    createButton.setAttribute('id', 'createUsername');
    createButton.textContent = 'Create Username';
    errorMessage.appendChild(createButton);

    createButton.addEventListener('click', () => {
        createUsername(username);
    });
}

// Function to create the username
function createUsername(username) {
    console.log(`Username created: ${username}`);
    errorMessage.style.display = 'none';
    // Perform any necessary actions to create the username in the backend
    // Refresh the page or perform any other necessary updates
    location.reload();
}

// Handle form submission
form.addEventListener('submit', (event) => {
    event.preventDefault();
    checkUsername();
});
