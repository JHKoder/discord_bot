package github.jhkoder.chatgpt.service;

import github.jhkoder.chatgpt.client.Message;
import github.jhkoder.chatgpt.client.request.ChatGptRequest;
import github.jhkoder.chatgpt.client.response.ChatGptResponse;
import github.jhkoder.chatgpt.config.WebClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@Configuration
@RequiredArgsConstructor
public class ChatGptClientService {

    private final WebClientConfig client;

    @Value("${chat-gpt.token-3.5}")
    private String token;
    @Value("${chat-gpt.uri}")
    private String uri;
    @Value("${chat-gpt.model}")
    private String model;


    public ChatGptResponse textMessage(Message message) {
        List<Message> messages = new ArrayList<>();
        messages.add(message);
        ChatGptRequest request = new ChatGptRequest(model, messages);
        System.out.println("----");
        System.out.println(token);
        System.out.println(uri);
        System.out.println(model);
        System.out.println(message);
        return client.webClient()
                .post()
                .uri(uri)
                .bodyValue(request)
                .acceptCharset(UTF_8)
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(ChatGptResponse.class)
                .block();
    }
}
