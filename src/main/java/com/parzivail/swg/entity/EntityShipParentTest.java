package com.parzivail.swg.entity;

import com.parzivail.swg.StarWarsGalaxy;
import com.parzivail.swg.network.MessageShipOrientation;
import com.parzivail.util.entity.EntityUtils;
import com.parzivail.util.math.RotatedAxes;
import com.parzivail.util.math.lwjgl.Vector3f;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import java.util.List;

public class EntityShipParentTest extends Entity implements IEntityAdditionalSpawnData
{
	public RotatedAxes orientation;
	public RotatedAxes previousOrientation;
	public Vector3f angularMomentum;
	public float throttle;
	public EntityShipChildTest[] seats;
	public boolean isInitialized;

	public EntityShipParentTest(World worldIn)
	{
		super(worldIn);
		setSize(1, 1);
		orientation = previousOrientation = new RotatedAxes(0, 0, 0);
		angularMomentum = new Vector3f(0, 0, 0);
		throttle = 0;
	}

	public EntityShipParentTest(World worldIn, int x, int y, int z)
	{
		this(worldIn);
		setPosition(x + 0.5, y + 0.5f, z + 0.5);
	}

	@Override
	protected void entityInit()
	{
		if (!worldObj.isRemote)
			createChildren();
	}

	@SideOnly(Side.CLIENT)
	public void setPositionAndRotation2(double x, double y, double z, float yaw, float pitch, int rotationIncrements)
	{
		setPosition(x, y, z);
		setRotation(yaw, pitch);
	}

	@Override
	public void setDead()
	{
		super.setDead();

		//		if (worldObj.isRemote)
		//			camera.setDead();

		for (EntityShipChildTest seat : seats)
			if (seat != null)
				seat.setDead();
	}

	@Override
	public void onUpdate()
	{
		if (ridingEntity != null && ridingEntity.isDead)
			ridingEntity = null;

		if (posY < -64.0D)
			kill();

		if (!isInitialized)
		{
			spawnChildren();
			isInitialized = true;
		}

		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		prevRotationPitch = rotationPitch;
		prevRotationYaw = rotationYaw;
		previousOrientation = orientation.clone();

		Entity driver = seats == null ? null : (seats[0] == null ? null : seats[0].riddenByEntity);
		if (driver instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)driver;

			throttle += player.moveForward / 10f;
			throttle = MathHelper.clamp_float(throttle, 0, 1);
			//orientation.rotateLocalYaw(player.moveStrafing * 10);
			orientation.setAngles(-player.rotationYaw, -player.rotationPitch, 0);

			Vector3f forward = orientation.findLocalVectorGlobally(new Vector3f(0, 0, 1));

			if (ticksExisted % 5 == 0 && !worldObj.isRemote)
			{
				EntityTracker tracker = ((WorldServer)worldObj).getEntityTracker();
				IMessage message = new MessageShipOrientation(this);
				for (EntityPlayer entityPlayer : tracker.getTrackingPlayers(this))
					StarWarsGalaxy.network.sendTo(message, (EntityPlayerMP)entityPlayer);
			}

			motionX = forward.x * throttle;
			motionY = forward.y * throttle;
			motionZ = forward.z * throttle;
		}
		else
		{
			motionX = 0;
			motionY = 0;
			motionZ = 0;
		}

		List<AxisAlignedBB> aabb = EntityUtils.getBlockAABBs(worldObj, boundingBox.expand(0, 5, 0).addCoord(0, 5, 0));
		//		double closest = 6;
		//		if (!aabb.isEmpty())
		//		{
		//			for (AxisAlignedBB bb : aabb)
		//				closest = Math.abs(posY - bb.maxY);
		//		}
		//		if (closest > 5)
		//		{
		//			moveEntity(0, -closest / 2, 0);
		//		}
		if (aabb.isEmpty())
		{
			moveEntity(motionX, motionY > 0 ? -motionY * 2 : -1, motionZ);
		}
		else
			moveEntity(motionX, motionY, motionZ);
	}

	private void spawnChildren()
	{
		if (worldObj.isRemote)
			return;

		for (int i = 0; i < seats.length; i++)
		{
			seats[i] = new EntityShipChildTest(worldObj, this, i);
			worldObj.spawnEntityInWorld(seats[i]);
		}
	}

	public void updateRiderPosition()
	{
		if (riddenByEntity == null)
			return;

		riddenByEntity.setPosition(posX, posY + getMountedYOffset() + riddenByEntity.getYOffset(), posZ);
	}

	@Override
	public boolean interactFirst(EntityPlayer player)
	{
		return false;
	}

	public boolean canBeCollidedWith()
	{
		return false;
	}

	public boolean canBePushed()
	{
		return true;
	}

	@Override
	public double getMountedYOffset()
	{
		return 0.6;
	}

	@Override
	protected void readEntityFromNBT(NBTTagCompound tagCompound)
	{
		throttle = tagCompound.getFloat("throttle");
		createChildren();
	}

	private void createChildren()
	{
		//spawnCamera();
		seats = new EntityShipChildTest[1];
	}

	@Override
	protected void writeEntityToNBT(NBTTagCompound tagCompound)
	{
		tagCompound.setFloat("throttle", throttle);
	}

	@Override
	public void writeSpawnData(ByteBuf buffer)
	{
	}

	@Override
	public void readSpawnData(ByteBuf additionalData)
	{
		createChildren();
	}
}
