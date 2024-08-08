package api.test;

import api.endpoints.UserEndPointsCRUD;
import api.payload.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests {
    Faker faker;
    User payload;

    @BeforeClass
    public void beforeClass() {
        faker = new Faker();
        payload = new User(faker.idNumber().hashCode(), faker.name().username(), faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.internet().password(), faker.phoneNumber().cellPhone());
    }

    // test post user
    @Test(priority = 1)
    public void testPostUser() throws JsonProcessingException {

        // Create an ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Convert the User object to a pretty-printed JSON string
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(payload);

        // Print the JSON string
        System.out.println(jsonString);

        // Create a user using JSON file content
        Response response = UserEndPointsCRUD.createUser(payload);
        response.prettyPrint();

    }

    // test get user by username
    @Test(priority = 2)
    public void testGetUserByUsername() {
        // Get a user by username
        Response response = UserEndPointsCRUD.getUserByUsername(payload.getUsername());
        response.prettyPrint();
    }

    // test update usename with Iustin name
    @Test(priority = 3)
    public void testUpdateUser() {
        // Update a user by username
        payload.setFirstName("Iustin");
        Response response = UserEndPointsCRUD.updateUser(payload.getUsername(), payload);
        response.prettyPrint();
    }

    // test delete user by username
    @Test(priority = 4)
    public void testDeleteUser() {
        // Delete a user by username
        Response response = UserEndPointsCRUD.deleteUser(payload.getUsername());
        response.prettyPrint();
    }


}
