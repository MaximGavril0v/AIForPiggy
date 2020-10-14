package com.maxgavr.feeder.events;

import com.maxgavr.feeder.Feeder;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.entity.ai.goal.GoalSelector;

@Mod.EventBusSubscriber(modid = Feeder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents{

    private GoalSelector goalSelector;


    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof AnimalEntity) doTasksStuff((AnimalEntity) event.getEntity(), event);
    }

    private void doTasksStuff(AnimalEntity animal, Event event){

        this.goalSelector.addGoal(3, new net.minecraft.entity.ai.goal.AvoidEntityGoal(animal, PlayerEntity.class, 16.0F, 0.8D, 1.33D));
    }



}



