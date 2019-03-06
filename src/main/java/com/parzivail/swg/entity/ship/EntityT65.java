package com.parzivail.swg.entity.ship;

import net.minecraft.world.World;

public class EntityT65 extends EntityShip
{
	private static final ShipData shipData;

	static
	{
		shipData = new ShipData();
	}

	public EntityT65(World worldIn)
	{
		super(worldIn);
	}

	@Override
	public ShipData getData()
	{
		return shipData;
	}
}
