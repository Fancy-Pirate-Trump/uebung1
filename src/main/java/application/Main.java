package application;

import java.util.Random;

import gui.ControllerCustomer;
import gui.ControllerShop;
import gui.ModelCustomer;
import gui.ModelShop;
import gui.ViewCustomer;
import gui.ViewShop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
			sceneShop.getStylesheets().add("http://a.pomf.cat/mapggz.css");
			Scene sceneCustomer = new Scene(viewCustomer);
			Stage secondaryStage = new Stage();


			primaryStage.setScene(sceneShop);
			secondaryStage.setScene(sceneCustomer);

			primaryStage.setTitle("( ͡° ͜ʖ ͡°)");
			primaryStage.getIcons().add(new Image("http://a.pomf.cat/rulhzl.png"));

			secondaryStage.show();
			primaryStage.show();

			primaryStage.setResizable(false);

			secondaryStage.setX(primaryStage.getX());
			
			ModelCustomer modelCustomer = new ModelCustomer();
			ControllerCustomer controlCustomer = new ControllerCustomer();
			controlCustomer.link(viewCustomer, modelCustomer);
			
			new Thread(new Runnable(){

				@Override
				public void run() {
					modelCustomer.startDateService();
					
				}
				
				
			}).start();
			
			

		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
