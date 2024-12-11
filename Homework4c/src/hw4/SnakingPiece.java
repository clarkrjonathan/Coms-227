package hw4;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * Concrete piece that represents a snaking piece which slides around its bounding box in a figure 8 pattern
 * @author Jonathan Clark
 *
 */
public class SnakingPiece extends BBB{
	
	private static final Position[] POSITION_ORDER = {
			new Position(0,0),
			new Position(0,1),
			new Position(0,2),
			new Position(1,2),
			new Position(1,1),
			new Position(1,0),
			new Position(2,0),
			new Position(2,1),
			new Position(2,2),
			new Position(1,2),
			new Position(1,1),
			new Position(1,0),
	};
	
	private int currPos;

	public SnakingPiece(Position position, Icon[] icons) {
		super(position);
		if(icons.length != 4) {
			throw new IllegalArgumentException();
		}
		Cell[] cells = new Cell[4];
		cells[0] = new Cell(icons[0], new Position(0,0));
		cells[1] = new Cell(icons[1], new Position(1, 0));
		cells[2] = new Cell(icons[2], new Position(1,1));
		cells[3] = new Cell(icons[3], new Position(1, 2));
		
		super.setCells(cells);
		currPos = 0;
	}

	@Override
	public void transform() {
		super.slide(POSITION_ORDER[currPos]);
		currPos = currPos > 11 ? 0 : currPos + 1;
		
	}

}
