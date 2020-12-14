package com.maxgavr.feeder.entity.ai.goal;

import com.maxgavr.feeder.blocks.FeederBlock;
import com.maxgavr.feeder.util.RegistryHandler;
import jdk.nashorn.internal.ir.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SweetBerryBushBlock;
import net.minecraft.block.pattern.BlockStateMatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ColorHandlerEvent;

import java.util.EnumSet;
import java.util.Random;
import java.util.function.Predicate;

public class EatFromFeederGoal extends MoveToBlockGoal  {
    private final PigEntity pig;
    protected int field_220731_g;
    protected final Random rand = new Random();
    private int counter;

    public EatFromFeederGoal(PigEntity pigIn, double p_i50737_2_, int p_i50737_4_, int p_i50737_5_) {
        super(pigIn, p_i50737_2_, p_i50737_4_, p_i50737_5_);
        this.pig = pigIn;
    }

    public double getTargetDistanceSq() {
        return 2.0D;
    }

    public boolean shouldMove() {
        return this.timeoutCounter % 100 == 0;
    }

    protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
        BlockState blockstate = worldIn.getBlockState(pos);
        return blockstate.getBlock() == RegistryHandler.FEEDER.get();
    }

    public void tick() {
        if (this.getIsAboveDestination()) {
            if (this.field_220731_g >= 40) {
                this.eatWheat();
            } else {
                ++this.field_220731_g;
            }
        } else if (!this.getIsAboveDestination() && EatFromFeederGoal.this.rand.nextFloat() < 0.05F) {
            pig.playSound(SoundEvents.ENTITY_PIG_AMBIENT, 0.5F, 0.5F);
        }
        super.tick();
    }

    protected void eatWheat() {
        if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(pig.world, pig)) {
            BlockState blockstate = pig.world.getBlockState(this.destinationBlock);
            if (blockstate.getBlock() == RegistryHandler.FEEDER.get()) {
                int i = blockstate.get(FeederBlock.LEVEL);
                if (i > 0 && counter >= 40) {
                    blockstate.with(FeederBlock.LEVEL, Integer.valueOf(i - 1));
                    pig.world.setBlockState(this.destinationBlock, blockstate.with(FeederBlock.LEVEL, Integer.valueOf(i - 1)), 2);
                    pig.playSound(SoundEvents.ENTITY_FOX_EAT, 1.0F, 1.0F);
                    pig.setInLove(600);
                    counter = 0;
                } else if (counter < 40) {
                    ++counter;
                }
            }
        }
    }

    public boolean shouldExecute() { return super.shouldExecute(); }

    public void startExecuting() {
        this.counter = 40;
        this.field_220731_g = 0;
        super.startExecuting();
    }
}

