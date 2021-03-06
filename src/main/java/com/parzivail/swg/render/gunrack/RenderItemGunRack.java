package com.parzivail.swg.render.gunrack;

import com.parzivail.swg.proxy.Client;
import com.parzivail.swg.tile.TileGunRack;
import com.parzivail.util.ui.gltk.GL;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class RenderItemGunRack implements IItemRenderer
{
	private final TileEntitySpecialRenderer render;
	private final TileEntity tile;

	public RenderItemGunRack()
	{
		render = new RenderGunRack();
		tile = new TileGunRack();
		tile.setWorldObj(Client.mc.theWorld);
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		GL11.glPushMatrix();
		tile.setWorldObj(Client.mc.theWorld);
		tile.updateEntity();
		switch (type)
		{
			case INVENTORY:
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glTranslatef(0, -0.3f, 0);
				GL11.glRotatef(90, 0, 1, 0);
				GL.Scale(0.75f);
				GL11.glScalef(1, 1, -1);
				GL11.glTranslatef(-.03f, -.6f, 0);
				render.renderTileEntityAt(tile, 0, 0, 0, 0);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
				break;
			case EQUIPPED:
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glRotatef(45, 0, 1, 0);
				GL11.glScalef(0.45f, 0.45f, -0.45f);
				GL11.glTranslatef(-0.5f, 1.2f, -3);
				GL11.glRotatef(45, 1, 0, 0);
				render.renderTileEntityAt(tile, 0, 0, 0, 0);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
				break;
			case EQUIPPED_FIRST_PERSON:
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glScalef(1.5f, 1.5f, -1.5f);
				GL11.glTranslatef(3, -1, 0.5f);
				GL11.glRotatef(135, 0, 1, 0);
				render.renderTileEntityAt(tile, 0, 0, 0, 0);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
				break;
			default:
				GL11.glPushMatrix();
				GL11.glDisable(GL11.GL_CULL_FACE);
				GL11.glRotatef(90, 0, 1, 0);
				GL11.glScalef(0.85f, 0.85f, -0.85f);
				GL11.glTranslatef(-0.5f, 0.4f, -0.5f);
				render.renderTileEntityAt(tile, 0, 0, 0, 0);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return true;
	}
}
