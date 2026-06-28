package com.dreggcake.mineRoyale.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.Subcommand;
import com.dreggcake.mineRoyale.MineRoyale;
import com.dreggcake.mineRoyale.Team;
import com.dreggcake.mineRoyale.Unit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

@CommandAlias("cr|clashroyale")
public class SpawnCommand extends BaseCommand {

    MineRoyale plugin;

    public SpawnCommand(MineRoyale plugin) {
        this.plugin = plugin;
    }

    @Default
    @Description("Main command")
    public void onDefault(CommandSender sender) {
        sender.sendMessage("No subcommand specified!");
    }

    @Subcommand("knight")
    @Description("Spawn a knight")
    public void onSpawnKnight(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You need to be a player to use that!");
            return;
        }
        Player player = (Player) sender;
        Location location = player.getLocation();
        World world = player.getWorld();
        Zombie zombie = world.spawn(location, Zombie.class);

        Unit knight = new Unit(zombie, Team.BLUE, plugin);
        plugin.getUnitManager().add(knight);

    }

}
