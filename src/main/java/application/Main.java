package application;

import database.JDBCConnector;
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
			sceneShop.getStylesheets().add(getClass().getResource("AlternativeCSS.css").toExternalForm());
			Scene sceneCustomer = new Scene(viewCustomer);
			Stage secondaryStage = new Stage();

			primaryStage.setScene(sceneShop);
			primaryStage.show();

			secondaryStage.setScene(sceneCustomer);
			secondaryStage.show();
			secondaryStage.setX(primaryStage.getX()+400);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
