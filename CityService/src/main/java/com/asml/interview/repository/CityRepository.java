package com.asml.interview.repository;

import com.asml.interview.model.CityModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<CityModel,Long> {
}
