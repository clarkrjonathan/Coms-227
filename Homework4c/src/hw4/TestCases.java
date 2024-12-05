package hw4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import api.Cell;
import api.Icon;
import api.Position;

/**
 * List of test cases for testing my implementation
 * @author Jonathan Clark
 *
 */
class TestCases {

	
	@Test
	void test0() {
		Icon[] icons = new Icon[4];
		
		for(int i = 0; i < 4; i ++) {
			icons[i] = new Icon(Color.RED);
		}
		
		LShapedPiece piece = new LShapedPiece(new Position(0, 0), icons);
		assertEquals(piece.getCells()[0].getCol(), 0);
	}
	
	@Test
	void test1() {
		Icon[] icons = new Icon[4];
		
		for(int i = 0; i < 4; i ++) {
			icons[i] = new Icon(Color.RED);
		}
		
		LShapedPiece piece = new LShapedPiece(new Position(0,0), icons);
		piece.shiftDown();
		assertEquals(1, piece.getCells()[0].getRow());
		piece.shiftRight();
		assertEquals(1, piece.getCells()[0].getCol());
		piece.shiftLeft();
		assertEquals(0, piece.getCells()[0].getCol());
	}
	
	@Test
	void test2() {
		Icon[] icons = new Icon[4];
		
		for(int i = 0; i < 4; i ++) {
			icons[i] = new Icon(Color.RED);
		}
		
		LShapedPiece piece = new LShapedPiece(new Position(0,0), icons);
		piece.transform();
		assertEquals(2, piece.getCells()[0].getCol());
		assertEquals(1, piece.getCells()[1].getCol());
		assertEquals(1, piece.getCells()[2].getCol());
		assertEquals(1, piece.getCells()[3].getCol());
	}
	
	@Test
	void test3() {
		Icon[] icons = new Icon[4];
		
		for(int i = 0; i < 4; i ++) {
			icons[i] = new Icon(Color.RED);
		}
		
		LShapedPiece piece = new LShapedPiece(new Position(0,1), icons);
		piece.transform();
		assertEquals(piece.getCellsAbsolute()[0].getCol(), 3);
	}
	
	@Test
	void test4() {
		Icon[] icons = new Icon[4];
		
		icons[0] = new Icon(Color.RED);
		icons[1] = new Icon(Color.CYAN);
		icons[2] = new Icon(Color.BLUE);
		icons[3] = new Icon(Color.YELLOW);
		
		
		LShapedPiece piece = new LShapedPiece(new Position(0,0), icons);
		piece.cycle();
		assertEquals(Color.YELLOW, piece.getCells()[0].getIcon().getColorHint());
		assertEquals(Color.RED, piece.getCells()[1].getIcon().getColorHint());
		assertEquals(Color.CYAN, piece.getCells()[2].getIcon().getColorHint());
		assertEquals(Color.BLUE, piece.getCells()[3].getIcon().getColorHint());
		
	}
	
	@Test
	void test5() {
		Icon[] icons = new Icon[4];
		
		icons[0] = new Icon(Color.RED);
		icons[1] = new Icon(Color.CYAN);
		icons[2] = new Icon(Color.BLUE);
		icons[3] = new Icon(Color.YELLOW);
		
		
		LShapedPiece piece = new LShapedPiece(new Position(0,0), icons);
		Cell[] cells = piece.getCells();
		cells[0] = new Cell(new Icon(Color.GREEN), new Position(3, 4));
		assertEquals(Color.RED, piece.getCells()[0].getIcon().getColorHint());
	}
	
	

}
