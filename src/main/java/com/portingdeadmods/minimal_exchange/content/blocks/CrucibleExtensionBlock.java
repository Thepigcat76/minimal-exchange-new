package com.portingdeadmods.minimal_exchange.content.blocks;

import com.mojang.serialization.MapCodec;
import com.portingdeadmods.minimal_exchange.registries.MEBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class CrucibleExtensionBlock extends BaseEntityBlock {
    public static final VoxelShape SHAPE = Stream.of(
            Shapes.box(0, 0, 0, 0.1875, 1, 1),
            Shapes.box(0.1875, 0, 0.8125, 0.8125, 1, 1),
            Shapes.box(0.8125, 0, 0, 1, 1, 1),
            Shapes.box(0.1875, 0, 0, 0.8125, 1, 0.1875)
    ).reduce(Shapes::or).get();

    public CrucibleExtensionBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(CrucibleBlock.CONNECTED_TOP, false)
                .setValue(CrucibleBlock.CONNECTED_BOTTOM, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(CrucibleBlock.CONNECTED_TOP, CrucibleBlock.CONNECTED_BOTTOM));
    }

    @Override
    protected @NotNull RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected @NotNull MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(CrucibleExtensionBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return MEBlockEntityTypes.CRUCIBLE_EXTENSION.get().create(blockPos, blockState);
    }
}
