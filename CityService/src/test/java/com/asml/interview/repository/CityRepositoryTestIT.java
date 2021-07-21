package com.asml.interview.repository;

import com.asml.interview.model.CityModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CityRepositoryTestIT {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private CityRepository cityRepository;


    @Test
    public void whenFindByName_returnCity() {
        //given:
        CityModel model = new CityModel();
        model.setName("test");
        entityManager.persist(model);
        entityManager.flush();

        // when
        CityModel result = cityRepository.findCityModelByName(model.getName());

        // then
        assertEquals(result.getName(),model.getName());
    }
}
