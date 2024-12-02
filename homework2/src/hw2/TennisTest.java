package hw2;

public class TennisTest {
	public static void main(String[] args)
	{
		Tennis tennis = new Tennis("Jon", "Clark", true, true, true);
		
		System.out.println(tennis);
		
		tennis.runSets("abab");
		tennis.runGames("abbababaabba");
		tennis.runPoints("ababababababbabb");
		
//		tennis.runPoints("b");
//		tennis.runGames("abababababab");
//		tennis.runPoints("ababababababa");
		
//		tennis.runGames("abbababaabbaa");
		
	//	tennis.runGames("abbababaabbaa");
	//	System.out.println(tennis.getPlayerAServing() + ": Expected false");
		
	
	}

}
