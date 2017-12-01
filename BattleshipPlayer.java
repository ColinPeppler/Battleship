
public class BattleshipPlayer {
	/*private int[][] carrierPositions;
	private int[][] battleshipPositions;
	private int[][] cruiserPositions;
	private int[][] destroyerPositions;
	private int[][] submarinePositions;*/
	
	private Ship ship;
	private int shipSize;
	private int[][] shipPositions;
	private int counter;
	private int xInitial = 1;
	private int yInitial = 1;
	private int xFinal = 1;
	private int yFinal = 1;
	
	public BattleshipPlayer(int shipSize) {
		this.shipSize = shipSize;
		findShip();
		shipPositions = new int[shipSize][2];		// Creates a two dimensional array that contains x and y, the row indicates position number
													// the 1st column contains x values, the 2nd column contains y values
	}
	
	public Ship getShip() {
		return ship;
	}
	
	// Declares the ship's actual type based off the size of the ship
	private void findShip() {
		switch(shipSize){
			case 5: ship = new AircraftCarrier();
				break;
			case 4: ship = new Battleship();
				break;
			case 3: ship = new Cruiser();
				break;
			case 2: ship = new Destroyer();
				break;
			case 1: ship = new Submarine();
				break;
		}
	}
	
	public void sendPosition(int x, int y) {
		shipPositions[counter][0] = x;
		shipPositions[counter][1] = y;
		counter++;
		System.out.printf("Counter %d, column %d, row %d", counter, x, y);
	}
	
	// Sets initial along with final x and y values
	public void setXandY() {
		xInitial = shipPositions[0][0];
		yInitial = shipPositions[0][1];
		xFinal = shipPositions[shipSize-1][0];
		yFinal = shipPositions[shipSize-1][1];
	}
	
	/*public void calculateDirection() {
		if(yInitial > yFinal) {
			ship.setDirection("North");
		}
		else if(yInitial < yFinal) {
			ship.setDirection("South");
		}
		else if(xInitial > xFinal) {
			ship.setDirection("West");
		}
		else if(xInitial < xInitial) {
			ship.setDirection("East");
		}
	}*/
	
	
	
	
}
