package com.parzivail.swg.handler;

import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameData.GameDataSnapshot;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileHandler
{
	public static void saveNbtMappings(World world)
	{
		File dir = world.getSaveHandler().getWorldDirectory();
		File file = new File(dir, "cdfidmap.nbt");

		NBTTagCompound compound = new NBTTagCompound();
		NBTTagList blockMap = new NBTTagList();

		GameDataSnapshot gameDataSnapshot = GameData.buildItemDataList();

		for (String key : gameDataSnapshot.idMap.keySet())
		{
			NBTTagCompound c = new NBTTagCompound();
			c.setString("k", key.substring(1)); // substring because GameDataSnapshot adds a discriminator or something dumb
			c.setInteger("v", gameDataSnapshot.idMap.get(key));
			blockMap.appendTag(c);
		}
		compound.setTag("map", blockMap);

		try
		{
			OutputStream outputStream = new FileOutputStream(file);
			CompressedStreamTools.writeCompressed(compound, outputStream);
			outputStream.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
