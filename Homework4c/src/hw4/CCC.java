package hw4;

import api.Cell;
import api.Position;

/**
 * Abstract piece that can rotate 90 degrees about the center
 * Parent to {@link QuotesPiece)}, {@link RotatingSPiece}
 * @author Jonathan Clark
 *
 */
public abstract class CCC extends AbstractPiece{

	protected CCC(Position position) {
		super(position);
	}
	
	/**
	 * Common transform method that just runs through all cells and rotates them
	 */
	public void transform() {
		Cell[] transformedCells = super.getCells();
		for(int i = 0; i < transformedCells.length; i++) {
			Cell cell = transformedCells[i];
			cell.setPosition(rotatedPos(new Position(cell.getRow(), cell.getCol())));
		}
		
		super.setCells(transformedCells);
		
	}
	
	/**
	 * Rotates a position
	 * @param pos the position to rotate
	 * @return the rotated position
	 */
	private Position rotatedPos(Position pos) {
		int row = pos.col();
		int col = pos.row();
		
		if(col == 0) {
			col = 2;
		} else if (col == 2) {
			col = 0;
		}
	
		return new Position(row, col);
	}
	

}
