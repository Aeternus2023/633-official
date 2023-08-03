package com.rs.plugin.impl.interfaces;

import com.rs.game.player.Player;
import com.rs.plugin.listener.RSInterfaceListener;
import com.rs.plugin.wrapper.RSInterfaceSignature;
import com.rs.utilities.IntegerInputAction;

import skills.crafting.SilverItemCasting;

@RSInterfaceSignature(interfaceId = { 438 })
public class SilverCastingInterfacePlugin extends RSInterfaceListener {

	@Override
	public void execute(Player player, int interfaceId, int componentId, int packetId, byte slotId, int slotId2) {
		if (packetId == 9) {
			player.getPackets().sendInputIntegerScript("How many would you like to make?", new IntegerInputAction() {
				@Override
				public void handle(int input) {
					new SilverItemCasting(player, SilverItemCasting.getType(componentId), packetId, input).start();
				}
			});
		} else
			new SilverItemCasting(player, SilverItemCasting.getType(componentId), packetId, 0).start();
	}
}