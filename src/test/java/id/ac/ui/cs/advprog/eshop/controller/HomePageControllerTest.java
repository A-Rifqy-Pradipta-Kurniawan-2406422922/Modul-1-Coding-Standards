package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomePageControllerTest {

    private final HomePageController controller = new HomePageController();

    @Test
    void homePageReturnsHomePageTemplate() {
        assertEquals("HomePage", controller.homePage());
    }
}
