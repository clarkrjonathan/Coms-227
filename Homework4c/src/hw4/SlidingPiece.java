package hw4;

import api.Cell;
import api.Position;

/**
 * Represents a piece that transforms by "Sliding" 
 * @author Jonathan Clark
 *
 */
public abstract class SlidingPiece extends AbstractPiece{

	protected SlidingPiece(Position position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Slides blocks starting from head, and moving the head towards the given position
	 * The rest of the blocks in the line will follow copying the position from the next cell in line
	 */
	protected void slide(Cell head, Position position) {
		//TODO
		
	}


}
