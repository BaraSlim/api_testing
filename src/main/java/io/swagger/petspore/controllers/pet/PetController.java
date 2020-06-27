package io.swagger.petspore.controllers.pet;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.swagger.petspore.models.pet.PetModel;

public class PetController{

    private RequestSpecification petSpec;

    public PetController() {
        petSpec = new RequestSpecBuilder()
                .addHeader("api_key", "55555")
                .setBaseUri("http://petstore.swagger.io")
                .setBasePath("/v2/pet")
                .setContentType(ContentType.JSON)
                .setAccept("application/json")
                .log(LogDetail.ALL).build();
    }

    public PetModel addNewPet(PetModel pet) {        //экземпляр PetModel соответсвует полям body для POST запроса.
        return RestAssured.given(petSpec)
                .body(pet)              //передается сформированное на основе model классов, специальное body запроса для POST
                .when()
                .post()
                .as(PetModel.class);    //для дисериализации response к модели PetModel
    }

}
