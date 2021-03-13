package com.add.voting.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.add.voting.models.VotingResult;
import com.add.voting.models.entities.Guideline;
import com.add.voting.models.entities.Voting;
import com.add.voting.repositories.VotingRepository;

@Service
public class VotingService {

	@Autowired
	private VotingRepository votingRepository;
	
	@Autowired
	private GuidelineService guidelineService;
	
	@Transactional
	public void save(String vote, Long userId, Long guidelineId) throws Exception {
		if (!vote.toUpperCase().equals("SIM") && !vote.toUpperCase().equals("NÃO")) {
			throw new Exception("Votos aceitos são apenas: 'Sim' ou 'Não'");
		}
		
		Guideline guideline = this.guidelineService.getById(guidelineId);
		if (guideline == null) {
			throw new Exception("Guideline ID ("+ guidelineId +") não existe!");
		}
		
		Voting voting = this.votingRepository.findByUserIdAndGuideline(userId, guideline);
		
		if (voting != null) {
			throw new Exception("Cada associado só pode votar apenas uma vez por pauta");
		}
		
		Voting newVoting = new Voting();
		newVoting.setUserId(userId);
		newVoting.setGuideline(guideline);
		
		if (vote.toUpperCase().equals("SIM")) {
			newVoting.setYes(true);
		} else {
			newVoting.setYes(false);
		}
		
		this.votingRepository.save(newVoting);
	}
	
	public VotingResult getResult(Long guidelineId) {
		Guideline guideline = this.guidelineService.getById(guidelineId);
		if (guideline != null) {
			List<Voting> votings = this.votingRepository.findAllByGuideline(guideline);
			
			VotingResult result = new VotingResult();
			result.setGuideline(guideline);
			
			if (votings != null) {
				for (Voting voting : votings) {
					if (voting.getYes()) {
						result.countOneMoreYes();
					} else {
						result.countOneMoreNo();
					}
				}
			}
			
			return result;
		}
		
		return null;
	}
	
}
