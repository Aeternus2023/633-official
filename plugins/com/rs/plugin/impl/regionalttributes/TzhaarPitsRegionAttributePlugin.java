package com.rs.plugin.impl.regionalttributes;

import com.rs.game.map.WorldTile;
import com.rs.plugin.listener.RegionAttributeListener;
import com.rs.plugin.wrapper.RegionAttributeSignature;

@RegionAttributeSignature(forceMultiRegions = {})
public class TzhaarPitsRegionAttributePlugin implements RegionAttributeListener {

	@Override
	public boolean withinMultiZonedBoundaries(WorldTile tile) {
		int destX = tile.getX();
		int destY = tile.getY();
		return destX >= 2376 && 5127 >= destY && destX <= 2422 && 5168 <= destY;
	}
}