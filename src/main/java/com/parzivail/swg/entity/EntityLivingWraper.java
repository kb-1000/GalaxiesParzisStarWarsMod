package com.parzivail.swg.entity;

import com.parzivail.swg.ship.MultipartFlightModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class EntityLivingWraper extends EntityLivingBase
{
	public EntityLivingWraper(MultipartFlightModel ship)
	{
		super(ship.worldObj);
		copyLocationAndAnglesFrom(ship);
	}

	@Override
	public ItemStack getHeldItem()
	{
		return null;
	}

	@Override
	public ItemStack getEquipmentInSlot(int p_71124_1_)
	{
		return null;
	}

	@Override
	public void setCurrentItemOrArmor(int slotIn, ItemStack itemStackIn)
	{

	}

	@Override
	public ItemStack[] getInventory()
	{
		return new ItemStack[0];
	}
}
