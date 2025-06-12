package work.rustam.common.services.api;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@Component
@Lazy
@Getter
@Setter
@Slf4j
public class RestClient {
    protected ThreadLocal<Response> response = ThreadLocal.withInitial(() -> null);
    protected RequestSpecification request;
    //*********************************************Constructors*******************************************************//
    public RestClient() {
        request = new RequestSpecBuilder().addHeader("X-ACCEPTANCE-TEST","TRUE")
                .setConfig(new RestAssuredConfig().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation("TLSv1.2"))
                        .logConfig(new LogConfig()))
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                .addFilter(new ResponseLoggingFilter())
                .addHeader("X-ACCEPTANCE-TEST","TRUE")
                .addHeader("x-api-key","reqres-free-v1")
                .build();
    }

    public RequestSpecification cleanBasicAuth() {
        request = new RequestSpecBuilder()
                .setConfig(new RestAssuredConfig().sslConfig(new SSLConfig().allowAllHostnames().relaxedHTTPSValidation("TLSv1.2"))
                        .logConfig(new LogConfig()))
                .setContentType(ContentType.JSON)
                .addFilter(new AllureRestAssured())
                .addFilter(new RequestLoggingFilter())
                //.addFilter(new ResponseLoggingFilter())
                .addHeader("X-ACCEPTANCE-TEST","TRUE")
                .addHeader("x-api-key","reqres-free-v1")
                .build();

        return given().spec(request).auth().none();
    }

    //*********************************************Authorization******************************************************//

    public RequestSpecification setBasicAuth(String username, String password) {
        return given().spec(request).auth().basic(username, password);
    }

    public RequestSpecification setPreemptiveBasicAuth(String username, String password) {
        return given().spec(request).auth().preemptive().basic(username, password);
    }

    public Response sendPostRequest(String endPoint, Map<String, String> body) {
         response.set(given().spec(request).body(body).post(endPoint));
         return response.get();
    }

    //*********************************************Operations*********************************************************//

    //GET
    public Response sendGetRequest(String endPoint, HashMap<String, String> paramsMap) {
        response.set(given().spec(request).params(paramsMap).get(endPoint));
        return response.get();
    }

    public Response sendGetRequest(String endPoint) {
        response.set(given().spec(request).get(endPoint));
        return response.get();
    }


    //POST
    public Response sendPostRequest(String endPoint, String body) {
        response.set(given().spec(request).body(body).post(endPoint));
        return response.get();
    }

    public Response sendPostRequest(String endPoint, Object object) {
        response.set(given().spec(request).body(object).post(endPoint));
        return response.get();
    }


    //PUT
    public Response sendPutRequest(String endPoint, Object object) {
        response.set(given().spec(request).body(object).put(endPoint));
        return response.get();
    }

    //DELETE
    public Response sendDeleteRequest(String endPoint) {
        response.set(given().spec(request).delete(endPoint));
        return response.get();
    }

    //*********************************************Parameters*********************************************************//

    //Parameters
    public RequestSpecification setQueryParam(String parameter, Object... values) {
        return given().spec(request).queryParam(parameter, values);
    }

    public RequestSpecification setParam(String parameter, Object... values) {
        return given().spec(request).param(parameter, values);
    }

    public RequestSpecification setPathParam(String pathParameterName, Object pathParameterValue) {
        return given().spec(request).pathParam(pathParameterName, pathParameterValue);
    }


}
