package com.example.tictactoefx.controller;

import com.example.tictactoefx.checking_algoritms.DiagonalWinAlgorithmImpl;
import com.example.tictactoefx.checking_algoritms.HorizontalWinAlgorithmImpl;
import com.example.tictactoefx.checking_algoritms.VerticalWinAlgorithmImpl;
import com.example.tictactoefx.checking_algoritms.WinAlgorithm;
import com.example.tictactoefx.user.User;
import com.example.tictactoefx.user.UserImpl;
import com.example.tictactoefx.user.UserType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TicTacToeController implements Initializable {
    GraphicsContext graphicsContext;

    @FXML
    Pane gamePane;
    @FXML
    Canvas mainFieldCanvas;
    @FXML
    Text resultText;
    @FXML
    MenuBar menuBar;
    @FXML
    MenuItem startGameMenuItem;
    @FXML
    MenuItem closeMenuItem;

    private static final int TILE_SIZE = 100;
    private static final int FIELD_SIZE = 3;

    private boolean gameOver = true;
    private char[][] gameBoard;

    private List<User> userList;
    private int currentUserIndex;
    private User currentUser;

    public final List<WinAlgorithm> winAlgorithmList = List.of(
            new DiagonalWinAlgorithmImpl(),
            new HorizontalWinAlgorithmImpl(),
            new VerticalWinAlgorithmImpl()
    );

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        createGameField();
        startGameMenuItem.setOnAction(this::startGameAction);
    }

    private void createGameField() {
        graphicsContext = mainFieldCanvas.getGraphicsContext2D();
        double cellWidth = mainFieldCanvas.getWidth() / FIELD_SIZE;
        double cellHeight = mainFieldCanvas.getHeight() / FIELD_SIZE;

        graphicsContext.clearRect(0, 0, mainFieldCanvas.getWidth(), mainFieldCanvas.getHeight());
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(2);

        for (int i = 1; i < FIELD_SIZE; i++) {
            graphicsContext.strokeLine(i * cellWidth, 0, i * cellWidth, mainFieldCanvas.getHeight());
        }
        for (int i = 1; i < FIELD_SIZE; i++) {
            graphicsContext.strokeLine(0, i * cellHeight, mainFieldCanvas.getWidth(), i * cellHeight);
        }
    }

    public void startGameAction(ActionEvent event) {
        newGameInitialization();
    }

    private void newGameInitialization() {
        gameOver = false;
        gameBoard = new char[FIELD_SIZE][FIELD_SIZE];
        userList = List.of(new UserImpl(UserType.X), new UserImpl(UserType.O));
        currentUserIndex = 0;
        currentUser = userList.get(currentUserIndex);
        resultText.setText("");
        createGameField();
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        if (gameOver) {
            return;
        }

        int x = (int) mouseEvent.getX() / TILE_SIZE;
        int y = (int) mouseEvent.getY() / TILE_SIZE;

        if (gameBoard[x][y] == 0) {
            drawGameMove(x, y);

            boolean result = checkMove(gameBoard, currentUser.getUserType());
            checkOnWinner(result);

            changeCurrentUser();
        }
    }

    private void checkOnWinner(boolean result) {
        if (result) {
            gameOver = true;
            resultText.setText("User " + currentUser.getUserType() + " win this game !!!");
        } else if (isNobodyWin(gameBoard)) {
            gameOver = true;
            resultText.setText("Nobody win this game !!!");
        }
    }

    private void drawGameMove(int x, int y) {
        if (currentUser.getUserType().name().equals("X")) {
            drawX(graphicsContext, x, y);
            gameBoard[x][y] = 'X';
        } else {
            drawO(graphicsContext, x, y);
            gameBoard[x][y] = 'O';
        }
    }

    private void drawX(GraphicsContext gc, int x, int y) {
        gc.setStroke(Color.BLUE);
        gc.strokeLine(x * TILE_SIZE + 10, y * TILE_SIZE + 10, (x + 1) * TILE_SIZE - 10, (y + 1) * TILE_SIZE - 10);
        gc.strokeLine((x + 1) * TILE_SIZE - 10, y * TILE_SIZE + 10, x * TILE_SIZE + 10, (y + 1) * TILE_SIZE - 10);
    }

    private void drawO(GraphicsContext gc, int x, int y) {
        gc.setStroke(Color.RED);
        gc.strokeOval(x * TILE_SIZE + 10, y * TILE_SIZE + 10, TILE_SIZE - 20, TILE_SIZE - 20);
    }

    private void changeCurrentUser() {
        currentUserIndex = (currentUserIndex + 1) % userList.size();
        currentUser = userList.get(currentUserIndex);
    }

    public boolean checkMove(char[][] gameTable, UserType userType) {
        for (WinAlgorithm winAlgorithm : winAlgorithmList) {
            if (winAlgorithm.doCheck(gameTable, userType)) {
                return true;
            }
        }
        return false;
    }

    public boolean isNobodyWin(char[][] gameDesk) {
        for (char[] strings : gameDesk) {
            for (char string : strings) {
                if (string == '\0') {
                    return false;
                }
            }
        }
        return true;
    }

    public void closeGameAction(ActionEvent event) {
        System.exit(0);
    }
}