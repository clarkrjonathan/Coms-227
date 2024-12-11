package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * Concrete class that represents an L shaped piece which can flip along its vertical axis
 * @author Jonathan Clark
 *
 */
public class LShapedPiece extends AAA{
	public LShapedPiece(Position position, Icon[] icons) {
		super(position);
		Cell[] cells = new Cell[4];
		cells[0] = new Cell(icons[0], new Position(0,0));
		cells[1] = new Cell(icons[1], new Position(0, 1));
		cells[2] = new Cell(icons[2], new Position(1,1));
		cells[3] = new Cell(icons[3], new Position(2, 1));
		
		super.setCells(cells);
	}

}
