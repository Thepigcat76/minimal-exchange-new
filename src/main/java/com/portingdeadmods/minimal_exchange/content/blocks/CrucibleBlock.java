package com.portingdeadmods.minimal_exchange.content.blocks;

import com.mojang.serialization.MapCodec;
import com.portingdeadmods.minimal_exchange.content.blockentities.CrucibleBlockEntity;
import com.portingdeadmods.minimal_exchange.content.blockentities.CrucibleExtensionBlockEntity;
import com.portingdeadmods.minimal_exchange.registries.MEBlockEntityTypes;
import com.portingdeadmods.minimal_exchange.registries.MEBlocks;
import com.portingdeadmods.portingdeadlibs.api.blockentities.ContainerBlockEntity;
import com.portingdeadmods.portingdeadlibs.api.blocks.ContainerBlock;
import com.portingdeadmods.portingdeadlibs.utils.BlockUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Stream;

public class CrucibleBlock extends ContainerBlock {
    public static final BooleanProperty CONNECTED_TOP = BooleanProperty.create("connected_top");
    public static final BooleanProperty CONNECTED_BOTTOM = BooleanProperty.create("connected_bottom");
    public static final VoxelShape SHAPE = Stream.of(
            Shapes.box(0, 0, 0, 0.375, 0.125, 0.1875),
            Shapes.box(0, 0, 0.1875, 0.1875, 0.125, 0.375),
            Shapes.box(0.8125, 0, 0.1875, 1, 0.125, 0.375),
            Shapes.box(0.625, 0, 0, 1, 0.125, 0.1875),
            Shapes.box(0.625, 0, 0.8125, 1, 0.125, 1),
            Shapes.box(0.8125, 0, 0.625, 1, 0.125, 0.8125),
            Shapes.box(0, 0, 0.8125, 0.375, 0.125, 1),
            Shapes.box(0, 0.125, 0, 1, 0.25, 1),
            Shapes.box(0.8125, 0.25, 0, 1, 1, 1),
            Shapes.box(0.1875, 0.25, 0, 0.8125, 1, 0.1875),
            Shapes.box(0.1875, 0.25, 0.8125, 0.8125, 1, 1),
            Shapes.box(0, 0.25, 0, 0.1875, 1, 1),
            Shapes.box(0, 0, 0.625, 0.1875, 0.125, 0.8125)
    ).reduce(Shapes::or).get();

    public CrucibleBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(CONNECTED_TOP, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(CONNECTED_TOP));
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState aboveState = level.getBlockState(pos.above());

        BlockState state = super.getStateForPlacement(context);
        if (state != null) {
            return state.setValue(CONNECTED_TOP, aboveState.is(MEBlocks.CRUCIBLE_EXTENSION.get()));
        }
        return null;
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP) {
            return state.setValue(CONNECTED_TOP, neighborState.is(MEBlocks.CRUCIBLE_EXTENSION));
        }
        return state;
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            CrucibleBlockEntity be = BlockUtils.getBE(CrucibleBlockEntity.class, level, pos);

            Set<BlockPos> removedPositions = new LinkedHashSet<>();
            for (BlockPos extensionPos : be.getExtensionPositions()) {
                CrucibleExtensionBlockEntity extensionBe = BlockUtils.getBE(CrucibleExtensionBlockEntity.class, level, extensionPos);
                if (extensionBe != null) {
                    extensionBe.setMainCruciblePos(null);
                    removedPositions.add(extensionPos);
                }
            }
            for (BlockPos blockPos : removedPositions) {
                be.removeExtensionPos(blockPos);
            }
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public boolean tickingEnabled() {
        return true;
    }

    @Override
    public BlockEntityType<? extends ContainerBlockEntity> getBlockEntityType() {
        return MEBlockEntityTypes.CRUCIBLE.get();
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(CrucibleBlock::new);
    }
}
