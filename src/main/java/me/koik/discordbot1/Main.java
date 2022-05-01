package me.koik.discordbot1;

import me.koik.discordbot1.listeners.MessageListener;
import me.koik.discordbot1.utils.ClassUtils;
import me.koik.discordbot1.utils.CommandExecutor;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class Main {
    private static String token = "OTcwMzM5OTM5ODE0MzQyNzQ2.Ym6hfQ.woncAskESLbwXklI0sZKd6wirvQ";

    public static HashMap<String, CommandExecutor> COMMANDS = new HashMap<>();
    public static HashMap<String, CommandExecutor> ALIASES = new HashMap<>();

    public static String PREFIX = "*";

    public static void main(String[] args) throws LoginException, IOException {
        JDABuilder builder = JDABuilder.createDefault(token)
                .setChunkingFilter(ChunkingFilter.ALL)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .enableIntents(GatewayIntent.GUILD_MEMBERS)
                .enableIntents(GatewayIntent.GUILD_PRESENCES);

        builder.addEventListeners(new MessageListener());
        builder.addEventListeners(new ListenerAdapter() {
            @Override
            public void onReady(@NotNull ReadyEvent event) {
                event.getJDA().getPresence().setActivity(Activity.of(Activity.ActivityType.STREAMING, "VenomRP"));
                System.out.println("Uruchomiono bota!");
            }
        });

        ClassUtils.registerAllCommands();

        for(CommandExecutor commandExecutor : COMMANDS.values()) {
            if(commandExecutor.aliases() != null) {
                for(String alias : commandExecutor.aliases()) {
                    ALIASES.put(alias.toLowerCase(), commandExecutor);
                }
            }
        }

        builder.build();
    }

    public static void executeCommand(String prefix, MessageReceivedEvent event) throws IOException {

        String chatMessage = event.getMessage().getContentRaw();
        String[] splitCommand = chatMessage.split(" ");
        String command = splitCommand[0].replaceFirst("[" + prefix + "]", "");
        String commandArgs = event.getMessage().getContentRaw().replace(prefix + command, "");
        commandArgs = commandArgs.replaceFirst(" ", "");
        String[] args = commandArgs.split(" ");

        if (args.length == 1) {
            if (args[0].isEmpty() || args[0].equals(" ") || args[0].equals(prefix + command) || args[0].equals(prefix)) {
                args = new String[0];
            }
        }
        if(COMMANDS.get(command.toLowerCase()) != null) {
            if(!COMMANDS.get(command.toLowerCase()).execute(args, event)) {
                return;
            }
        }
        if(ALIASES.get(command.toLowerCase()) != null) {
            if(!ALIASES.get(command.toLowerCase()).execute(args, event)) {
                return;
            }
        }
    }
}
