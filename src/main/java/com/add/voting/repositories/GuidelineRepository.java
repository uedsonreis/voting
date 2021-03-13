package com.add.voting.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.add.voting.models.entities.Guideline;

@Repository
public interface GuidelineRepository extends CrudRepository<Guideline, Long> {

	List<Guideline> findAll();
	
}