package com.xebia.repository;

import com.xebia.domain.Car;
import com.xebia.domain.Color;

import java.util.Date;
import java.util.List;

public interface ICarRepository {

    public List<Car> findCarsByConstructor(String constructor);

    public List<Car> findCarsByModel(String model);

    public List<Car> findCarsByDate(Integer date);

    public List<Car> findCarsBeforeDate(Date date);

    public List<Car> findCarsAfterDate(Date date);

    public List<Car> findCarsByColor(Color color);
}
