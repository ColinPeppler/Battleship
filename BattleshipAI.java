
public class BattleshipAI {
	public static void main(String[] args) {
		
		//Creates ships and finds the direction the ships will be facing
		Ship aircraftCarrier = new AircraftCarrier();
		aircraftCarrier.setDirection(findDirection());
		setPosition(aircraftCarrier);
		
		Ship battleship = new Battleship();
		battleship.setDirection(findDirection());
		setPosition(battleship);
		
		Ship cruiser = new Cruiser();
		cruiser.setDirection(findDirection());
		setPosition(cruiser);
		
		Ship destroyer = new Destroyer();
		destroyer.setDirection(findDirection());
		setPosition(destroyer);
		
		Ship submarine = new Submarine();
		submarine.setDirection(findDirection());
		setPosition(submarine);
		
		/*System.out.println(carrierDirection);
		System.out.println(battleshipDirection);
		System.out.println(cruiserDirection);
		System.out.println(submarineDirection);*/
		
		
	}
	
	public static String findDirection() {
		int directionNumber = (int)(Math.random() * 4);
		String direction = "";
		//System.out.println(directionNumber);
		
		// finds the direction the ship faces based off a number 0-3
		switch(directionNumber) {
			case 0:  direction = "North";
				break;
			case 1: direction = "South";
				break;
			case 2: direction = "East";
				break;
			case 3: direction = "West";
				break;
		}
		
		return direction;
	}
	
	public static void setPosition(Ship ship) {
		String direction = ship.getDirection();
		int size = ship.getSize();
		int min = 1;
		int possibleSpaces = 10 - size;
		int xInitial = 1;
		int yInitial = 1;
		int xFinal = 1;
		int yFinal = 1;
		
		// finds the ship's position on the grid based off the direction they face
		// the position is randomly calculated
		if(direction.equals("North")){
			xInitial = (int)(1 + Math.random() * 10);
			yInitial = (int)(min + Math.random() * possibleSpaces);
			xFinal = xInitial;
			yFinal = yInitial + size - 1;
		}
		else if(direction.equals("South")) {
			min = size;
			xInitial = (int)(1 + Math.random() * 10);
			yInitial = (int)(min + Math.random() * possibleSpaces);
			xFinal = xInitial;
			yFinal = yInitial - size + 1;
		}
		else if(direction.equals("East")) {
			min = size;
			xInitial = (int)(min + Math.random() * possibleSpaces);
			yInitial = (int)(1 + Math.random() * 10);
			xFinal = xInitial - size + 1;
			yFinal = yInitial;
		}
		else if(direction.equals("West")) {
			xInitial = (int)(min + Math.random() * possibleSpaces);
			yInitial = (int)(1 + Math.random() * 10);
			xFinal = xInitial + size - 1;
			yFinal = yInitial;
		}
		
		/*System.out.println(ship);
		System.out.println(ship.getDirection());
		System.out.println(xInitial);
		System.out.println(xFinal);
		System.out.println(yInitial);
		System.out.println(yFinal);*/
		
		ship.setXInitial(xInitial);
		ship.setYInitial(yInitial);
		ship.setXFinal(xFinal);
		ship.setYFinal(yFinal);
		
	}
}
