package com.rs.game.dialogue.impl;

import com.rs.game.dialogue.DialogueEventListener;
import com.rs.game.player.Player;

import skills.SkillsDialogue;
import skills.crafting.SpinningWheel;
import skills.crafting.SpinningWheel.SpinningData;

public class SpinningDialogue extends DialogueEventListener {

	private SpinningData[] data;

	public SpinningDialogue(Player player, SpinningData[] data) {
		super(player);
		this.data = data;
	}

	@Override
	public void start() {
		 int[] ids = new int[SpinningData.values().length];
	        for (int i = 0; i < ids.length; i++)
	            ids[i] = SpinningData.values()[i].item.getId();
		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.SELECT, 28, ids, null, true);
	}

	@Override
	public void listenToDialogueEvent(int button) {
		new SpinningWheel(player, data[SkillsDialogue.getItemSlot(button)], 28).start();
	}
}