package com.example.cargologger.repositories;

import com.example.cargologger.models.users.Driver;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DriverRepository extends CrudRepository<Driver, Long> {
    List<Driver> findByEmployer_Id(long id);
}
