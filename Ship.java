
public class Ship {
	private String name;
	private int size;
	private int numberOfHits = 0;
	private String direction;
	private int xInitial = 1;
	private int yInitial = 1;
	private int xFinal = 1;
	private int yFinal = 1;

	public Ship(String name, int size) {
		this.name = name;
		this.size = size;
	}
	
	// GETTER Methods
	public int getSize() {
		return size;
	}
	
	public String getDirection() {
		return direction;
	}
	
	public int getXInitial() {
		return xInitial;
	}
	
	public int getYInitial() {
		return yInitial;
	}
	
	public int getXFinal() {
		return xFinal;
	}
	
	public int getYFinal() {
		return yFinal;
	}
	
	// SETTER Methods
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void setXInitial(int xInitial) {
		this.xInitial = xInitial;
	}
	
	public void setYInitial(int yInitial) {
		this.yInitial = yInitial;
	}
	
	public void setXFinal(int xFinal) {
		this.xFinal = xFinal;
	}
	
	public void setYFinal(int yFinal) {
		this.yFinal = yFinal;
	}
	
	// increments the number of times the ship gets hit
	public void damaged() {
		numberOfHits++;
	}

	// returns a boolean to tell if the ship is completely destroyed
	public boolean isDestroyed() {
		if (numberOfHits == size) {
			return true;
		}
		return false;
	}
	
	// finds a random direction for the ship to face
	public void findDirection() {
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
		
		setDirection(direction);
	}
	
	public void setPosition() {
		int min = 1;
		int possibleSpaces = 10 - size;
		
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
		
		setXInitial(xInitial);
		setYInitial(yInitial);
		setXFinal(xFinal);
		setYFinal(yFinal);
	}

	@Override
	public String toString() {
		String status;
		if (isDestroyed()) {
			status = "is destroyed";
		} else {
			status = "is not destroyed";
		}
		return String.format("%s %s \n x0: %d xf: %d \n y0: %d yf: %d", name, status, xInitial, xFinal, yInitial, yFinal);
	}

}
