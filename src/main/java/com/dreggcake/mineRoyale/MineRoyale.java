package com.dreggcake.mineRoyale;

import co.aikar.commands.PaperCommandManager;
import com.dreggcake.mineRoyale.commands.SpawnCommand;
import com.dreggcake.mineRoyale.core.Arena;
import com.dreggcake.mineRoyale.core.UnitManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineRoyale extends JavaPlugin {
    private PaperCommandManager commandManager;
    private UnitManager unitManager;

    @Override
    public void onEnable() {
        registerCommands();
        unitManager = new UnitManager();
        Arena arena = new Arena(this);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            unitManager.tick();
        }, 1L, 1L);

    }

    public UnitManager getUnitManager() {
        return unitManager;
    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);
        commandManager.enableUnstableAPI("help");

        commandManager.registerCommand(new SpawnCommand(this));

        commandManager.setDefaultExceptionHandler((command, registeredCommand, sender, args, t) -> {
            getLogger().warning("Error occurred while executing command " + command.getName());
            return false; // mark as unhandeled, sender will see default message
        });
    }
}
