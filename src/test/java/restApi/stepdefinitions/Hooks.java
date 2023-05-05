package restApi.stepdefinitions;

import restApi.helpers.TestCaseContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import static restApi.clients.ClickUpClient.deleteFolder;

public class Hooks {
    @Before
    public void before() {
        TestCaseContext.init();
    }

    @After
    public void after() {
        System.out.println("--- Clean up started ---");
        deleteFolder(TestCaseContext.getFolder().getFolderId());
        System.out.println("--- Clean up finished ---");
    }
}
