package tfc.dynamic_rendering;

import net.minecraft.block.BlockState;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import tfc.dynamic_rendering.TestingRegistry.DeferredTileEntities;

import javax.annotation.Nullable;

public class Block extends net.minecraft.block.Block implements ITileEntityProvider {
	public Block() {
		super(Properties.create(Material.ROCK)
				.hardnessAndResistance(3.0F, 40.0F)
				.sound(SoundType.STONE)
				.harvestLevel(0)
				.notSolid()
				.harvestTool(ToolType.PICKAXE)
		);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return getCollisionShape(state, worldIn, pos, context);
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return getRenderShape(state, worldIn, pos);
	}
	
	@Override
	public VoxelShape getRenderShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
//		VoxelShape shape = VoxelShapes.or(
//				net.minecraft.block.Block.makeCuboidShape(0,0,0,1,16,16),
//				net.minecraft.block.Block.makeCuboidShape(0,0,0,16,1,16),
//				net.minecraft.block.Block.makeCuboidShape(0,0,0,16,16,1),
//				net.minecraft.block.Block.makeCuboidShape(0,0,15,16,16,16),
//				net.minecraft.block.Block.makeCuboidShape(15,0,0,16,16,16),
//				net.minecraft.block.Block.makeCuboidShape(0,15,0,16,16,16)
//		);
		VoxelShape shape = net.minecraft.block.Block.makeCuboidShape(0,0,0,16,16,16);
		return shape;
	}
	
	@Override
	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 0;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<net.minecraft.block.Block, BlockState> builder) {
		super.fillStateContainer(builder);
		builder.add(
			FACING
		);
	}
	
	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		return this.getDefaultState().with(FACING, context.getFace().getOpposite());
	}
	
	public static final DirectionProperty FACING = DirectionalBlock.FACING;
	
	@Nullable
	@Override
	public TileEntity createNewTileEntity(IBlockReader worldIn) {
		return new te();
	}
	
	public static class te extends TileEntity {
		public te() {
			super(DeferredTileEntities.TILE_ENTITY.get());
		}
	}
}
