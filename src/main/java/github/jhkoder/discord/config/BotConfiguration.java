package github.jhkoder.discord.config;


import github.jhkoder.chatgpt.service.ChatGptClientService;
import github.jhkoder.discord.event.DiscordListener;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class BotConfiguration {


    private static final Logger log = LoggerFactory.getLogger( BotConfiguration.class );

    @Value("${discord.token}")
    private String token;

    private final ChatGptClientService chatGptClientService;

    @Bean
    public JDA gatewayDiscordClient() {
        return JDABuilder.createDefault(token)
                .setActivity(Activity.playing("서버 구성중"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(new DiscordListener(chatGptClientService))
                .build();
    }
}
