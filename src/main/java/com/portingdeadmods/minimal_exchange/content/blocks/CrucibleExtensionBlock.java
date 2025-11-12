package com.portingdeadmods.minimal_exchange.content.blocks;

import com.mojang.serialization.MapCodec;
import com.portingdeadmods.minimal_exchange.content.blockentities.CrucibleBlockEntity;
import com.portingdeadmods.minimal_exchange.content.blockentities.CrucibleExtensionBlockEntity;
import com.portingdeadmods.minimal_exchange.registries.MEBlockEntityTypes;
import com.portingdeadmods.minimal_exchange.registries.MEBlocks;
import com.portingdeadmods.portingdeadlibs.utils.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
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
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState belowState = level.getBlockState(pos.below());
        BlockState aboveState = level.getBlockState(pos.above());

        BlockState state = super.getStateForPlacement(context);
        if (state != null) {
            return state
                    .setValue(CrucibleBlock.CONNECTED_TOP, aboveState.is(MEBlocks.CRUCIBLE_EXTENSION))
                    .setValue(CrucibleBlock.CONNECTED_BOTTOM, belowState.is(MEBlocks.CRUCIBLE) || belowState.is(MEBlocks.CRUCIBLE_EXTENSION));
        }
        return null;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP) {
            return state.setValue(CrucibleBlock.CONNECTED_TOP, neighborState.is(MEBlocks.CRUCIBLE_EXTENSION));
        } else if (direction == Direction.DOWN) {
            return state.setValue(CrucibleBlock.CONNECTED_BOTTOM, neighborState.is(MEBlocks.CRUCIBLE) || neighborState.is(MEBlocks.CRUCIBLE_EXTENSION));
        }
        return state;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        if (!state.is(oldState.getBlock())) {
            CrucibleExtensionBlockEntity be = BlockUtils.getBE(CrucibleExtensionBlockEntity.class, level, pos);
            if (level.getBlockState(pos.below()).is(MEBlocks.CRUCIBLE)) {
                CrucibleBlockEntity crucibleBe = BlockUtils.getBE(CrucibleBlockEntity.class, level, pos.below());
                be.setMainCruciblePos(pos.below());
                crucibleBe.addExtensionPos(pos);
            } else if (level.getBlockState(pos.below()).is(MEBlocks.CRUCIBLE_EXTENSION)) {
                BlockPos mainCruciblePos = BlockUtils.getBE(CrucibleExtensionBlockEntity.class, level, pos.below()).getMainCruciblePos();
                if (mainCruciblePos != null) {
                    be.setMainCruciblePos(mainCruciblePos);
                    CrucibleBlockEntity crucibleBe = BlockUtils.getBE(CrucibleBlockEntity.class, level, mainCruciblePos);
                    crucibleBe.addExtensionPos(pos);
                }
            }
        }

    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        CrucibleExtensionBlockEntity extensionBE = BlockUtils.getBE(CrucibleExtensionBlockEntity.class, level, pos);
        BlockPos mainCruciblePos = extensionBE.getMainCruciblePos();
        if (mainCruciblePos != null) {
            CrucibleBlockEntity crucibleBe = BlockUtils.getBE(CrucibleBlockEntity.class, level, mainCruciblePos);
            if (crucibleBe != null) {
                crucibleBe.recheckExtensions();
            }
        }

        super.onRemove(state, level, pos, newState, movedByPiston);

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
