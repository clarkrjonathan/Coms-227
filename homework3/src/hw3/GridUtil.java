package hw3;

import static api.Orientation.*;
import static api.CellType.*;

import java.util.ArrayList;

import api.Cell;
import api.CellType;
import api.Orientation;

/**
 * Utilities for parsing string descriptions of a grid.
 */
public class GridUtil {
	/**
	 * Constructs a 2D grid of Cell objects given a 2D array of cell descriptions.
	 * String descriptions are a single character and have the following meaning.
	 * <ul>
	 * <li>"*" represents a wall.</li>
	 * <li>"e" represents an exit.</li>
	 * <li>"." represents a floor.</li>
	 * <li>"[", "]", "^", "v", or "#" represent a part of a block. A block is not a
	 * type of cell, it is something placed on a cell floor. For these descriptions
	 * a cell is created with CellType of FLOOR. This method does not create any
	 * blocks or set blocks on cells.</li>
	 * </ul>
	 * The method only creates cells and not blocks. See the other utility method
	 * findBlocks which is used to create the blocks.
	 * 
	 * @param desc a 2D array of strings describing the grid
	 * @return a 2D array of cells the represent the grid without any blocks present
	 */
	public static Cell[][] createGrid(String[][] desc) {
		Cell[][] cells = new Cell[desc.length][desc[0].length];

		for (int i = 0; i < cells.length; i++) {
			
			for (int j = 0; j < cells[0].length; j++) {
				
				CellType type;
				
				switch (desc[i][j]) {
				case ("*"):
					type = CellType.WALL;
					break;
				case ("e"):
					type = CellType.EXIT;
					break;
				default:
					type = CellType.FLOOR;
				}
				
				cells[i][j] = new Cell(type, i, j);
				
			}
		}
		
		return cells;
	}

	/**
	 * Returns a list of blocks that are constructed from a given 2D array of cell
	 * descriptions. String descriptions are a single character and have the
	 * following meanings.
	 * <ul>
	 * <li>"[" the start (left most column) of a horizontal block</li>
	 * <li>"]" the end (right most column) of a horizontal block</li>
	 * <li>"^" the start (top most row) of a vertical block</li>
	 * <li>"v" the end (bottom most column) of a vertical block</li>
	 * <li>"#" inner segments of a block, these are always placed between the start
	 * and end of the block</li>
	 * <li>"*", ".", and "e" symbols that describe cell types, meaning there is not
	 * block currently over the cell</li>
	 * </ul>
	 * 
	 * @param desc a 2D array of strings describing the grid
	 * @return a list of blocks found in the given grid description
	 */
	public static ArrayList<Block> findBlocks(String[][] desc) {
		
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		//counting from top left
		for(int i = 0; i < desc.length; i++) {
			
			for(int j = 0; j < desc[0].length; j++) {
				
				if(!desc[i][j].equals("^") && !desc[i][j].equals("[")) {
					continue;
				}
				
				Orientation orientation = desc[i][j].equals("^") ? Orientation.VERTICAL : Orientation.HORIZONTAL;
				
				int length = findLength(desc, orientation, i, j);
				
				blocks.add(new Block(i, j, length, orientation));
				
			}
		}

		return blocks;
	}
	
	/**
	 * Finds the length of a block given the top left corner
	 * @param desc string description of grid
	 * @param orientation orientation of the block
	 * @param row first row of the block
	 * @param column first column of the block
	 * @return length of the block
	 */
	private static int findLength(String[][] desc, Orientation orientation, int row, int column) {
		int length = 2;
		
		if (orientation == Orientation.VERTICAL) {
			
			while(!desc[row + length - 1][column].equals("v")) {
				length++;
			}
			
		} else if (orientation == Orientation.HORIZONTAL) {
			
			while(!desc[row][column + length - 1].equals("]")) {
				length++;
			}
			
		}
		
		return length;
	}
}
