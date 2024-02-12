package stepdefinitions;

import io.cucumber.java.en.*;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;
import resources.APIResources;
import resources.TestDataBuild;
import resources.utils;

import java.io.IOException;

import static io.restassured.RestAssured.*;

public class StepDefinitions extends utils {
    RequestSpecification res;
    ResponseSpecification resspec;
    Response response;
    TestDataBuild testData = new TestDataBuild();
    JsonPath js;
    String id;
    static String place_id;
    @Given("get the list of users using {string} api")
    public void get_the_list_of_users_using_api(String resource) throws IOException {
        APIResources resourceAPI=APIResources.valueOf(resource);
        res = given().spec(requestSpecification()).log().all();
        System.out.println(resourceAPI.getResource());

        resspec = new ResponseSpecBuilder().expectStatusCode(200).build();
        response =res.when().get(resourceAPI.getResource()).then().spec(resspec).log().all().extract().response();
    }

    @Given("get the list of user for invalid api {string} api")
    public void get_the_list_of_user_for_invalid_api_api(String invalidRes) throws IOException {
    System.out.println("Invalid api operation started");
        APIResources resourceAPI=APIResources.valueOf(invalidRes);
        res = given().spec(requestSpecification()).log().all();
        System.out.println(resourceAPI.getResource());

        resspec = new ResponseSpecBuilder().expectStatusCode(404).build();
        response =res.when().get(resourceAPI.getResource()).then().spec(resspec).log().all().extract().response();
        System.out.println("Invalid api operarion done");
    }
    @Then("user create the api with details {string} and {string} with {string} api")
    public void user_create_the_api_with_details_and_with_api(String name, String job, String createRes) throws IOException {
        System.out.println("create api operation started");
         res = given().spec(requestSpecification()).log().all()
            .body(testData.addReqresPayLoad(name,job));

        APIResources resourceAPI=APIResources.valueOf(createRes);
        resspec = new ResponseSpecBuilder().expectStatusCode(201).build();
        response =res.when().post(resourceAPI.getResource()).then().spec(resspec).log().all().extract().response();
         String id = response.jsonPath().get("id").toString();

        System.out.println("Extracted id is: "+id);
        System.out.println("create api operation done");
    }
    @Then("user update the api with formated details {string} and {string} using {string} api")
    public void user_update_the_api_with_formated_details_and_using_api(String updateName, String updateJob, String formatApi) throws IOException {
        System.out.println("update api operation started");
        res = given().spec(requestSpecification()).log().all()
                .body(testData.addReqresPayLoad(updateName,updateJob));

        APIResources resourceAPI=APIResources.valueOf(formatApi);
        resspec = new ResponseSpecBuilder().expectStatusCode(200).build();
        response =res.when().put(resourceAPI.getResource()).then().spec(resspec).log().all().extract().response();
        String name = response.jsonPath().get("name").toString();

        System.out.println("Extracted name is: "+name);
        System.out.println("update api operation done");
    }
    @Then("user calls {string} api with check the status code")
    public void user_api_calls_with_check_the_status_code(String deleteRes ) throws IOException {
        APIResources resourceAPI=APIResources.valueOf(deleteRes);
        res = given().spec(requestSpecification()).log().all();
        System.out.println(resourceAPI.getResource());

        resspec = new ResponseSpecBuilder().expectStatusCode(204).build();
        response =res.when().delete("/api/users/"+id).then().spec(resspec).log().all().extract().response();

    }
}
