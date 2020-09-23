package me.hugosrc.Bank;

import lombok.Getter;
import me.hugosrc.Bank.commands.CreateNpcCommand;
import me.hugosrc.Bank.database.MySQLDatabase;
import me.hugosrc.Bank.database.MySQLDatabase.Credentials;
import me.hugosrc.Bank.entities.managers.LevelManager;
import me.hugosrc.Bank.listeners.NPCClick;
import me.hugosrc.Bank.listeners.PlayerQuit;
import me.hugosrc.Bank.tasks.BankAutoSaveTask;
import me.hugosrc.Bank.tasks.BankIncomeTask;
import me.hugosrc.Bank.util.inventory.MenuListener;
import me.hugosrc.Bank.economy.Economy;
import me.hugosrc.Bank.economy.EconomyProvider;
import me.hugosrc.Bank.economy.VaultEconomyProvider;
import me.hugosrc.Bank.entities.objects.Bank;
import me.hugosrc.Bank.listeners.PlayerJoin;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@Getter
public class BankPlugin extends JavaPlugin {

    public static BankPlugin instance;
    public static Logger logger;

    private final EconomyProvider economyProvider = new VaultEconomyProvider();
    private Economy economy;
    private LevelManager levelManager;
    private MySQLDatabase mySQLDatabase;
    private Bank bank;

    private final Credentials credentials = Credentials
            .builder()
            .ip(getConfig().getString("database.host"))
            .port(getConfig().getInt("database.port"))
            .user(getConfig().getString("database.user"))
            .password(getConfig().getString("database.password"))
            .database(getConfig().getString("database.name"))
            .build();

    @Override
    public void onEnable() {
        loadConfig();
        loadInstances();

        registerCommands();
    }

    @Override
    public void onDisable() {
        bank.saveAll();
        mySQLDatabase.disconnect();
    }

    private void loadInstances() {
        instance = this;
        logger = getLogger();

        levelManager = new LevelManager();
        economy = economyProvider.getEconomy();
        mySQLDatabase = new MySQLDatabase(credentials);
        bank = new Bank();

        registerListeners(
                new MenuListener(),
                new PlayerJoin(),
                new PlayerQuit(),
                new NPCClick());

        new BankAutoSaveTask().start();
        new BankIncomeTask().start();
    }

    private void loadConfig() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    private void registerCommands() {
        getCommand("bank").setExecutor(new CreateNpcCommand());
    }

    public static BankPlugin getInstance() {
        return instance;
    }
}
