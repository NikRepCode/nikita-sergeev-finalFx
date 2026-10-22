package com.example.nikitasergeevfinalfx;

import com.example.nikitasergeevfinalfx.jdbc.AppConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HelloController {
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField urlField;
    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<ProfileCard> resultTable;
    @FXML
    private TableColumn<ProfileCard, String> colFirstName;
    @FXML
    private TableColumn<ProfileCard, String> colLastName;
    @FXML
    private TableColumn<ProfileCard, String> colChar;
    @FXML
    private TableColumn<ProfileCard, String> colMarital;
    @FXML
    private ImageView pirateImageView;
    @FXML
    private Button searchButton;

    private final ObservableList<ProfileCard> searchResults = FXCollections.observableArrayList();
    private DatabaseUtil currentDb = null;

    @FXML
    public void initialize() {
        InputStream inputStream = getClass().getResourceAsStream("/images/Island.png");

        if (inputStream != null) {
            Image bg = new Image(inputStream);
            BackgroundImage bgImage = new BackgroundImage(
                    bg,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(1.0, 1.0, true,
                            true, false, true)
            );
            rootPane.setBackground(new Background(bgImage));
        }

        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        colChar.setCellValueFactory(new PropertyValueFactory<>("character"));
        colMarital.setCellValueFactory(new PropertyValueFactory<>("maritalStatus"));
        resultTable.setItems(searchResults);

        resultTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showPirateImage(newValue.getPhotoFilename());
                    } else {
                        pirateImageView.setImage(null);
                    }
                }
        );

        AppConfig config = new AppConfig();
        urlField.setText(config.getUrl());
        userField.setText(config.getUsername());

        searchField.setDisable(true);
        searchButton.setDisable(true);
    }

    private void showPirateImage(String filename) {
        if (filename == null || filename.isEmpty()) {
            pirateImageView.setImage(null);
            return;
        }

        InputStream stream = getClass().getResourceAsStream("/images/" + filename);

        if (stream != null) {
            pirateImageView.setImage(new Image(stream));
        } else {
            System.out.println("Картинка не найдена: " + filename);
            pirateImageView.setImage(null);
        }
    }

    @FXML
    protected void checkConnect() {
        String url = urlField.getText().trim();
        String user = userField.getText().trim();
        String password = passwordField.getText();

        if (url.isEmpty()) {
            showAlert("Введите URL базы данных");
            return;
        }

        try {
            DatabaseUtil databaseUtil = new DatabaseUtil(url, user, password);
            try (Connection conn = databaseUtil.getConnection()) {
                currentDb = databaseUtil;

                searchField.setDisable(false);
                searchButton.setDisable(false);

                showInfo("Подключение успешно!");
                getSearchResults();
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Не удалось подключиться: " + e.getMessage());
        }
    }

    @FXML
    protected void getSearchResults() {
        String query = searchField.getText().trim();
        DatabaseUtil databaseUtil = (currentDb != null) ? currentDb : new DatabaseUtil(new AppConfig());
        searchResults.clear();
        String sql;

        if (query.isEmpty()) {
            sql = "SELECT first_name, last_name, character_crew_member, photo_character, marital_status " +
                    "FROM profile_card ORDER BY first_name";
        } else {
            sql = "SELECT first_name, last_name, character_crew_member, photo_character, marital_status " +
                    "FROM profile_card WHERE LOWER(first_name) LIKE LOWER(?) OR LOWER(last_name) LIKE LOWER(?) " +
                    "ORDER BY first_name";
        }

        try (Connection conn = databaseUtil.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            if (!query.isEmpty()) {
                String searchPattern = "%" + query + "%";
                preparedStatement.setString(1, searchPattern);
                preparedStatement.setString(2, searchPattern);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    searchResults.add(new ProfileCard(
                            resultSet.getString("first_name"),
                            resultSet.getString("last_name"),
                            resultSet.getString("character_crew_member"),
                            resultSet.getString("marital_status"),
                            resultSet.getString("photo_character")
                    ));
                }

                if (!searchResults.isEmpty()) {
                    resultTable.getSelectionModel().selectFirst();
                } else {
                    pirateImageView.setImage(null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Ошибка поиска: " + e.getMessage());
        }
    }

    @FXML
    protected void clearFields() {
        searchField.clear();
        searchResults.clear();
        pirateImageView.setImage(null);
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.showAndWait();
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.showAndWait();
    }
}