package com.example.restwsejbdemo

import com.example.restwsejbdemo.domain.Computer
import com.example.restwsejbdemo.domain.Owner
import com.example.restwsejbdemo.domain.Producer
import com.example.restwsejbdemo.domain.Warranty
import com.google.gson.GsonBuilder
import com.jayway.restassured.RestAssured
import com.jayway.restassured.http.ContentType
import org.junit.BeforeClass
import org.junit.Test

import javax.ws.rs.core.MediaType

import static com.jayway.restassured.RestAssured.given
import static com.jayway.restassured.RestAssured.when

class ComputerIT
{
    final String MODEL = "Razor"
    final String CPU = "IntelI9"
    final String GPU = "TitanX"
    final double PRICE = 3999.0
    final String FIRST_OWNER_NAME = "Janek"
    final String FIRST_OWNER_SURNAME = "Dzbanek"
    final String FIRST_OWNER_CITY = "Sosnowiec"
    final String SECOND_OWNER_NAME = "Jagoda"
    final String SECOND_OWNER_SURNAME = "Swoboda"
    final String SECOND_OWNER_CITY= "Radom"

    @BeforeClass
    static void "set up RestAssuerd settings" ()
    {
        RestAssured.baseURI = "http://localhost"
        RestAssured.port = 8080
        RestAssured.basePath = "/restwsejbdemo/computer"
    }

    @Test
    void "resource should add computer without other relations and return 201" ()
    {
        def gson = new GsonBuilder().create()
        def jsonComputer = gson.toJson(new Computer(MODEL,PRICE))

        given().
            contentType(MediaType.APPLICATION_JSON).
            body(jsonComputer).
        when().
            post("add").
        then()
            .statusCode(201)
    }

    @Test
    void "resource should add computer with related entities and return 201" ()
    {
        def gson = new GsonBuilder().create()
        def owners = new ArrayList<>()
        owners.add(new Owner(FIRST_OWNER_NAME, FIRST_OWNER_SURNAME, FIRST_OWNER_CITY))
        owners.add(new Owner(SECOND_OWNER_NAME, SECOND_OWNER_SURNAME, SECOND_OWNER_CITY))

        def producer = new Producer("MSI")
        def warranty = new Warranty("basic")

        given().
            contentType(ContentType.JSON).
            body(gson.toJson(new Computer(MODEL,owners, PRICE, producer, warranty))).
        when()
            .post("add").
        then()
            .statusCode(201)
    }
}
