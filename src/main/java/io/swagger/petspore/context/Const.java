package io.swagger.petspore.context;

public enum Const {

    BASEURL ("http://petstore.swagger.io");

    private final String title;

    Const(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
