package com.example.tictactoefx.checking_algoritms;


import com.example.tictactoefx.user.UserType;

public class HorizontalWinAlgorithmImpl implements WinAlgorithm {
    @Override
    public boolean doCheck(char[][] gameDesk, UserType userType) {
        for (char[] array1: gameDesk) {
            int counter = 0;

            for (char arr: array1 ) {
                if(arr != '\0' && arr == userType.name().charAt(0)) {
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
