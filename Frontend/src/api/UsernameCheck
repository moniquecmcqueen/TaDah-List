const checkUsername = () => {
    const username = document.getElementById('usernameInput').value;

    // Make a GET request to the backend API to check the parent and child usernames
    fetch(`/api/username/${username}`)
        .then(response => response.json())
        .then(data => {
            // Data contains the response from the backend API
            if (data.parent === true) {
                // Parent username exists
                console.log(`Parent username "${username}" exists`);
                // Add your logic here for when the parent username exists
            } else if (data.child === true) {
                // Child username exists
                console.log(`Child username "${username}" exists`);
                // Add your logic here for when the child username exists
            } else {
                // Both parent and child usernames don't exist
                console.log(`Both parent and child usernames "${username}" don't exist`);
                // Add your logic here for when both parent and child usernames don't exist
            }
        })
        .catch(error => {
            // Handle any errors that occurred during the API request
            console.error('Error:', error);
        });
};

// Add event listener to the button
document.getElementById('checkUsernameButton').addEventListener('click', checkUsername);
