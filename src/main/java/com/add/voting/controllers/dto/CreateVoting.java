package com.add.voting.controllers.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateVoting {
	private String vote;
	private Long userId;
	private Long guidelineId;
}