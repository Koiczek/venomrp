package me.koik.discordbot1.utils;

import com.google.common.reflect.ClassPath;
import me.koik.discordbot1.Main;

import java.io.IOException;

public class ClassUtils {

    public static void registerAllCommands() throws IOException {

        ClassPath cp = ClassPath.from(ClassUtils.class.getClassLoader());
        cp.getTopLevelClassesRecursive("me.koik.discordbot1.commands").forEach(classinfo -> {
            try {
                Class c = Class.forName(classinfo.getName());
                Object obj = c.newInstance();
                if(obj instanceof CommandExecutor) {
                    CommandExecutor commandExecutor = (CommandExecutor) obj;
                    Main.COMMANDS.put(commandExecutor.name().toLowerCase(), commandExecutor);
                }
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
