
public class Ship {
	private String name;
	private int size;
	private int numberOfHits = 0;
	private String direction;
	private int xInitial;
	private int yInitial;
	private int xFinal;
	private int yFinal;

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

	@Override
	public String toString() {
		String status;
		if (isDestroyed()) {
			status = " is destroyed";
		} else {
			status = " is not destroyed";
		}
		return (name + status);
	}

}
