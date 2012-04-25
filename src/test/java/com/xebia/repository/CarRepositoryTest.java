package com.xebia.repository;

import com.xebia.domain.Car;
import com.xebia.domain.Color;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.fest.assertions.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.File;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/test-application-context.xml"})
@TransactionConfiguration(defaultRollback = true)
public class CarRepositoryTest {

    @Resource(name = "carRepository")
    private ICarRepository carRepository;

    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(dbConn, getDataCar());
    }

    private IDataSet getDataCar() throws MalformedURLException, DataSetException {
        return new FlatXmlDataSetBuilder().build(new File(getClass().getClassLoader().getResource("CAR.xml").getFile()));
    }

    @After
    public void after() throws Exception {
        IDatabaseConnection dbConn = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.DELETE_ALL.execute(dbConn, getDataCar());
    }

    @Test
    public void should_findCarsByConstructor() {
        List<Car> constructors = carRepository.findCarsByConstructor("Peugeot");
        Assertions.assertThat(constructors).isNotEmpty();

        Assertions.assertThat(constructors.get(0).getConstructor()).isEqualTo("Peugeot");
    }

    @Test
    public void should_findCarsByModel() {
        List<Car> constructors = carRepository.findCarsByModel("Laguna");
        Assertions.assertThat(constructors).isNotEmpty();

        Assertions.assertThat(constructors.get(0).getModel()).isEqualTo("Laguna");
    }

    @Test
    public void should_findCarsByDate() {
        List<Car> constructors = carRepository.findCarsByDate(2005);
        Assertions.assertThat(constructors).isNotEmpty();

        Assertions.assertThat(constructors).hasSize(1);
        Assertions.assertThat(constructors.get(0).getConstructor()).isEqualTo("Peugeot");
    }

    @Test
    public void should_findCarsBeforeDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        List<Car> constructors = carRepository.findCarsBeforeDate(format.parse("10/03/2006"));
        Assertions.assertThat(constructors).isNotEmpty();

        Assertions.assertThat(constructors.get(0).getConstructor()).isEqualTo("Peugeot");
    }

    @Test
    public void should_findCarsAfterDate() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        List<Car> constructors = carRepository.findCarsAfterDate(format.parse("18/08/2005"));
        Assertions.assertThat(constructors).isNotEmpty();

        Assertions.assertThat(constructors.get(0).getConstructor()).isEqualTo("Renault");
    }

    @Test
    public void should_findCarsByColor() throws ParseException {
        List<Car> constructors = carRepository.findCarsByColor(Color.GREY);
        Assertions.assertThat(constructors).isNotEmpty();

        Assertions.assertThat(constructors.get(0).getConstructor()).isEqualTo("Peugeot");
    }
}