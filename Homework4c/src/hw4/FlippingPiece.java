package hw4;

import api.Cell;
import api.Position;

/**
 * abstract piece that can flip along its vertical axis
 * @author Jonathan Clark
 *
 */
public abstract class FlippingPiece extends AbstractPiece{


	
	protected FlippingPiece(Position position) {
		super(position);
	}

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
