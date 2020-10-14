package com.maxgavr.feeder.events;

import com.maxgavr.feeder.Feeder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.OcelotEntity;
import net.minecraft.entity.passive.SheepEntity;
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
        if (entity instanceof AnimalEntity){
            AnimalEntity animal = (AnimalEntity) entity;
            animal.goalSelector.addGoal(3, new AvoidEntityGoal(animal, PlayerEntity.class, 16.0F, 0.8D, 1.33D));
        }
    }

   @SubscribeEvent
    public static void onSheepDamage(AttackEntityEvent event){
       if(event.getEntityLiving().getHeldItemMainhand().getItem() == Items.WHEAT){
           if(event.getTarget().isAlive()){
               LivingEntity target = (LivingEntity) event.getTarget();
               if (target instanceof SheepEntity){
                   target.addPotionEffect(new EffectInstance(Effects.POISON, 10*20));
                   target.setGlowing(true);

               }
           }
       }
   }


}



