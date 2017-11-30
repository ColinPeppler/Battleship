import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BattleshipDisplay extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// Creates grid for the game
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(1);
		
		// Set up the AI's ships
		Ship[] ships = setup();
		
		// Sets the horizontal 1-10 and vertical heading A-J
		for(int i = 1; i < 11; i++) {
			String number = String.valueOf(i);
			Text numberText = new Text(number);
			numberText.setFont(new Font("Times New Roman", 28));
			grid.add(numberText, i, 0);
			
			char letter = (char)(i+64);
			Text letterText = new Text(String.valueOf(letter));
			letterText.setFont(new Font("Times New Roman", 28));
			grid.add(letterText, 0, i);
		}
		
		// Fills the grid with squares
		for(int column = 1; column < 11; column++) {
			final int c = column;	// final variable is used, so that the variable can be accessed inside the lambda expression
			for(int row = 1; row < 11; row++) {
				final int r = row;
				
				Rectangle gridBox = new Rectangle();
				gridBox.setStroke(Color.BLACK);
				gridBox.setFill(Color.BLUE);
				gridBox.setHeight(25);
				gridBox.setWidth(25);
				grid.add(gridBox, column, row);
				gridBox.setOnMouseClicked(e -> {
					Rectangle missBox = new Rectangle();
					missBox.setStroke(Color.BLACK);
					missBox.setFill(Color.WHITE);
					missBox.setHeight(25);
					missBox.setWidth(25);
					
					grid.add(missBox, c, r);
					
					System.out.println("Missed");
					
				});
			}
		}
		
		// Adds the ships to the grid
		for(Ship s : ships) {
			int rowStart = s.getYInitial();
			int rowEnd = s.getYFinal();
			int columnStart = s.getXInitial();
			int columnEnd = s.getXFinal();
			
			for(int column = columnStart; column <= columnEnd; column++) {
				final int c = column;
				for(int row = rowStart; row <= rowEnd; row++) {
					final int r = row;
					
					Rectangle shipBox = new Rectangle();
					shipBox.setStroke(Color.BLACK);
					shipBox.setFill(Color.GREEN);
					shipBox.setHeight(25);
					shipBox.setWidth(25);
					grid.add(shipBox, column, row);
					
					// Lambda expression for when player clicks on box with a ship
					shipBox.setOnMouseClicked(e -> {
						Rectangle hitBox = new Rectangle();
						hitBox.setStroke(Color.BLACK);
						hitBox.setFill(Color.RED);
						hitBox.setHeight(25);
						hitBox.setWidth(25);
						grid.add(hitBox, c, r);
						
						s.damaged();
						if(s.isDestroyed()) {
							System.out.println(s.getName() + " is destroyed");
						}
					});
					
				}
			}
		}
		
		// Creates a BorderPane
		BorderPane layout = new BorderPane();
		layout.setCenter(grid);
		
		// Creates and displays scene
		Scene primaryScene = new Scene(layout, 400, 400);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
	
	// Creates all 5 ships
	public Ship[] setup() {
		BattleshipAI bot = new BattleshipAI();
		System.out.println(bot.getSpaces());
		
		Ship[] ships = bot.getShips();
		
		return ships;
	}
}

