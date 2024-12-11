package hw4;

import api.Icon;
import api.Position;

/**
 * Concrete piece that represents a snaking piece which slides around its bounding box in a figure 8 pattern
 * @author Jonathan Clark
 *
 */
public class SnakingPiece extends BBB{

	public SnakingPiece(Position position, Icon[] icons) {
		super(position);
		if(icons.length != 4) {
			throw new IllegalArgumentException();
		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void transform() {
		// TODO Auto-generated method stub
		
	}

}
