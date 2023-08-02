package com.example.tictactoefx.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class User {
    protected UserType userType;

    public User(UserType userType) {
        this.userType = userType;
    }

    public String[][] doGameMove(String[][] gameTable) {
        System.out.println("User " + userType.name() + " input your cell number using ',' or '.' ");
        int[] coordinates = inputMove();

        if (gameTable[coordinates[0]][coordinates[1]] != null) {
            System.out.println("This cell is already occupied ! Try again");
            return doGameMove(gameTable);
        }

        gameTable[coordinates[0]][coordinates[1]] = userType.name();
        return gameTable;
    }

    private int[] inputMove() {
        String inputString = readStringFromConsole();

        String[] strings = inputString.split("[.,]");

        checkOnIncorrectInput(strings);
        checkOnExit(strings);

        int firstCoordinate = Integer.parseInt(strings[0]) - 1;
        int secondCoordinate = Integer.parseInt(strings[1]) - 1;
        return new int[]{firstCoordinate, secondCoordinate};
    }

    private String readStringFromConsole() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        try{
            input = bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return input;
    }

    private void checkOnIncorrectInput(String[] strings) {
        try {
            if (strings.length > 2 || Integer.parseInt(strings[0]) > 3 || Integer.parseInt(strings[1]) > 3) {
                System.err.println("Your input is incorrect !!! Please try again !");
                inputMove();
            }
        } catch (NumberFormatException ex) {
            System.err.println("Your input is incorrect !!! Please try again !");
            inputMove();
        }
    }

    private void checkOnExit(String[] strings) {
        if (strings[0].equals("q")) {
            System.exit(0);
        }
    }

    public UserType getUserType() {
        return userType;
    }
}


