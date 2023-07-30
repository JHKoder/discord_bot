package github.jhkoder.chatgpt.client.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@Getter
@Setter
@JsonAutoDetect(getterVisibility = ANY)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usage{
    @JsonProperty("completion_tokens")
    private int completionTokens;
    @JsonProperty("prompt_tokens")
    private int promptTokens;
    @JsonProperty("total_tokens")
    private int totalTokens;
}
