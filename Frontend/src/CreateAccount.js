// CreateAccount.js

document.getElementById('create-account-submit-btn').addEventListener('click', () => {
    const parentUsername = document.getElementById('create-username-input').value;
    const childUsernames = document.getElementById('child-username-input').value.split(',');

    // Create parent
    const createParent = async () => {
        try {
            const parentResponse = await fetch('http://localhost:5001/parents', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ parentUsername }),
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

    // Create child
    const createChild = async (parentId, childUsername) => {
        try {
            const childResponse = await fetch(`http://localhost:5001/parents/${parentId}/children`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ childUsername }),
            });

            if (!childResponse.ok) {
                throw new Error('Failed to create child');
            }
        } catch (error) {
            throw new Error(`Error creating child: ${error.message}`);
        }
    };

    // Account creation
    createParent()
        .then(parentId => {
            const childCreationPromises = childUsernames.map(childUsername => createChild(parentId, childUsername.trim()));
            return Promise.all(childCreationPromises);
        })
        .then(() => {
            console.log('Account created successfully!');
        })
        .catch(error => {
            console.error('Error creating account:', error);
        });
});
