package com.example.tictactoefx.checking_algoritms;

import com.example.tictactoefx.user.UserType;

public class DiagonalWinAlgorithmImpl implements WinAlgorithm {
    @Override
    public boolean doCheck(char[][] gameDesk, UserType userType) {
        int counter1 = 0;
        int counter2 = 0;

        for (int i = 0; i < gameDesk.length; i++) {
            if (gameDesk[i][i] != '\0' && gameDesk[i][i] == userType.name().charAt(0)) {
                counter1++;
            }

            if (gameDesk[i][gameDesk.length - i - 1] != '\0' && gameDesk[i][gameDesk.length - i - 1] == userType.name().charAt(0)) {
                counter2++;
            }
        }

        return counter1 == 3 || counter2 == 3;
    }
}
