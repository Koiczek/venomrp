package me.koik.discordbot1.commands;

import me.koik.discordbot1.Main;
import me.koik.discordbot1.utils.CommandExecutor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Random;

public class RestartCommand implements CommandExecutor {

    @Override
    public boolean execute(String[] args, MessageReceivedEvent event) {
        Message msg = event.getMessage();
        if(!event.getMember().getRoles().contains(event.getGuild().getRoleById("970353616588247091"))) {
            event.getChannel().sendMessage("**BŁĄD:** Nie posiadasz uprawnień do tej komendy!").queue();
            return false;
        }

        EmbedBuilder eb =  new EmbedBuilder();
        eb.setColor(Color.MAGENTA);
        eb.setDescription("Serwer w przeciągu najbliższych **15 minut** zostanie zrestartowany!\nProsimy o opuszczenie wyspy i cierpliwość :D\n```Naciśnij F8 i połącz się za pomocą: connect venomrp.com, bądź znajdź nas na liście serwerów FiveM.```");
        event.getChannel().sendMessageEmbeds(eb.build()).queue();
        msg.delete().queue();
        return true;
    }

    @Override
    public String name() {
        return "restart";
    }

    @Override
    public String description() {
        return "restart serwera";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
