package io.swagger.petspore.controllers.pet;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.swagger.petspore.context.Const;
import io.swagger.petspore.models.pet.PetModel;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;

public class PetController {

    private RequestSpecification petReqSpec;
    private ResponseSpecification petResSpec;
    private PetModel pet;

    public PetController(PetModel newPet) {

        petReqSpec = new RequestSpecBuilder()            //спецификатор для request
                .addHeader("api_key", "55555")
                .setBaseUri(Const.BASEURL.getTitle())
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

    public void getPet(String idValue) {
        given(petReqSpec).get(idValue).prettyPrint();
    }

    public PetModel addNewPet() {        //экземпляр PetModel соответсвует полям body для POST запроса.
        return given(petReqSpec)
                .body(pet)             //передается сформированное на основе model классов, специальное body запроса для POST
                .when()
                .post()
                .then()
                .spec(petResSpec)
                .extract()
                .response()
                .as(PetModel.class);    //для дисериализации response к модели PetModel
    }

    public PetModel upDatePet() {
       Response response = given(petReqSpec)
                .body(pet)
                .put();
       response.prettyPrint();
       return response.as(PetModel.class);

    }

    public void deletePet(String idValue) {
        given(petReqSpec).delete(idValue).prettyPrint();
    }
}
