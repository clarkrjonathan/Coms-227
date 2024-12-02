package hw3;

import java.util.ArrayList;
import java.util.Scanner;

import api.Cell;
import api.Direction;
import api.Orientation;
import api.CellType;

public class Main {
	
	public static void main(String[] args) {
		
		String[] gameGrid =  {"* * * * * * * *"
							, "* [ ] ^ ^ . . *"
							, "* . . v v [ ] *"
							, "* [ # # ] ^ . *"
							, "* ^ [ ] . v ^ e"
							, "* # ^ . [ ] # *"
							, "* # v . [ ] v *"
							, "* v [ # # # ] *"
							, "* * * * * * * *"};
		String[][] cells = new String[gameGrid.length][8];
		
		for (int i = 0; i < gameGrid.length; i++) {
			Scanner scner = new Scanner(gameGrid[i]);
			for(int j = 0; j < cells[0].length; j++) {
				cells[i][j] = scner.next();
			}
			scner.close();
		}
		
		String[][] desc = {{"*", "*", "*", "*", "*"},
				{"*", ".", ".", ".", "*"},
				{"[", "#", "]", "^", "e"},
				{"*", ".", ".", "#", "*"},
				{"*", ".", ".", "#", "*"},
				{"*", "*", "*", "v", "*"}};
		
		Board board = new Board(desc);
		
		board.grabBlockAtCell(2, 0);
		
		System.out.println(board.getGrabbedBlock());
		
		board.moveGrabbedBlock(Direction.LEFT);
		
		board.getAllPossibleMoves();
		
		
//		Solver solver = new Solver(13);
//		
//		solver.solve(board);
//		solver.printSolutions();
//		
//		System.out.println(board);
//		
//		
		
	}
	
	

}
