package github.jhkoder.chatgpt.client;

import github.jhkoder.chatgpt.client.request.ChatGptRequest;
import github.jhkoder.chatgpt.client.response.ChatGptResponse;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.netty.handler.codec.http.HttpHeaders.Values.APPLICATION_JSON;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class ClientTest {

    private String token ="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";

    private WebClient client;

    @BeforeEach
    public void setup(){
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100_000)
                .responseTimeout(Duration.ofMillis(100_000))
                .doOnConnected(conn -> {
                    conn.addHandlerLast(new ReadTimeoutHandler(100_000, TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(100_000, TimeUnit.MILLISECONDS));
                });
        client = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .defaultHeader(CONTENT_TYPE,APPLICATION_JSON)
                .defaultHeader(ACCEPT,APPLICATION_JSON)
                .build();
    }

    @Test
    void s(){
        String uri ="https://api.openai.com/v1/chat/completions";

        String message = "hello";
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("user",message));
        ChatGptRequest request = new ChatGptRequest("gpt-3.5-turbo",messages);
        ChatGptResponse response = client
                .post()
                .uri(uri)
                .bodyValue(request)
                .acceptCharset(UTF_8)
                .header("Authorization","Bearer "+token)
                .retrieve()
                .bodyToMono(ChatGptResponse.class)
                .block();//        System.out.println(response.toString());
    }
}
