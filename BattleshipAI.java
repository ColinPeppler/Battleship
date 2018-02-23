public class BattleshipAI {
	
	// Listing all the Ships that the game will use
	private Ship aircraftCarrier, battleship, cruiser, destroyer, submarine;
	private int[][] shipGrid;
	private int[][] shotGrid;	// Creates a grid of where the bot has shot
	private Ship[] ships;
	private int spaces = 0;
	
	
	public BattleshipAI() {
		// Sets the grid, so we know where ships are located
		shipGrid = new int[10][10];
		shotGrid = new int[10][10];
		
		// Methods that initialize ships are invoked
		makeAircraftCarrier();	
		makeBattleship();
		makeCruiser();	
		makeDestroyer();
		makeSubmarine();
		
		// Adds the newly initialized ships into an array
		ships = new Ship[]{aircraftCarrier, battleship, cruiser, destroyer, submarine};
	}
	
	public int getSpaces() {
		return spaces;
	}
	
	public Ship[] getShips() {
		return ships;
	}
	
	// Creates Ship object with an actual type
	//*******************************************************************
	private void makeAircraftCarrier() {
		do {
			aircraftCarrier = new AircraftCarrier();
		} while(!fillGrid(aircraftCarrier));
	}
	
	private void makeBattleship() {
		do {
			battleship = new Battleship();
		}while(!fillGrid(battleship));
	}
	
	private void makeCruiser() {
		do {
			cruiser = new Cruiser();
		}while(!fillGrid(cruiser));
	}
	
	private void makeDestroyer() {
		do {
			destroyer = new Destroyer();
		}while(!fillGrid(destroyer));
	}
	
	private void makeSubmarine() {
		do {
			submarine = new Submarine();
		}while(!fillGrid(submarine));
	}
	
//***********************************************************
	
	// Fills in the grid where the ships are located and makes sure the ship's position
	// doesn't overlap with other ship's positions
	private boolean fillGrid(Ship s) {
		int rowStart = s.getYInitial() - 1;
		int rowEnd = s.getYFinal() - 1;
		int columnStart = s.getXInitial() - 1;
		int columnEnd = s.getXFinal() - 1 ;
		int size = s.getSize();
		int count = 0;
		
		for(int row = rowStart; row <= rowEnd; row++) {
			for(int column = columnStart; column <= columnEnd; column++) {
				if(shipGrid[row][column] == 0) {
					count++;
					shipGrid[row][column] = 1;
				}
			}
		}
		
		if(count == size) {
			spaces += count;
			return true;
		}
		return false;
	}
	
	public int[] makeGuess() {
		int[] guessShot = new int[2];
		boolean repeat = true;
		
		// repeats until the bot makes a guess at a spot where they've never made a guess before
		do {
			int row = (int)(1 + Math.random() * 10);
			int column = (int)(1 + Math.random() * 10);
			if(shotGrid[row-1][column-1] == 0) {
				shotGrid[row-1][column-1] = 1;
				guessShot[0] = row;
				guessShot[1] = column;
				repeat = false;
			}
		}while(repeat);
		
		return guessShot;
	}
	
	
}
