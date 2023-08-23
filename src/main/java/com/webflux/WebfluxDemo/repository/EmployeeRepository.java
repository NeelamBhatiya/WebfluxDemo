package com.webflux.WebfluxDemo.repository;

import com.webflux.WebfluxDemo.entity.EmployeeEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface EmployeeRepository extends R2dbcRepository<EmployeeEntity, Integer> {
}
