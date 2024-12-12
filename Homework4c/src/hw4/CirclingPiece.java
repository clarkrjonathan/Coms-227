package hw4;

import api.Cell;
import api.Icon;
import api.Position;

public class CirclingPiece extends BBB{
	
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
	
	private int currPos;

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

	@Override
	public void transform() {
		currPos = currPos > 7 ? 0 : currPos + 1;
		super.slide(POSITION_ORDER[currPos]);
	}

}
