package petstoreAPITest;

import io.swagger.petspore.context.BaseContext;
import io.swagger.petspore.controllers.pet.PetController;
import io.swagger.petspore.models.pet.PetModel;
import io.swagger.petspore.utils.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestObjectScenario {
    public static int dogId2 = RandomUtils.nextInt(0, 9999);
    public static String dogName = RandomStringUtils.randomAlphabetic(6);
    public static String phoneNumber = BaseContext.getPhoneBySenderDescription("defaultPet");
    public static PetModel petModel = new PetModel();
    public static String mobApp = Properties.get("mobApp");


    @Test
    public void adavncedAPIWithModelTest() {
        //Создается модель body запроса для создания нового ресурса(Jacobs)
        petModel = new PetModel(dogId2, null, dogName, null, null, "aviable");

        //Controller класс получает эту модель и использует её для отправки запроса.
        PetModel petResponse = new PetController(petModel).addNewPet(); // метод addNewPet содержит body(model) + GiveVhenThen.post
    }

    @Test
    public void advancedAPIWithJsonAndPropertiesTest() {

        petModel = new PetModel(12224, null, phoneNumber, null, null, mobApp);
        PetModel petResponse = new PetController(petModel).addNewPet();         //возвращает RS по модели.

        Assert.assertEquals("name is not equal", petResponse.getName(), phoneNumber);
//        BaseContext.waitFor(5);
        Assert.assertTrue("message is not equal", petResponse.getStatus().equalsIgnoreCase(mobApp));
    }

    @Test
    public void advancedAPIWithScenario() {
        //add(Post) + update(Put)
        //создание модели запроса
        petModel = new PetModel(12224, null, phoneNumber, null, null, mobApp);
        //создание контроллера для запроса
        PetController petController = new PetController(petModel);
        //отправка POST запроса
        petController.addNewPet();
        //обновление сущетсвующей модели
        petModel.setStatus(Properties.get("petCategory1"));
        //отпарвка запроса PUT (upDate)
        PetModel petResponse = petController.upDatePet();
        Assert.assertEquals("Статус не обновился", Properties.get("petCategory1"), petResponse.getStatus());

    }

    @Test
    public void advancedApiWithScenario2(){
        //add(Post) + delete + get
        //создание модели запроса
        petModel = new PetModel(12224, null, phoneNumber, null, null, mobApp);
        //создание контроллера для запроса
        PetController petController = new PetController(petModel);
        //отправка POST запроса
        petController.addNewPet();
        petController.deletePet("12224");
        petController.getPet("12224");

        }
}
