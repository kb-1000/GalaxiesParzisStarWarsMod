package com.parzivail.swg.network;

import com.parzivail.swg.ship.EntityLegacyShip;
import com.parzivail.util.math.RotatedAxes;
import com.parzivail.util.math.lwjgl.Vector3f;
import com.parzivail.util.network.PMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;

/**
 * Created by colby on 12/29/2017.
 */
public class MessageFlightModelUpdate extends PMessage<MessageFlightModelUpdate>
{
	public int shipId;
	public int shipDim;
	public Vector3f angularMomentum;
	public RotatedAxes orientation;
	public float throttle;

	public MessageFlightModelUpdate()
	{
	}

	public MessageFlightModelUpdate(EntityLegacyShip ship)
	{
		angularMomentum = ship.angularMomentum;
		orientation = ship.orientation;
		throttle = ship.throttle;
		shipId = ship.getEntityId();
		shipDim = ship.dimension;
	}

	@Override
	public IMessage handleMessage(MessageContext context)
	{
		Entity ship = MinecraftServer.getServer().worldServerForDimension(shipDim).getEntityByID(shipId);

		((EntityLegacyShip)ship).angularMomentum = angularMomentum;
		((EntityLegacyShip)ship).orientation = orientation;
		((EntityLegacyShip)ship).throttle = throttle;

		return null;
	}
}
