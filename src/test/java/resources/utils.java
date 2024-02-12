package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class utils {
    public static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException
    {
        /*Here we defined req as null because, suppose if we use multiple data sets in examples, then at the time code is running
         * completed, the last example data response is coming because it overwriting the previous example data.
         * so for that purpose we added if-else loop.
         *
         *  Here when we run the code with first example, then req will be null. so it execute requstSpecBuilder. but
         *  when code is ran with 2nd time then already some data stored in req variable. so it return the prev request data
         *  so it wont override.*/
        if(req==null)
        {
           req = new RequestSpecBuilder().setBaseUri("https://reqres.in/").setContentType(ContentType.JSON).build();
           return req;
        }
        return req;


    }
}

