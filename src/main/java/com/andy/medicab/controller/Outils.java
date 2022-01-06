package com.andy.medicab.controller;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

public class Outils {
    public static String passwordHasher(String password){
        if(password ==null)
            return null;
            return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }
}
