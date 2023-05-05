package restApi.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import restApi.domain.List;
import restApi.domain.Tasks;
import restApi.helpers.TestCaseContext;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import restApi.domain.Folder;

import static restApi.clients.ClickUpClient.*;


public class ClickUpSteps {
    String randomTaskName = RandomStringUtils.randomAlphabetic(10);
    @Given("I create {string} folder and verify name")
    public void iCreateClickUpFolderAndVerifyName(String folderName) {
        JSONObject obj = new JSONObject();
        obj.put("name", folderName);
        Response response = createFolder(obj);
        Folder folder = response.as(Folder.class);
        TestCaseContext.setFolder(folder);

        Assertions.assertThat(folder.getFolderName())
                .as("We assert that folder name is correct!")
                .isEqualTo(folderName);
    }

    @When("I create new list {string} in Folder")
    public void iCreateNewListMyListInFolder(String listName) {
        JSONObject obj = new JSONObject();
        obj.put("name", listName);
        Response response = createList(TestCaseContext.getFolder().getFolderId(), obj);

        List list = response.as(List.class);
        TestCaseContext.setList(list);
    }

    @Then("I verify list name {string} is correct")
    public void iVerifyListNameIsCorrectAndItIsAddedToCorrectFolder(String listName) {
        Assertions.assertThat(TestCaseContext.getList().getListName())
                .as("We assert that list name is correct!")
                .isEqualTo(listName);
    }

    @Then("I create unique task in list")
    public void iCreateUniqueTaskInList() {
        JSONObject obj = new JSONObject();
        obj.put("name", randomTaskName);
        Response response = createTaskInFolder(TestCaseContext.getList().getListId(), obj);

        Tasks tasks = response.as(Tasks.class);
        TestCaseContext.setTasks(tasks);
    }

    @And("I check that the task name is correct")
    public void iCheckThatTheTaskNameIsCorrect() {
        Assertions.assertThat(TestCaseContext.getTasks().getTaskName())
                .as("We assert that task name is correct!")
                .isEqualTo(randomTaskName);
    }

    @When("I remove task from the list")
    public void iRemoveTaskFromTheList() {
        Response response = deleteTask(TestCaseContext.getTasks().getTaskId());
    }

    @Then("I check there is no tasks left in list")
    public void iCheckThereIsNoTaskInList() {
        Response response = getList(TestCaseContext.getFolder().getFolderId());
    }
}
