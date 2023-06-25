import { addTodoItemPage } from './addTodoItemPage.js';
import { deleteTodoItemPage } from './deleteTodoItemPage.js';
import markTaskAsCompletePage from "./markTaskAsCompletePage";

// Code for initializing and running your application
function initializeApp() {
    // Initialization logic goes here

    // Example usage of the addTodoItemPage module
    addTodoItemPage.init();

    // Example usage of the deleteTodoItemPage module
    deleteTodoItemPage.init();

    // Example usage of the markTaskAsCompletePage module
    markTaskAsCompletePage.init();

    // Other initialization logic goes here
}

// Run the application
initializeApp();
