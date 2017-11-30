
public class Battleship extends Ship {
	// I use the number here so that we can indicate which battleship by using numbers
	// because in the game of Battleship there are 2 battleships
	public Battleship(int number) {
		super(String.format("Battleship %d", number), 4);
	}
}
