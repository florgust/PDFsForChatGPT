package com.example.demo.infrastructure.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.domain.model.AnswerResponseModel;
import com.example.demo.domain.model.QuestionModel;
import com.example.demo.domain.port.SearchAnswerAPIChatGPT;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SearchAnswerAPIChatGPTImpl implements SearchAnswerAPIChatGPT {

    @Autowired
    private final Environment env;

    private final RestTemplate restTemplate;
    private final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions"; // Endpoint da API

    @Value("${api_key}")
    private String API_KEY;

    @Override
    @SneakyThrows
    public AnswerResponseModel searchAnswerAPIChatGPT(QuestionModel question) {
        String prompt = env.getProperty(question.getTypeOfPDFs().getValue());
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + API_KEY);
        headers.set("Content-Type", "application/json");

        String requestBody = "{\n" +
                "  \"model\": \"gpt-4o-mini\",\n" +
                "  \"messages\": [\n" +
                "    {\"role\": \"user\", \"content\": \"" + String.format("%s %s", prompt, question.getQuestion())
                + "\"}\n" +
                "  ]\n" +
                "}";

        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.exchange(
                OPENAI_API_URL,
                HttpMethod.POST,
                entity,
                String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        String result = root.path("choices").get(0).path("message").path("content").asText();

        return AnswerResponseModel.builder().answer(result).build();
    }

}
