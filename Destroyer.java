
public class Destroyer extends Ship {
	// I use the number here so that we can indicate which destroyer by using numbers
		// because in the game of Battleship there are 4 destroyers
	public Destroyer(int number) {
		super(String.format("Destroyer %d", number), 2);
	}
}
