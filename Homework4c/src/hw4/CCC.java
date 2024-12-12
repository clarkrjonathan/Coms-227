package hw4;

import api.Cell;
import api.Position;

/**
 * Abstract piece that can rotate 90 degrees about the center
 * @author Jonathan Clark
 *
 */
public abstract class CCC extends AbstractPiece{

	protected CCC(Position position) {
		super(position);
	}
	
	public void transform() {
		Cell[] transformedCells = super.getCells();
		for(int i = 0; i < transformedCells.length; i++) {
			Cell cell = transformedCells[i];
			cell.setPosition(rotatedPos(new Position(cell.getRow(), cell.getCol())));
		}
		
		super.setCells(transformedCells);
		
	}
	
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
