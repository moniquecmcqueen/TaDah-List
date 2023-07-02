// Function to create a new parent
const createParent = async (parentUsername) => {
    const response = await fetch('/parents', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            parentUsername: parentUsername,
        }),
    });

    if (response.ok) {
        const parent = await response.json();
        return parent;
    } else {
        const error = await response.text();
        throw new Error(error);
    }
};

// Function to create a new child
const createChild = async (parentId, childUsername) => {
    const response = await fetch(`/parents/${parentId}/children`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            childUsername: childUsername,
        }),
    });

    if (response.ok) {
        const child = await response.json();
        return child;
    } else {
        const error = await response.text();
        throw new Error(error);
    }
};

// Function to create a new task for a child
const createTask = async (parentId, childId, taskTitle) => {
    const response = await fetch(`/parents/${parentId}/children/${childId}/tasks`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            taskTitle: taskTitle,
            isCompleted: false,
        }),
    });

    if (response.ok) {
        const task = await response.json();
        return task;
    } else {
        const error = await response.text();
        throw new Error(error);
    }
};

// Usage example:
const handleCreateAccountSubmit = async () => {
    const parentUsername = document.getElementById('create-username-input').value;
    const childUsername = document.getElementById('child-username-input').value;

    try {
        // Create the parent
        const parent = await createParent(parentUsername);

        // Create the child
        const child = await createChild(parent.parentId, childUsername);

        // Create a new task for the child
        const taskTitle = 'Task 1'; // Get the task title from the input field
        const task = await createTask(parent.parentId, child.childId, taskTitle);

        // Handle the successful creation of parent, child, and task
        console.log('Parent created:', parent);
        console.log('Child created:', child);
        console.log('Task created:', task);

        // Perform any additional actions or UI updates
    } catch (error) {
        // Handle the error
        console.error('Error creating account:', error);
    }
};

// Attach event listener to the create account submit button
const createAccountSubmitBtn = document.getElementById('create-account-submit-btn');
createAccountSubmitBtn.addEventListener('click', handleCreateAccountSubmit);
