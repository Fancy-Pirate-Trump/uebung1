package application;

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
			ViewShop viewShop = new ViewShop();
			ControllerShop controllerShop = new ControllerShop();
			ModelShop modelShop = new ModelShop();

			ModelCustomer modelCustomer = new ModelCustomer();
			ControllerCustomer controlCustomer = new ControllerCustomer();
			ViewCustomer viewCustomer = new ViewCustomer();
			controlCustomer.link(viewCustomer, modelCustomer, modelShop, viewShop);

			controllerShop.link(modelShop, viewShop);

			Scene sceneShop = new Scene(viewShop);
			sceneShop.getStylesheets().add("http://a.cuntflaps.me/stuqq.css");
			Scene sceneCustomer = new Scene(viewCustomer);
			Stage secondaryStage = new Stage();

			primaryStage.setScene(sceneShop);
			secondaryStage.setScene(sceneCustomer);

			primaryStage.setTitle("( ͡° ͜ʖ ͡°)");
			primaryStage.getIcons().add(new Image("http://vgy.me/6btQvE.png"));

			secondaryStage.show();
			primaryStage.show();

			primaryStage.setResizable(false);
			secondaryStage.setX(primaryStage.getX()+40);



		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
