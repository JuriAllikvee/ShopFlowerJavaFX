package com.example.FlowerShop.service;

import com.example.FlowerShop.FlowerShopApplication;
import com.example.FlowerShop.tool.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FromService {

    private final SpringFXMLLoader springFXMLLoader;

    public FromService(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    public void loadNewProductForm() {
        loadForm("/product/newProductForm.fxml", "Добавить новый товар");
    }

    public void loadMainForm(){
        loadForm("/main/mainForm.fxml", "Главное окно магазина цветов");
    }


    public void loadProductListForm(){
        loadForm("/product/productListForm.fxml", "Список товаров");
    }

    public void loadNewOrderForm(){
        loadForm("/order/newOrderForm.fxml", "Новый заказ");
    }

    public void loadOrderListForm(){
        loadForm("/order/orderListForm.fxml", "Список заказов");
    }

    public void loadLoginForm(){
        loadForm("/user/loginForm.fxml", "Вход в систему");
    }
    public void loadRegistrationForm(){
        loadForm("/user/registrationForm.fxml", "Регистрация пользователя");
    }
    public void loadEditProductForm(){
        loadForm("/product/editProductForm.fxml", "Редактировать товар");
    }



    private void loadForm(String fxmlPath, String title){
        try {
            FXMLLoader loader = springFXMLLoader.load(fxmlPath);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = getPrimaryStage();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке формы: " + fxmlPath, e);
        }
    }

    private Stage getPrimaryStage(){
        return FlowerShopApplication.primaryStage;
    }
}
