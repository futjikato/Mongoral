package de.futjikato.mongoral;

import de.futjikato.mongoral.com.Com;
import de.futjikato.mongoral.views.DatabaseView;
import de.futjikato.mongoral.views.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Mongoral");
        stage.setResizable(false);
        stage.show();

        // get Com
        Com com = Com.getIsntance();

        // init database view
        DatabaseView dbv = new DatabaseView();
        dbv.injectStage(stage);
        com.addObserver(dbv);

        // init login view
        LoginView lv = LoginView.getInstance();
        lv.injectStage(stage);
        com.addObserver(lv);

        // send start command via com
        com.notifyAppStart();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
