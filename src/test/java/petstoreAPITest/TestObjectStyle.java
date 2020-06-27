package petstoreAPITest;

import io.swagger.petspore.controllers.pet.PetController;
import io.swagger.petspore.models.pet.PetModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

public class TestObjectStyle {
    public int dogId2 = RandomUtils.nextInt(0, 9999);
    public String dogName = RandomStringUtils.randomAlphabetic(6);

    @Test
    public void Test() {
        //Создается модель body запроса для создания нового ресурса(Jacobs)
        PetModel testPetModel = new PetModel(dogId2, null, dogName, null, null, "aviable");

        //Controller класс получает эту модель и использует её для отправки запроса.
        PetModel pet = new PetController().addNewPet(testPetModel); // метод addNewPet содержит body(model) + GiveVhenThen.post
        System.out.println(pet);
    }
}
