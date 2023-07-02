document.addEventListener("DOMContentLoaded", function() {
    // Code to be executed when the DOM is fully loaded

    document.getElementById("addBtn").addEventListener("click", addTaskElement);
    document.getElementById("myInput").addEventListener("keydown", function(event) {
        if (event.key === "Enter") {
            addTaskElement();
        }
    });

    function addTaskElement() {
        var inputValue = document.getElementById("myInput").value.trim();
        if (inputValue === '') {
            return; // Exit the function if the input is empty
        }

        // Create task elements
        var table = document.getElementById("myTable");
        if (!table) {
            table = document.createElement("table");
            table.id = "myTable";
            table.className = "task-table";
            var headerRow = document.createElement("tr");
            var idHeader = document.createElement("th");
            idHeader.textContent = "Task ID";
            headerRow.appendChild(idHeader);
            var titleHeader = document.createElement("th");
            titleHeader.textContent = "Task Title";
            headerRow.appendChild(titleHeader);
            var buttonsHeader = document.createElement("th");
            buttonsHeader.textContent = "Actions";
            headerRow.appendChild(buttonsHeader);
            table.appendChild(headerRow);
            document.getElementById("myDIV").appendChild(table);
        }

        var newRow = document.createElement("tr");
        newRow.className = "task-row";

        var taskIdCell = document.createElement("td");
        taskIdCell.className = "task-id";
        taskIdCell.textContent = "Task ID: "; // You can replace this with the actual task ID from the backend

        var taskTitleCell = document.createElement("td");
        taskTitleCell.className = "task-title";
        taskTitleCell.textContent = inputValue;

        var buttonsCell = document.createElement("td");
        buttonsCell.className = "task-buttons";

        var completeButton = document.createElement("span");
        completeButton.textContent = "✓";
        completeButton.className = "task-button complete";
        completeButton.addEventListener("click", function () {
            newRow.remove();
            showGoodJobPopup();
        });

        var deleteButton = document.createElement("span");
        deleteButton.textContent = "🗑";
        deleteButton.className = "task-button delete";
        deleteButton.addEventListener("click", function () {
            newRow.remove();
            playThrowawaySound();
        });

        buttonsCell.appendChild(completeButton);
        buttonsCell.appendChild(deleteButton);

        newRow.appendChild(taskIdCell);
        newRow.appendChild(taskTitleCell);
        newRow.appendChild(buttonsCell);

        table.appendChild(newRow);

        document.getElementById("myInput").value = "";
        playTaskAddedSound();
    }

    function showGoodJobPopup() {
        var popup = document.getElementById("goodJobPopup");
        popup.style.display = "block";

        // Play the Tada sound
        var audio = new Audio("/audio/Tada-sound.mp3");
        audio.play();

        setTimeout(function () {
            popup.style.display = "none";
        }, 3000);
    }

    function playThrowawaySound() {
        var audio = new Audio("/audio/throwawaytask.mp3");
        audio.play();
    }

    function playTaskAddedSound(){

    var audio = new Audio("/audio/taskadded.mp3");
    audio.play;
}

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
});
