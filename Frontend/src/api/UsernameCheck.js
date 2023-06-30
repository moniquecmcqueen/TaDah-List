// src/api/UsernameCheck.js
const checkUsername = () => {
    const username = document.getElementById('username-input').value;

    // Make a GET request to the backend API to check the username
    fetch(`/api/username/${username}`)
        .then(response => response.json())
        .then(data => {
            // Data contains the response from the backend API
            if (data.exists) {
                // Username exists
                console.log(`Username "${username}" exists`);
                // Add your logic here for when the username exists
            } else {
                // Username doesn't exist
                console.log(`Username "${username}" doesn't exist`);
                // Add your logic here for when the username doesn't exist
            }
        })
        .catch(error => {
            // Handle any errors that occurred during the API request
            console.error('Error:', error);
        });
};

export default checkUsername;
