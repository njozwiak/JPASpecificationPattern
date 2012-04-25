package com.xebia.repository;

import com.xebia.domain.Car;
import com.xebia.domain.Color;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Transactional
@Repository("carRepository")
public class CarRepository implements ICarRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Car> findCarsByConstructor(String constructor) {
        TypedQuery<Car> query = entityManager.createQuery("FROM CAR c WHERE c.constructor = :constructor", Car.class);
        query.setParameter("constructor", constructor);

        return query.getResultList();
    }

    @Override
    public List<Car> findCarsByModel(String model) {
        TypedQuery<Car> query = entityManager.createQuery("FROM CAR c WHERE c.model = :model", Car.class);
        query.setParameter("model", model);

        return query.getResultList();
    }

    @Override
    public List<Car> findCarsByDate(Integer year) {
        TypedQuery<Car> query = entityManager.createQuery("FROM CAR c WHERE YEAR (c.buildDate) = :year ", Car.class);
        query.setParameter("year", year);

        return query.getResultList();
    }

    @Override
    public List<Car> findCarsBeforeDate(Date date) {
        TypedQuery<Car> query = entityManager.createQuery("FROM CAR c WHERE c.buildDate < :date ", Car.class);
        query.setParameter("date", date);

        return query.getResultList();
    }

    @Override
    public List<Car> findCarsAfterDate(Date date) {
        TypedQuery<Car> query = entityManager.createQuery("FROM CAR c WHERE c.buildDate > :date ", Car.class);
        query.setParameter("date", date);

        return query.getResultList();
    }

    @Override
    public List<Car> findCarsByColor(Color color) {
        TypedQuery<Car> query = entityManager.createQuery("FROM CAR c WHERE c.color = :color ", Car.class);
        query.setParameter("color", color);

        return query.getResultList();
    }
}
