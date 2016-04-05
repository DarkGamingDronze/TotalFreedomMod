package me.StevenLawson.TotalFreedomMod.Commands;

import me.StevenLawson.TotalFreedomMod.Config.TFM_ConfigEntry;
import me.StevenLawson.TotalFreedomMod.Config.TFM_MainConfig;
import me.StevenLawson.TotalFreedomMod.TFM_AdminList;
import me.StevenLawson.TotalFreedomMod.TFM_BanManager;
import me.StevenLawson.TotalFreedomMod.TFM_CommandBlocker;
import me.StevenLawson.TotalFreedomMod.TFM_Log;
import me.StevenLawson.TotalFreedomMod.TFM_PermbanList;
import me.StevenLawson.TotalFreedomMod.TFM_PlayerList;
import me.StevenLawson.TotalFreedomMod.TotalFreedomMod;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandPermissions(level = AdminLevel.ALL, source = SourceType.BOTH)
@CommandParameters(description = "Shows information about ImmaFreedomMod or reloads it", usage = "/<command> [reload]")
public class Command_tfm extends TFM_Command
{
    @Override
    public boolean run(CommandSender sender, Player sender_p, Command cmd, String commandLabel, String[] args, boolean senderIsConsole)
    {
        if (args.length == 1)
        {
            if (!args[0].equals("reload"))
            {
                return false;
            }

            if (!TFM_AdminList.isSuperAdmin(sender))
            {
                playerMsg(TFM_Command.MSG_NO_PERMS);
                return true;
            }

            TFM_MainConfig.load();
            TFM_AdminList.load();
            TFM_PermbanList.load();
            TFM_PlayerList.load();
            TFM_BanManager.load();
            TFM_CommandBlocker.load();

            final String message = String.format("%s v%s reloaded.",
                    TotalFreedomMod.pluginName,
                    TotalFreedomMod.pluginVersion);

            playerMsg(message);
            TFM_Log.info(message);
            return true;
        }
        // The '§' is just there to make it easier and simpler for other developers to understand it. ~DarkGamingDronze 
        TotalFreedomMod.BuildProperties build = TotalFreedomMod.build;
        playerMsg("§4§lImmaFreedomMod");
        playerMsg("Running on " + TFM_ConfigEntry.SERVER_NAME.getString() + ".");
        playerMsg("§a§lMade by: Valencia_Orange,DarkGamingDronze, AwesomePinch, and falceso");
        playerMsg(String.format("Version "
                + ChatColor.BLUE + "%s.%s " +  ChatColor.LIGHT_PURPLE + "("
                + ChatColor.BLUE + "%s" + ")",
                TotalFreedomMod.pluginVersion,
                build.number,
                build.head), ChatColor.GOLD);
        playerMsg(String.format("Compiled "
                + ChatColor.BLUE + "%s" + ChatColor.GOLD + " by "
                + ChatColor.BLUE + "%s",
                build.date,
                build.builder), ChatColor.GOLD);
        playerMsg("Visit " + ChatColor.AQUA + "§3§lhttp://github.com/TotalFreedom/TotalFreedomMod"
                 + " §6for more information.");

        return true;
    }
}
