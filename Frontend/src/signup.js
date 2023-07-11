document.addEventListener('DOMContentLoaded', function() {
    // Function to select the user type

    // Function to handle signup


    function handleSignup() {
        const parentUsername = document.getElementById('signup-parent-username').value;
        const childUsername = document.getElementById('signup-child-username').value;

        // Check if the signup page is loaded
        const isSignupPage = window.location.pathname.includes('signup.html');

        if (isSignupPage) {
            // Perform sign-up confirm logic and API post call here
            console.log('Create Account button clicked');
            console.log('Parent Username:', parentUsername);
            console.log('Child Username:', childUsername);

            // Make API post call to /parents/ endpoint
            // Replace the API endpoint with your actual endpoint
            fetch('/parents/', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    parentUsername: parentUsername,
                    childUsername: childUsername,
                }),
            })
                .then(response => {
                    if (response.ok) {
                        console.log('API call successful');
                        // Perform any desired actions upon successful signup
                    } else {
                        console.log('API call failed');
                        // Handle API error
                    }
                })
                .catch(error => {
                    console.log('API call failed with error:', error);
                    // Handle API error
                });
        } else {
            // Redirect to the signup page with parent and child usernames as query parameters
            window.location.href = `signup.html?parentUsername=${parentUsername}&childUsername=${childUsername}`;
        }
    }

    // Function to handle parent login
    function checkParentUsername(event) {
        event.preventDefault(); // Prevent default form submission
        console.log('Parent Login button clicked');
        const signupFields = document.getElementById('signup-fields');
        signupFields.style.display = 'block';
    }

    // Function to handle child login
    function checkChildUsername(event) {
        event.preventDefault(); // Prevent default form submission
        console.log('Child Login button clicked');
    }

    // Get the parent login form and child login form elements
    const parentLoginForm = document.getElementById('parent-login-form');
    const childLoginForm = document.getElementById('child-login-form');

    // Add event listeners to the form submit events
    parentLoginForm.addEventListener('submit', checkParentUsername);
    childLoginForm.addEventListener('submit', checkChildUsername);

    // Get the signup confirm button element
    const signupConfirmButton = document.getElementById('signup-confirm-button');

    // Add event listener to the signup confirm button
    signupConfirmButton.addEventListener('click', handleSignup);
});
