import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PostmanEcho {
    private static final String BASE_URL = "https://postman-echo.com";

    @Test
    void testGet() {
        Response response = RestAssured.get(BASE_URL + "/get?foo1=bar1&foo2=bar2");
        assertEquals(200, response.getStatusCode());
        assertEquals("bar1", response.jsonPath().getString("args.foo1"));
        assertEquals("bar2", response.jsonPath().getString("args.foo2"));
    }

    @Test
    void testPost() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "John");
        requestBody.put("age", "30");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL + "/post");

        assertEquals(200, response.getStatusCode());
        assertEquals("John", response.jsonPath().getString("json.name"));
        assertEquals("30", response.jsonPath().getString("json.age"));
    }

    @Test
    void testPut() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("id", "123");
        requestBody.put("value", "updated");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(BASE_URL + "/put");

        assertEquals(200, response.getStatusCode());
        assertEquals("123", response.jsonPath().getString("json.id"));
        assertEquals("updated", response.jsonPath().getString("json.value"));
    }

    @Test
    void testPatch() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("status", "patched");

        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(BASE_URL + "/patch");

        assertEquals(200, response.getStatusCode());
        assertEquals("patched", response.jsonPath().getString("json.status"));
    }

    @Test
    void testDelete() {
        Response response = RestAssured.delete(BASE_URL + "/delete");
        assertEquals(200, response.getStatusCode());
        assertEquals("https://postman-echo.com/delete", response.jsonPath().getString("url"));
    }

    @Test
    void testHead() {
        Response response = RestAssured.head(BASE_URL + "/get");
        assertEquals(200, response.getStatusCode());
    }

    @Test
    void testOptions() {
        Response response = RestAssured.options(BASE_URL + "/get");
        assertEquals(200, response.getStatusCode());
        String allowHeader = response.getHeader("Allow");
        assertNotNull(allowHeader);
    }
}
