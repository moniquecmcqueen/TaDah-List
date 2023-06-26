class TaskController {
    constructor() {
        // Initialize the controller
    }

    async createTask(task) {
        const response = await fetch('/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(task)
        });

        if (response.ok) {
            return await response.json();
        } else {
            throw new Error('Failed to create task');
        }
    }

    async getAllTasks() {
        const response = await fetch('/tasks');

        if (response.ok) {
            return await response.json();
        } else {
            throw new Error('Failed to get tasks');
        }
    }

    async getTaskById(taskId) {
        const response = await fetch(`/tasks/${taskId}`);

        if (response.ok) {
            return await response.json();
        } else {
            throw new Error('Failed to get task by ID');
        }
    }

    async updateTask(taskId, updatedTask) {
        const response = await fetch(`/tasks/${taskId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedTask)
        });

        if (response.ok) {
            return await response.json();
        } else {
            throw new Error('Failed to update task');
        }
    }

    async deleteTask(taskId) {
        const response = await fetch(`/tasks/${taskId}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            return;
        } else {
            throw new Error('Failed to delete task');
        }
    }
}

export default TaskController;
