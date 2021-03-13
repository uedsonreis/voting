package com.add.voting.models.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@SuppressWarnings("unused")
@EqualsAndHashCode(of = "id")
@Entity(name = "votings")
public class Voting {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private Long userId;
	private Boolean yes;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "guideline_id", nullable = false)
	private Guideline guideline;

}