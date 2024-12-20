package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * Concrete class that represents a piece in an "s" shape that can rotate 90 degrees about its center
 * @author Jonathan Clark
 *
 */
public class RotatingSPiece extends CCC{
	
	/**
	 * Constructs a RotatingSPiece
	 * @param position upper left corner of piece
	 * @param icons icon data of piece
	 */
	public RotatingSPiece(Position position, Icon[] icons) {
		super(position);
		if(icons.length != 4) {
			throw new IllegalArgumentException();
		}
		Cell[] cells = new Cell[4];
		cells[0] = new Cell(icons[0], new Position(0,0));
		cells[1] = new Cell(icons[1], new Position(0, 1));
		cells[2] = new Cell(icons[2], new Position(1,1));
		cells[3] = new Cell(icons[3], new Position(1, 2));
		
		super.setCells(cells);
	}

	

}
