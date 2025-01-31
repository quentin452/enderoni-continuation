package fluke.stygian;

import fluke.stygian.entity.EntityEndSkeleton;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fluke.stygian.proxy.CommonProxy;
import fluke.stygian.util.Reference;
import fluke.stygian.world.BiomeRegistrar;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION, acceptableRemoteVersions="*")
public class Stygian
{
	public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

	@Instance
	public static Stygian instance;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;

	static
	{
		FluidRegistry.enableUniversalBucket();
	}

	public static Item END_SKELETON_SPAWN_EGG;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
	}

	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		proxy.init();
		BiomeRegistrar.registerBiomes();
		// Register EntityEndSkeleton as a spawnable entity
		EntityRegistry.registerModEntity(new ResourceLocation(Reference.MOD_ID, "end_skeleton"), EntityEndSkeleton.class, "end_skeleton", 1, Reference.MOD_ID, 64, 1, true);
	}


	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event)
	{
	}

	@EventHandler
	public void startServer(FMLServerStartingEvent event)
	{

	}

	@SubscribeEvent
	public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
		LOGGER.info("Registering custom entities...");
		event.getRegistry().register(
				new EntityEntry(
						EntityEndSkeleton.class,
						EntityEndSkeleton.NAME
				)
		);
	}
}