package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * Piece that circles around the bounding box clockwise
 * @author Jonathan Clark
 *
 */
public class CirclingPiece extends BBB{
	
	/**
	 * Order of positions that the "head" piece moves in
	 */
	private static final Position[] POSITION_ORDER = {
			new Position(0,0),
			new Position(0,1),
			new Position(0,2),
			new Position(1,2),
			new Position(2,2),
			new Position(2,1),
			new Position(2,0),
			new Position(1,0)
	};
	
	/**
	 * Index of the current position of the head
	 */
	private int currPos;
	
	/**
	 * Creates a piece that circles around the bounding box clockwise
	 * @param position upper left corner of the piece bounding box
	 * @param icons color data for each cell
	 */
	public CirclingPiece(Position position, Icon[] icons) {
		super(position);
		if(icons.length != 4) {
			throw new IllegalArgumentException();
		}
		Cell[] cells = new Cell[4];
		cells[0] = new Cell(icons[0], new Position(0,0));
		cells[1] = new Cell(icons[1], new Position(1, 0));
		cells[2] = new Cell(icons[2], new Position(2,0));
		cells[3] = new Cell(icons[3], new Position(2, 1));
		
		super.setCells(cells);
		currPos = 0;
	}
	
	/**
	 * Circles the piece one step
	 */
	@Override
	public void transform() {
		currPos = currPos >= 7 ? 0 : currPos + 1;
		super.slide(POSITION_ORDER[currPos]);
	}

}
