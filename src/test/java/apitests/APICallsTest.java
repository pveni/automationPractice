package apitests;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.config.EncoderConfig.encoderConfig;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

//This test covers different api calls with CRUD options which will send the request and expect the response

public class APICallsTest {


    final String baseURI = "https://reqres.in";

    @BeforeClass
    public void setUp() {
        RestAssured.config = new RestAssuredConfig().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
        RestAssured.baseURI = baseURI;
    }


    @Test
    public void testForGetListOfUsers() throws Exception {
        given().
                when().
                get(baseURI + "/api/users?page=2").
                then().
                statusCode(200).
                body("page", equalTo(2)).
                body("per_page", equalTo(3)).
                body("total", equalTo(12)).
                body("total_pages", equalTo(4)).
                body("data.id[0]", equalTo(4)).
                body("data.first_name[0]", equalTo("Eve")).
                body("data.last_name[0]", equalTo("Holt")).
                body("data.avatar[0]", equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/marcoramires/128.jpg")).
                body("data.id[1]", equalTo(5)).
                body("data.first_name[1]", equalTo("Charles")).
                body("data.last_name[1]", equalTo("Morris")).
                body("data.avatar[1]", equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/stephenmoon/128.jpg")).
                body("data.id[2]", equalTo(6)).
                body("data.first_name[2]", equalTo("Tracey")).
                body("data.last_name[2]", equalTo("Ramos")).
                body("data.avatar[2]", equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/bigmancho/128.jpg"));
    }

    @Test
    public void testForGetSingleUser() throws Exception {
        given().
                when().
                get(baseURI + "/api/users/2").
                then().
                statusCode(200).
                body("data.id", equalTo(2)).
                body("data.first_name", equalTo("Janet")).
                body("data.last_name", equalTo("Weaver")).
                body("data.avatar", equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg"));
    }


    @Test
    public void testForGetSingleUserNotFound() throws Exception {
        given().
                when().
                get(baseURI + "/api/users/23").
                then().
                statusCode(404).
                body("isEmpty()", Matchers.is(true));
    }

    @Test
    public void testForGetListOfResources() throws Exception {
        given().
                when().
                get(baseURI + "/api/unknown").
                then().
                statusCode(200).
                body("page", equalTo(1)).
                body("per_page", equalTo(3)).
                body("total", equalTo(12)).
                body("total_pages", equalTo(4)).
                body("data.id[0]", equalTo(1)).
                body("data.name[0]", equalTo("cerulean")).
                body("data.year[0]", equalTo(2000)).
                body("data.color[0]", equalTo("#98B2D1")).
                body("data.pantone_value[0]", equalTo("15-4020")).
                body("data.id[1]", equalTo(2)).
                body("data.name[1]", equalTo("fuchsia rose")).
                body("data.year[1]", equalTo(2001)).
                body("data.color[1]", equalTo("#C74375")).
                body("data.pantone_value[1]", equalTo("17-2031")).
                body("data.id[2]", equalTo(3)).
                body("data.name[2]", equalTo("true red")).
                body("data.year[2]", equalTo(2002)).
                body("data.color[2]", equalTo("#BF1932")).
                body("data.pantone_value[2]", equalTo("19-1664"));
    }

    @Test
    public void testForGetSingleResource() throws Exception {
        given().
                when().
                get(baseURI + "/api/unknown/2").
                then().
                statusCode(200).
                body("data.id", equalTo(2)).
                body("data.name", equalTo("fuchsia rose")).
                body("data.year", equalTo(2001)).
                body("data.color", equalTo("#C74375")).
                body("data.pantone_value", equalTo("17-2031"));
    }

    @Test
    public void testForGetSingleResourceNotFound() throws Exception {
        given().
                when().
                get(baseURI + "/api/unknown/23").
                then().
                statusCode(404).
                body("isEmpty()", Matchers.is(true));
    }

    @Test
    public void testForCreateUser() throws Exception {
        Response response =
                given().
                        formParam("name", "morpheus").
                        formParam("job", "leader").
                        when().
                        post(baseURI + "/api/users");

        System.out.print(response.asString());
        response.then().
                body("name", equalTo("morpheus")).
                body("job", equalTo("leader")).
                body("id", notNullValue()).
                body("createdAt", notNullValue()).
                statusCode(201);
    }

    @Test
    public void testForUpdateUserUsingPut() throws Exception {
        Response response =
                given().
                        formParam("name", "morpheus").
                        formParam("job", "zion resident").
                        when().
                        put(baseURI + "/api/users/2");

        System.out.print(response.asString());
        response.then().
                body("name", equalTo("morpheus")).
                body("job", equalTo("zion resident")).
                body("updatedAt", notNullValue()).
                statusCode(200);
    }

    @Test
    public void testForUpdateUserUsingPatch() throws Exception {
        Response response =
                given().
                        formParam("name", "morpheus").
                        formParam("job", "zion resident").
                        when().
                        patch(baseURI + "/api/users/2");

        System.out.print(response.asString());
        response.then().
                body("name", equalTo("morpheus")).
                body("job", equalTo("zion resident")).
                body("updatedAt", notNullValue()).
                statusCode(200);
    }

    @Test
    public void testForDeleteUser() throws Exception {
        when().
                delete(baseURI + "/api/users/2").
                then().
                statusCode(204);
    }

    @Test
    public void testForRegisterSuccessful() throws Exception {
        Response response =
                given().
                        formParam("email", "sydney@fife").
                        formParam("password", "pistol").
                        when().
                        post(baseURI + "/api/register");

        System.out.print(response.asString());
        response.then().
                body("token", equalTo("QpwL5tke4Pnpja7X")).
                statusCode(201);
    }

    @Test
    public void testForRegisterUnSuccessful() throws Exception {
        Response response =
                given().
                        formParam("email", "sydney@fife").
                        when().
                        post(baseURI + "/api/register");

        System.out.print(response.asString());
        response.then().
                body("error", equalTo("Missing password")).
                statusCode(400);
    }

    @Test
    public void testForLoginSuccessful() throws Exception {
        Response response =
                given().
                        formParam("email", "peter@klaven").
                        formParam("password", "cityslicka").
                        when().
                        post(baseURI + "/api/login");

        System.out.print(response.asString());
        response.then().
                body("token", equalTo("QpwL5tke4Pnpja7X")).
                statusCode(200);
    }

    @Test
    public void testForLoginUnSuccessful() throws Exception {
        Response response =
                given().
                        formParam("email", "peter@klaven").
                        when().
                        post(baseURI + "/api/login");

        System.out.print(response.asString());
        response.then().
                body("error", equalTo("Missing password")).
                statusCode(400);
    }

    @Test
    public void testForDelayedResponse() throws Exception {
        given().
                when().
                get(baseURI + "/api/users?delay=3").
                then().
                statusCode(200).
                body("page", equalTo(1)).
                body("per_page", equalTo(3)).
                body("total", equalTo(12)).
                body("total_pages", equalTo(4)).
                body("data.id[0]", equalTo(1)).
                body("data.first_name[0]", equalTo("George")).
                body("data.last_name[0]", equalTo("Bluth")).
                body("data.avatar[0]", equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg")).
                body("data.id[1]", equalTo(2)).
                body("data.first_name[1]", equalTo("Janet")).
                body("data.last_name[1]", equalTo("Weaver")).
                body("data.avatar[1]", equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/josephstein/128.jpg")).
                body("data.id[2]", equalTo(3)).
                body("data.first_name[2]", equalTo("Emma")).
                body("data.last_name[2]", equalTo("Wong")).
                body("data.avatar[2]", equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/olegpogodaev/128.jpg"));
    }
}


