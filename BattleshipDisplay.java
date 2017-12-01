import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BattleshipDisplay extends Application {
	
	BattleshipAI bot;
	boolean playerTurn = true;
	
	
	// Creates grid for the player
	GridPane playerGrid = new GridPane();
	
	BorderPane test = new BorderPane();
	
	VBox checkBoxes = new VBox();
	
	// Creates console layout
	Pane botConsole = new Pane();

	// Creates grid for the bot
	GridPane botGrid = new GridPane();
	
	// Creates console for the player
	Pane playerConsole = new Pane();
	
	// Creates CheckBoxes for player to select which ship to place
	CheckBox carrierBox = new CheckBox("Aircraft Carrier");
	CheckBox battleshipBox = new CheckBox("Battleship");
	CheckBox cruiserBox = new CheckBox("Cruiser");
	CheckBox destroyerBox = new CheckBox("Destroyer");
	CheckBox submarineBox = new CheckBox("Submarine");
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		checkBoxes.getChildren().addAll(carrierBox, battleshipBox, cruiserBox, destroyerBox, submarineBox);

		// Creates BattleshipPlayer instances which will provide information about the player's designated ships
		BattleshipPlayer aircraftCarrier = new BattleshipPlayer(5);
		BattleshipPlayer battleship = new BattleshipPlayer(4);
		BattleshipPlayer cruiser = new BattleshipPlayer(3);
		BattleshipPlayer destroyer = new BattleshipPlayer(2);
		BattleshipPlayer submarine = new BattleshipPlayer(1);

		// Creates an array of Ships from BattleshipPlayer instances
		Ship playerAircraftCarrier = aircraftCarrier.getShip();
		Ship playerBattleship = battleship.getShip();
		Ship playerCruiser = cruiser.getShip();
		Ship playerDestroyer = destroyer.getShip();
		Ship playerSubmarine = submarine.getShip();
		Ship[] playerShips = {playerAircraftCarrier, playerBattleship, playerCruiser, playerDestroyer, playerSubmarine};


		// Sets the horizontal 1-10 and vertical heading A-J
		for(int i = 1; i < 11; i++) {
			String number = String.valueOf(i);
			Text numberText = new Text(number);
			numberText.setFont(new Font("Times New Roman", 28));
			playerGrid.add(numberText, i, 0);

			char letter = (char)(i+64);		// gets letters A-J using numbers 1-11
			Text letterText = new Text(String.valueOf(letter));
			letterText.setFont(new Font("Times New Roman", 28));
			playerGrid.add(letterText, 0, i);
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
				playerGrid.add(gridBox, column, row);

				// Player selects which boxes the ships will be located
				gridBox.setOnMouseClicked(e -> {
					Rectangle shipBox = new Rectangle();
					shipBox.setStroke(Color.BLACK);
					shipBox.setFill(Color.GREEN);
					shipBox.setHeight(25);
					shipBox.setWidth(25);

					// If player checks the Aircraft Carrier box
					if(carrierBox.isSelected()) {
						System.out.println("carrier");
						aircraftCarrier.sendPosition(c, r);
						playerGrid.add(shipBox, c, r);
					}

					// If player checks the Battleship box
					if(battleshipBox.isSelected()) {
						System.out.println("battleship");
						battleship.sendPosition(c, r);
						playerGrid.add(shipBox, c, r);
					}

					// If player checks the Cruiser box
					if(cruiserBox.isSelected()) {
						System.out.println("cruiser");
						cruiser.sendPosition(c, r);
						playerGrid.add(shipBox, c, r);
					}

					// If player checks the Destroyer box
					if(destroyerBox.isSelected()) {
						System.out.println("destroyer");
						destroyer.sendPosition(c, r);
						playerGrid.add(shipBox, c, r);
					}

					// If player checks the Submarine box
					if(submarineBox.isSelected()) {
						System.out.println("submarine");
						submarine.sendPosition(c, r);
						playerGrid.add(shipBox, c, r);
					}
				});
			}
		}





		// Set up the AI's ships
		Ship[] botShips = botShipSetup();

		// Sets the horizontal 1-10 and vertical heading A-J
		for(int i = 1; i < 11; i++) {
			String number = String.valueOf(i);
			Text numberText = new Text(number);
			numberText.setFont(new Font("Times New Roman", 28));
			botGrid.add(numberText, i, 0);

			char letter = (char)(i+64);		// gets letters A-J using numbers 1-11
			Text letterText = new Text(String.valueOf(letter));
			letterText.setFont(new Font("Times New Roman", 28));
			botGrid.add(letterText, 0, i);
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
				botGrid.add(gridBox, column, row);

				gridBox.setOnMouseClicked(e -> {
					// Clears the console
					botConsole.getChildren().clear();

					// Displays "Missed" when player misses ship
					Text missedText = new Text("Missed");
					missedText.setFont(new Font("Times New Roman", 30));
					botConsole.getChildren().add(missedText);

					// Creates white boxes to indicate ship is missed
					Rectangle missBox = new Rectangle();
					missBox.setStroke(Color.BLACK);
					missBox.setFill(Color.WHITE);
					missBox.setHeight(25);
					missBox.setWidth(25);

					botGrid.add(missBox, c, r);
					
					changeTurns();
				});
			}
		}

			// Displays in the console that's the player's turn
			botConsole.getChildren().clear();
			Text turn = new Text("Your turn!");
			turn.setFont(new Font("Times New Roman", 30));
			botConsole.getChildren().add(turn);
			
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
						shipBox.setFill(Color.GREEN);
						shipBox.setHeight(25);
						shipBox.setWidth(25);
						botGrid.add(shipBox, column, row);

						// Lambda expression for when player clicks on box with a ship
						shipBox.setOnMouseClicked(e -> {
							// Clears the console
							botConsole.getChildren().clear();

							// Creates red box when ship is hit
							Rectangle hitBox = new Rectangle();
							hitBox.setStroke(Color.BLACK);
							hitBox.setFill(Color.RED);
							hitBox.setHeight(25);
							hitBox.setWidth(25);
							botGrid.add(hitBox, c, r);

							// Adds text to console
							String consoleText = s.damaged();
							Text console = new Text(consoleText);
							console.setFont(new Font("Times New Roman", 30));
							botConsole.getChildren().add(console);

							// When ship is destroyed, console displays ship is destroyed
							if(s.isDestroyed()) {
								botConsole.getChildren().clear();
								Text shipIsDestroyed = new Text(s.toString());
								shipIsDestroyed.setFont(new Font("Times New Roman", 30));
								botConsole.getChildren().add(shipIsDestroyed);
							}
						});
					}
				}
			}
			
			// bot makes a guess
			int[] botGuess = bot.makeGuess();
			System.out.println("Bot's turn");
			for(Ship s : playerShips) {
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
						shipBox.setFill(Color.GREEN);
						shipBox.setHeight(25);
						shipBox.setWidth(25);
						botGrid.add(shipBox, column, row);

						// Lambda expression for when player clicks on box with a ship
						shipBox.setOnMouseClicked(e -> {
							// Clears the console
							botConsole.getChildren().clear();

							// Creates red box when ship is hit
							Rectangle hitBox = new Rectangle();
							hitBox.setStroke(Color.BLACK);
							hitBox.setFill(Color.RED);
							hitBox.setHeight(25);
							hitBox.setWidth(25);
							botGrid.add(hitBox, c, r);

							// Adds text to console
							String consoleText = s.damaged();
							Text console = new Text(consoleText);
							console.setFont(new Font("Times New Roman", 30));
							botConsole.getChildren().add(console);

							// When ship is destroyed, console displays ship is destroyed
							if(s.isDestroyed()) {
								botConsole.getChildren().clear();
								Text shipIsDestroyed = new Text(s.toString());
								shipIsDestroyed.setFont(new Font("Times New Roman", 30));
								botConsole.getChildren().add(shipIsDestroyed);
							}
						});
					}
				}
			}

			
		test.setCenter(playerGrid);
		test.setRight(checkBoxes);

		// Creates a BorderPane
		BorderPane layout = new BorderPane();
		layout.setPadding(new Insets(20,20,20,20));
		layout.setLeft(botGrid);
		layout.setRight(test);
		layout.setBottom(botConsole);

		// Creates and displays scene
		Scene primaryScene = new Scene(layout, 800, 800);
		primaryStage.setScene(primaryScene);
		primaryStage.show();

	}
	
	public static void setPlayerBoard() {
		
	}
	
	
	
	// Creates all 5 ships
	public Ship[] botShipSetup() {
		bot = new BattleshipAI();
		System.out.println(bot.getSpaces());

		Ship[] ships = bot.getShips();

		return ships;
	}
	
	public void changeTurns() {
		playerTurn = false;
	}

}

