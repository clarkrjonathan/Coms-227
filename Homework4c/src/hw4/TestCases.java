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
		assertEquals(0, piece.getCells()[0].getRow());
		assertEquals(1, piece.getCellsAbsolute()[0].getRow());
		piece.shiftRight();
		assertEquals(0, piece.getCells()[0].getCol());
		assertEquals(1, piece.getCellsAbsolute()[0].getCol());
		piece.shiftLeft();
		assertEquals(0, piece.getCells()[0].getCol());
		assertEquals(0, piece.getCellsAbsolute()[0].getCol());
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
	
	@Test
	void test6() {
		Icon[] icons = new Icon[4];
		
		icons[0] = new Icon(Color.RED);
		icons[1] = new Icon(Color.CYAN);
		icons[2] = new Icon(Color.BLUE);
		icons[3] = new Icon(Color.YELLOW);
		
		
		TeePiece piece = new TeePiece(new Position(0,0), icons);
		piece.cycle();
		assertEquals(Color.YELLOW, piece.getCells()[0].getIcon().getColorHint());
		assertEquals(Color.RED, piece.getCells()[1].getIcon().getColorHint());
		assertEquals(Color.CYAN, piece.getCells()[2].getIcon().getColorHint());
		assertEquals(Color.BLUE, piece.getCells()[3].getIcon().getColorHint());
	}
	
	@Test
	void test7() {
		Icon[] icons = new Icon[4];
		
		for(int i = 0; i < 4; i ++) {
			icons[i] = new Icon(Color.RED);
		}
		
		TeePiece piece = new TeePiece(new Position(0,0), icons);
		piece.shiftDown();
		assertEquals(0, piece.getCells()[0].getRow());
		assertEquals(1, piece.getCellsAbsolute()[0].getRow());
		piece.shiftRight();
		assertEquals(0, piece.getCells()[0].getCol());
		assertEquals(1, piece.getCellsAbsolute()[0].getCol());
		piece.shiftLeft();
		assertEquals(0, piece.getCells()[0].getCol());
		assertEquals(0, piece.getCellsAbsolute()[0].getCol());
	}
	
	//qoutes rotate
	@Test
	void test8() {
		Icon[] icons = new Icon[4];
		
		for(int i = 0; i < 4; i ++) {
			icons[i] = new Icon(Color.RED);
		}
		
		QoutesPiece piece = new QoutesPiece(new Position(0,0), icons);
		piece.transform();
		
		//coords (0,2)
		assertEquals(0,piece.getCells()[0].getRow());
		assertEquals(2,piece.getCells()[0].getCol());
		
		//coords (0,1)
		assertEquals(0,piece.getCells()[1].getRow());
		assertEquals(1,piece.getCells()[1].getCol());

		//coords (2, 2)
		assertEquals(2,piece.getCells()[2].getRow());
		assertEquals(2,piece.getCells()[2].getCol());
		
		//coords (2, 1)
		assertEquals(2,piece.getCells()[3].getRow());
		assertEquals(1,piece.getCells()[3].getCol());
		
	}
	
	//rotate s piece
	@Test
	void test9() {
		Icon[] icons = new Icon[4];
		
		for(int i = 0; i < 4; i ++) {
			icons[i] = new Icon(Color.RED);
		}
		
		RotatingSPiece piece = new RotatingSPiece(new Position(0,0), icons);
		piece.transform();
		
		//coords (0,2)
		assertEquals(0,piece.getCells()[0].getRow());
		assertEquals(2,piece.getCells()[0].getCol());
		
		//coords (1,2)
		assertEquals(1,piece.getCells()[1].getRow());
		assertEquals(2,piece.getCells()[1].getCol());

		//coords (1, 1)
		assertEquals(1,piece.getCells()[2].getRow());
		assertEquals(1,piece.getCells()[2].getCol());
		
		//coords (2, 1)
		assertEquals(2,piece.getCells()[3].getRow());
		assertEquals(1,piece.getCells()[3].getCol());
		
	}
	

}
