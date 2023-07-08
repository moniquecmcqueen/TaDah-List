document.getElementById('create-account-submit-btn').addEventListener('click', async () => {
    const parentUsername = document.getElementById('create-username-input').value;
    const childUsernames = document.getElementById('child-username-input').value.split(',');

    // Create parent
    const createParent = async () => {
        try {
            const requestBody = {
                parentUsername: parentUsername,
                children: childUsernames,
            };

            const parentResponse = await fetch('http://localhost:5001/parents', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestBody),
            });

            if (!parentResponse.ok) {
                throw new Error('Failed to create parent');
            }

            const parentData = await parentResponse.json();
            return parentData.parentId;
        } catch (error) {
            throw new Error(`Error creating parent: ${error.message}`);
        }
    };

    try {
        const parentId = await createParent();
        console.log('Parent created with ID:', parentId);
        // Perform any other necessary actions after successful parent creation
    } catch (error) {
        console.error('Error creating parent:', error);
        // Handle the error appropriately
    }
});
