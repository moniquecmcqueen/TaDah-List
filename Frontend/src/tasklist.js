function newElement() {
    var inputValue = document.getElementById("myInput").value;

    if (inputValue === "") {
        alert("Please enter a task!");
        return;
    }

    var li = document.createElement("li");
    li.className = "task-row";

    var taskInfo = document.createElement("div");
    taskInfo.className = "task-info";

    var taskId = document.createElement("span");
    taskId.className = "task-id";
    taskId.textContent = "Task ID: "; // You can replace this with the actual task ID from the backend
    taskInfo.appendChild(taskId);

    var taskTitle = document.createElement("span");
    taskTitle.className = "task-title";
    taskTitle.textContent = inputValue;
    taskInfo.appendChild(taskTitle);

    var buttonsDiv = document.createElement("div");
    buttonsDiv.className = "task-buttons";

    var completeButton = document.createElement("span");
    completeButton.textContent = "✓";
    completeButton.className = "task-button complete";
    completeButton.addEventListener("click", function () {
        li.remove();
        showGoodJobPopup();
    });

    var deleteButton = document.createElement("span");
    deleteButton.textContent = "🗑";
    deleteButton.className = "task-button delete";
    deleteButton.addEventListener("click", function () {
        li.remove();
    });

    buttonsDiv.appendChild(completeButton);
    buttonsDiv.appendChild(deleteButton);

    li.appendChild(taskInfo);
    li.appendChild(buttonsDiv);

    document.getElementById("myUL").appendChild(li);

    document.getElementById("myInput").value = "";
}


function showGoodJobPopup() {
    var popup = document.getElementById("goodJobPopup");
    popup.style.display = "block";
    setTimeout(function () {
        popup.style.display = "none";
    }, 3000);

    // Function to get a task by its ID
    function getTaskById(taskId) {
        return fetch(`/tasks/${taskId}`)
            .then(response => response.json())
            .catch(error => {
                console.error('Error fetching task by ID:', error);
                throw error;
            });
    }

// Function to get a list of tasks associated with a child ID
    function getTasksByChildId(childId) {
        return fetch(`/tasks/child/${childId}`)
            .then(response => response.json())
            .catch(error => {
                console.error('Error fetching tasks by child ID:', error);
                throw error;
            });
    }

// Function to create a new task
    function createTask(taskData) {
        return fetch('/tasks', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(taskData)
        })
            .then(response => response.json())
            .catch(error => {
                console.error('Error creating task:', error);
                throw error;
            });
    }

// Function to update an existing task
    function updateTask(taskId, taskData) {
        return fetch(`/tasks/${taskId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(taskData)
        })
            .then(response => response.json())
            .catch(error => {
                console.error('Error updating task:', error);
                throw error;
            });
    }

// Function to delete a task
    function deleteTask(taskId) {
        return fetch(`/tasks/${taskId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error deleting task');
                }
            })
            .catch(error => {
                console.error('Error deleting task:', error);
                throw error;
            });
    }

}
