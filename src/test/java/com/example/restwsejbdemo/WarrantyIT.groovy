package com.example.restwsejbdemo

import com.example.restwsejbdemo.domain.Warranty
import com.google.gson.GsonBuilder
import com.jayway.restassured.RestAssured
import org.junit.BeforeClass
import org.junit.Test

import javax.ws.rs.core.MediaType

import static com.jayway.restassured.RestAssured.given

class WarrantyIT {
    final String WARRANTY_TYPE = "extended"

    @BeforeClass
    static void "set up RestAssuerd settings" ()
    {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = 8080
        RestAssured.basePath = "/myapp/warranty"

    }

    @Test
    void "resource should add warranty without other relations and return 201" ()
    {
        def gson = new GsonBuilder().create()

        given().
                contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(new Warranty(WARRANTY_TYPE))).
        when()
                .post("add").
        then()
            .assertThat()
            .statusCode(201)
    }
}
