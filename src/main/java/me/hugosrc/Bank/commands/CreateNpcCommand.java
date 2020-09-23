package me.hugosrc.Bank.commands;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class CreateNpcCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        Player player = (Player) sender;

        if (!player.hasPermission("server.createnpc.bank")) {
            player.sendMessage("§cVocê não possui permissão para fazer isso.");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUse /bank set");
            return true;
        }

        if (args[0].equals("set")) {
            NPC npc = CitizensAPI.getNPCRegistry().createNPC(EntityType.PLAYER, "§6§lBanqueiro");
            npc.spawn(player.getLocation());
            player.sendMessage("§aBanqueiro adicionado com sucesso!");
        }

        return false;
    }
}
