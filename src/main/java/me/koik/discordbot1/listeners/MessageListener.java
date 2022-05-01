package me.koik.discordbot1.listeners;

import me.koik.discordbot1.Main;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot() || event.isWebhookMessage()) {
            return;
        }

        if(event.getChannelType().equals(ChannelType.TEXT)) {
            try {
                Main.executeCommand(Main.PREFIX, event);
            } catch (IOException exception) {
                event.getChannel().sendMessage("Wystąpił błąd podczas wykonywaia komendy.").queue();
                exception.printStackTrace();
            }
        }
    }
}
