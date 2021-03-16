package net.minecraft.world.gen.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;

public class UnderwaterCaveWorldCarver extends CaveWorldCarver {
   public UnderwaterCaveWorldCarver(Codec<ProbabilityConfig> p_i231920_1_) {
      super(p_i231920_1_, 256);
      this.replaceableBlocks = ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.TERRACOTTA, Blocks.WHITE_TERRACOTTA, Blocks.ORANGE_TERRACOTTA, Blocks.MAGENTA_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.YELLOW_TERRACOTTA, Blocks.LIME_TERRACOTTA, Blocks.PINK_TERRACOTTA, Blocks.GRAY_TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.PURPLE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.BROWN_TERRACOTTA, Blocks.GREEN_TERRACOTTA, Blocks.RED_TERRACOTTA, Blocks.BLACK_TERRACOTTA, Blocks.SANDSTONE, Blocks.RED_SANDSTONE, Blocks.MYCELIUM, Blocks.SNOW, Blocks.SAND, Blocks.GRAVEL, Blocks.WATER, Blocks.LAVA, Blocks.OBSIDIAN, Blocks.AIR, Blocks.CAVE_AIR, Blocks.PACKED_ICE);
   }

   protected boolean hasWater(IChunk p_222700_1_, int p_222700_2_, int p_222700_3_, int p_222700_4_, int p_222700_5_, int p_222700_6_, int p_222700_7_, int p_222700_8_, int p_222700_9_) {
      return false;
   }

   protected boolean carveBlock(IChunk p_230358_1_, Function<BlockPos, Biome> p_230358_2_, BitSet p_230358_3_, Random p_230358_4_, BlockPos.Mutable p_230358_5_, BlockPos.Mutable p_230358_6_, BlockPos.Mutable p_230358_7_, int p_230358_8_, int p_230358_9_, int p_230358_10_, int p_230358_11_, int p_230358_12_, int p_230358_13_, int p_230358_14_, int p_230358_15_, MutableBoolean p_230358_16_) {
      return carveBlock(this, p_230358_1_, p_230358_3_, p_230358_4_, p_230358_5_, p_230358_8_, p_230358_9_, p_230358_10_, p_230358_11_, p_230358_12_, p_230358_13_, p_230358_14_, p_230358_15_);
   }

   protected static boolean carveBlock(WorldCarver<?> p_222728_0_, IChunk p_222728_1_, BitSet p_222728_2_, Random p_222728_3_, BlockPos.Mutable p_222728_4_, int p_222728_5_, int p_222728_6_, int p_222728_7_, int p_222728_8_, int p_222728_9_, int p_222728_10_, int p_222728_11_, int p_222728_12_) {
      if (p_222728_11_ >= p_222728_5_) {
         return false;
      } else {
         int i = p_222728_10_ | p_222728_12_ << 4 | p_222728_11_ << 8;
         if (p_222728_2_.get(i)) {
            return false;
         } else {
            p_222728_2_.set(i);
            p_222728_4_.set(p_222728_8_, p_222728_11_, p_222728_9_);
            BlockState blockstate = p_222728_1_.getBlockState(p_222728_4_);
            if (!p_222728_0_.canReplaceBlock(blockstate)) {
               return false;
            } else if (p_222728_11_ == 10) {
               float f = p_222728_3_.nextFloat();
               if ((double)f < 0.25D) {
                  p_222728_1_.setBlockState(p_222728_4_, Blocks.MAGMA_BLOCK.defaultBlockState(), false);
                  p_222728_1_.getBlockTicks().scheduleTick(p_222728_4_, Blocks.MAGMA_BLOCK, 0);
               } else {
                  p_222728_1_.setBlockState(p_222728_4_, Blocks.OBSIDIAN.defaultBlockState(), false);
               }

               return true;
            } else if (p_222728_11_ < 10) {
               p_222728_1_.setBlockState(p_222728_4_, Blocks.LAVA.defaultBlockState(), false);
               return false;
            } else {
               boolean flag = false;

               for(Direction direction : Direction.Plane.HORIZONTAL) {
                  int j = p_222728_8_ + direction.getStepX();
                  int k = p_222728_9_ + direction.getStepZ();
                  if (j >> 4 != p_222728_6_ || k >> 4 != p_222728_7_ || p_222728_1_.getBlockState(p_222728_4_.set(j, p_222728_11_, k)).isAir()) {
                     p_222728_1_.setBlockState(p_222728_4_, WATER.createLegacyBlock(), false);
                     p_222728_1_.getLiquidTicks().scheduleTick(p_222728_4_, WATER.getType(), 0);
                     flag = true;
                     break;
                  }
               }

               p_222728_4_.set(p_222728_8_, p_222728_11_, p_222728_9_);
               if (!flag) {
                  p_222728_1_.setBlockState(p_222728_4_, WATER.createLegacyBlock(), false);
                  return true;
               } else {
                  return true;
               }
            }
         }
      }
   }
}
