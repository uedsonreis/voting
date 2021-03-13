package com.add.voting.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.EqualsAndHashCode;

@Getter
@Setter
@ToString
@SuppressWarnings("unused")
@EqualsAndHashCode(of = "id")
@Entity(name = "guidelines")
public class Guideline {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String title;
	private String description;

}