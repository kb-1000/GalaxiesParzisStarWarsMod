package com.parzivail.util.component;

import com.parzivail.swg.StarWarsGalaxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class PBlock extends Block
{
	protected String name;

	public PBlock(String name, Material material)
	{
		super(material);
		this.name = name;
		setUnlocalizedName(name);
		setRegistryName(name);
	}

	public void registerItemModel(Item itemBlock)
	{
		StarWarsGalaxy.proxy.registerItemRenderer(itemBlock, name);
	}

	public Item createItemBlock()
	{
		return new ItemBlock(this).setRegistryName(getRegistryName());
	}
}