package me.hugosrc.Bank.util.builders;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder {

    protected ItemStack itemStack;
    protected ItemMeta itemMeta;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int quantity) {
        this.itemStack = new ItemStack(material, quantity);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack item) {
        this.itemStack = item.clone();
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(Material material, int amount, short damage) {
        this.itemStack = new ItemStack(material, amount, damage);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder name(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder lore(List<String> lines) {
        this.itemMeta.setLore(lines);
        return this;
    }

    public ItemBuilder addLoreLine(String line) {
        List<String> lores = new ArrayList<>();

        if (this.itemMeta.hasLore()) {
            lores = this.itemMeta.getLore();
        }

        lores.add(line);

        itemMeta.setLore(lores);

        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int strength) {
        this.itemMeta.addEnchant(enchantment, strength, true);
        return this;
    }

    public ItemBuilder hideAllFlags() {
        this.itemMeta.addItemFlags(ItemFlag.values());
        return this;
    }

    public ItemBuilder hideFlag(ItemFlag itemFlag) {
        this.itemMeta.addItemFlags(itemFlag);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);

        return this.itemStack;
    }
}
