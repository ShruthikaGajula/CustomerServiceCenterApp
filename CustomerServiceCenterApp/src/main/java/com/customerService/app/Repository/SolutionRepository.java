package com.customerService.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerService.app.Entity.Solution;
@Repository
public interface SolutionRepository extends JpaRepository<Solution, Integer>{

}
