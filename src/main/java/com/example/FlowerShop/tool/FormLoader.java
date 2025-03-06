package com.example.FlowerShop.tool;

import com.example.FlowerShop.FlowerShopApplication;
import com.example.FlowerShop.controller.EditCustomerFormController;
import com.example.FlowerShop.controller.EditProductFormController;
import com.example.FlowerShop.model.Customer;
import com.example.FlowerShop.model.Product;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FormLoader {

    private final SpringFXMLLoader springFXMLLoader;

    public FormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    public void loadNewProductForm() {
        loadForm("/product/newProductForm.fxml", "Добавить новый товар");
    }

    public void loadMainForm() {
        loadForm("/main/mainForm.fxml", "Главное окно магазина цветов");
    }

    public void loadLoginForm() {
        loadForm("/user/loginForm.fxml", "Вход в систему");
    }

    public void loadRegistrationForm() {
        loadForm("/user/registrationForm.fxml", "Регистрация пользователя");
    }
    public void loadCustomerListForm() {
        loadForm("/customer/customerListForm.fxml", "Список покупателей");
    }


    public void loadProductListForm() {
        loadForm("/product/productListForm.fxml", "Список товаров");
    }
    public void loadNewOrderForm() {
        loadForm("/order/newOrderForm.fxml", "Создать новый заказ");
    }
    public void loadNewCustomerForm() {
        loadForm("/customer/newCustomerForm.fxml", "Добавить покупателя");
    }



    public void loadEditProductForm(Product product) {
        try {
            FXMLLoader loader = springFXMLLoader.load("/product/editProductForm.fxml");
            Parent root = loader.load();

            // Получаем контроллер и передаем продукт
            EditProductFormController controller = loader.getController();
            controller.setProduct(product);

            Scene scene = new Scene(root);
            Stage stage = getPrimaryStage();
            stage.setTitle("Редактировать товар");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке формы: /product/editProductForm.fxml", e);
        }
    }

    public void loadEditCustomerForm(Customer customer) {
        try {
            FXMLLoader loader = springFXMLLoader.load("/customer/editCustomerForm.fxml");
            Parent root = loader.load();
            EditCustomerFormController controller = loader.getController();
            controller.setCustomer(customer);
            Scene scene = new Scene(root);
            Stage stage = getPrimaryStage();
            stage.setTitle("Редактировать покупателя");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке формы: /customer/editCustomerForm.fxml", e);
        }
    }


    private void loadForm(String fxmlPath, String title) {
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

    private Stage getPrimaryStage() {
        return FlowerShopApplication.primaryStage;
    }
}
