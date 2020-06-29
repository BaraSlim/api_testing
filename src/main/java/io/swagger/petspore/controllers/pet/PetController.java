package io.swagger.petspore.controllers.pet;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.swagger.petspore.models.pet.PetModel;
import org.hamcrest.Matchers;

public class PetController {

    private RequestSpecification petReqSpec;
    private ResponseSpecification petResSpec;
    private PetModel pet;

    public PetController(PetModel newPet) {

        petReqSpec = new RequestSpecBuilder()            //спецификатор для request
                .addHeader("api_key", "55555")
                .setBaseUri("http://petstore.swagger.io")
                .setBasePath("/v2/pet")
                .setContentType(ContentType.JSON)
                .setAccept("application/json")
                .log(LogDetail.ALL).build();

        petResSpec = new ResponseSpecBuilder()         //спецификатор для responce
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(15000L)).log(LogDetail.ALL).build();
        this.pet = newPet;

    }

    public PetModel addNewPet() {        //экземпляр PetModel соответсвует полям body для POST запроса.
        return RestAssured.given(petReqSpec)
                .body(pet)             //передается сформированное на основе model классов, специальное body запроса для POST
                .when()
                .post()
                .then()
                .spec(petResSpec)
                .extract()
                .response()
                .as(PetModel.class);    //для дисериализации response к модели PetModel
    }
}
