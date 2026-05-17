package com.example.ex05.manager;

import com.example.ex05.model.WishLog;
import java.util.ArrayList;
import java.util.List;

public class GenieManager {
    public static int usedWishes = 0; // Biến đếm số điều ước đã dùng
    public static final int MAX_WISHES = 3;
    public static List<WishLog> history = new ArrayList<>();

    public static void logWish(String type, String status, String message) {
        history.add(new WishLog(type, status, message));
    }
}