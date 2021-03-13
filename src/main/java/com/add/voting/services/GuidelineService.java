package com.add.voting.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.add.voting.models.entities.Guideline;
import com.add.voting.repositories.GuidelineRepository;

@Service
public class GuidelineService {

	@Autowired
	private GuidelineRepository guidelineRepository;
	
	public Guideline getById(Long id) {
		return this.guidelineRepository.findById(id).get();
	}
	
	public List<Guideline> getAll() {
		return this.guidelineRepository.findAll();
	}
	
	@Transactional
	public Guideline save(Guideline reservation) {
		return this.guidelineRepository.save(reservation);
	}	

}