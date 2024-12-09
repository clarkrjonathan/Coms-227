package hw4;

import api.Cell;
import api.Position;

/**
 * Abstract piece that can rotate 90 degrees about the center
 * @author Jonathan Clark
 *
 */
public abstract class RotatingPiece extends AbstractPiece{
	
	//TODO check declaration
	private static final Position[] EDGES = {new Position(0,1), new Position(1, 2), new Position(2, 1), new Position(1, 0)};
	private static final Position[] CORNERS = {new Position(0,0), new Position(0, 2), new Position(2,2), new Position(2, 0)};
	

	protected RotatingPiece(Position position) {
		super(position);
	}
	
	public void transform() {
		Cell[] transformedCells = super.getCells();
		for(int i = 0; i < transformedCells.length; i++) {
			Cell cell = transformedCells[i];
			cell.setPosition(rotatedPos(new Position(cell.getRow(), cell.getCol())));
		}
		
		super.setCells(transformedCells);
		
	}
	
	private Position rotatedPos(Position pos) {
		//TODO make general for all bounding boxes, set coords for center to be origin
		
	
		Position rotatedPos = new Position(pos.row(),pos.col());
		for(int i = 0; i < EDGES.length; i++) {
			if (EDGES[i].compareTo(pos) == 0) {
				//Immutable object so just copying reference is enough
				rotatedPos = i < 3 ? EDGES[i + 1] : EDGES[0];
				
			} else if (CORNERS[i].compareTo(pos) == 0) {
				
				rotatedPos = i < 3 ? CORNERS[i + 1] : CORNERS[0];
				
			}
			
		}
	
		return rotatedPos;
	}
	

}
