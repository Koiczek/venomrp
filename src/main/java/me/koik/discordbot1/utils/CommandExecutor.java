package me.koik.discordbot1.utils;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;

public interface CommandExecutor {

    boolean execute(String[] args, MessageReceivedEvent event) throws IOException;

    String name();

    String description();

    String[] aliases();
}
