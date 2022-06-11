package com.andy.Medicab.controller;

import com.andy.Medicab.model.Hopital;
import com.andy.Medicab.model.Position;
import com.google.common.hash.Hashing;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.awt.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Outils {
    public static final String SAVE_ = "save";
    public static final String UPDATE_ = "update";
    public static final String FIND_BY_ID = "findById/";
    public static final String DELETE_BY_ID = "deleteByID/";
    public static final String FIND_ALL = "findAll";
    public static final String CHECK_EXIST_BY_ID = "checkExist/";
    public static final String BASE_URL = "*";
    public static final String ERROR = "erreur";
    public static final String SUCCES = "succes";
    public static final String ATT_USER = "user";
    public static final String ATT_ACCOUNT = "account";
    public static final String ATT_HOPITAL = "hopital";
    public static final String ATT_DRIVER = "driver";


    public static final ResponseEntity buildErrorMessage(Exception ex, String message) {
        System.out.println(ex.getMessage());
        Map<String, String> body = new HashMap<String, String>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(ERROR, message);
        body.put(ERROR, message);
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    public static final ResponseEntity buildErrorMessage(String message) {
        Map<String, String> bod = new HashMap<String, String>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(ERROR, message);
        bod.put(ERROR, message);
        return new ResponseEntity<>(bod, HttpStatus.CONFLICT);
    }

    public static final ResponseEntity buildSuccessMessage(String message) {
        Map<String, String> body = new HashMap<String, String>();
        HttpHeaders headers = new HttpHeaders();
        headers.add(SUCCES, message);
        body.put(SUCCES, message);
        return new ResponseEntity<>(body, headers, HttpStatus.OK);
    }

    public static String encryptPassword(String password) {
        if (password == null) {
            return null;
        }
        return Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
    }

    private static final double p = Math.PI / 180;

    private static double c(double d) {
        return Math.cos(d);
    }

    public static final double getDistance(Position p1, Position p2) {
        double lat1 = p1.getLatitude();
        double long1 = p1.getLongitude();
        double lat2 = p2.getLatitude();
        double long2 = p2.getLongitude();
        double a = 0.5 - c((lat2 - lat1) * p) / 2 + c(lat1 * p) * c(lat2 * p) * (1 - c((long2 - long1) * p)) / 2;
        return 12742 * Math.asin(Math.sqrt(a)); // 2 * R; R = 6371 km ;
    }
    public static Hopital getNearHopital(Position position, List<Hopital> hopitals){
        Hopital hopital = null;

        if(!hopitals.isEmpty()){
            double min = getDistance(position,hopitals.get(0).getPosition());
            for (Hopital hop:hopitals){
                if (position == hop.getPosition())
                    return  hop;
                double dist = getDistance(position,hop.getPosition());
                if (dist <= min) {
                    min = dist;
                    hopital = hop;
                }
            }
        }
        return  hopital;
    }
    public static String codeGenerator(){
        Random rd = new Random();

        int a = rd.nextInt(10);
        int b = rd.nextInt(10);
        int c = rd.nextInt(10);
        int d = rd.nextInt(10);
        StringBuilder sb = new StringBuilder();
        sb.append(a).append(b).append(c).append(d);
        return  sb.toString();
    }
}
