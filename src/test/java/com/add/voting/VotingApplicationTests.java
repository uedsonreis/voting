package com.add.voting;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.add.voting.controllers.dto.CreateVoting;
import com.add.voting.models.entities.Guideline;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class VotingApplicationTests {

	@Autowired
    private MockMvc mockMvc;
	
	@Test
	void contextLoads() {
		ObjectMapper mapper = new ObjectMapper();
		
		Guideline guideline = new Guideline();
		guideline.setTitle("Aumento de Impostos");
		guideline.setDescription("Você vota a favor?");
		
		Long guidelineId = null;
		
		try {
			String body = mapper.writeValueAsString(guideline);
			MvcResult result = this.mockMvc.perform(
					post("/guidelines").contentType(MediaType.APPLICATION_JSON).content(body)
			).andExpect(status().isOk()).andReturn();
			
			guidelineId = Long.valueOf(result.getResponse().getContentAsString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		CreateVoting voting = new CreateVoting();
		voting.setUserId(Long.valueOf("1"));
		voting.setGuidelineId(guidelineId);
		voting.setVote("NÃO");
		
		try {
			String body = mapper.writeValueAsString(voting);
			this.mockMvc.perform(
					post("/votings").contentType(MediaType.APPLICATION_JSON).content(body)
			).andExpect(status().isOk()).andReturn();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			String body = mapper.writeValueAsString(voting);
			this.mockMvc.perform(
					post("/votings").contentType(MediaType.APPLICATION_JSON).content(body)
			).andExpect(status().isBadRequest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			this.mockMvc.perform(get("/votings/"+guidelineId)).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
