package Chat.Client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

import java.io.IOException;


public class ChatController implements Initializable {

    public ListView<String> listView;
    public TextField input;
    public Button delete;
    private Network network;


    public void click(MouseEvent mouseEvent) {
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("MOUSE_CLICKED");
            }
        });

    }

    public void clear(ActionEvent actionEvent) {
        input.clear();
        Tooltip tooltip = new Tooltip();
        tooltip.setText("You can clear the field");
        delete.setTooltip(tooltip);
    }

    public void sendMessage(ActionEvent actionEvent) throws IOException {
        network.writeMessage(input.getText());
        input.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        network = Network.getInstance();

        new Thread(() -> {
            try {
                while (true) {
                    String message = network.readMessage();
                    if (message.equals("exit")) {
                        network.close();
                        break;
                    }
                    Platform.runLater(() -> listView.getItems().add(message));
                }
            } catch (IOException ioException) {
                System.err.println("SERVER WAS BROKEN...");
                Platform.runLater(() -> listView.getItems().add("SERVER WAS BROKEN..."));
            }
        }).start();

    }
}
