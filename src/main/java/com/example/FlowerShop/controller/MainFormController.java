package com.example.FlowerShop.controller;

import com.example.FlowerShop.tool.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainFormController implements Initializable {

    private final SpringFXMLLoader springFXMLLoader;

    @FXML private VBox menuContainer;

    public MainFormController(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadMenu();
    }

    private void loadMenu() {
        try {
            VBox menu = springFXMLLoader.load("/menu/menuForm.fxml").load();
            menuContainer.getChildren().add(menu);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке меню", e);
        }
    }

    @FXML private void showEditOrderForm() {
    }

    @FXML private void showNewOrderForm() {
    }
}
