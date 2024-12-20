package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * Tee shaped piece that flips along its vertical axis
 * @author Jonathan Clark
 *
 */
public class TeePiece extends AAA{
	
	/**
	 * Constructs a TeePiece
	 * @param position position of upper left corner
	 * @param icons icon data for cells
	 */
	public TeePiece(Position position, Icon[] icons) {
		super(position);
		if(icons.length != 4) {
			throw new IllegalArgumentException();
		}
		Cell[] cells = new Cell[4];
		cells[0] = new Cell(icons[0], new Position(0,0));
		cells[1] = new Cell(icons[1], new Position(1, 0));
		cells[2] = new Cell(icons[2], new Position(1,1));
		cells[3] = new Cell(icons[3], new Position(2, 0));
		
		super.setCells(cells);
	}

}
