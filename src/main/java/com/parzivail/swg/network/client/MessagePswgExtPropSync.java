package com.parzivail.swg.network.client;

import com.parzivail.swg.StarWarsGalaxy;
import com.parzivail.swg.player.PswgExtProp;
import com.parzivail.util.network.PMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Created by colby on 12/29/2017.
 */
public class MessagePswgExtPropSync extends PMessage<MessagePswgExtPropSync>
{
	public int entityId;
	public NBTTagCompound ieep;

	public MessagePswgExtPropSync()
	{

	}

	public MessagePswgExtPropSync(EntityPlayer player, PswgExtProp props)
	{
		entityId = player.getEntityId();
		ieep = new NBTTagCompound();
		props.saveNBTData(ieep);
	}

	@Override
	public IMessage handleMessage(MessageContext context)
	{
		StarWarsGalaxy.proxy.handlePlayerDataSync(entityId, ieep);
		return null;
	}
}
