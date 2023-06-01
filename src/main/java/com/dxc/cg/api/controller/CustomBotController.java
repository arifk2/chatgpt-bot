package com.dxc.cg.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dxc.cg.api.dto.ChatGPTRequest;
import com.dxc.cg.api.dto.ChatGPTResponse;

@RestController
@RequestMapping("/bot")
public class CustomBotController {

	@Value("${openai.model}")
	private String model;

	@Value("${openaiChat.url}")
	private String url;



	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/chat")
	public String chat(@RequestParam("prompt") String prompt) {
		ChatGPTRequest chatGPTRequest = new ChatGPTRequest(model, prompt);
		ChatGPTResponse chatGPTResponse = restTemplate.postForObject(url, chatGPTRequest, ChatGPTResponse.class);
		return chatGPTResponse.getChoices().get(0).getMessage().getContent();
	}
}
