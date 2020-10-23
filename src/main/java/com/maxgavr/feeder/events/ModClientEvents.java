package com.maxgavr.feeder.events;

import com.maxgavr.feeder.Feeder;
import com.maxgavr.feeder.entity.ai.goal.EatFromFeederGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.EntityPredicates;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.entity.ai.goal.GoalSelector;

@Mod.EventBusSubscriber(modid = Feeder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents{



   @SubscribeEvent
    public static void avoidPlayer(EntityJoinWorldEvent event){
        Entity entity = event.getEntity();
        if (entity instanceof PigEntity){
            PigEntity pig = (PigEntity) entity;
            pig.goalSelector.addGoal(2, new AvoidEntityGoal<>(pig, PlayerEntity.class, 16.0F, 0.8D, 1.33D));
            //pig.goalSelector.addGoal(10, new EatFromFeederGoal(pig, 1.0D, 12, 2));
            pig.goalSelector.addGoal(5, new EatGrassGoal(pig));
        }
    }




}



