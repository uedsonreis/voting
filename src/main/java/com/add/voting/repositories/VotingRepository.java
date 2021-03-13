package com.add.voting.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.add.voting.models.entities.Guideline;
import com.add.voting.models.entities.Voting;

@Repository
public interface VotingRepository extends CrudRepository<Voting, Long> {

	Voting findByUserIdAndGuideline(Long userId, Guideline guideline);
	
	List<Voting> findAllByGuideline(Guideline guideline);
	
}