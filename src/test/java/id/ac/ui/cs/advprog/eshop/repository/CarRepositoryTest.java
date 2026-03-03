package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {

    @InjectMocks
    CarRepository carRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("White");
        car.setCarQuantity(10);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedCar = carIterator.next();
        assertEquals(car.getCarId(), savedCar.getCarId());
        assertEquals(car.getCarName(), savedCar.getCarName());
        assertEquals(car.getCarColor(), savedCar.getCarColor());
        assertEquals(car.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneCar() {
        Car car1 = new Car();
        car1.setCarName("Toyota");
        car1.setCarColor("White");
        car1.setCarQuantity(10);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarName("Honda");
        car2.setCarColor("Black");
        car2.setCarQuantity(5);
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        carIterator.next();
        assertTrue(carIterator.hasNext());
        carIterator.next();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testEditCar() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("White");
        car.setCarQuantity(10);
        carRepository.create(car);

        Car updatedCar = new Car();
        updatedCar.setCarName("Tesla");
        updatedCar.setCarColor("Red");
        updatedCar.setCarQuantity(20);

        carRepository.update(car.getCarId(), updatedCar);

        Car foundCar = carRepository.findById(car.getCarId());
        assertNotNull(foundCar);
        assertEquals("Tesla", foundCar.getCarName());
        assertEquals("Red", foundCar.getCarColor());
        assertEquals(20, foundCar.getCarQuantity());
    }

    @Test
    void testDeleteCar() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("White");
        car.setCarQuantity(10);
        carRepository.create(car);
        carRepository.delete(car.getCarId());

        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setCarName("Toyota");
        car.setCarColor("White");
        car.setCarQuantity(10);
        carRepository.create(car);

        Car foundCar = carRepository.findById(car.getCarId());
        assertNotNull(foundCar);
        assertEquals(car.getCarId(), foundCar.getCarId());
    }

    @Test
    void testFindByIdIfNotFound() {
        Car foundCar = carRepository.findById("unknown-id");
        assertNull(foundCar);
    }

    @Test
    void testFindByIdSkipsNonMatchingCarBeforeMatch() {
        Car firstCar = new Car();
        firstCar.setCarName("First");
        firstCar.setCarColor("Blue");
        firstCar.setCarQuantity(1);
        carRepository.create(firstCar);

        Car secondCar = new Car();
        secondCar.setCarName("Second");
        secondCar.setCarColor("Green");
        secondCar.setCarQuantity(2);
        carRepository.create(secondCar);

        Car foundCar = carRepository.findById(secondCar.getCarId());

        assertNotNull(foundCar);
        assertEquals(secondCar.getCarId(), foundCar.getCarId());
    }

    @Test
    void testUpdateOnNonExistentCar() {
        Car car1 = new Car();
        car1.setCarName("Original");
        car1.setCarColor("Black");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car updatedInfo = new Car();
        updatedInfo.setCarName("Updated");
        updatedInfo.setCarColor("White");
        updatedInfo.setCarQuantity(50);

        carRepository.update("missing-id", updatedInfo);

        Car foundCar = carRepository.findById(car1.getCarId());
        assertEquals("Original", foundCar.getCarName());
        assertEquals("Black", foundCar.getCarColor());
        assertEquals(100, foundCar.getCarQuantity());
    }

    @Test
    void testDeleteOnNonExistentCar() {
        Car car1 = new Car();
        car1.setCarName("Original");
        car1.setCarColor("Black");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        carRepository.delete("missing-id");

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        carIterator.next();
        assertFalse(carIterator.hasNext());
    }
}
