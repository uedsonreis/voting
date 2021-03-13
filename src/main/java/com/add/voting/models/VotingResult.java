package com.add.voting.models;

import com.add.voting.models.entities.Guideline;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotingResult {

	private Guideline guideline;
	
	private Integer yesCount = 0;
	private Integer noCount = 0;
	
	public void countOneMoreYes() {
		this.yesCount++;
	}
	
	public void countOneMoreNo() {
		this.noCount++;
	}
	
}