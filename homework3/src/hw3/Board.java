package hw3;

import java.util.ArrayList;
import java.util.Iterator;

import api.Cell;
import api.Direction;
import api.Move;
import api.Orientation;

/**
 * Represents a board in the Block Slider game. A board contains a 2D grid of
 * cells and a list of blocks that slide over the cells.
 */
public class Board {
	/**
	 * 2D array of cells, the indexes signify (row, column) with (0, 0) representing
	 * the upper-left cornner of the board.
	 */
	private Cell[][] grid;

	/**
	 * A list of blocks that are positioned on the board.
	 */
	private ArrayList<Block> blocks;

	/**
	 * A list of moves that have been made in order to get to the current position
	 * of blocks on the board.
	 */
	private ArrayList<Move> moveHistory;
	
	/**
	 * reference of block currently grabbed, if none then value is null
	 */
	private Block grabbedBlock;
	
	/**
	 * records the cell of the current grabbed block
	 */
	private Cell grabbedCell;
	
	/**
	 * true if game has been won
	 */
	private boolean isGameOver;

	/**
	 * Constructs a new board from a given 2D array of cells and list of blocks. The
	 * cells of the grid should be updated to indicate which cells have blocks
	 * placed over them (i.e., setBlock() method of Cell). The move history should
	 * be initialized as empty.
	 * 
	 * @param grid   a 2D array of cells which is expected to be a rectangular shape
	 * @param blocks list of blocks already containing row-column position which
	 *               should be placed on the board
	 */
	public Board(Cell[][] grid, ArrayList<Block> blocks) {
		
		this.grid = grid;
		this.blocks = blocks;
		moveHistory = new ArrayList<Move>();
		isGameOver = false;
		
		updateGrid();
	}
	
	/**
	 * Sets all cells to have no block
	 */
	private void clearBlocksFromGrid() {
		
		for(int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				
				grid[i][j].setBlock(null);
				
			}
		}
	}
	/**
	 * updates the cells according to the blocks list
	 */
	private void updateGrid() {
		
		clearBlocksFromGrid();
		
		for (int i = 0; i < blocks.size(); i++) {
			updateOneBlock(blocks.get(i));
		}
	}
	
	/**
	 * Updates cells that block parameter covers
	 * @param block
	 */
	private void updateOneBlock(Block block) {
		
		for (int j = 0; j < block.getLength(); j++) {
			
			if(block.getOrientation() == Orientation.HORIZONTAL) {
				
				grid[block.getFirstRow()][block.getFirstCol() + j].setBlock(block);
				
			} else if(block.getOrientation() == Orientation.VERTICAL) {
				
				grid[block.getFirstRow() + j][block.getFirstCol()].setBlock(block);
				
			}
		}
	}
	

	/**
	 * Constructs a new board from a given 2D array of String descriptions.
	 * <p>
	 * DO NOT MODIFY THIS CONSTRUCTOR
	 * 
	 * @param desc 2D array of descriptions
	 */
	public Board(String[][] desc) {
		this(GridUtil.createGrid(desc), GridUtil.findBlocks(desc));
	}
	

	/**
	 * Models the user grabbing a block over the given row and column. The purpose
	 * of grabbing a block is for the user to be able to drag the block to a new
	 * position, which is performed by calling moveGrabbedBlock(). This method
	 * records two things: the block that has been grabbed and the cell at which it
	 * was grabbed.
	 * 
	 * @param row row to grab the block from
	 * @param col column to grab the block from
	 */
	public void grabBlockAtCell(int row, int col) {
		
		grabbedBlock = grid[row][col].getBlock();
		grabbedCell = grid[row][col];
	}

	/**
	 * Set the currently grabbed block to null.
	 */
	public void releaseBlock() {
		grabbedBlock = null;
		grabbedCell = null;
	}
	

	/**
	 * Returns the currently grabbed block.
	 * 
	 * @return the current block
	 */
	public Block getGrabbedBlock() {
		return grabbedBlock;
	}
	

	/**
	 * Returns the currently grabbed cell.
	 * 
	 * @return the current cell
	 */
	public Cell getGrabbedCell() {
		return grabbedCell;
	}
	

	/**
	 * Returns true if the cell at the given row and column is available for a block
	 * to be placed over it. Blocks can only be placed over floors and exits. A
	 * block cannot be placed over a cell that is occupied by another block.
	 * 
	 * @param row row location of the cell
	 * @param col column location of the cell
	 * @return true if the cell is available for a block, otherwise false
	 */
	public boolean canPlaceBlock(int row, int col) {
		//TODO implement for cell
		Cell cell = grid[row][col];
		
		if(cell.getBlock() != null || cell.isWall()) {
			return false;
		}
		return true;
	}
	

	/**
	 * Returns the number of moves made so far in the game.
	 * 
	 * @return the number of moves
	 */
	public int getMoveCount() {
		return moveHistory.size();
	}
	

	/**
	 * Returns the number of rows of the board.
	 * 
	 * @return number of rows
	 */
	public int getRowSize() {
		return grid.length;
	}
	

	/**
	 * Returns the number of columns of the board.
	 * 
	 * @return number of columns
	 */
	public int getColSize() {
		return grid[0].length;
	}
	

	/**
	 * Returns the cell located at a given row and column.
	 * 
	 * @param row the given row
	 * @param col the given column
	 * @return the cell at the specified location
	 */
	public Cell getCell(int row, int col) {
		return grid[row][col];
	}
	

	/**
	 * Returns a list of all blocks on the board.
	 * 
	 * @return a list of all blocks
	 */
	public ArrayList<Block> getBlocks() {
		return blocks;
	}
	

	/**
	 * Returns true if the player has completed the puzzle by positioning a block
	 * over an exit, false otherwise.
	 * 
	 * @return true if the game is over
	 */
	public boolean isGameOver() {
		return isGameOver;
	}
	

	/**
	 * Moves the currently grabbed block by one cell in the given direction. A
	 * horizontal block is only allowed to move right and left and a vertical block
	 * is only allowed to move up and down. A block can only move over a cell that
	 * is a floor or exit and is not already occupied by another block. The method
	 * does nothing under any of the following conditions:
	 * <ul>
	 * <li>The game is over.</li>
	 * <li>No block is currently grabbed by the user.</li>
	 * <li>A block is currently grabbed by the user, but the block is not allowed to
	 * move in the given direction.</li>
	 * </ul>
	 * If none of the above conditions are meet, the method does the following:
	 * <ul>
	 * <li>Moves the block object by calling its move method.</li>
	 * <li>Sets the block for the grid cell that the block is being moved into.</li>
	 * <li>For the grid cell that the block is being moved out of, sets the block to
	 * null.</li>
	 * <li>Moves the currently grabbed cell by one cell in the same moved direction.
	 * The purpose of this is to make the currently grabbed cell move with the block
	 * as it is being dragged by the user.</li>
	 * <li>Adds the move to the end of the moveHistory list.</li>
	 * <li>Increment the count of total moves made in the game.</li>
	 * </ul>
	 * 
	 * @param dir the direction to move
	 */
	public void moveGrabbedBlock(Direction dir) {

		if(isLegalMove(grabbedBlock, dir)) {
			
			grabbedBlock.move(dir);
			grabbedCell = getNextCell(grabbedCell, dir);
			
			moveHistory.add(new Move(grabbedBlock, dir));
			updateGrid();
			
			isGameOver = getEndCellOfBlock(grabbedBlock).isExit() || getStartCellOfBlock(grabbedBlock).isExit();
		}
		
	}
	
	/**
	 * returns the last cell of a block
	 * @param block
	 * @return the cell on the edge of a block
	 */
	private Cell getEndCellOfBlock(Block block) {
		
		if (block.getOrientation() == Orientation.HORIZONTAL) {
			return grid[block.getFirstRow()][block.getFirstCol() + block.getLength() - 1];
		} else {
			return grid[block.getFirstRow() + block.getLength() - 1][block.getFirstCol()];
		}
	}
	
	/**
	 * gets the cell the block starts on
	 * @param block Block to check
	 * @return returns first cell
	 */
	private Cell getStartCellOfBlock(Block block) {
		
		return grid[block.getFirstRow()][block.getFirstCol()];
		
	}
	
	/**
	 * helper method to check if a move is legal in a given direction
	 * @param block block to be moved
	 * @param dir direction to move the block
	 * @return true if moving the block in given direction is legal
	 */
	private boolean isLegalMove(Block block, Direction dir) {
		
		if(block == null) {
			return false;
		}
		
		if (block.getOrientation() == Orientation.HORIZONTAL && 
				(dir == Direction.UP || dir == Direction.DOWN)) {
				return false;
		}
		
		if(block.getOrientation() == Orientation.VERTICAL && 
				(dir == Direction.LEFT || dir == Direction.RIGHT)) {
			return false;
		}
		
		Cell nextCell;
		
		if (dir == Direction.UP || dir == Direction.LEFT) {
			
			nextCell = getNextCell(getStartCellOfBlock(block), dir);
			
		} else {
			
			nextCell = getNextCell(getEndCellOfBlock(block), dir);
			
		}
		
		if (nextCell == null) {
			return false;
		}
		
		return canPlaceBlock(nextCell.getRow(), nextCell.getCol());
	}
	
	/**
	 * Returns the cell one cell in the moveDir
	 * @param currentCell cell currently on
	 * @param moveDir direction to move
	 * @return
	 */
	private Cell getNextCell(Cell currentCell, Direction moveDir) {
		
		int row = currentCell.getRow();
		int col = currentCell.getCol();
		
		switch (moveDir) {
		case UP:
			row--;
			break;
		case DOWN:
			row++;
			break;
		case RIGHT:
			col++;
			break;
		case LEFT:
			col--;
			break;
		}
		
		if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0) {
			return null;
		}
		
		
		return grid[row][col];
	}
	
	/**
	 * Runs through block list and resets all of them to their initial states
	 */
	private void resetBlocks() {
		
		Iterator<Block> blockIterator = blocks.iterator();
		
		while(blockIterator.hasNext()) {
			blockIterator.next().reset();
		}
	}

	/**
	 * Resets the state of the game back to the start, which includes the move
	 * count, the move history, and whether the game is over. The method calls the
	 * reset method of each block object. It also updates each grid cells by calling
	 * their setBlock method to either set a block if one is located over the cell
	 * or set null if no block is located over the cell.
	 */
	public void reset() {
		
		//this.grid = copyCells(initialGrid);
		
		resetBlocks();
		
		this.moveHistory = new ArrayList<Move>();
		
		updateGrid();
		
		isGameOver = false;
		
	}
	

	/**
	 * Returns a list of all legal moves that can be made by any block on the
	 * current board. If the game is over there are no legal moves.
	 * 
	 * @return a list of legal moves
	 */
	public ArrayList<Move> getAllPossibleMoves() {
		
		ArrayList<Move> possibleMoves = new ArrayList<Move>();
		
		for (int i = 0; i < blocks.size(); i++) {
			
			Block block = blocks.get(i);
			
			addLegalMove(possibleMoves, block, Direction.UP);
			addLegalMove(possibleMoves, block, Direction.DOWN);
			addLegalMove(possibleMoves, block, Direction.LEFT);
			addLegalMove(possibleMoves, block, Direction.RIGHT);
			
		}
		
		return possibleMoves;
	}
	
	/**
	 * adds the move to possibleMoves if its legal
	 */
	private void addLegalMove(ArrayList<Move> possibleMoves, Block block, Direction dir) {
		if (isLegalMove(block, dir)) {
			possibleMoves.add(new Move(block, dir));
		}
	}

	/**
	 * Gets the list of all moves performed to get to the current position on the
	 * board.
	 * 
	 * @return a list of moves performed to get to the current position
	 */
	public ArrayList<Move> getMoveHistory() {
		return moveHistory;
	}

	/**
	 * EXTRA CREDIT 5 POINTS
	 * <p>
	 * This method is only used by the Solver.
	 * <p>
	 * Undo the previous move. The method gets the last move on the moveHistory list
	 * and performs the opposite actions of that move, which are the following:
	 * <ul>
	 * <li>grabs the moved block and calls moveGrabbedBlock passing the opposite
	 * direction</li>
	 * <li>decreases the total move count by two to undo the effect of calling
	 * moveGrabbedBlock twice</li>
	 * <li>if required, sets is game over to false</li>
	 * <li>removes the move from the moveHistory list</li>
	 * </ul>
	 * If the moveHistory list is empty this method does nothing.
	 */
	public void undoMove() {
		Move lastMove = moveHistory.get(moveHistory.size()-1);
		Block block = lastMove.getBlock();
		grabBlockAtCell(block.getFirstRow(),block.getFirstCol());
		
		switch (lastMove.getDirection()) {
		case UP:
			moveGrabbedBlock(Direction.DOWN);
			break;
		case DOWN:
			moveGrabbedBlock(Direction.UP);
			break;
		case LEFT:
			moveGrabbedBlock(Direction.RIGHT);
			break;
		case RIGHT:
			moveGrabbedBlock(Direction.LEFT);
			break;
		}
		
		moveHistory.remove(moveHistory.size()-1);
		moveHistory.remove(moveHistory.size()-1);
		
	}

	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		boolean first = true;
		for (Cell row[] : grid) {
			if (!first) {
				buff.append("\n");
			} else {
				first = false;
			}
			for (Cell cell : row) {
				buff.append(cell.toString());
				buff.append(" ");
			}
		}
		return buff.toString();
	}
}
