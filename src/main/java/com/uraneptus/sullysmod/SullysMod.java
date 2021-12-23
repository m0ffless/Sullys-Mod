package com.uraneptus.sullysmod;

import com.teamabnormals.blueprint.core.util.registry.RegistryHelper;
import com.uraneptus.sullysmod.common.entities.CopperGolemEntity;
import com.uraneptus.sullysmod.core.data.BlockStates;
import com.uraneptus.sullysmod.core.data.ItemModels;
import com.uraneptus.sullysmod.core.data.LangProvider;
import com.uraneptus.sullysmod.core.registry.SMEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod(SullysMod.MOD_ID)
@Mod.EventBusSubscriber(modid = SullysMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SullysMod {
    public static final String MOD_ID = "sullysmod";
    public static final RegistryHelper REGISTRY_HELPER = new RegistryHelper(MOD_ID);

    public SullysMod() {
        IEventBus event_bus = FMLJavaModLoadingContext.get().getModEventBus();
        event_bus.addListener(this::setup);
        event_bus.addListener(this::gatherData);

        REGISTRY_HELPER.register(event_bus);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public static void addEntityAttributes(final EntityAttributeCreationEvent event) {
        event.put(SMEntityType.COPPER_GOLEM.get(), CopperGolemEntity.createAttributes().build());
    }

    @SubscribeEvent
    public void gatherData(GatherDataEvent event) {
        event.getGenerator().addProvider(new BlockStates(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(new ItemModels(event.getGenerator(), event.getExistingFileHelper()));
        event.getGenerator().addProvider(new LangProvider(event.getGenerator()));
    }
}
