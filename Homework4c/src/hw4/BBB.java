package hw4;

import api.Cell;
import api.Position;

/**
 * Represents a piece that transforms by "Sliding". 
 * Parent to {@link SnakingPiece}, {@link CirclingPiece}
 * 
 * 
 * @author Jonathan Clark
 *
 */
public abstract class BBB extends AbstractPiece{


	protected BBB(Position position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Slides blocks starting from head, and moving the head towards the given position
	 * The rest of the blocks in the line will follow copying the position from the next cell in line
	 */
	protected void slide(Position position) {
		Cell[] updatedCells = super.getCells();
		Cell[] currentCells = super.getCells();
		
		for (int i = 1; i < super.getCells().length; i++) {
			updatedCells[i].setPosition(new Position(currentCells[i - 1].getRow(),currentCells[i - 1].getCol()));
		}
		
		updatedCells[0].setPosition(position);
		
		super.setCells(updatedCells);
		
	}


}
