package hw4;

import api.Cell;
import api.Position;

/**
 * Abstract piece that can flip along its vertical axis
 * Parent to {@link LShapedPiece}, {@link TeePiece}
 * @author Jonathan Clark
 *
 */
public abstract class AAA extends AbstractPiece{


	
	protected AAA(Position position) {
		super(position);
	}
	
	/**
	 * Flips a cell along its vertical axis
	 */
	@Override
	public void transform() {
		Cell[] cells = new Cell[super.getCells().length];
		for(int i = 0; i < cells.length; i++) {
			Cell startCell = super.getCells()[i];
			cells[i] = new Cell(startCell.getIcon(), new Position(startCell.getRow(), 2 - startCell.getCol()));
		}
		
		super.setCells(cells);
	}

}
