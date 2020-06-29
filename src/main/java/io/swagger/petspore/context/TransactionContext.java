package io.swagger.petspore.context;

import org.junit.Assert;

public class TransactionContext extends BaseContext {

    //проверка изменяющегося статуса прослушиванием

    public void сheckExecutedStatus(String expectedStatus) {
        String currentStatus = "";
        for (int i = 0; i < 25; i++) {
            waitFor(5);
//            currentStatus = checkSomeStatus();
            if (currentStatus.equalsIgnoreCase(expectedStatus)) {
                break;
            }
        }

        Assert.assertEquals("ожидаемый параметр не верный", currentStatus, expectedStatus);
    }

}