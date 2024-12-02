package com.example.christianbocanegra_comp228lab5;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class HelloController {

    // Required FXML fields
    @FXML
    private TextField registerFirstNameField, registerLastNameField, registerAddressField,
            registerPostalCodeField, registerProvinceField, registerPhoneNumberField;
    @FXML
    private ComboBox<String> playerIdScoreComboBox,gameIdScoreComboBox;
    @FXML
    private TextField titleField, scoreField;
    @FXML
    private DatePicker playingDatePicker;
    @FXML
    private ComboBox<String> playerIdUpdatePlayerComboBox;
    @FXML
    private TextField updateFirstNameField, updateLastNameField, updateAddressField,
            updatePostalCodeField, updateProvinceField, updatePhoneNumberField;
    @FXML private TableView<Player> playerTable;
    @FXML private TableColumn<Player, Integer> playerIdColumn;
    @FXML private TableColumn<Player, String> firstNameColumn;
    @FXML private TableColumn<Player, String> lastNameColumn;
    @FXML private TableColumn<Player, String> addressColumn;
    @FXML private TableColumn<Player, String> postalCodeColumn;
    @FXML private TableColumn<Player, String> provinceColumn;
    @FXML private TableColumn<Player, String> phoneNumberColumn;
    @FXML private TableView<Games> gameTable;
    @FXML private TableColumn<Games, String> gameTitleColumn;
    @FXML private TableColumn<Games, String> playingDateColumn;
    @FXML private TableColumn<Games, String> scoreColumn;
    @FXML private TableColumn<Player, Integer> playerIdColumnInGameTable;


    private Connection dbConnection;

    private ObservableList<String> playerList = FXCollections.observableArrayList();
    private ObservableList<String> gameList = FXCollections.observableArrayList();


    public HelloController() {
        try {

            dbConnection = HelloApplication.DatabaseConnector.connect();
        } catch (Exception e) {
            e.printStackTrace();
            showError("Failed to connect to the database: " + e.getMessage());
        }
    }

    @FXML
    public void initialize() {

        loadPlayers();
        loadGames();

        playerIdScoreComboBox.setItems(playerList);
        playerIdUpdatePlayerComboBox.setItems(playerList);
        gameIdScoreComboBox.setItems(gameList);

        playerIdColumn.setCellValueFactory(new PropertyValueFactory<>("playerId"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        postalCodeColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        provinceColumn.setCellValueFactory(new PropertyValueFactory<>("province"));
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        gameTitleColumn.setCellValueFactory(new PropertyValueFactory<>("gameTitle"));
        playingDateColumn.setCellValueFactory(new PropertyValueFactory<>("playingDate"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));


        playerIdColumnInGameTable.setCellValueFactory(new PropertyValueFactory<>("playerId"));

    }

    private void loadPlayers() {
        playerList.clear();
        try {
            String query = "SELECT player_id, first_name, last_name FROM Christian_Bocanegra_player order by 1 asc";
            PreparedStatement stmt = dbConnection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int playerId = rs.getInt("player_id");
                String fullName = rs.getString("first_name") + " " + rs.getString("last_name");
                playerList.add(playerId + " - " + fullName);
            }

            if (playerList.isEmpty()) {
                showError("No players found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in loading players: " + e.getMessage());
        }
    }

    private void loadGames() {
        gameList.clear();
        try {
            String query = "SELECT game_id, game_title FROM Christian_Bocanegra_Mape_game order by 1 asc";
            PreparedStatement stmt = dbConnection.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int gameId = rs.getInt("game_id");
                String gameName = rs.getString("game_title");
                gameList.add(gameId + " - " + gameName);
            }

            if (gameList.isEmpty()) {
                showError("No Games found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in loading players: " + e.getMessage());
        }
    }

    @FXML
    private void onSubmitRegisterPlayer(ActionEvent event) {
        try {

            if (registerFirstNameField.getText().isEmpty() || registerLastNameField.getText().isEmpty()) {
                showError("First Name and Last Name are required.");
                return;
            }

            String query = "INSERT INTO Christian_Bocanegra_player (first_name, last_name, address, postal_code, province, phone_number) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = dbConnection.prepareStatement(query);

            stmt.setString(1, registerFirstNameField.getText());
            stmt.setString(2, registerLastNameField.getText());
            stmt.setString(3, registerAddressField.getText());
            stmt.setString(4, registerPostalCodeField.getText());
            stmt.setString(5, registerProvinceField.getText());
            stmt.setString(6, registerPhoneNumberField.getText());

            stmt.executeUpdate();
            showMessage("Player registered successfully.");
            clearRegisterPlayerFields();
            loadPlayers();
        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in registering player: " + e.getMessage());
        }
    }



    private void clearRegisterPlayerFields() {
        registerFirstNameField.clear();
        registerLastNameField.clear();
        registerAddressField.clear();
        registerPostalCodeField.clear();
        registerProvinceField.clear();
        registerPhoneNumberField.clear();
    }


    @FXML
    private void onPlayerSelection(ActionEvent event) {
        ComboBox<String> source = (ComboBox<String>) event.getSource();
        String selectedPlayer = source.getValue();

        if (selectedPlayer != null) {
            String[] parts = selectedPlayer.split(" - ");
            int playerId = Integer.parseInt(parts[0]);
            populatePlayerDetails(playerId, source);
        }
    }

    private int extractPlayerIdFromComboBox(String selectedPlayer) {
        String[] parts = selectedPlayer.split(" - ");
        String playerIdString = parts[0].trim();
        return Integer.parseInt(playerIdString);
    }

    private void populatePlayerDetails(int playerId, ComboBox<String> sourceComboBox) {
        try {
            String query = "SELECT * FROM Christian_Bocanegra_player  WHERE player_id = ?";
            PreparedStatement stmt = dbConnection.prepareStatement(query);
            stmt.setInt(1, playerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (sourceComboBox == playerIdUpdatePlayerComboBox) {
                    updateFirstNameField.setText(rs.getString("first_name"));
                    updateLastNameField.setText(rs.getString("last_name"));
                    updateAddressField.setText(rs.getString("address"));
                    updatePostalCodeField.setText(rs.getString("postal_code"));
                    updateProvinceField.setText(rs.getString("province"));
                    updatePhoneNumberField.setText(rs.getString("phone_number"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in loading player details: " + e.getMessage());
        }
    }

    @FXML
    private void onSubmitRegisterGame(ActionEvent event) {
        try {

            if (titleField.getText().isEmpty()) {
                showError("Game's title is required.");
                return;
            }

            String query = "INSERT INTO Christian_Bocanegra_Mape_game (game_title) " +
                    "VALUES (?)";
            PreparedStatement stmt = dbConnection.prepareStatement(query);
            stmt.setString(1, titleField.getText());

            stmt.executeUpdate();
            showMessage("Game registered successfully.");
            clearRegisterGameFields();
            loadGames();
        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in registering game: " + e.getMessage());
        }
    }

    @FXML
    private void clearRegisterGameFields() {

        titleField.clear();
    }

    @FXML
    private void onGameSelection(ActionEvent event) {
        ComboBox<String> source = (ComboBox<String>) event.getSource();
        String selectedGame= source.getValue();

        if (selectedGame != null) {
            String[] parts = selectedGame.split(" - ");
            int gameId = Integer.parseInt(parts[0]);
            populateGameDetails(gameId, source);
        }
    }

    private int extractGameIdFromComboBox(String selectedGame) {
        String[] parts = selectedGame.split(" - ");
        String gameIdString = parts[0].trim();
        return Integer.parseInt(gameIdString);
    }

    private void populateGameDetails(int gameId, ComboBox<String> sourceComboBox) {
        try {
            String query = "SELECT * FROM Christian_Bocanegra_Mape_game WHERE game_id = ?";
            PreparedStatement stmt = dbConnection.prepareStatement(query);
            stmt.setInt(1, gameId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (sourceComboBox == gameIdScoreComboBox) {
                    titleField.setText(rs.getString("game_title"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in loading player details: " + e.getMessage());
        }
    }


    @FXML
    private void onSubmitScore(ActionEvent event) {
        try {
            if (playingDatePicker.getValue() == null || scoreField.getText().isEmpty()) {
                showError("Playing Date and Score are required.");
                return;
            }

            String selectedPlayer = playerIdScoreComboBox.getValue();
            if (selectedPlayer == null || selectedPlayer.isEmpty()) {
                showError("Please select a Player.");
                return;
            }

            String selectedGame = gameIdScoreComboBox.getValue();
            if (selectedGame == null || selectedGame.isEmpty()) {
                showError("Please select a Game.");
                return;
            }

            int selectedPlayerId = extractPlayerIdFromComboBox(selectedPlayer);
            int selectedGameId = extractGameIdFromComboBox(selectedGame);

            LocalDate playingDate = playingDatePicker.getValue();
            java.sql.Date sqlDate = java.sql.Date.valueOf(playingDate);

            String query = "INSERT INTO Christian_B_PlayerAndGame (game_id, player_id, playing_date, score) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = dbConnection.prepareStatement(query);
            stmt.setInt(1, selectedGameId);
            stmt.setInt(2, selectedPlayerId);
            stmt.setDate(3, sqlDate);
            stmt.setInt(4, Integer.parseInt(scoreField.getText()));

            stmt.executeUpdate();
            showMessage("Score registered successfully.");
            clearScore();

        } catch (NumberFormatException e) {
            showError("Score must be a valid number.");
        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in registering score: " + e.getMessage());
        }
    }

    @FXML
    private void clearScore() {

        playingDatePicker.setValue(null);
        scoreField.clear();
        playerIdScoreComboBox.getSelectionModel().clearSelection();
        gameIdScoreComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void onUpdatePlayer(ActionEvent event) {

        String selectedPlayer = playerIdUpdatePlayerComboBox.getValue();
        if (selectedPlayer == null || selectedPlayer.isEmpty()) {
            showError("Please select a Player to update.");
            return;
        }

        int selectedPlayerId = extractPlayerIdFromComboBox(selectedPlayer);

        String firstName = updateFirstNameField.getText();
        String lastName = updateLastNameField.getText();
        String address = updateAddressField.getText();
        String postalCode = updatePostalCodeField.getText();
        String province = updateProvinceField.getText();
        String phoneNumber = updatePhoneNumberField.getText();

        if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty() || postalCode.isEmpty()
                || province.isEmpty() || phoneNumber.isEmpty()) {
            showError("All fields must be filled out to update the player's information.");
            return;
        }

        String updateQuery = "UPDATE Christian_Bocanegra_player SET first_name = ?, last_name = ?, address = ?, postal_code = ?, " +
                "province = ?, phone_number = ? WHERE player_id = ?";

        try (PreparedStatement preparedStatement = dbConnection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, postalCode);
            preparedStatement.setString(5, province);
            preparedStatement.setString(6, phoneNumber);
            preparedStatement.setInt(7, selectedPlayerId);  // Use the integer player ID

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                showMessage("Player information updated successfully.");
                clearUpdatePlayerFields();
            } else {
                showError("No record was updated. Please try again.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showError("An error occurred while updating the player's information: " + e.getMessage());
        }
    }

    private void clearUpdatePlayerFields() {
        updateFirstNameField.clear();
        updateLastNameField.clear();
        updateAddressField.clear();
        updatePostalCodeField.clear();
        updateProvinceField.clear();
        updatePhoneNumberField.clear();
    }

    @FXML
    public void onDisplayInformationTabSelected() {

        playerTable.getItems().clear();
        gameTable.getItems().clear();
        initializeComboBox();
        loadAllGames();

        try (PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM Christian_Bocanegra_player ORDER BY 1 ASC");
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<Player> players = FXCollections.observableArrayList();

            while (rs.next()) {
                Player player = new Player(
                        rs.getInt("player_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("postal_code"),
                        rs.getString("province"),
                        rs.getString("phone_number")
                );
                players.add(player);
            }

            playerTable.setItems(players);
            playerTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    fetchGamesForPlayer(newValue.getPlayerId());
                } else {
                    loadAllGames();
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in fetching player data: " + e.getMessage());
        }
    }


    private void fetchGamesForPlayer(int playerId) {
        String gameQuery = "SELECT a.player_id, b.game_title, a.playing_date, a.score " +
                "FROM Christian_B_PlayerAndGame a " +
                "JOIN Christian_Bocanegra_Mape_game b ON a.game_id = b.game_id " +
                "WHERE a.player_id = ?";

        try (PreparedStatement stmt = dbConnection.prepareStatement(gameQuery)) {
            stmt.setInt(1, playerId);

            try (ResultSet rs = stmt.executeQuery()) {
                ObservableList<Games> games = FXCollections.observableArrayList();

                while (rs.next()) {
                    Games game = new Games(
                            rs.getInt("player_id"),
                            rs.getString("game_title"),
                            rs.getDate("playing_date"),
                            rs.getInt("score")
                    );
                    games.add(game);
                }

                gameTable.setItems(games);

            } catch (SQLException e) {
                e.printStackTrace();
                showError("SQL Error in fetching game data: " + e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in fetching game data for selected player: " + e.getMessage());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error occurred");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private ComboBox<String> playerIdComboBox;

    @FXML
    public void initializeComboBox() {
        String query = "SELECT player_id, first_name, last_name FROM Christian_Bocanegra_player ORDER BY 1 ASC";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<String> playerOptions = FXCollections.observableArrayList();

            while (rs.next()) {
                int playerId = rs.getInt("player_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String displayValue = playerId + " - " + firstName + " " + lastName;
                playerOptions.add(displayValue);
            }

            playerIdComboBox.setItems(playerOptions);

        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in loading Player IDs: " + e.getMessage());
        }
    }

    @FXML
    public void onPlayerIdSelected() {
        String selectedValue = playerIdComboBox.getValue();

        if (selectedValue != null) {
            int selectedPlayerId = extractPlayerIdFromComboBox(selectedValue);

            playerTable.getItems().clear();
            String query = "SELECT * FROM Christian_Bocanegra_player WHERE player_id = ?";

            try (PreparedStatement stmt = dbConnection.prepareStatement(query)) {
                stmt.setInt(1, selectedPlayerId);

                try (ResultSet rs = stmt.executeQuery()) {
                    ObservableList<Player> players = FXCollections.observableArrayList();

                    while (rs.next()) {
                        Player player = new Player(
                                rs.getInt("player_id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("address"),
                                rs.getString("postal_code"),
                                rs.getString("province"),
                                rs.getString("phone_number")
                        );
                        players.add(player);
                    }

                    playerTable.setItems(players);
                    fetchGamesForPlayer(selectedPlayerId);

                } catch (SQLException e) {
                    e.printStackTrace();
                    showError("SQL Error in filtering player data: " + e.getMessage());
                }

            } catch (SQLException e) {
                e.printStackTrace();
                showError("SQL Error in fetching player data: " + e.getMessage());
            }
        }
    }

    @FXML
    private void loadAllGames() {
        String query = "SELECT a.player_id, b.game_title, a.playing_date, a.score " +
                "FROM Christian_B_PlayerAndGame a " +
                "JOIN Christian_Bocanegra_Mape_game b ON a.game_id = b.game_id";

        try (PreparedStatement stmt = dbConnection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            ObservableList<Games> games = FXCollections.observableArrayList();

            while (rs.next()) {
                Games game = new Games(
                        rs.getInt("player_id"),
                        rs.getString("game_title"),
                        rs.getDate("playing_date"),
                        rs.getInt("score")
                );
                games.add(game);
            }
            gameTable.setItems(games);

        } catch (SQLException e) {
            e.printStackTrace();
            showError("SQL Error in loading all games: " + e.getMessage());
        }
    }




}