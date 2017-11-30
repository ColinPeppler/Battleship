
public class Submarine extends Ship {
	// I use the number here so that we can indicate which submarine by using numbers
	// because in the game of Battleship there are 5 submarines
	public Submarine(int number) {
		super(String.format("Submarine %d", number), 1);
	}
}
