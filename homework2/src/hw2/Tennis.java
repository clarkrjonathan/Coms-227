package hw2;

/*
 * Runs the logic for scoring a Tennis match.
 */
public class Tennis {

	/*
	 * Player A's numeric points this set
	 */
	private int playerAPoints;

	/*
	 * Player A's sets
	 */
	private int playerASets;

	/*
	 * Player A's games
	 */
	private int playerAGames;

	/*
	 * Player A's name
	 */
	private String playerAName;

	/*
	 * Player A serving
	 */
	private boolean playerAServing;

	/*
	 * Player B's numeric points this set
	 */
	private int playerBPoints;

	/*
	 * Player B's sets
	 */
	private int playerBSets;

	/*
	 * Player B's games
	 */
	private int playerBGames;

	/*
	 * Player B's name
	 */
	private String playerBName;

	/**
	 * True if player A won the last game
	 */
	private boolean playerAWonLastGame;

	/*
	 * True if player A won the last set
	 */
	private boolean playerAWonLastSet;
	
	private boolean playerAServedFirstLastGame;

	/*
	 * If true, matches are best of 5. Otherwise, two sets are required to win a
	 * best of three.
	 */
	private boolean isBestOfFive;

	// tracks serving for tiebreak games
	private int tiebreakServeCount;

	/*
	 * When true, if the number of games is tied at 6-6, then the next game will be
	 * a tiebreak game, in which we count scores normally and the winner must attain
	 * a score of (usually) 7 and lead by 2, in order to win the set, with the only
	 * exception being if it is a grandslam.
	 */
	private boolean isPlayingTiebreaks;

	private boolean isTiebreakGame;

	private boolean playerAWonMatch;

	private int totalGames;

	private boolean matchOver;

	/*
	 * When isPlayingTiebreaks is true and isGrandSlam is also true, if the number
	 * of games in the last set is 6-6, then the next game will be a tiebreak game
	 * and the winner must attain a score of 10 and lead by 2, in order to win the
	 * match.
	 */
	private boolean isGrandSlam;
	
	private boolean isGrandSlamSet;

	/**
	 * Accepts names of two players, and an indication of whether this is a best of
	 * five sets match or else a best of three sets match.
	 * 
	 * @param playerAName        name of the player that serves first in the match
	 * @param playerBName        name of the player that receives first in the match
	 * @param isBestOfFive       When true, three sets are required to win.
	 *                           Otherwise, two sets are required to win a best of
	 *                           three.
	 * @param isPlayingTiebreaks When true, if the number of games is tied at 6-6,
	 *                           then the next game will be a tiebreak game, in
	 *                           which we count scores normally and the winner must
	 *                           attain a score of (usually) 7 and lead by 2, in
	 *                           order to win the set, with the only exception being
	 *                           the following case
	 * @param isGrandSlam        When isPlayingTiebreaks is true and isGrandSLam is
	 *                           also true, if the number of games in the last set
	 *                           is 6-6, then the next game will be a tiebreak like game
	 *                           and the winner must attain a score of 10 and lead
	 *                           by 2, in order to win the match.
	 */
	public Tennis(String playerAName, String playerBName, boolean isBestOfFive, boolean isPlayingTiebreaks,
			boolean isGrandSlam) {
		this.playerAName = playerAName;
		this.playerBName = playerBName;

		this.isBestOfFive = isBestOfFive;
		this.isPlayingTiebreaks = isPlayingTiebreaks;
		this.isGrandSlam = isGrandSlam;

		this.playerAServing = true;
		this.isTiebreakGame = false;

		this.matchOver = false;
		this.tiebreakServeCount = 1;
		this.totalGames = 0;
		this.playerAServedFirstLastGame = true;
		
		this.isGrandSlamSet = false;
	}

	/**
	 * Prints out what the announcer must say on the mic to the crowd.
	 */
	public String getCallOut() {
		/*
		 * TODO implement
		 */
		// if both scores zero should return winner of last game
		if (playerAPoints > 0 || playerBPoints > 0) {

			if (playerAPoints == playerBPoints) {

				if (playerAPoints >= 3 && !isTiebreakGame && !isGrandSlamSet) {
					return "Deuce";
				} else {
					return calloutWord(playerAPoints) + "-All";
				}
			}
			if (isTiebreakGame || isGrandSlamSet) {
				if (playerAPoints > playerBPoints) {
					return calloutWord(playerAPoints) + "-" + calloutWord(playerBPoints) + " " + playerAName;
				} else {
					return calloutWord(playerBPoints) + "-" + calloutWord(playerAPoints) + " " + playerBName;
				}
			} else if (playerAServing) {
				return calloutWord(playerAPoints) + "-" + calloutWord(playerBPoints);
			} else {
				return calloutWord(playerBPoints) + "-" + calloutWord(playerAPoints);
			}

		} else {
			// game was won or start of match
			if (playerAGames + playerASets + playerBGames + playerBSets > 0) {
				// not start of match
				if (matchOver) {
					// somebody won last set
					return "Game, Set and Match: " + (playerAWonMatch ? playerAName : playerBName);
				} else if (playerASets + playerBSets > 0) {
					return "Game and Set: " + (playerAWonLastSet ? playerAName : playerBName);
				}
				return "Game: " + (playerAWonLastGame ? playerAName : playerBName);
			}
			return "";
		}

	}

	/*
	 * returns word associated with points 0,1,2,3 etc.
	 */
	private String calloutWord(int points) {

		if (isTiebreakGame || isGrandSlam) {
			return String.valueOf(points);
		}

		switch (points) {
		case 0:
			return "Love";
		case 1:
			return "Fifteen";
		case 2:
			return "Thirty";
		case 3:
			return "Forty";
		default:
			return null;
		}
	}

	/**
	 * Gets one of the components of the overall score.
	 */
	public int getPlayerAGames() {
		return playerAGames;
	}

	/**
	 * Gets the name of PlayerA as provided in the constructor call
	 */
	public String getPlayerAName() {
		return playerAName;
	}

	/**
	 * Returns the playerA score as a string of length exactly 2, using the string
	 * representation of 0,15,30,40 as appropriate.
	 */
	public String getPlayerAScore() {
		if (!isTiebreakGame && !isGrandSlamSet) {
			switch (playerAPoints) {
			case 0:
				return " 0";
			case 1:
				return "15";
			case 2:
				return "30";
			default:
				return "40";
			}
		} else {
			return " " + String.valueOf(playerAPoints);
		}
	}

	/**
	 * Indicates who is serving next.
	 */
	public boolean getPlayerAServing() {
		return playerAServing;
	}

	/**
	 * Gets one of the components of the overall score.
	 */
	public int getPlayerASets() {
		return playerASets;
	}

	/**
	 * gets one of the components of the overall score.
	 */
	public int getPlayerBGames() {
		return playerBGames;
	}

	/**
	 * Gets the name of playerB as provided in the constructor call
	 */
	public String getPlayerBName() {
		return playerBName;
	}

	/**
	 * Returns the playerB score as a string of length exactly 2, using the string
	 * representation of 0,15,30,40 as appropriate.
	 */
	public String getPlayerBScore() {

		if (!isTiebreakGame && !isGrandSlamSet) {
			switch (playerBPoints) {
			case 0:
				return " 0";
			case 1:
				return "15";
			case 2:
				return "30";
			default:
				return "40";
			}
		} else {
			return " " + String.valueOf(playerBPoints);
		}
	}

	/**
	 * Gets one of the components of the overall score.
	 */
	public int getPlayerBSets() {
		return playerBSets;
	}

	/**
	 * checks if the current game has been won, returns true if yes
	 */
	private boolean isGameWon() {

		int amountToWin;
		if (isTiebreakGame) {
			amountToWin = 7;
		} else if (isGrandSlamSet){
			amountToWin = 10;
		} else {
			amountToWin = 3;
		}
		// has to reach 40 to win
		if (playerAPoints >= amountToWin | playerBPoints >= amountToWin) {

			// both at or above 40
			if (playerAPoints >= amountToWin && playerBPoints >= amountToWin) {
				if (Math.abs(playerAPoints - playerBPoints) >= 2) {
					// still winner
					if (playerAPoints > playerBPoints) {
						// player A wins
						winGame(true);
					} else {
						winGame(false);
					}

					return true;

				} else {
					// break loop no winner
					return false;
				}
			} else {
				if (!isTiebreakGame || !isGrandSlamSet || Math.abs(playerAPoints - playerBPoints) >= 2) {
					if (playerAPoints > playerBPoints) {
						// player A won
						winGame(true);
					} else {
						// player B won
						winGame(false);
					}
				} 
			}

		}
		// no winner
		return false;
	}

	/**
	 * Call this method with a value of true to indicate that playerA has won a game
	 * 
	 * @param playerAWins True if playerA has won
	 */
	public void winGame(boolean playerAWins) {
		totalGames++;
		if (playerAWins) {
			playerAWonLastGame = true;
			playerAGames++;
		} else {
			playerAWonLastGame = false;
			playerBGames++;
		}

		// check if next game is tiebreak
		if (isPlayingTiebreaks) {
			if (!isTiebreakGame) {
				if (playerAGames == 6 && playerBGames == 6 && !isGrandSlamSet) {
					
					isTiebreakGame = true;
				}
			} else {
				//if a tiebreak game is won, then automatically set is won
				isTiebreakGame = false;
				winSet(playerAWins);
			}
		}
		
		playerAServing = totalGames % 2 == 0;


		playerAPoints = 0;
		playerBPoints = 0;
	}

	/**
	 * Call this method with a value of true to indicate that playerA has won a
	 * point.
	 * 
	 * @param playerAWins True if playerA has won a point
	 */
	public void winPoint(boolean playerAWins) {
		if (playerAWins) {
			playerAPoints++;
		} else {
			playerBPoints++;
		}
		if (isGameWon()) {
			isSetWon();
		}

		if (isTiebreakGame || isGrandSlamSet) {
			tiebreakServeCount++;
			if (tiebreakServeCount > 1) {
				tiebreakServeCount = 0;
				playerAServing = !playerAServing;
			}
		} else {
			playerAServing = !playerAServing;
		}
	}

	private boolean isSetWon() {
		if (!isTiebreakGame && !isGrandSlam) {
			if (playerAGames >= 6 | playerBGames >= 6) {

				if (playerAGames >= 6 && playerBGames >= 6) {
					if (Math.abs(playerAGames - playerBGames) >= 2) {
						// still winner
						if (playerAGames > playerBGames) {
							// player A wins
							winSet(true);
						} else {
							winSet(false);
						}

						return true;

					} else {
						// break loop no winner
						return false;
					}
				} else {
					// there was a winner
					if (playerAGames > playerBGames) {
						// player A won
						winSet(true);
					} else {
						// player B won
						winSet(false);
					}
					return true;
				}

			}
		}
		return false;
	}

	/**
	 * Call this method with a value of true to indicate that playerA has won a set.
	 * 
	 * @param playerAWins True if playerA won a set.
	 */
	public void winSet(boolean playerAWins) {
		if (!isPlayingTiebreaks) {
			playerAServing = true;
		}
		
		if (playerAWins) {
			playerAWonLastSet = true;
			playerASets++;
		} else {
			playerAWonLastSet = false;
			playerBSets++;
		}

		int setsToWin = isBestOfFive ? 3 : 2;
		if (playerASets >= setsToWin) {
			matchOver = true;
			playerAWonMatch = true;
		} else if (playerBSets >= setsToWin) {
			matchOver = true;
			playerAWonMatch = false;
		} else if ((playerASets >= setsToWin - 1 || playerBSets >= setsToWin - 1) && isGrandSlam) {
			isGrandSlamSet = true;
		}
		
		playerAGames = 0;
		playerBGames = 0;
	}
	/*
	 * Everything below this line is given to students, either because we haven't
	 * covered loops yet, or because we want to ensure very precise formatting so
	 * that we can test by comparing strings which are printed by the same function.
	 */

	/**
	 * Prints out what the scoreboard must indicate. It counts sets and games in a
	 * natural way, with whole numbers. However points within a game are counted
	 * using the nonconsecutive numbers 0, 15, 30, 40. When there is a deuce, it is
	 * indicated as 40 for both players. If one player has an advantage, their score
	 * is represented as "AD", while the other player's score is simultaneously
	 * indicated as "--". When counting score in tiebreak games, we simply use
	 * normal counting.
	 */
	@Override
	public String toString() {
		String playerAServingIndicator;
		String playerBServingIndicator;
		if (getPlayerAServing()) {
			playerAServingIndicator = "S>";
			playerBServingIndicator = "  ";
		} else {
			playerAServingIndicator = "  ";
			playerBServingIndicator = "S>";
		}
		String returned = String.format("%2s %-12s %2d %2d %6s\n%2s %-12s %2d %2d %6s\n", playerAServingIndicator,
				getPlayerAName(), getPlayerASets(), getPlayerAGames(), getPlayerAScore(), playerBServingIndicator,
				getPlayerBName(), getPlayerBSets(), getPlayerBGames(), getPlayerBScore());

		return returned;
	}

	/**
	 * For testing purposes, converts a string of a's and b's into a sequence of
	 * calls to winPoint, using an argument of true if the corresponding character
	 * is an a, and false if the corresponding character is a b. Provides a
	 * convenient way to run many winPoint method calls with very concise notation.
	 *
	 * @param pointList - "script" that is converted into winPoint method calls.
	 */
	public void runPoints(String pointList) {
		for (int i = 0; i < pointList.length(); ++i) {
			if (pointList.charAt(i) == 'a') {
				winPoint(true);
			} else if (pointList.charAt(i) == 'b') {
				winPoint(false);
			} else {
				// skip the character silently
			}

		}
	}

	/**
	 * For testing purposes, converts a string of a's and b's into a sequence of
	 * calls to winGame, using an argument of true if the corresponding character is
	 * an a, and false if the corresponding character is a b. Provides a convenient
	 * way to run many winGame method calls with very concise notation.
	 *
	 * @param gameList - "script" that is converted into winGame method calls.
	 */
	public void runGames(String gameList) {
		for (int i = 0; i < gameList.length(); ++i) {
			if (gameList.charAt(i) == 'a') {
				winGame(true);
			} else if (gameList.charAt(i) == 'b') {
				winGame(false);
			} else {
				// skip the character silently
			}
		}
	}

	/**
	 * For testing purposes, converts a string of a's and b's into a sequence of
	 * calls to winSet, using an argument of true if the corresponding character is
	 * an a, and false if the corresponding character is a b. Provides a convenient
	 * way to run many winSet method calls with very concise notation.
	 *
	 * @param setList - "script" that is converted into winSet method calls.
	 */
	public void runSets(String setList) {
		for (int i = 0; i < setList.length(); ++i) {
			if (setList.charAt(i) == 'a') {
				winSet(true);
			} else if (setList.charAt(i) == 'b') {
				winSet(false);
			} else {
				// skip the character silently
			}
		}
	}

}
