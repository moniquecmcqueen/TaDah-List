import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import DeleteClient from "../api/deleteClient";


/**
 * Logic needed for the view playlist page of the website.
 */
class DeleteToDoItemPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onDelete'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('delete-byId-form').addEventListener('submit', this.onDelete);
        this.client = new DeleteClient();


    }

    // Render Methods --------------------------------------------------------------------------------------------------

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onDelete(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let taskId = document.getElementById("taskId-field").value;


        let result = await this.client.deleteTask(taskId, this.errorHandler);

        this.showMessage('Deleted Successfully')

        }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const deleteToDoItemPage = new DeleteToDoItemPage();
    deleteToDoItemPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
