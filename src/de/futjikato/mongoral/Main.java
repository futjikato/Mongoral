package de.futjikato.mongoral;

import de.futjikato.mongoral.views.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Mongoral");
        stage.show();

        // init login view
        LoginView lv = LoginView.getInstance();
        lv.injectStage(stage);
        lv.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
