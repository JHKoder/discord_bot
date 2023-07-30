package github.jhkoder.chatgpt.client.response;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@Getter
@Setter
@JsonAutoDetect(getterVisibility = ANY)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatGptResponse {
    private String id;
    private String object;
    private int created;
    private String model;
    private List<Choices> choices;
    private Usage usage;
}
