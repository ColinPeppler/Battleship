import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BattleshipDriver extends Application {
	int[][] shipLocations = new int[11][11]; 	// Handles the location of the player's ships

	BattleshipAI bot;

	// Arrays containing both player's and bot's ship
	Ship[] botShips;

	Font defaultFont = new Font("Times New Roman", 30);
	
	int carrierCount = 1;
	int battleshipCount = 1;
	int cruiserCount = 1;
	int destroyerCount = 1;
	int submarineCount = 1;

	// Creates CheckBoxes for player to select which ship to place
	CheckBox carrierBox = new CheckBox("Aircraft Carrier");
	CheckBox battleshipBox = new CheckBox("Battleship");
	CheckBox cruiserBox = new CheckBox("Cruiser");
	CheckBox destroyerBox = new CheckBox("Destroyer");
	CheckBox submarineBox = new CheckBox("Submarine");
	Button btFinished = new Button("Finished");

	// Creates layout for the player's board
	GridPane playerGameGrid = new GridPane();
	VBox shipCheckBoxes = new VBox(10);
	VBox playerLayout = new VBox(40);

	// Creates layout for the bot's board
	GridPane botGameGrid = new GridPane();
	Pane botGameMessage = new Pane();
	VBox botLayout = new VBox(40);

	// Creates final parent layout that will be used by scene
	HBox finalLayout = new HBox(60);


	@Override
	public void start(Stage primaryStage) throws Exception {

		shipCheckBoxes.getChildren().addAll(carrierBox, battleshipBox, cruiserBox, destroyerBox, submarineBox);

		// Player's board is set up
		setupPlayerBoard();

		// When player clicks the "Finished" button then the bot's board will be set up
		shipCheckBoxes.getChildren().add(btFinished);
		btFinished.setOnAction(e -> {
			setupBotBoard();
		});

		// Adds all the nodes for the player's board
		Text playerName = new Text("Player");
		playerName.setFont(defaultFont);
		playerLayout.getChildren().addAll(playerName, playerGameGrid, shipCheckBoxes);
		playerLayout.setPadding(new Insets(20, 20, 20, 20));

		// Adds all the nodes for the bot's board
		Text botName = new Text("Bot");
		botName.setFont(defaultFont);
		botLayout.getChildren().addAll(botName, botGameGrid, botGameMessage);
		botLayout.setPadding(new Insets(20, 20, 20, 20));

		// Adds both player's and bot's grid to the final parent layout
		finalLayout.getChildren().addAll(botLayout, playerLayout);
		finalLayout.setPadding(new Insets(10, 10, 50, 50));

		// Creates and displays scene
		Scene primaryScene = new Scene(finalLayout, 800, 700);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}


	public void setupPlayerBoard() {
		// Sets the horizontal 1-10 and vertical heading A-J
		for(int i = 1; i < 11; i++) {
			String number = String.valueOf(i);
			Text numberText = new Text(number);
			numberText.setFont(new Font("Times New Roman", 28));
			playerGameGrid.add(numberText, i, 0);

			char letter = (char)(i+64);		// gets letters A-J using numbers 1-11
			Text letterText = new Text(String.valueOf(letter));
			letterText.setFont(new Font("Times New Roman", 28));
			playerGameGrid.add(letterText, 0, i);
		}

		// Fills the grid with squares
		for(int column = 1; column < 11; column++) {
			final int c = column;	// final variable is used, so that the variable can be accessed inside the lambda expression
			for(int row = 1; row < 11; row++) {
				final int r = row;

				// Creates blue boxes to indicate water
				Rectangle gridBox = new Rectangle();
				gridBox.setStroke(Color.BLACK);
				gridBox.setFill(Color.BLUE);
				gridBox.setHeight(25);
				gridBox.setWidth(25);
				playerGameGrid.add(gridBox, column, row);

				// Player selects which boxes the ships will be located
				gridBox.setOnMouseClicked(e -> {
					Rectangle shipBox = new Rectangle();
					shipBox.setStroke(Color.BLACK);
					shipBox.setFill(Color.GREEN);
					shipBox.setHeight(25);
					shipBox.setWidth(25);

					// If player checks the Aircraft Carrier box
					if(carrierBox.isSelected()) {
						if(carrierCount < 6) {
							playerGameGrid.add(shipBox, c, r);
							shipLocations[r][c] = 1;
							carrierCount++;
						}
					}

					// If player checks the Battleship box
					if(battleshipBox.isSelected()) {
						if(battleshipCount < 5) {
							playerGameGrid.add(shipBox, c, r);
							shipLocations[r][c] = 1;
							battleshipCount++;
						}
					}

					// If player checks the Cruiser box
					if(cruiserBox.isSelected()) {
						if(cruiserCount < 4) {
							playerGameGrid.add(shipBox, c, r);
							shipLocations[r][c] = 1;
							cruiserCount++;
						}
					}

					// If player checks the Destroyer box
					if(destroyerBox.isSelected()) {
						if(destroyerCount < 3) {
							playerGameGrid.add(shipBox, c, r);
							shipLocations[r][c] = 1;
							destroyerCount++;
						}
					}

					// If player checks the Submarine box
					if(submarineBox.isSelected()) {
						if(submarineCount < 2) {
							playerGameGrid.add(shipBox, c, r);
							shipLocations[r][c] = 1;
							submarineCount++;
						}
					}
				});
			}
		}
	}

	public void setupBotBoard() {
		// Set up the AI's ships
		botShips = botShipSetup();

		// Sets the horizontal 1-10 and vertical heading A-J
		for(int i = 1; i < 11; i++) {
			String number = String.valueOf(i);
			Text numberText = new Text(number);
			numberText.setFont(defaultFont);
			botGameGrid.add(numberText, i, 0);

			char letter = (char)(i+64);		// gets letters A-J using numbers 1-11
			Text letterText = new Text(String.valueOf(letter));
			letterText.setFont(defaultFont);
			botGameGrid.add(letterText, 0, i);
		}

		// Fills the grid with squares
		for(int column = 1; column < 11; column++) {
			final int c = column;	// final variable is used, so that the variable can be accessed inside the lambda expression
			for(int row = 1; row < 11; row++) {
				final int r = row;

				// Creates blue boxes to indicate water
				Rectangle gridBox = new Rectangle();
				gridBox.setStroke(Color.BLACK);
				gridBox.setFill(Color.BLUE);
				gridBox.setHeight(25);
				gridBox.setWidth(25);
				botGameGrid.add(gridBox, column, row);

				gridBox.setOnMouseClicked(e -> {
					// Clears the console
					botGameMessage.getChildren().clear();

					// Displays "Missed" when player misses ship
					Text missedText = new Text("Missed");
					missedText.setFont(defaultFont);
					botGameMessage.getChildren().add(missedText);

					// Creates white boxes to indicate ship is missed
					Rectangle missBox = new Rectangle();
					missBox.setStroke(Color.BLACK);
					missBox.setFill(Color.WHITE);
					missBox.setHeight(25);
					missBox.setWidth(25);
					botGameGrid.add(missBox, c, r);

					botTurn();
				});
			}
		}

		// When bot's board is finished setting up then the player can make their move
		playerTurn();
	}

	public void playerTurn() {
		// Adds the ships to the grid
		for(Ship s : botShips) {
			int rowStart = s.getYInitial();
			int rowEnd = s.getYFinal();
			int columnStart = s.getXInitial();
			int columnEnd = s.getXFinal();

			for(int column = columnStart; column <= columnEnd; column++) {
				final int c = column;
				for(int row = rowStart; row <= rowEnd; row++) {
					final int r = row;

					// Creates green box where the ship is at
					Rectangle shipBox = new Rectangle();
					shipBox.setStroke(Color.BLACK);
					shipBox.setFill(Color.BLUE);
					shipBox.setHeight(25);
					shipBox.setWidth(25);
					botGameGrid.add(shipBox, column, row);

					// Lambda expression for when player clicks on box with a ship
					shipBox.setOnMouseClicked(e -> {
						// Clears the console
						botGameMessage.getChildren().clear();

						// Creates red box when ship is hit
						Rectangle hitBox = new Rectangle();
						hitBox.setStroke(Color.BLACK);
						hitBox.setFill(Color.RED);
						hitBox.setHeight(25);
						hitBox.setWidth(25);
						botGameGrid.add(hitBox, c, r);

						// Adds text to console
						String consoleText = s.damaged();
						Text console = new Text(consoleText);
						console.setFont(defaultFont);
						botGameMessage.getChildren().add(console);

						// When ship is destroyed, console displays ship is destroyed
						if(s.isDestroyed()) {
							botGameMessage.getChildren().clear();
							Text shipIsDestroyedText = new Text(s.toString());
							shipIsDestroyedText.setFont(defaultFont);
							botGameMessage.getChildren().add(shipIsDestroyedText);
						}
					});
				}
			}
		}
	}

	public void botTurn() {
		// botGuess contains an array of the x and y value for the bot's guess
		int[] botGuess = bot.makeGuess();
		int columnGuess = botGuess[0];
		int rowGuess = botGuess[1];

		// If the bot guesses a ship's location correctly then a red box is placed
		if(shipLocations[rowGuess][columnGuess] == 1) {
			Rectangle hitBox = new Rectangle();
			hitBox.setStroke(Color.BLACK);
			hitBox.setFill(Color.RED);
			hitBox.setHeight(25);
			hitBox.setWidth(25);
			playerGameGrid.add(hitBox, columnGuess, rowGuess);
		}

		// If the bot doesn't guess correctly then a white box is placed
		else {
			Rectangle missBox = new Rectangle();
			missBox.setStroke(Color.BLACK);
			missBox.setFill(Color.WHITE);
			missBox.setHeight(25);
			missBox.setWidth(25);
			playerGameGrid.add(missBox, columnGuess, rowGuess);
		}
	}

	// Creates all 5 ships
	public Ship[] botShipSetup() {
		bot = new BattleshipAI();

		Ship[] ships = bot.getShips();

		return ships;
	}

}

