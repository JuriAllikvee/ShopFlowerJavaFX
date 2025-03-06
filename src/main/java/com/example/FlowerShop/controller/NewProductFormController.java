package com.example.FlowerShop.controller;

import com.example.FlowerShop.model.Product;
import com.example.FlowerShop.tool.FormLoader;
import com.example.FlowerShop.service.AppService;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class NewProductFormController {

    private final AppService<Product> productService;
    private final FormLoader formLoader;

    @FXML private TextField tfName;
    @FXML private TextField tfCategory;
    @FXML private TextField tfPrice;
    @FXML private TextField tfQuantity;

    public NewProductFormController(AppService<Product> productService, FormLoader formLoader) {
        this.productService = productService;
        this.formLoader = formLoader;
    }

    @FXML
    private void saveProduct() {
        try {
            String name = tfName.getText().trim();
            String category = tfCategory.getText().trim();
            double price = Double.parseDouble(tfPrice.getText().trim());
            int quantity = Integer.parseInt(tfQuantity.getText().trim());

            if (name.isEmpty() || category.isEmpty()) {
                throw new IllegalArgumentException("Название и категория не могут быть пустыми.");
            }

            Product product = new Product(name, category, price, quantity);
            productService.create(product);

            showSuccessAlert("Товар добавлен", "Товар \"" + name + "\" успешно добавлен.");

            formLoader.loadMainForm();

        } catch (NumberFormatException e) {
            showErrorAlert("Ошибка", "Цена и количество должны быть числами.");
        } catch (Exception e) {
            showErrorAlert("Ошибка", "Ошибка при добавлении товара: " + e.getMessage());
        }
    }

    private void showSuccessAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showErrorAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
