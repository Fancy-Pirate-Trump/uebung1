package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			ViewShop     viewShop     = new ViewShop();
			ViewCustomer viewCustomer = new ViewCustomer();

			Scene sceneShop     = new Scene(viewShop);
			Scene sceneCustomer = new Scene(viewCustomer);
			Stage secondaryStage = new Stage();
			
			primaryStage.setScene(sceneShop);
			primaryStage.show();
			
			secondaryStage.setScene(sceneCustomer);
			secondaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
