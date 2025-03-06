package com.example.FlowerShop.controller;

import com.example.FlowerShop.model.AppUser;
import com.example.FlowerShop.service.AppUserService;
import com.example.FlowerShop.service.FromService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class RegistrationFromController {

    private final AppUserService appUserService;
    private final FromService fromService;

    @FXML private TextField tfFirstname;
    @FXML private TextField tfLastname;
    @FXML private TextField tfUsername;
    @FXML private TextField pfPassword;

    public RegistrationFromController(AppUserService appUserService, FromService fromService) {
        this.appUserService = appUserService;
        this.fromService = fromService;
    }

    @FXML
    private void registerUser(){
        try {
            AppUser newUser = new AppUser();
            newUser.setFirstname(tfFirstname.getText());
            newUser.setLastname(tfLastname.getText());
            newUser.setUsername(tfUsername.getText());
            newUser.setPassword(pfPassword.getText());
            newUser.getRoles().add(AppUserService.ROLES.USER.toString());

            appUserService.add(newUser);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Пользователь зарегистрирован!");
            alert.showAndWait();

            fromService.loadLoginForm();
        } catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ошибка: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
