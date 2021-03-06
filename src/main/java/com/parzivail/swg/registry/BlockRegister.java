package com.parzivail.swg.registry;

import com.parzivail.swg.Resources;
import com.parzivail.swg.StarWarsGalaxy;
import com.parzivail.swg.block.*;
import com.parzivail.swg.block.antenna.BlockSatelliteDish;
import com.parzivail.swg.block.atmosphere.BlockSoundHothTelemetry;
import com.parzivail.swg.item.ItemPourstoneSlab;
import com.parzivail.util.block.*;
import com.parzivail.util.item.PItemBlockCardinalDecoration;
import com.parzivail.util.item.PItemBlockSubcardinalDecoration;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by colby on 12/26/2017.
 */
public class BlockRegister
{
	public static PBlock fastGrass;
	public static PBlockPillar endorLog;
	public static PBlockSand tatooineSand;

	public static PBlock oreChromium;
	public static PBlock oreTitanium;
	public static PBlock oreRubindum;
	public static PBlock oreCortosis;

	public static PBlockSlab pourstoneSlab;
	public static PBlockSlab pourstoneDoubleSlab;

	public static PBlockContainer blasterWorkbench;
	public static PBlockContainer lightsaberForge;

	public static PBlockContainer gunRack;
	public static PBlockContainer satelliteDish;

	public static void register()
	{
		register(fastGrass = new BlockFastGrass());

		register(new PBlockSand("oxidizedSand"));
		register(new PBlock("oxidizedSandStone"));
		register(new PBlockSand("irradiatedSand"));
		register(new PBlockSand("rhodocrositeSand"));
		register(new PBlock("hothStone"));
		register(new PBlock("concrete"));
		register((PBlock)new PBlock("lavaRock").setLightLevel(0.625f));
		register(new PBlock("oldBrick"));
		register(new PBlockLayer("salt", ItemRegister.saltPile));
		register(new PBlock("bespinElevator"));
		register(new PBlock("pumice"));
		register(new PBlock("crate"));
		register(new PBlock("hardpackSnow"));
		register(new PBlock("hothDoor"));
		register(new PBlock("hothSnowCut"));
		register(new PBlockConnected("hothSandbag", "hothSandbag", Material.craftedSnow));
		register(new PBlock("mud").withHarvestLevel("shovel", HarvestLevel.STONE));

		PBlock metalWhite;
		PBlock metalGray;
		PBlock metalDarkGray;
		PBlock metalBlack;
		PBlock metalCaution;
		register(metalWhite = (PBlock)new PBlock("metalWhite", "white").setStepSound(Block.soundTypeMetal));
		register(metalGray = (PBlock)new PBlock("metalGray", "gray").setStepSound(Block.soundTypeMetal));
		register((PBlock)new PBlock("metalGrayBevel", "grayBevel").setStepSound(Block.soundTypeMetal));
		register(metalDarkGray = (PBlock)new PBlock("metalDarkGray", "darkGray").setStepSound(Block.soundTypeMetal));
		register(metalBlack = (PBlock)new PBlock("metalBlack", "black").setStepSound(Block.soundTypeMetal));
		register(metalCaution = (PBlock)new PBlock("metalCaution", "caution").setStepSound(Block.soundTypeMetal));

		register(new PBlockStairs("metalWhiteStairs", metalWhite, 0));
		register(new PBlockStairs("metalGrayStairs", metalGray, 0));
		register(new PBlockStairs("metalDarkGrayStairs", metalDarkGray, 0));
		register(new PBlockStairs("metalBlackStairs", metalBlack, 0));
		register(new PBlockStairs("metalCautionStairs", metalCaution, 0));

		register((PBlockConnected)new PBlockConnected("grateLightGray", "grateLightGray", "grate", Material.iron).setStepSound(Block.soundTypeMetal));
		register((PBlockConnected)new PBlockConnected("grateDarkGray", "grateDarkGray", "grate", Material.iron).setStepSound(Block.soundTypeMetal));
		register((PBlockConnected)new PBlockConnected("grateBlack", "grateBlack", "grate", Material.iron).setStepSound(Block.soundTypeMetal));

		register(new BlockGrayLight());
		register(new BlockDarkGrayLight());
		register(new BlockGrayLightVertical());
		//register(new BlockDarkGrayLightVertical());

		register(new PBlock("templeStone"));
		register(new PBlock("templeStoneBrick"));
		register(new PBlock("templeStoneBrickFancy"));
		register(new PBlock("templeStoneSlab"));
		register(new PBlock("templeStoneSlabTop"));
		register(new PBlock("templeStoneSlabTopDark"));

		register(endorLog = new BlockEndorLog());
		register(new BlockEndorFallenLog());
		register(new BlockPalmLog());

		register(new PBlock("labWall"));

		register(oreChromium = new PBlock("oreChromium"));
		OreDictionary.registerOre("oreChromium", oreChromium);
		register(oreTitanium = new PBlock("oreTitanium"));
		OreDictionary.registerOre("oreTitanium", oreTitanium);
		register(oreRubindum = new PBlock("oreRubindum"));
		OreDictionary.registerOre("oreRubindum", oreRubindum);
		register(oreCortosis = new PBlock("oreCortosis"));
		OreDictionary.registerOre("oreCortosis", oreCortosis);

		registerDecoration(new BlockChair("chairBasic"));

		register(tatooineSand = new PBlockSand("tatooineSand"));
		PBlock pourstone;
		register(pourstone = new PBlock("pourstone"));
		register(new PBlockStairs("pourstoneStairs", pourstone, 0));
		pourstoneSlab = new PBlockSlab("pourstoneSlab", pourstone, 0, false);
		pourstoneDoubleSlab = new PBlockSlab("pourstoneDoubleSlab", pourstone, 0, true);
		registerWithItem(pourstoneSlab, ItemPourstoneSlab.class);
		registerWithItem(pourstoneDoubleSlab, ItemPourstoneSlab.class);

		// Normal tile entities
		registerWithTile(blasterWorkbench = new BlockBlasterWorkbench().withPlaceholderTexture());
		registerWithTile(lightsaberForge = new BlockLightsaberForge().withPlaceholderTexture());
		registerWithTile(new BlockSabaccTable().withPlaceholderTexture());
		registerWithTile(new BlockSoundHothTelemetry().withPlaceholderTexture());
		registerWithTile(new BlockNpcSpawner());

		// Tile entities that need to keep a TESR
		registerWithTile(gunRack = new BlockGunRack());
		registerWithTile(satelliteDish = new BlockSatelliteDish());

		// Specialty decoration blocks
		registerWithModel(new BlockLadder());

		// Decoration blocks
		registerDecoration("blockPanelHoth").setTransparent().setBlockBounds(1, 2);
		registerDecoration("blockConsoleHoth1").setBlockBounds(1, 2);
		registerDecoration("blockConsoleHoth2").setBlockBounds(1, 2);
		registerDecoration("blockConsoleHoth3").setBlockBounds(1, 2);
		registerDecoration("medicalConsole").setBlockBounds(0.8f, 1);
		registerDecoration("medicalConsole2").setBlockBounds(1, 2);

		registerDecoration("crate1").setBlockBounds(0.9f, 1);
		registerDecoration("crateHoth1");
		registerDecoration("crateHoth2");
		registerDecoration("crateMosEspa").setBlockBounds(0.8f, 1);
		registerDecoration("crateVilla").setBlockBounds(0.5f, 1);

		registerDecoration("wallIndicator").setPassible().setBlockBounds(0.5f, 0.25f, 0.5f);
		registerDecoration("wallIndicatorCluster").setPassible().setBlockBounds(0.5f, 0.25f, 0.5f);
		registerDecoration("wallControlPanel").setPassible().setBlockBounds(0.5f, 0.25f, 0.5f);
		registerDecoration("wallControlPanelTall").setPassible().setBlockBounds(0.5f, 0.25f, 0.5f);

		registerDecoration("floorLight").setBlockBounds(0.5f, 0.25f).setLightLevel(1);
		registerDecoration("floorLight2").setBlockBounds(0.7f, 0.4f).setLightLevel(1);
		registerDecoration("hothCeilingLight").setPassible().setBlockBounds(0.25f, 0.85f, 0.15f).setLightLevel(1);
		registerDecoration("hothCeilingLight2").setLightLevel(1);
		registerDecoration("angledWallLight").setPassible().setBlockBounds(0.5f, 0.25f, 0.5f).setLightLevel(1);
		registerDecoration("floorLightDome").setBlockBounds(0.5f, 0.5f).setTransparent().setLightLevel(1);

		registerDecoration("antennaThin").setBlockBounds(0.125f, 2);
		registerDecoration("airTank").setBlockBounds(0.8f, 2);

		registerDecoration("moistureVaporator").setBlockBounds(1, 2);
		registerDecoration("moistureVaporator2").setBlockBounds(1, 2);
		registerDecoration("spokedMachine");
		registerDecoration("tubeMachine").setBlockBounds(1, 2);

		registerDecoration("pipeSmallBent").setBlockBounds(0.5f, 0.5f);
		registerDecoration("quadVentPipe").setBlockBounds(1, 2);
		registerDecoration("tallVentedPipe").setBlockBounds(0.5f, 0.5f);
		registerDecoration("wallPipeLarge").setBlockBounds(0.5f, 0.5f);

		//registerDecoration("clampedCable");
		registerDecoration("yavinGenerator");

		registerConnectingDecoration("groundCable").setBlockBounds(1, 0.2f);

		registerStaticDecoration("darkGrayLightVertical");
		registerStaticDecoration("darkGrayLightVertical2");
	}

	private static PDecorativeBlock registerDecoration(String name)
	{
		PDecorativeBlock block = new PDecorativeBlock(name);
		StarWarsGalaxy.proxy.registerBlockModel(block);
		GameRegistry.registerBlock(block, PItemBlockSubcardinalDecoration.class, block.name);
		return block;
	}

	private static PDecorativeBlock registerDecoration(PDecorativeBlock block)
	{
		StarWarsGalaxy.proxy.registerBlockModel(block);
		GameRegistry.registerBlock(block, PItemBlockSubcardinalDecoration.class, block.name);
		return block;
	}

	private static PDecorativeBlock registerCardinalDecoration(String name)
	{
		PDecorativeBlock block = new PDecorativeBlock(name);
		StarWarsGalaxy.proxy.registerBlockModel(block);
		GameRegistry.registerBlock(block, PItemBlockCardinalDecoration.class, block.name);
		return block;
	}

	private static PDecorativeBlock registerStaticDecoration(String name)
	{
		PDecorativeBlock block = new PDecorativeBlock(name);
		StarWarsGalaxy.proxy.registerBlockModel(block);
		GameRegistry.registerBlock(block, PItemBlockCardinalDecoration.class, block.name);
		return block;
	}

	private static PDecorativeBlock registerConnectingDecoration(String name, Block... connectsTo)
	{
		PDecorativeBlock block = new PDecorativeBlock(name);
		block.setConnectsTo(connectsTo);
		StarWarsGalaxy.proxy.registerBlockModel(block);
		GameRegistry.registerBlock(block, block.name);
		return block;
	}

	private static <T extends Block & INameProvider> void registerWithModel(T item)
	{
		StarWarsGalaxy.proxy.registerBlockModel(item);
		GameRegistry.registerBlock(item, PItemBlockSubcardinalDecoration.class, item.getName());
	}

	private static <T extends Block & INameProvider> void registerWithItem(T item, Class<? extends ItemBlock> clazz)
	{
		GameRegistry.registerBlock(item, clazz, item.getName());
	}

	private static <T extends Block & INameProvider> void register(T item)
	{
		GameRegistry.registerBlock(item, item.getName());
	}

	private static <T extends BlockContainer & INameProvider> void registerWithTile(T item)
	{
		GameRegistry.registerBlock(item, item.getName());
		GameRegistry.registerTileEntity(item.createNewTileEntity(null, 0).getClass(), Resources.tileDot(item.getName()));
	}
}
