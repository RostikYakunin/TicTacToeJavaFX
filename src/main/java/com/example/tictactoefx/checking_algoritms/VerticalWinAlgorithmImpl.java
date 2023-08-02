package com.example.tictactoefx.checking_algoritms;


import com.example.tictactoefx.user.UserType;

public class VerticalWinAlgorithmImpl implements WinAlgorithm {
    @Override
    public boolean doCheck(char[][] gameDesk, UserType userType) {
        for (int i = 0; i < gameDesk.length; i++) {
            int counter = 0;

            for (char[] strings : gameDesk) {
                if (strings[i] != '\0' && strings[i] == userType.name().charAt(0)) {
                    counter++;
                }

                if (counter == 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
