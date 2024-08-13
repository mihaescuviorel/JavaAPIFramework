package api.test;

import api.endpoints.UserEndPointsCRUD;
import api.payload.User;
import api.utilities.DataProviders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DDTests {
    User userPayload;
    public Logger logger;

    @BeforeClass
    public void beforeClass() {
        userPayload = new User(); // create a new User object
        logger = LogManager.getLogger(this.getClass()); // create a logger instance
    }

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostUser(String id, String username, String firstName, String lastName, String email, String password, String phone) throws JsonProcessingException {

        logger.info("********** Creating a new user **********");

        // Set the values for the userPayload object
        userPayload.setId(Integer.parseInt(id));
        userPayload.setUsername(username);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        // Create an ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();

        // Convert the User object to a pretty-printed JSON string
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(userPayload);

        // Print the JSON string
        System.out.println(jsonString);

        // Create a user using JSON file content
        Response response = UserEndPointsCRUD.createUser(userPayload);
        response.prettyPrint();

        logger.info("********** User created successfully **********");

    }

    @Test(priority = 2, dataProvider = "UserNames", dataProviderClass = DataProviders.class)
    public void deleteUser(String username) {
        logger.info("********** Deleting a user **********");
        // Delete a user by username
        Response response = UserEndPointsCRUD.deleteUser(username);
        response.prettyPrint();
        logger.info("********** User deleted successfully **********");
    }

}
