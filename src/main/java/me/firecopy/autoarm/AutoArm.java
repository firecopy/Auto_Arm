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
    int[] Weapons = {276, 267, 272, 268, 283};
    ItemStack ArmorSelector;
    ItemStack temp;
    ItemStack[] InventorySlot;
    int ArmorSlotId, InventorySlotId;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            player = ((Player) sender);
        } else {
            sender.sendMessage("You must be a player you noob");
            return false;
        }

        if (commandLabel.equalsIgnoreCase("autoarm") || commandLabel.equalsIgnoreCase("arm")) {
            if (sender.hasPermission("autoarm.arm")) {
                if (sender.hasPermission("autoarm.message")){
                    player.sendMessage(ChatColor.GREEN + "You have equipped your best armor and weapon");
                }
                getPlayerArmor();
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to use Auto_Arm");
                return false;
            }

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
        autoArmor();
        autoWeapon();
    }
    int i;

    void autoArmor() {
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
            } else {
                break;
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

            player.getInventory().setItem(i, InventorySlot[i]);

            //reseting vars
            InventorySlot[i] = null;
        }



    }
    int WeaponSlotId = 0;
    ItemStack WeaponSlot;

    void autoWeapon() {
        for (i = 0; i <= 35; i++) {
            InventorySlot = player.getInventory().getContents();
            if (InventorySlot[0] != null) {
                WeaponSlotId = InventorySlot[0].getTypeId();
            }
            WeaponSlot = InventorySlot[0];
            if (InventorySlot[i] != null) {
                InventorySlotId = InventorySlot[i].getTypeId();
                if (WeaponSlotId == Weapons[0]) {
                    break;
                } else if (WeaponSlotId == Weapons[1]) {
                    diamondWeaponCheck();
                } else if (WeaponSlotId == Weapons[2]) {
                    diamondIronWeaponCheck();
                } else if (WeaponSlotId == Weapons[3]) {
                    diamondIronStoneWeaponCheck();
                } else if (WeaponSlotId == Weapons[4]) {
                    diamondIronStoneWoodWeaponCheck();
                } else {
                    allWeaponCheck();
                }
            }

            player.getInventory().setItem(0, WeaponSlot);
            InventorySlot[i] = null;
        }

    }

    private void diamondWeaponCheck() {
        if (InventorySlotId == Weapons[0]) {
            weaponSwitch();
        }
    }

    private void diamondIronWeaponCheck() {
        if (InventorySlotId == Weapons[0]) {
            weaponSwitch();
        } else if (InventorySlotId == Weapons[1]) {
            weaponSwitch();
        }
    }

    private void diamondIronStoneWeaponCheck() {
        if (InventorySlotId == Weapons[0]) {
            weaponSwitch();
        } else if (InventorySlotId == Weapons[1]) {
            weaponSwitch();
        } else if (InventorySlotId == Weapons[2]) {
            weaponSwitch();
        }
    }

    private void diamondIronStoneWoodWeaponCheck() {
        if (InventorySlotId == Weapons[0]) {
            weaponSwitch();
        } else if (InventorySlotId == Weapons[1]) {
            weaponSwitch();
        } else if (InventorySlotId == Weapons[2]) {
            weaponSwitch();
        } else if (InventorySlotId == Weapons[3]) {
            weaponSwitch();
        }
    }

    private void allWeaponCheck() {
        if (InventorySlotId == Weapons[0]) {
            weaponSwitch();
        } else if (InventorySlotId == Weapons[1]) {
            weaponSwitch();
        } else if (InventorySlotId == Weapons[2]) {
            weaponSwitch();
        } else if (InventorySlotId == Weapons[3]) {
            weaponSwitch();
        } else if (InventorySlotId == Weapons[4]) {
            weaponSwitch();
        }
    }

    private void weaponSwitch() {
        temp = WeaponSlot;
        WeaponSlot = InventorySlot[i];
        InventorySlot[i] = temp;
        player.getInventory().setItem(i, InventorySlot[i]);
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
