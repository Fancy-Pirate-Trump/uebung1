package application;

import java.util.Random;

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
			String[] css = {"DunklesMetro2.css"};
			Random random = new Random();
			sceneShop.getStylesheets().add(getClass().getResource(css[random.nextInt(css.length)]).toExternalForm());
			Scene sceneCustomer = new Scene(viewCustomer);
			Stage secondaryStage = new Stage();


			primaryStage.setScene(sceneShop);
			secondaryStage.setScene(sceneCustomer);

			primaryStage.setTitle("( ͡° ͜ʖ ͡°)");

//			secondaryStage.show();
			primaryStage.show();

			primaryStage.setResizable(false);

			secondaryStage.setX(primaryStage.getX());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
