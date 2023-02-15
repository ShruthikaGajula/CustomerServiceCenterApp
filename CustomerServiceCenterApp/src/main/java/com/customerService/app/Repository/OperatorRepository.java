package com.customerService.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerService.app.Entity.Operator;
@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer>{

}
