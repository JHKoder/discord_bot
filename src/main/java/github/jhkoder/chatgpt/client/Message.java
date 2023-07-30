package github.jhkoder.chatgpt.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@Getter
@Setter
@JsonAutoDetect(getterVisibility = ANY)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message{
    private String role;
    private String content;
}