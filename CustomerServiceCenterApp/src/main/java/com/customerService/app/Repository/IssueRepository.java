package com.customerService.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerService.app.Entity.Issue;
@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer>{

}
