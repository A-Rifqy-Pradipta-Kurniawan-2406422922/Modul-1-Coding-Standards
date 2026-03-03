package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepositoryRead;
import id.ac.ui.cs.advprog.eshop.repository.CarRepositoryWrite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @InjectMocks
    CarServiceImpl carService;

    @Mock
    CarRepositoryRead carRepositoryRead;

    @Mock
    CarRepositoryWrite carRepositoryWrite;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateCar() {
        Car car = new Car();

        Car result = carService.create(car);

        verify(carRepositoryWrite, times(1)).create(car);
        assertEquals(car, result);
    }

    @Test
    void testFindAllCar() {
        Car car1 = new Car();
        Car car2 = new Car();
        Iterator<Car> iterator = Arrays.asList(car1, car2).iterator();

        when(carRepositoryRead.findAll()).thenReturn(iterator);

        List<Car> result = carService.findAll();

        verify(carRepositoryRead, times(1)).findAll();
        assertEquals(2, result.size());
        assertEquals(car1, result.get(0));
        assertEquals(car2, result.get(1));
    }

    @Test
    void testFindByIdCar() {
        String carId = "car-1";
        Car car = new Car();
        when(carRepositoryRead.findById(carId)).thenReturn(car);

        Car result = carService.findById(carId);

        verify(carRepositoryRead, times(1)).findById(carId);
        assertEquals(car, result);
    }

    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setCarId("car-1");

        carService.update(car.getCarId(), car);

        verify(carRepositoryWrite, times(1)).update(car.getCarId(), car);
    }

    @Test
    void testDeleteCar() {
        String carId = "car-1";

        carService.deleteCarById(carId);

        verify(carRepositoryWrite, times(1)).delete(carId);
    }
}
