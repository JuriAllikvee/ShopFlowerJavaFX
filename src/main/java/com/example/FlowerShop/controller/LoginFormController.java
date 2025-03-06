package com.example.FlowerShop.controller;

import com.example.FlowerShop.model.AppUser;
import com.example.FlowerShop.service.AppUserService;
import com.example.FlowerShop.service.FromService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginFormController {

    private final FromService fromService;
    private final AppUserService appUserService;

    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;

    public LoginFormController(FromService fromService, AppUserService appUserService) {
        this.fromService = fromService;
        this.appUserService = appUserService;
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void showRegistrationForm() {
        fromService.loadRegistrationForm();
    }

    @FXML
    private void login() {
        String username = tfUsername.getText();
        String password = pfPassword.getText();

        Optional<AppUser> optionalUser = appUserService.findByUsername(username);

        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
            AppUserService.currentUser = optionalUser.get();
            fromService.loadMainForm();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка входа");
            alert.setHeaderText(null);
            alert.setContentText("Неверное имя пользователя или пароль!");
            alert.showAndWait();
        }
    }
}
