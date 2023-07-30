package github.jhkoder.discord.event;

import github.jhkoder.chatgpt.client.Message;
import github.jhkoder.chatgpt.client.response.ChatGptResponse;
import github.jhkoder.chatgpt.service.ChatGptClientService;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@RequiredArgsConstructor
public class DiscordListener extends ListenerAdapter {
    private final String discordName = "Kang";
    private final ChatGptClientService chatGptClientService;

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        TextChannel channel = event.getChannel().asTextChannel();
        System.out.println(event.getMessage());

        String message = event.getMessage().getContentDisplay();
        String[] messages = message.split(" ");
        if (messages[0].equalsIgnoreCase(discordName)) {
            ChatGptResponse response = chatGptClientService.textMessage(new Message("user",message.substring(discordName.length())));
            channel.sendMessage(response.getChoices().get(0).getMessage().getContent()).queue();
        }
    }

}
