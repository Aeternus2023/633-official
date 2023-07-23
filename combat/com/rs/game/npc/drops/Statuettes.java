package com.rs.game.npc.drops;

import java.util.EnumSet;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utilities.RandomUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Statuettes {

    BROKEN_STATUE_HEADDRESS(14892, 5000),
    THIRD_AGE_CARAFE(14891, 10000),
    BRONZED_DRAGON_CLAW(14890, 20000),
    ANCIENT_PSALTERY_BRIDGE(14889, 30000),
    SARADOMIN_AMPHORA(14888, 40000),
    BANDOS_SCRIMSHAW(14887, 50000),
    SARADOMIN_CARVING(14886, 75000),
    ZAMORAK_MEDALLION(14885, 100000),
    ARMADYL_TOTEM(14884, 150000),
    GUTHIXIAN_BRAZIER(14883, 200000),
    RUBY_CHALICE(14882, 250000),
    BANDOS_STATUETTE(14881, 300000),
    SARADOMIN_STATUETTE(14880, 400000),
    ZAMORAK_STATUETTE(14879, 500000),
    ARMADYL_STATUETTE(14878, 750000),
    SEREN_STATUETTE(14877, 1000000),
    ANCIENT_STATUETTE(14876, 5000000);

	@Getter
    private int itemId, value;
	
    public int getNotedId() {
        return itemId + 17;
    }
    /**
     * Caches our enum values.
     */
    private static final ImmutableSet<Statuettes> VALUES = Sets.immutableEnumSet(EnumSet.allOf(Statuettes.class));
    
    public static int getTotalValue(Player player) {
        int value = 0;
        for (Item item : player.getInventory().getItems().getItems()) {
            if (item == null)
                continue;
            Statuettes statuette = null;
            for (Statuettes stat : Statuettes.values()) {
                if (stat.getItemId() == item.getId() || stat.getNotedId() == item.getId()) {
                    statuette = stat;
                }
            }
            if (statuette != null) {
                value += statuette.getValue() * item.getAmount();
            }
        }
        return value;
    }

    public static int shuffleStatues() {
        Statuettes statue = RandomUtils.random(VALUES.asList());
        return statue.getItemId();
    }

    public static void exchangeStatuettes(Player player) {
        int total = getTotalValue(player);
        if (total > 0) {
            for (Item item : player.getInventory().getItems().getItems()) {
                if (item == null)
                    continue;
                Statuettes statuette = null;
                for (Statuettes stat : Statuettes.values()) {
                    if (stat.getItemId() == item.getId() || stat.getNotedId() == item.getId()) {
                        statuette = stat;
                    }
                }
                if (statuette != null) {
                    player.getInventory().deleteItem(item.getId(), item.getAmount());
                    player.getInventory().addItem(995, statuette.getValue() * item.getAmount());
                    player.getDetails().getStatistics().addStatistic("PVP_Statues_Exchanged");
                }
            }
        } else {
            player.getPackets().sendGameMessage("You don't have any statuettes to hand in.");
        }
    }
}