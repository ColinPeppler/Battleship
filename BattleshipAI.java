public class BattleshipAI {
	/*private AircraftCarrier aircraftCarrier;
	private Battleship battleship;
	private Cruiser cruiser;
	private Destroyer destroyer;
	private Submarine submarine;*/
	
	// Listing all the Ships that the game will use
	// 1 AircraftCarrier, 2 Battleships, 3 Cruisers, 4 Destroyers and 5 Submarines
	// total ships will be 15
	private Ship aircraftCarrier;
	private Ship battleship1, battleship2;
	private Ship cruiser1, cruiser2, cruiser3;
	private Ship destroyer1, destroyer2, destroyer3, destroyer4;
	private Ship submarine1, submarine2, submarine3, submarine4, submarine5;
	
	private int[][] grid;
	private Ship[] ships;
	private int spaces = 0;
	
	public BattleshipAI() {
		// Sets the grid, so we know where ships are located
		grid = new int[10][10];
		
		// Methods that initialize ships are invoked
		makeAircraftCarrier();	
		makeBattleships();
		makeCruisers();	
		makeDestroyers();
		makeSubmarines();
		
		// Adds the newly initialized ships into an array
		ships = new Ship[]{aircraftCarrier,
				battleship1, battleship2,
				cruiser1, cruiser2, cruiser3,
				destroyer1, destroyer2, destroyer3, destroyer4,
				submarine1, submarine2, submarine3, submarine4, submarine5};
	}
	
	public int getSpaces() {
		return spaces;
	}
	
	public Ship[] getShips() {
		return ships;
	}
	
	// Creates ships with an actual type
//*******************************************************************
	private void makeAircraftCarrier() {
		do {
			aircraftCarrier = new AircraftCarrier();
		} while(!fillGrid(aircraftCarrier));
	}
	
	private void makeBattleships() {
		do {
			battleship1 = new Battleship(1);
		}while(!fillGrid(battleship1));
		
		do {
			battleship2 = new Battleship(2);
		}while(!fillGrid(battleship2));
	}
	
	private void makeCruisers() {
		do {
			cruiser1 = new Cruiser(1);
		}while(!fillGrid(cruiser1));
		
		do {
			cruiser2 = new Cruiser(2);
		}while(!fillGrid(cruiser2));
		
		do {
			cruiser3 = new Cruiser(3);
		}while(!fillGrid(cruiser3));
	}
	
	private void makeDestroyers() {
		do {
			destroyer1 = new Destroyer(1);
		}while(!fillGrid(destroyer1));
		
		do {
			destroyer2 = new Destroyer(2);
		}while(!fillGrid(destroyer2));
		
		do {
			destroyer3 = new Destroyer(3);
		}while(!fillGrid(destroyer3));
		
		do {
			destroyer4 = new Destroyer(4);
		}while(!fillGrid(destroyer4));
	}
	
	private void makeSubmarines() {
		do {
			submarine1 = new Submarine(1);
		}while(!fillGrid(submarine1));
		
		do {
			submarine2 = new Submarine(2);
		}while(!fillGrid(submarine2));
		
		do {
			submarine3 = new Submarine(3);
		}while(!fillGrid(submarine3));
		
		do {
			submarine4 = new Submarine(4);
		}while(!fillGrid(submarine4));
		
		do {
			submarine5 = new Submarine(5);
		}while(!fillGrid(submarine5));
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
				if(grid[row][column] == 0) {
					count++;
					grid[row][column] = 1;
				}
			}
		}
		
		
		if(count == size) {
			spaces += count;
			return true;
		}
		return false;
	}
}
