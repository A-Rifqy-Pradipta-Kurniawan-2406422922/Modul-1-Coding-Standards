package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;

public interface CarRepositoryWrite {
    Car create(Car car);
    Car update(String id, Car updatedCar);
    void delete(String id);
}
