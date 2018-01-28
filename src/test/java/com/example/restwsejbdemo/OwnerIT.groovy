package com.example.restwsejbdemo

import com.example.restwsejbdemo.domain.Owner
import com.google.gson.GsonBuilder
import com.jayway.restassured.RestAssured
import com.jayway.restassured.builder.RequestSpecBuilder
import com.jayway.restassured.http.ContentType
import org.junit.BeforeClass
import org.junit.Test

import javax.ws.rs.core.MediaType

import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.RestAssured.requestSpecification
import static com.jayway.restassured.RestAssured.when

class OwnerIT
{
    final String FIRST_OWNER_NAME = "Rafal"
    final String FIRST_OWNER_SURNAME = "Testowy"
    final String FIRST_OWNER_CITY = "Gdansk"

    @BeforeClass
    static void "set up RestAssuerd settings" ()
    {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = 8080
        RestAssured.basePath = "/myapp/owner"

    }

    @Test
    void "resource should add owner without other relations and return 201" ()
    {
        def gson = new GsonBuilder().create()

        given().
            contentType(MediaType.APPLICATION_JSON)
            .body(gson.toJson(new Owner(FIRST_OWNER_NAME,FIRST_OWNER_SURNAME,FIRST_OWNER_CITY))).
                when().post("add").then().assertThat().statusCode(201)
    }

}
