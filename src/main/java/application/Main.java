package application;

import gui.ControllerShop;
import gui.ModelShop;
import gui.ViewCustomer;
import gui.ViewShop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			ViewShop     viewShop     = new ViewShop();
			ViewCustomer viewCustomer = new ViewCustomer();
			ControllerShop controllerShop = new ControllerShop();
			ModelShop modelShop = new ModelShop();
			
			controllerShop.link(modelShop, viewShop);

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
