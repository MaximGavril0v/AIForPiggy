package com.maxgavr.feeder.entity.ai.goal;

import com.maxgavr.feeder.blocks.FeederBlock;
import com.maxgavr.feeder.util.RegistryHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.function.Predicate;

public class EatFromFeederGoal extends Goal {
    private final MobEntity wheatEaterEntity;
    private final World entityWorld;
    private int eatingFromFeederTimer;

    public EatFromFeederGoal(MobEntity wheatEaterEntityIn){
        this.wheatEaterEntity = wheatEaterEntityIn;
        this.entityWorld = wheatEaterEntityIn.world;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.JUMP));
    }

    public boolean shouldExecute() {
        if (this.wheatEaterEntity.getRNG().nextInt(this.wheatEaterEntity.isChild() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockpos = new BlockPos(this.wheatEaterEntity);

        }
        return false;
    }

    public int getEatingFromFeederTimer() {return this.eatingFromFeederTimer;}
}
