package com.maxgavr.feeder.entity.ai;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.world.World;


public class PigAvoidPlayer extends PigEntity {
    private PigAvoidPlayer.AvoidEntityGoal<PlayerEntity> field_213531_bB;


    public PigAvoidPlayer(EntityType<? extends PigEntity> p_i50250_1_, World p_i50250_2_) {
        super(p_i50250_1_, p_i50250_2_);
    }



    protected void func_213529_dV() {
        if (this.field_213531_bB == null) {
            this.field_213531_bB = new PigAvoidPlayer.AvoidEntityGoal<>(this, PlayerEntity.class, 16.0F, 0.8D, 1.33D);
        }

    }

    static class AvoidEntityGoal<T extends LivingEntity> extends net.minecraft.entity.ai.goal.AvoidEntityGoal<T> {
        private final PigEntity pig;

        public AvoidEntityGoal(PigEntity pigIn, Class<T> p_i50037_2_, float p_i50037_3_, double p_i50037_4_, double p_i50037_6_) {
            super(pigIn, p_i50037_2_, p_i50037_3_, p_i50037_4_, p_i50037_6_, EntityPredicates.CAN_AI_TARGET::test);
            this.pig = pigIn;
        }


    }


}
