package austeretony.oxygen_dailyrewards.client.command;

import java.util.concurrent.TimeUnit;

import austeretony.oxygen_core.client.api.OxygenHelperClient;
import austeretony.oxygen_core.common.command.ArgumentExecutor;
import austeretony.oxygen_dailyrewards.client.RewardsMenuManager;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class DailyRewardsArgumentClient implements ArgumentExecutor {

    @Override
    public String getName() {
        return "dailyrewards";
    }

    @Override
    public void process(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1)
            OxygenHelperClient.scheduleTask(RewardsMenuManager::openDailyRewardsMenuDelegated, 100L, TimeUnit.MILLISECONDS);
    }
}
