package api.endpoints;

import api.payload.User;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

// Created for perform CRUD operations on User module
public class UserEndPointsCRUD {

    // method to get the URL
    static ResourceBundle getURL() {
        ResourceBundle routes = ResourceBundle.getBundle("routes"); // Load the properties file
        return routes;
    }

    public static Response createUser(User payload) {

        String postUrl = getURL().getString("postCreateUser_url");

        // Create a user using JSON file content
        Response response = given()
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .post(postUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response;
    }

    public static Response getUserByUsername(String username) {
        String getUrl = getURL().getString("getUserByUsername_url");

        // Get a user by username
        Response response = given()
                .pathParam("username", username)
                .when()
                .get(getUrl)
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();
        return response;
    }

    public static Response updateUser(String username, User payload) {
        String putUrl = getURL().getString("putUpdateUser_url");
        // Update a user by username
        Response response = given()
                .pathParam("username", username)
                .header("Content-Type", "application/json")
                .body(payload)
                .when()
                .put(putUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();
        return response;
    }

    public static Response deleteUser(String username) {
        String deleteUrl = getURL().getString("deleteUser_url");

        // Delete a user by username
        Response response = given()
                .pathParam("username", username)
                .when()
                .delete(deleteUrl)
                .then()
                .statusCode(200)
                .extract()
                .response();

        return response;
    }

}
