package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepositoryRead;
import id.ac.ui.cs.advprog.eshop.repository.CarRepositoryWrite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepositoryRead carRepositoryRead;

    @Autowired
    private CarRepositoryWrite carRepositoryWrite;

    @Override
    public Car create(Car car) {
        carRepositoryWrite.create(car);
        return car;
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepositoryRead.findAll();
        List<Car> allCar = new ArrayList<>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car findById(String carId) {
        Car car = carRepositoryRead.findById(carId);
        return car;
    }

    @Override
    public void update(String carId, Car car) {
        carRepositoryWrite.update(carId, car);
    }

    @Override
    public void deleteCarById(String carId) {
        carRepositoryWrite.delete(carId);
    }
}
