package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.config.MatcherConfig;
import io.restassured.http.Method;
import io.restassured.matcher.RestAssuredMatchers;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class UserJsonTest {

    @Test
    public void deveVerificarPrimeiroNivel(){
        given()
                .when()
                    .get("https://restapi.wcaquino.me:/users/1")
                .then()
                    .statusCode(200)
                     .body("id",is(1));
    }

    @Test
    public void deveVerificarPrimeiroNivelOutrasFormas(){
        Response response = RestAssured.request(Method.GET, "https://restapi.wcaquino.me:/users/1");

        //path
        Assert.assertEquals(new Integer(1), response.path("id"));
        Assert.assertEquals(new Integer(1), response.path("%s", "id"));

        //jsonpath
        JsonPath jpath = new JsonPath(response.asString());
        Assert.assertEquals(1, jpath.getInt("id"));

        //from
        int id = JsonPath.from(response.asString()).getInt("id");
        Assert.assertEquals(1,id);

    }

    @Test
    public void deveVerificarSegundoNivel(){
        given()
                .when()
                .get("https://restapi.wcaquino.me:/users/2")
                .then()
                .statusCode(200)
               // .body("name","Joaquina")
                .body("endereço.rua", is("Rua dos bobos"));
    }
}
