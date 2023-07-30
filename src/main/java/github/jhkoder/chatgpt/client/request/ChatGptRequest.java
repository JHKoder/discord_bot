package github.jhkoder.chatgpt.client.request;

import github.jhkoder.chatgpt.client.Message;
import lombok.Data;

import java.util.List;

@Data
public class ChatGptRequest {
    private String model;
    private List<Message> messages;

    public ChatGptRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }
}
