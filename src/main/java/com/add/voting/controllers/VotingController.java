package com.add.voting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.add.voting.controllers.dto.CreateVoting;
import com.add.voting.models.VotingResult;
import com.add.voting.services.VotingService;

@RestController
@RequestMapping("votings")
public class VotingController {

	@Autowired
	private VotingService votingService;
	
	@PostMapping
    public ResponseEntity<String> store(@RequestBody(required = true) CreateVoting body) throws Exception {
		try {
			this.votingService.save(body.getVote(), body.getUserId(), body.getGuidelineId());
			return new ResponseEntity<>("Voto registrado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
    }
	
	@GetMapping("/{id}")
	public VotingResult getResult(@PathVariable(value="id") String id) {
		return this.votingService.getResult(Long.valueOf(id));
	}
	
}
