// main.js
import checkUsernameExists from '../api/UsernameCheck';

document.addEventListener('DOMContentLoaded', () => {
    // Get the form element
    const form = document.querySelector('form');

    // Get the error message element
    const errorMessage = document.getElementById('error');

    // Get the username input element
    const usernameInput = document.getElementById('username-input');

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
    if (usernameInput) {
        usernameInput.addEventListener('input', checkUsername);
    }

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
    if (form) {
        form.addEventListener('submit', (event) => {
            event.preventDefault();
            checkUsername();
        });
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

    // Plus button click event listener
    document.getElementById('add-child-btn').addEventListener('click', () => {
        const additionalChildrenSection = document.getElementById('additional-children-section');
        additionalChildrenSection.style.display = 'block';

        const inputContainer = document.createElement('div');
        inputContainer.classList.add('input-container');

        const additionalChildInput = document.createElement('input');
        additionalChildInput.type = 'text';
        additionalChildInput.classList.add('additional-child-input');
        additionalChildInput.placeholder = "Additional Child Username";
        inputContainer.appendChild(additionalChildInput);

        additionalChildrenSection.appendChild(inputContainer);
    });

    // Home button click event listener
    document.getElementById('home-btn').addEventListener('click', () => {
        document.getElementById('create-account-view').style.display = 'none';
        document.getElementById('initial-view').style.display = 'block';

        const additionalChildrenSection = document.getElementById('additional-children-section');
        additionalChildrenSection.innerHTML = '';
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
        document.getElementById('create-account-view').style.display = 'none';
        document.getElementById('account-confirmation-section').style.display = 'block';

        const confirmationUsername = document.getElementById('confirmation-username');
        confirmationUsername.textContent = username;

        const childUsername = document.getElementById('child-username-input').value;
        const childUsernameContainer = document.createElement('div');
        childUsernameContainer.classList.add('input-container');

        const childUsernameButton = document.createElement('button');
        childUsernameButton.classList.add('child-button');
        childUsernameButton.textContent = childUsername;
        childUsernameContainer.appendChild(childUsernameButton);

        const accountConfirmationSection = document.getElementById('account-confirmation-section');
        accountConfirmationSection.appendChild(childUsernameContainer);

        const additionalChildrenSection = document.getElementById('additional-children-section');
        const additionalChildInputs = additionalChildrenSection.getElementsByClassName('additional-child-input');
        const childButtonsContainer = document.getElementById('child-buttons-container');
        childButtonsContainer.innerHTML = '';

        for (let i = 0; i < additionalChildInputs.length; i++) {
            const childName = additionalChildInputs[i].value;
            const childButton = document.createElement('button');
            childButton.classList.add('child-button');
            childButton.textContent = childName;
            childButtonsContainer.appendChild(childButton);
        }

        // Child button click event listener
        const childButtons = document.getElementsByClassName('child-button');
        for (let j = 0; j < childButtons.length; j++) {
            childButtons[j].addEventListener('click', () => {
                const childUsername = childButtons[j].textContent;
                const redirectUrl = `tasklist.html?child=${encodeURIComponent(childUsername)}`;
                window.location.href = redirectUrl;
            });
        }
    });
});
