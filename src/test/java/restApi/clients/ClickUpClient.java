package restApi.clients;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static restApi.constants.ProjectConstants.API_TOKEN;
import static restApi.constants.ProjectConstants.SPACE_ID;

public class ClickUpClient {

    private static RequestSpecification clickupSpec() {
        return RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", API_TOKEN);

    }

    public static Response createFolder(JSONObject obj){
        return RestAssured
                .given(clickupSpec())
                .body(obj)
                .when()
                .post("https://api.clickup.com/api/v2/space/"+ SPACE_ID + "/folder")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response deleteFolder(String folderId){
        return RestAssured
                .given(clickupSpec())
                .when()
                .delete("https://api.clickup.com/api/v2/folder/" + folderId)
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createList(String folderId, JSONObject obj){
        return RestAssured
                .given(clickupSpec())
                .body(obj)
                .when()
                .post("https://api.clickup.com/api/v2/folder/" + folderId + "/list")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response getList(String folderId){
        return RestAssured
                .given(clickupSpec())
                .when()
                .get("https://api.clickup.com/api/v2/folder/" + folderId + "/list")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response createTaskInFolder(String listId, JSONObject obj){
        return RestAssured
                .given(clickupSpec())
                .body(obj)
                .when()
                .post("https://api.clickup.com/api/v2/list/"+ listId +"/task")
                .then().log().all()
                .statusCode(200)
                .extract().response();
    }

    public static Response deleteTask(String taskId){
        return RestAssured
                .given(clickupSpec())
                .when()
                .delete("https://api.clickup.com/api/v2/task/" + taskId)
                .then().log().all()
                .statusCode(204)
                .extract().response();
    }


}
