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

@Mod.EventBusSubscriber(modid = Feeder.MOD_ID,bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents{

    private GoalSelector goalSelector;
    private ModClientEvents.AvoidEntityGoal<PlayerEntity> field_213531_bB;

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof AnimalEntity) doTasksStuff((AnimalEntity) event.getEntity(), event);
    }

    private void doTasksStuff(AnimalEntity animal, Event event){

        this.goalSelector.addGoal(4, new net.minecraft.entity.ai.goal.AvoidEntityGoal(animal, PlayerEntity.class, 16.0F, 0.8D, 1.33D));
    }





    static class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
        private final AnimalEntity animal;

        public AvoidEntityGoal(AnimalEntity animalIn, Class<T> p_i50037_2_, float p_i50037_3_, double p_i50037_4_, double p_i50037_6_) {
            super(animalIn, p_i50037_2_, p_i50037_3_, p_i50037_4_, p_i50037_6_, EntityPredicates.CAN_AI_TARGET::test);
            this.animal = animalIn;
        }


        }
    }



