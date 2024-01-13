package org.pathz.graphic;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private Button btn;

    @FXML
    private TextField textEntry;

    @FXML
    void findUser(ActionEvent event) throws InterruptedException {
        new Thread(() -> {
            String userByUsername = null;
            try {
                userByUsername = UserDao.findUserByUsername(textEntry.getText());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(userByUsername);
        }).start();
    }
}
