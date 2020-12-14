package com.maxgavr.feeder.events;
import com.maxgavr.feeder.Feeder;
import com.maxgavr.feeder.entity.ai.goal.EatFromFeederGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.function.Predicate;


@Mod.EventBusSubscriber(modid = Feeder.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents{
    private static int individual;

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.CARROT, Items.POTATO, Items.BEETROOT);

    private static final Ingredient TEMPTATION_ITEMS_NEW = Ingredient.fromItems(Items.CARROT, Items.POTATO, Items.BEETROOT, Items.WHEAT);

    public static final Predicate<LivingEntity> TARGET_ENTITIES = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.RABBIT || entitytype == EntityType.FOX;
    };

    public static final Predicate<LivingEntity> TARGET_ENTITIES_NEW = (p_213440_0_) -> {
        EntityType<?> entitytype = p_213440_0_.getType();
        return entitytype == EntityType.SHEEP || entitytype == EntityType.RABBIT || entitytype == EntityType.FOX  || entitytype == EntityType.PIG;
    };

    public static <T extends Entity> List<T> getEntitiesInArea (Class<T> entityClass, World world, BlockPos pos, float range) {
        return world.getEntitiesWithinAABB(entityClass, new AxisAlignedBB(pos.add(-range, -range, -range), pos.add(range + 1, range + 1, range + 1)));
    }


   @SubscribeEvent
    public static void newGoalsForAnimals(EntityJoinWorldEvent event){
        Entity entity = event.getEntity();
        if (entity instanceof PigEntity){
            PigEntity pig = (PigEntity) entity;
            pig.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
            pig.goalSelector.addGoal(5, new AvoidEntityGoal<>(pig, PlayerEntity.class, 16.0F, 0.8D, 1.33D));
            pig.goalSelector.addGoal(6, new EatFromFeederGoal(pig, 1.0D, 12, 2));
            pig.goalSelector.addGoal(5, new MeleeAttackGoal(pig, 1.0D, true));
            //pig.goalSelector.addGoal(5, new AvoidEntityGoal<>(pig, WolfEntity.class, 16.0F, 0.8D, 1.33D));
            //pig.goalSelector.addGoal(5, new EatGrassGoal(pig));
            pig.goalSelector.removeGoal(new TemptGoal(pig, 1.2D, false, TEMPTATION_ITEMS));
            pig.goalSelector.addGoal(4, new TemptGoal(pig, 0.8D, true, TEMPTATION_ITEMS_NEW));
        }

        if (entity instanceof WolfEntity){
            WolfEntity wolf = (WolfEntity) entity;
            wolf.targetSelector.removeGoal(new NonTamedTargetGoal<>(wolf, AnimalEntity.class, false, TARGET_ENTITIES));
            wolf.targetSelector.addGoal(4, new NonTamedTargetGoal<>(wolf, AnimalEntity.class, false, TARGET_ENTITIES_NEW));
        }
    }

    @SubscribeEvent
    public static void onEntityDamaged(LivingDamageEvent event) {
        if (event.getEntityLiving() instanceof PigEntity) {
             PigEntity pig = (PigEntity) event.getEntityLiving();
             if (event.getSource().getTrueSource() instanceof WolfEntity) {
                 WolfEntity attacker = (WolfEntity) event.getSource().getTrueSource();
                 for (final PigEntity nearby : getEntitiesInArea(pig.getClass(), pig.world, pig.getPosition(), 16f)) {
                         nearby.setAttackTarget(attacker);
                 }
            }
            }
        }
}








