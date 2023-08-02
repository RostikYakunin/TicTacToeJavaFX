package com.example.tictactoefx.user;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static com.example.tictactoefx.controller.TicTacToeController.TILE_SIZE;

public abstract class User {
    protected UserType userType;

    public User(UserType userType) {
        this.userType = userType;
    }

    public void drawGameMove(GraphicsContext graphicsContext, char[][] gameBoard, int x, int y) {
        if (userType.name().equals("X")) {
            drawX(graphicsContext, x, y);
            gameBoard[x][y] = 'X';
        } else {
            drawO(graphicsContext, x, y);
            gameBoard[x][y] = 'O';
        }
    }

    public void drawX(GraphicsContext gc, int x, int y) {
        gc.setStroke(Color.BLUE);
        gc.strokeLine(x * TILE_SIZE + 10, y * TILE_SIZE + 10, (x + 1) * TILE_SIZE - 10, (y + 1) * TILE_SIZE - 10);
        gc.strokeLine((x + 1) * TILE_SIZE - 10, y * TILE_SIZE + 10, x * TILE_SIZE + 10, (y + 1) * TILE_SIZE - 10);
    }

    public void drawO(GraphicsContext gc, int x, int y) {
        gc.setStroke(Color.RED);
        gc.strokeOval(x * TILE_SIZE + 10, y * TILE_SIZE + 10, TILE_SIZE - 20, TILE_SIZE - 20);
    }

    public UserType getUserType() {
        return userType;
    }
}


