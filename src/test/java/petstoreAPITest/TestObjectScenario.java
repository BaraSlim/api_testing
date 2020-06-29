package petstoreAPITest;

import io.swagger.petspore.context.BaseContext;
import io.swagger.petspore.controllers.pet.PetController;
import io.swagger.petspore.models.pet.PetModel;
import io.swagger.petspore.utils.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class TestObjectScenario {
    public int dogId2 = RandomUtils.nextInt(0, 9999);
    public String dogName = RandomStringUtils.randomAlphabetic(6);

    @Test
    public void AdavncedAPIWithModelTest() {
        //Создается модель body запроса для создания нового ресурса(Jacobs)
        PetModel petModel = new PetModel(dogId2, null, dogName, null, null, "aviable");
        //Controller класс получает эту модель и использует её для отправки запроса.
        PetModel pet = new PetController(petModel).addNewPet(); // метод addNewPet содержит body(model) + GiveVhenThen.post
    }

    @Test
    public void AdvancedAPIWithJsonAndPropertiesTest() {
        String mobApp = Properties.get("mobApp");
        String phoneNumber = BaseContext.getPhoneBySenderDescription("defaultPet");
        PetModel petModel = new PetModel(12224, null, phoneNumber, null, null, mobApp);
        PetModel pet = new PetController(petModel).addNewPet();
    }

}
