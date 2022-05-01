package me.koik.discordbot1.commands;

import me.koik.discordbot1.Main;
import me.koik.discordbot1.utils.CommandExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PollCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        Message msg = event.getMessage();
        if(!event.getMember().getRoles().contains(event.getGuild().getRoleById("970353616588247091"))) {
            event.getChannel().sendMessage("**BŁĄD:** Nie posiadasz uprawnień do tej komendy!").queue();
            return false;
        }

        if(args.length < 1) {
            event.getChannel().sendMessage("**BŁĄD:** \n``Poprawne użycie " + Main.PREFIX + "ankieta <pytanie>``").queue();
            return false;
        }

        String question = "";
        for(int i = 0; i < args.length; i++) {
            question += args[i] + " ";
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        EmbedBuilder eb =  new EmbedBuilder();
        eb.setColor(Color.MAGENTA);
        eb.setAuthor("Ankieta", null, msg.getAuthor().getAvatarUrl());
        eb.setDescription("```"+question+"```");
        eb.setFooter("Zagłosuj w ankiecie reagując na wiadomość!");
        event.getTextChannel().sendMessageEmbeds(eb.build()).queue(message -> {
            message.addReaction("\uD83D\uDC4D").queue();
            message.addReaction("\uD83D\uDC4E").queue();
        });
        msg.delete().queue();
        return true;
    }

    @Override
    public String name() {
        return "ankieta";
    }

    @Override
    public String description() {
        return "ankieta";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
