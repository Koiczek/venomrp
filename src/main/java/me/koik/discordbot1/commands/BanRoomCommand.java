package me.koik.discordbot1.commands;

import me.koik.discordbot1.Main;
import me.koik.discordbot1.utils.CommandExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BanRoomCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        Message msg = event.getMessage();
        if(!event.getMember().getRoles().contains(event.getGuild().getRoleById("970353616588247091"))) {
            event.getChannel().sendMessage("**BŁĄD:** Nie posiadasz uprawnień do tej komendy!").queue();
            return false;
        }

        if(args.length < 4) {
            event.getChannel().sendMessage("**BŁĄD:** \n``Poprawne użycie " + Main.PREFIX + "banroom <nick> <rok/miesiac/dzien> <godzina> <powód>``").queue();
            return false;
        }

        String reason = "";
        for(int i = 3; i < args.length; i++) {
            reason += args[i] + " ";
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        EmbedBuilder eb =  new EmbedBuilder();
        eb.setColor(Color.RED);
        eb.setAuthor("BAN ROOM - Informacja o banie", null, msg.getAuthor().getAvatarUrl());
        eb.setFooter("Data nałożenia kary: " +dateFormat.format(date), "https://www.pngall.com/wp-content/uploads/5/Ban-PNG.png");
        eb.addField("Gracz", args[0], false);
        eb.addField("Czas trwania", args[1]+" "+args[2], false);
        eb.addField("Powód", reason, false);
        eb.addField("Administrator nakładający kare", event.getAuthor().getAsMention(), false);
        eb.addField(" ", "Apelacje od nałożonej kary możesz zgłosić na kanale "+event.getGuild().getTextChannelById("967070916867653643").getAsMention(), false);
        event.getChannel().sendMessageEmbeds(eb.build()).queue();
        msg.delete().queue();
        return true;
    }

    @Override
    public String name() {
        return "banroom";
    }

    @Override
    public String description() {
        return "baninfo";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
