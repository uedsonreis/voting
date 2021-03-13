package com.add.voting.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.add.voting.controllers.dto.CreateGuideline;
import com.add.voting.models.entities.Guideline;
import com.add.voting.services.GuidelineService;

@RestController
@RequestMapping("guidelines")
public class GuidelineController {

	@Autowired
	private GuidelineService guidelineService;
	
	@PostMapping
    public Long store(@RequestBody(required = true) CreateGuideline dto) throws Exception {
		Guideline guideline = new Guideline();
		
		guideline.setTitle(dto.getTitle());
		guideline.setDescription(dto.getDescription());
		
		return this.guidelineService.save(guideline).getId();
    }
	
	@GetMapping
	public List<Guideline> index() {
		return this.guidelineService.getAll();
	}
	
}
