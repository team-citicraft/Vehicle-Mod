package com.shadow.tbvm;

import com.shadow.tbvm.init.BlockInit;
import com.shadow.tbvm.init.ItemInit;
import net.minecraft.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod("tbvm")
@Mod.EventBusSubscriber(modid = VehicleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class VehicleMod
{

    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "tbvm";

    public VehicleMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        BlockInit.BLOCKS.register(bus);
        ItemInit.ITEMS.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
        BlockInit.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
            event.getRegistry().register(new BlockItem(block, new Item.Properties().group(VehicleMod.TAB))
                    .setRegistryName(block.getRegistryName()));
        });
    }

    public static final ItemGroup TAB = new ItemGroup("VehicleModTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(Items.OAK_BOAT);
        }
    };
}
