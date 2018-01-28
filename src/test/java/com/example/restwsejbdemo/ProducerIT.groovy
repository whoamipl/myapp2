package com.example.restwsejbdemo

import com.example.restwsejbdemo.domain.Owner
import com.example.restwsejbdemo.domain.Producer
import com.google.gson.GsonBuilder
import com.jayway.restassured.RestAssured
import org.junit.BeforeClass
import org.junit.Test

import javax.ws.rs.core.MediaType

import static com.jayway.restassured.RestAssured.given

class ProducerIT {
    final String PRODUCER_NAME = "MegaMachines"
    @BeforeClass
    static void "set up RestAssuerd settings" ()
    {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = 8080
        RestAssured.basePath = "/myapp/producer/"

    }

    @Test
    void "resource should add producer without other relations and return 201" ()
    {
        def gson = new GsonBuilder().create()

        given().
                contentType(MediaType.APPLICATION_JSON)
                .body(gson.toJson(new Producer(PRODUCER_NAME))).
        when().
            post("add").then().assertThat().statusCode(201)
    }
}
