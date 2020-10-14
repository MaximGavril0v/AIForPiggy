package com.maxgavr.feeder.entity;

import com.maxgavr.feeder.entity.ai.goal.EatFromFeederGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PigEatFromFeeder extends PigEntity {
    public PigEatFromFeeder(EntityType<? extends PigEntity> p_i50250_1_, World p_i50250_2_) {
        super(p_i50250_1_, p_i50250_2_);
    }

    private EatFromFeederGoal eatFromFeederGoal;
    private int pigTimer;


    protected void registerGoals() {
        super.registerGoals();
        this.eatFromFeederGoal = new EatFromFeederGoal(this);
        this.goalSelector.addGoal(9, this.eatFromFeederGoal);
    }

    protected void updateAITasks() {
        this.pigTimer = this.eatFromFeederGoal.getEatingFromFeederTimer();
        super.updateAITasks();
    }

    public void livingTick() {
        if (this.world.isRemote) {
            this.pigTimer = Math.max(0, this.pigTimer - 1);
        }

        super.livingTick();
    }

    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 10) {
            this.pigTimer = 40;
        } else {
            super.handleStatusUpdate(id);
        }
    }
}
