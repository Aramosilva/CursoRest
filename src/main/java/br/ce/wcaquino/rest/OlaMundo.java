package br.ce.wcaquino.rest;

import io.restassured.http.Method;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.*;

import org.hamcrest.MatcherAssert;
import org.junit.*;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertThat;

public class OlaMundo {

    @Test
    public void testOlaMundo(){
        Response response = request(Method.GET, "https://restapi.wcaquino.me:80/ola");
        Assert.assertTrue(response.getBody().asString().equals("Ola Mundo!"));
        Assert.assertTrue(response.statusCode() == 200);
        Assert.assertTrue("O status deveria ser: ", response.statusCode() == 200);
        Assert.assertEquals(200,response.statusCode());
        
        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);
    }

    @Test
    public void devoConhecerOutrasFormasRestAssured(){
        Response response = request(Method.GET, "https://restapi.wcaquino.me:/ola");
        ValidatableResponse validacao = response.then();
        validacao.statusCode(200);

        get("https://restapi.wcaquino.me:/ola").then().statusCode(200);

        /*Modo Fluente - Bem parecido com o Gherkin
        *Dado: pré condição,
        * quando: ação de fato,
        * então: assertivas
        */

        //Dado - Uma pré-condição
        given()
                //Quando - Ação - Acessar essa URL
                .when()
                    .get("https://restapi.wcaquino.me:/ola")
                //Então - Assertivas - Status Code deve ser 200
                .then()
                    .statusCode(200);
    }

    @Test
    public void devoConhecerMatchersHamcrest(){
        //assertThat("Maria", CoreMatchers.is("Maria"));
        //assertThat(123, CoreMatchers.is(123));
        //assertThat(123, CoreMatchers.isA(Integer.class));
        //assertThat(123d, CoreMatchers.isA(Double.class));
        //Maior que
        //Assert.assertThat(123d, CoreMatchers.greaterThan(128d));
        //Menor que
        // Assert.assertThat(123d, CoreMatchers.lessThan(128d));

        List<Integer> impares = Arrays.asList(1,3,5,7,9);
        //assertThat(impares, CoreMatchers.hasSize(5));
        //assertThat(impares, contains(1,3,5,7,9));
        //assertThat(impares, containsInAnyOrder(1,3,5,9,7));
        //assertThat(impares, hasItem(1));
        //assertThat(impares, hasItems(1,5));

        //assertThat("Maria", is(not("João")));
        //assertThat("Maria", not("João"));
        //assertThat("Joaquina", anyOff(is("Maria"), is("Joaquina")));
        //assertThat("Joaquina", allOf(starsWith("Joa"), endsWith("ina"), containsString("qui")));

      /*  @Test
        public void devoValidarBody(){
            given()
                    //Quando - Ação - Acessar essa URL
                    .when()
                    .get("https://restapi.wcaquino.me:/ola")
                    //Então - Assertivas - Status Code deve ser 200
                    .then()
                    .statusCode(200);
                  //  .body(Matchers.is("Ola Mundo!"));
        }*/


    }

}
