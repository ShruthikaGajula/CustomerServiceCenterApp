package com.customerService.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerService.app.Entity.Call;

@Repository
public interface CallRepository extends JpaRepository<Call, Integer>{

}
