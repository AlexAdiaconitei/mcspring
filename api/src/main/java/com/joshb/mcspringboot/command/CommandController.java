package com.joshb.mcspringboot.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Field;

import lombok.SneakyThrows;

@Controller
public class CommandController {
    
    private CommandMap commandMap;
    
    public CommandController() {
        this.commandMap = getCommandMap();
    }
    
    @SneakyThrows
    private CommandMap getCommandMap() {
        Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
        bukkitCommandMap.setAccessible(true);
        return (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
    }
    
    public void registerCommand(Command command) {
        commandMap.register(command.getLabel(), command);
    }
}