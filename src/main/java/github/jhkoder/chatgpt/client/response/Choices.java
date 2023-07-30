package github.jhkoder.chatgpt.client.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import github.jhkoder.chatgpt.client.Message;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@Getter
@Setter
@JsonAutoDetect(getterVisibility = ANY)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Choices {
    @JsonProperty("finish_reason")
    private String finishReason;
    private int index;
    private Message message;
}