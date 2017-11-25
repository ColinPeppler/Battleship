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
		for(int row = 1; row < 11; row++) {
			for(int column = 1; column < 11; column++) {
				Rectangle gridBox = new Rectangle();
				gridBox.setStroke(Color.BLACK);
				gridBox.setFill(Color.WHITE);
				gridBox.setHeight(25);
				gridBox.setWidth(25);
				grid.add(gridBox, row, column);
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

}
