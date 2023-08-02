package com.example.tictactoefx.checking_algoritms;

import com.example.tictactoefx.user.UserType;

public interface WinAlgorithm {
    boolean doCheck(char[][] gameDesk, UserType userType);
}
