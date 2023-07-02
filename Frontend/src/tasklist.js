function newElement() {
    var inputValue = document.getElementById("myInput").value;

    if (inputValue === "") {
        alert("Please enter a task!");
        return;
    }

    var li = document.createElement("li");
    li.className = "task-row";

    var taskTitle = document.createElement("span");
    taskTitle.className = "task-title";
    taskTitle.textContent = inputValue;

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

    li.appendChild(taskTitle);
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
}
