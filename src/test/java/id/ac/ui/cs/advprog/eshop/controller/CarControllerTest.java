package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.ConcurrentModel;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Mock
    CarService carService;

    @InjectMocks
    CarController carController;

    @Test
    void createCarPageSetsCarAndReturnsTemplate() {
        Model model = new ConcurrentModel();

        String view = carController.createCarPage(model);

        assertEquals("createCar", view);
        assertNotNull(model.getAttribute("car"));
    }

    @Test
    void createCarPostCreatesCarAndRedirects() {
        Car car = new Car();

        String view = carController.createCarPost(car, new ConcurrentModel());

        verify(carService).create(car);
        assertEquals("redirect:listCar", view);
    }

    @Test
    void carListPageAddsCarsAndReturnsTemplate() {
        Model model = new ConcurrentModel();
        Car c1 = new Car();
        Car c2 = new Car();
        List<Car> cars = List.of(c1, c2);
        when(carService.findAll()).thenReturn(cars);

        String view = carController.carListPage(model);

        verify(carService).findAll();
        assertEquals(cars, model.getAttribute("cars"));
        assertEquals("carList", view);
    }

    @Test
    void editCarPageAddsCarAndReturnsTemplate() {
        String carId = "car-1";
        Car car = new Car();
        when(carService.findById(carId)).thenReturn(car);
        Model model = new ConcurrentModel();

        String view = carController.editCarPage(carId, model);

        verify(carService).findById(carId);
        assertEquals(car, model.getAttribute("car"));
        assertEquals("editCar", view);
    }

    @Test
    void editCarPostUpdatesCarAndRedirects() {
        String carId = "car-1";
        Car car = new Car();
        car.setCarId(carId);

        String view = carController.editCarPost(car, new ConcurrentModel());

        verify(carService).update(carId, car);
        assertEquals("redirect:listCar", view);
    }

    @Test
    void deleteCarDeletesAndRedirects() {
        String carId = "car-1";

        String view = carController.deleteCar(carId);

        verify(carService).deleteCarById(carId);
        assertEquals("redirect:listCar", view);
    }
}
