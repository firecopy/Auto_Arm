/* How it will look in Game:
 * 1. A player has "armor" and "weapons" in their inventory.
 * 2. The player will type in /autoarm or /arm
 * 3. Strongest armor and weapons will be put on the player.
 * 4. Done!
 * 
 * How it will look in Code:
 * 1.Get items in player's armor slot
 * 2.Make a sort for the armor and items in inventory.
 * 3.Done;
 * 
 * Title: AutoArm: A Bukkit Plugin
 * Author: firecopy
 */
package me.firecopy.autoarm;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.logging.Logger;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class AutoArm extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");

    public void onEnabled() {
        log.info("Autoarm has been enabled!");
    }
    Player player;
    int ArmorCounter = 0;
    int[] Diamond_Armor = {310, 311, 312, 313};
    int[] Iron_Armor = {306, 307, 308, 309};
    int[] Chain_Armor = {302, 303, 304, 305};
    int[] Gold_Armor = {314, 315, 316, 317};
    int[] Leather_Armor = {298, 299, 300, 301};
    /*
     * Reference:
     * int
     * Diamond_Helmet
     * =
     * 310;
     * int
     * Diamond_Chest
     * =
     * 311;
     * int
     * Diamond_Leggings
     * =
     * 312;
     * int
     * Diamond_Boots
     * =
     * 313;
     *
     * int
     * Iron_Helmet
     * =
     * 306;
     * int
     * Iron_Chest
     * =
     * 307;
     * int
     * Iron_Leggings
     * =
     * 308;
     * int
     * Iron_Boots
     * =
     * 309;
     *
     * int
     * Chain_Helmet
     * =
     * 303;
     * int
     * Chain_Chest
     * =
     * 304;
     * int
     * Chain_Leggings
     * =
     * 305;
     * int
     * Chain_Boots
     * =
     * 306;
     *
     * int
     * Gold_Helmet
     * =
     * 314;
     * int
     * Gold_Chest
     * =
     * 315;
     * int
     * Gold_Leggings
     * =
     * 316;
     * int
     * Gold_Boots
     * =
     * 317;
     *
     * int
     * Leather_Helmet
     * =
     * 298;
     * int
     * Leather_Chest
     * =
     * 299;
     * int
     * Leather_Leggings
     * =
     * 300;
     * int
     * Leather_Boots
     * =
     * 301;
     */
    ItemStack ArmorSelector;
    ItemStack temp;
    ItemStack[] InventorySlot;
    int ArmorSlotId, InventorySlotId;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

        player = ((Player) sender);

        if (commandLabel.equalsIgnoreCase("autoarm") || commandLabel.equalsIgnoreCase("arm")) {
            player.sendMessage(ChatColor.GREEN + "You have been equiped your best armor");
            getPlayerArmor();
            return true;
        } else {
            return false;
        }
    }

    void getPlayerArmor() {

        ArmorSelector = player.getInventory().getHelmet();
        switchPlayerArmor();
        ArmorCounter++;

        ArmorSelector = player.getInventory().getChestplate();
        switchPlayerArmor();
        ArmorCounter++;

        ArmorSelector = player.getInventory().getLeggings();
        switchPlayerArmor();
        ArmorCounter++;

        ArmorSelector = player.getInventory().getBoots();
        switchPlayerArmor();
        ArmorCounter = 0;

    }

    void switchPlayerArmor() {
        InventorySlot = player.getInventory().getContents();
        Check();
    }
    int i;

    void Check() {
        for (i = 0; i <= 35; i++) {
            InventorySlot = player.getInventory().getContents();
            if (ArmorSelector != null) {
                ArmorSlotId = ArmorSelector.getTypeId();
            }
            if (ArmorSlotId != Diamond_Armor[ArmorCounter]) {
                if (ArmorSlotId != Iron_Armor[ArmorCounter]) {
                    if (ArmorSlotId != Chain_Armor[ArmorCounter]) {
                        if (ArmorSlotId != Gold_Armor[ArmorCounter]) {
                            if (ArmorSlotId != Leather_Armor[ArmorCounter]) {
                                allArmorCheck();
                            } else {
                                diamondIronChainGoldCheck();
                            }
                        } else {
                            diamondIronChainCheck();
                        }
                    } else {
                        diamondIronCheck();
                    }
                } else {
                    diamondCheck();
                }                
            }
            
            if (ArmorCounter == 0) {
                player.getInventory().setHelmet(ArmorSelector);
            }

            if (ArmorCounter == 1) {
                player.getInventory().setChestplate(ArmorSelector);

            }

            if (ArmorCounter == 2) {
                player.getInventory().setLeggings(ArmorSelector);

            }

            if (ArmorCounter == 3) {
                player.getInventory().setBoots(ArmorSelector);

            }

            player.getInventory().setItem(0 + i, InventorySlot[i]);

            //reseting vars
            InventorySlot[i] = null;
        }



    }

    private void diamondCheck() {
        if (InventorySlot[i] != null) {
            InventorySlotId = InventorySlot[i].getTypeId();
            if (InventorySlotId == Diamond_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                player.getInventory().setItem(i, InventorySlot[i]);
            }
        }
        
    }
    
    private void diamondIronCheck() {
        if (InventorySlot[i] != null) {
            InventorySlotId = InventorySlot[i].getTypeId();
            if (InventorySlotId == Diamond_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                player.getInventory().setItem(i, InventorySlot[i]);
            } else if (InventorySlotId == Iron_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                player.getInventory().setItem(i, InventorySlot[i]);
            }
        }
    }

    private void diamondIronChainCheck() {
        if (InventorySlot[i] != null) {
            InventorySlotId = InventorySlot[i].getTypeId();
            if (InventorySlotId == Diamond_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                player.getInventory().setItem(i, InventorySlot[i]);
            } else if (InventorySlotId == Iron_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                player.getInventory().setItem(i, InventorySlot[i]);
            } else if (InventorySlotId == Chain_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                player.getInventory().setItem(i, InventorySlot[i]);
            }
        }
    }

    private void diamondIronChainGoldCheck() {
        if (InventorySlot[i] != null) {
            InventorySlotId = InventorySlot[i].getTypeId();
            if (InventorySlotId == Diamond_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                player.getInventory().setItem(i, InventorySlot[i]);
            } else if (InventorySlotId == Iron_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                player.getInventory().setItem(i, InventorySlot[i]);
            } else if (InventorySlotId == Chain_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                player.getInventory().setItem(i, InventorySlot[i]);
            } else if (InventorySlotId == Gold_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                player.getInventory().setItem(i, InventorySlot[i]);
            }
        }
    }

    private void allArmorCheck() {
        if (InventorySlot[i] != null) {
            InventorySlotId = InventorySlot[i].getTypeId();
            if (InventorySlotId == Diamond_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                
            } else if (InventorySlotId == Iron_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                
            } else if (InventorySlotId == Chain_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                
            } else if (InventorySlotId == Gold_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                
            } else if (InventorySlotId == Leather_Armor[ArmorCounter]) {
                temp = ArmorSelector;
                ArmorSelector = InventorySlot[i];
                InventorySlot[i] = temp;
                
            }
        }
    }
}
