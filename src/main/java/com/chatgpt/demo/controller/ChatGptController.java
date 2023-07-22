package com.chatgpt.demo.controller;

import com.chatgpt.demo.dto.ChatGPTRequestDTO;
import com.chatgpt.demo.dto.ChatGPTResponseDTO;
import com.chatgpt.demo.exception.InternalServerError;
import com.chatgpt.demo.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/bot")
@Tag(name = "OpenAI ChatGpt Controller", description = "OpenAI ChatGpt API Documentation Details")
public class ChatGptController {

    private Logger log = LoggerFactory.getLogger(ChatGptController.class);

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api-url}")
    private String apiURL;

    @Autowired
    private RestTemplate restTemplate;

    @Operation(summary = "Retrieve a Product by category", description = "Get a Product object by specifying its category. The response is List of Product object with id, name,brand,description,price,inventary and attributes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Find List of Product ", content = {
                    @Content(schema = @Schema(implementation = String.class), mediaType = MediaType.ALL_VALUE)}),
            @ApiResponse(responseCode = "404", description = "Resource Not Found Exception ", content = {
                    @Content(schema = @Schema(implementation = ResourceNotFoundException.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error ", content = {
                    @Content(schema = @Schema(implementation = InternalServerError.class), mediaType = "application/json")})})
    @GetMapping("/getchat")
    public String chat(@RequestParam("prompt") String prompt) {
        ChatGPTRequestDTO request = new ChatGPTRequestDTO(model, prompt);
        log.info("Printing content: {}", request.getMessages().get(0).getContent());
        ChatGPTResponseDTO ChatGPTResponseDTO = restTemplate.postForObject(apiURL, request, ChatGPTResponseDTO.class);
        log.info("printing response:{}", ChatGPTResponseDTO.toString());
        return ChatGPTResponseDTO.toString();
//                ChatGPTResponseDTO.getChoices().get(0).getMessages().getContent();
//                ResponseEntity.ok(ChatGPTResponseDTO.getChoices().get(0).getMessages().getPrompt());
    }
}
