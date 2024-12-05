
package hw4;

import api.Cell;
import api.Icon;
import api.Piece;
import api.Position;

/**
 * Abstract superclass for implementations of the Piece interface.
 * @author Jonathan Clark
 */
public abstract class AbstractPiece implements Piece
{
  /**
   * Position of this Piece
   */
  private Position position;

  /**
   * Cells of this Piece
   */
  private Cell[] cells;
  
  /**
   * Constructs a piece with the given position.
   * Subclasses extending this class MUST call setCells to initialize
   * initial cell positions and icons.
   * @param position
   *   initial position for upper-left corner of bounding box
   */
  protected AbstractPiece(Position position)
  {
    this.position = position;
  }

  // YOUR CODE HERE TO IMPLEMENT THE Piece INTERFACE
  
  /**
	 * Returns the position of this piece (upper-left corner of its bounding box).
	 * 
	 * @return position of this shape
	 */
  public Position getPosition() {
	  return position;
  }
  
  /**
	 * Returns a deep copy of the Cell objects in this piece. The cell positions are relative to the upper-left corner of its bounding box.
	 * 
	 * @return copy of the cells in this piece
	 */
  public Cell[] getCells() {
	  Cell[] copyCells = new Cell[cells.length];
	  for(int i = 0; i < cells.length; i++) {
		  copyCells[i] = new Cell(cells[i]);
	  }
	  
	  return copyCells;
  }
  
  /**
	 * Returns a new array of Cell objects representing the icons in this piece with their absolute positions (relative positions plus position of bounding box).
	 * 
	 * @return copy of the cells in this piece, with absolute positions
	 */
  public Cell[] getCellsAbsolute() {
	  Cell[] absoluteCells = new Cell[cells.length];
	  for(int i = 0; i < cells.length; i++) {
		  Cell cell = cells[i];
		  absoluteCells[i] = new Cell(cell.getIcon(), 
				  			new Position(cell.getRow() + position.row(), cell.getCol() + position.col()));
	  }
	  //create cell array thats the same cells just with the position of the piece added to it
	  return absoluteCells;
  }
  
  /**
	 * Sets the cells in this piece, making a deep copy of the given array.
	 * 
	 * @param givenCells new cells for this piece
	 */
  public void setCells(Cell[] givenCells) {
	  //TODO
	  //for now only initializes cells, cant update, may need to change
	  cells = new Cell[givenCells.length];
	  
	  for(int i = 0; i < givenCells.length; i++) {
		  cells[i] = new Cell(givenCells[i]);
	  }
  }
  
  /**
	 * Shifts the position of this piece down (increasing the row) by one. No bounds checking is done.
	 */
  public void shiftDown() {
	  for(int i = 0; i < cells.length; i++) {
		  cells[i].setRow(cells[i].getRow() + 1);
	  }
  }
  
  /**
	 * Shifts the position of this piece left (decreasing the column) by one. No bounds checking is done.
	 */
  public void shiftLeft() {
	  for(int i = 0; i < cells.length; i++) {
		  cells[i].setCol(cells[i].getCol() - 1);
	  }
  }
  
  /**
	 * Shifts the position of this piece right (increasing the column) by one. No bounds checking is done.
	 */
  public void shiftRight() {
	  for(int i = 0; i < cells.length; i++) {
		  cells[i].setCol(cells[i].getCol() + 1);
	  }
  }
  
  /**
	 * Transforms this piece without altering its position according to the rules of the game to be implemented. Typical operations would be rotation or reflection. No bounds checking is done.
	 */
  public abstract void transform();
  
  /**
	 * Cycles the icons within the cells of this piece. Each icon is shifted forward to the next cell (in the original ordering of the cells). The last icon wraps around to the first cell.
	 */
  public void cycle() {
	  Cell[] cycledCells = getCells();
	  cycledCells[0].setIcon(cells[cells.length-1].getIcon());
	  for(int i = 1; i < cells.length; i++) {
		  cycledCells[i].setIcon(cells[i-1].getIcon());
	  }
	  setCells(cycledCells);
  }
    
  @Override
  public Piece clone()
  {
    try
    {
      AbstractPiece s = (AbstractPiece)super.clone();

      // make it into a deep copy (note there is no need to copy the position,
      // since Position is immutable)
      s.cells = new Cell[cells.length];
      for (int i = 0; i < cells.length; ++i)
      {
        s.cells[i] = new Cell(cells[i]);
      }
      return s;
    }
    catch (CloneNotSupportedException e)
    {
      // can't happen
      return null;
    }
  }
}
