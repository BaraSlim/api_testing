package petstoreAPITest;

import io.swagger.petspore.models.pet.PetModel;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.hamcrest.Matchers;
import org.junit.Test;

public class CommonTest {


    @Test
    public void Test() {
        String dogName = RandomStringUtils.randomAlphabetic(5);
        String dogId = RandomStringUtils.randomNumeric(5);
        int dogId2 = RandomUtils.nextInt(0,9999);

        RestAssured.given()                                                         //"дано"
                .baseUri("https://petstore.swagger.io/")                            //доменный адрес
                .basePath("v2/pet")                                                 //путь к ресурсу
                .header("api_key", "55555")                    //данные в headers-
                .header("Content-Type", "application/json")
                .header("accept", "application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"name\": \"" + dogName + "\",\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when().post().then()                                                //"когда" делаем post
//                .extract().response().prettyPrint();                                 //"тогда" вывести ответ
//                .body("id", Matchers.equalTo(Integer.valueOf(dogID)));             //тогда проверить что параметр id равен...
                .body("name", Matchers.equalTo(dogName));             //тогда проверить что параметр id равен...
    }

    @Test
    public void Test2(){

        int dogId2 = RandomUtils.nextInt(0,9999);
        PetModel petModel = new PetModel(dogId2,null,"Jacobs",null, null,"aviable");
        RequestSpecification builder = new RequestSpecBuilder()       //спецификация запросов.
                .setBaseUri("https://petstore.swagger.io/")
                .setBasePath("v2/pet")
                .setContentType(ContentType.JSON)
                .setAccept("application/json")
                .addHeader("api_key","55555").build();

        RestAssured.given(builder)
                .body(petModel)
                .when().post().then()
                .extract().response().prettyPrint();
        }

}
