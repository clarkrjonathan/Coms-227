package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * Concrete class that represents a piece with a shape similar to qoutes which can be rotated 90 degrees about the center
 * @author Jonathan Clark
 *
 */
public class QuotesPiece extends CCC{

	/**
	 * Constructs a QuotesPiece
	 * @param position upper left corner of the piece bounding box
	 * @param icons color data for each cell
	 */
	public QuotesPiece(Position position, Icon[] icons) {
		super(position);
		if(icons.length != 4) {
			throw new IllegalArgumentException();
		}
		Cell[] cells = new Cell[4];
		cells[0] = new Cell(icons[0], new Position(0,0));
		cells[1] = new Cell(icons[1], new Position(1, 0));
		cells[2] = new Cell(icons[2], new Position(0,2));
		cells[3] = new Cell(icons[3], new Position(1, 2));
		
		super.setCells(cells);
	}

	

}
