package com.example.FlowerShop.controller;

import com.example.FlowerShop.service.AppUserService;
import com.example.FlowerShop.service.FromService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuFormController implements Initializable {

    private final FromService fromService;

    @FXML private Menu mProducts;
    @FXML private Menu mOrders;
    @FXML private Menu mAdmin;

    @FXML private MenuItem miAddProduct;
    @FXML private MenuItem miEditProduct;
    @FXML private MenuItem miAddOrder;

    public MenuFormController(FromService fromService) {
        this.fromService = fromService;
    }

    @FXML private void showNewProductForm() {
        fromService.loadNewProductForm();
    }

    @FXML private void showEditProductForm() {
        fromService.loadEditProductForm();
    }

    @FXML private void showNewOrderForm() {
        fromService.loadNewOrderForm();
    }

    @FXML private void logout() {
        AppUserService.currentUser = null;
        fromService.loadLoginForm();
    }

    private void configureMenuByRole() {
        boolean isAdmin = AppUserService.currentUser.getRoles()
                .contains(AppUserService.ROLES.ADMINISTRATOR.toString());
        boolean isManager = AppUserService.currentUser.getRoles()
                .contains(AppUserService.ROLES.MANAGER.toString());

        mAdmin.setVisible(isAdmin);
        miAddProduct.setVisible(isAdmin || isManager);
        miEditProduct.setVisible(isAdmin || isManager);
        miAddOrder.setVisible(isAdmin || isManager);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        configureMenuByRole();
    }
}
