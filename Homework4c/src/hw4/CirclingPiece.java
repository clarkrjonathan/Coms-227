package hw4;

import api.Icon;
import api.Position;

public class CirclingPiece extends BBB{

	public CirclingPiece(Position position, Icon[] icons) {
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
