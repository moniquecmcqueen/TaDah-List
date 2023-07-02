export async function checkUsernameExists(username) {
    const parentEndpoint = `parents/parentUsername/${username}`;
    const childEndpoint = `children/childUsername/${username}`;

    try {
        // Check parent username
        const parentResponse = await fetch(parentEndpoint);
        if (parentResponse.ok) {
            const parentData = await parentResponse.json();
            // Parent username exists
            return { type: 'parent', data: parentData };
        }

        // Check child username
        const childResponse = await fetch(childEndpoint);
        if (childResponse.ok) {
            const childData = await childResponse.json();
            // Child username exists
            return { type: 'child', data: childData };
        }

        // Username doesn't exist
        return false;
    } catch (error) {
        // Handle the error appropriately
        throw new Error('Error checking username');
    }
}
