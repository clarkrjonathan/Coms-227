package hw4;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.jupiter.api.Test;

import api.Icon;
import api.Position;

/**
 * List of test cases for testing my implementation
 * @author Jonathan Clark
 *
 */
class TestCases {

	
	@Test
	void testConstructor() {
		Icon[] icons = new Icon[4];
		for(int i = 0; i < 4; i ++) {
			icons[0] = new Icon(Color.RED);
	}
		LShapedPiece piece = new LShapedPiece(new Position(0, 0), icons);
		assertEquals(piece.getCells()[0].getCol(), 0);
	}
	
	

}
