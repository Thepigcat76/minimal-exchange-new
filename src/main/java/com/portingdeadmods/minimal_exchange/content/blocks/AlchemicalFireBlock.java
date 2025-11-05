package com.portingdeadmods.minimal_exchange.content.blocks;

import com.mojang.serialization.MapCodec;
import com.portingdeadmods.minimal_exchange.content.recipes.AlchemicalFireTransmutationRecipe;
import com.portingdeadmods.minimal_exchange.registries.MESoundEvents;
import com.portingdeadmods.portingdeadlibs.api.blockentities.ContainerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.Containers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.Optional;

public class AlchemicalFireBlock extends BaseFireBlock {
    public static final MapCodec<AlchemicalFireBlock> CODEC = simpleCodec(AlchemicalFireBlock::new);

    public @NotNull MapCodec<AlchemicalFireBlock> codec() {
        return CODEC;
    }

    public AlchemicalFireBlock(BlockBehaviour.Properties props) {
        super(props, 2.0F);
    }

    protected @NotNull BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        return this.canSurvive(state, level, currentPos) ? this.defaultBlockState() : Blocks.AIR.defaultBlockState();
    }

    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return canSurviveOnBlock(level.getBlockState(pos.below()));
    }

    public static boolean canSurviveOnBlock(BlockState stateBelow) {
        return true;
    }

    protected boolean canBurn(BlockState state) {
        return true;
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof ItemEntity itemEntity) {
            ItemStack item = itemEntity.getItem();
            Optional<AlchemicalFireTransmutationRecipe> _recipe = level.getRecipeManager().getRecipeFor(AlchemicalFireTransmutationRecipe.TYPE, new SingleRecipeInput(item), level)
                    .map(RecipeHolder::value);
            if (_recipe.isPresent()) {
                level.playSound(null, pos, MESoundEvents.TRANSMUTE.get(), SoundSource.BLOCKS, 0.8f, 0.45f);

                AlchemicalFireTransmutationRecipe recipe = _recipe.get();
                int itemCount = item.getCount();
                int ingredientCount = recipe.input().count();
                while (itemCount >= ingredientCount) {
                    item.shrink(ingredientCount);
                    ItemStack result = recipe.result();
                    Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), result.copy());
                    itemCount -= ingredientCount;
                }

                if (item.isEmpty()) {
                    itemEntity.discard();
                }
            }
        }
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (random.nextInt(24) == 0) {
            level.playLocalSound((double) pos.getX() + (double) 0.5F, (double) pos.getY() + (double) 0.5F, (double) pos.getZ() + (double) 0.5F, SoundEvents.FIRE_AMBIENT, SoundSource.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
        }

        BlockPos blockpos = pos.below();
        BlockState blockstate = level.getBlockState(blockpos);
        ParticleOptions particle = new DustParticleOptions(random.nextInt(0, 5) == 0 ? new Vector3f(1.0f, 213f / 255f, 65f / 255f) : new Vector3f(0.9f, 0, 0), random.nextIntBetweenInclusive(1, 20) / 10f);
        if (!this.canBurn(blockstate) && !blockstate.isFaceSturdy(level, blockpos, Direction.UP)) {
            if (this.canBurn(level.getBlockState(pos.west()))) {
                for (int j = 0; j < 2; ++j) {
                    double d3 = (double) pos.getX() + random.nextDouble() * (double) 0.1F;
                    double d8 = (double) pos.getY() + random.nextDouble();
                    double d13 = (double) pos.getZ() + random.nextDouble();
                    level.addParticle(particle, d3, d8, d13, 0.0F, 0.0F, 0.0F);
                }
            }

            if (this.canBurn(level.getBlockState(pos.east()))) {
                for (int k = 0; k < 2; ++k) {
                    double d4 = (double) (pos.getX() + 1) - random.nextDouble() * (double) 0.1F;
                    double d9 = (double) pos.getY() + random.nextDouble();
                    double d14 = (double) pos.getZ() + random.nextDouble();
                    level.addParticle(particle, d4, d9, d14, 0.0F, 0.0F, 0.0F);
                }
            }

            if (this.canBurn(level.getBlockState(pos.north()))) {
                for (int l = 0; l < 2; ++l) {
                    double d5 = (double) pos.getX() + random.nextDouble();
                    double d10 = (double) pos.getY() + random.nextDouble();
                    double d15 = (double) pos.getZ() + random.nextDouble() * (double) 0.1F;
                    level.addParticle(particle, d5, d10, d15, 0.0F, 0.0F, 0.0F);
                }
            }

            if (this.canBurn(level.getBlockState(pos.south()))) {
                for (int i1 = 0; i1 < 2; ++i1) {
                    double d6 = (double) pos.getX() + random.nextDouble();
                    double d11 = (double) pos.getY() + random.nextDouble();
                    double d16 = (double) (pos.getZ() + 1) - random.nextDouble() * (double) 0.1F;
                    level.addParticle(particle, d6, d11, d16, 0.0F, 0.0F, 0.0F);
                }
            }

            if (this.canBurn(level.getBlockState(pos.above()))) {
                for (int j1 = 0; j1 < 2; ++j1) {
                    double d7 = (double) pos.getX() + random.nextDouble();
                    double d12 = (double) (pos.getY() + 1) - random.nextDouble() * (double) 0.1F;
                    double d17 = (double) pos.getZ() + random.nextDouble();
                    level.addParticle(particle, d7, d12, d17, 0.0F, 0.0F, 0.0F);
                }
            }
        } else {
            for (int i = 0; i < 3; ++i) {
                double d0 = (double) pos.getX() + random.nextDouble();
                double d1 = (double) pos.getY() + random.nextDouble() * (double) 0.5F + (double) 0.5F;
                double d2 = (double) pos.getZ() + random.nextDouble();
                level.addParticle(particle, d0, d1, d2, 0.0F, 0.0F, 0.0F);
            }
        }
    }
}
