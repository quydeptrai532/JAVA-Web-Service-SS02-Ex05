package com.example.ex05.model;

import java.time.LocalDateTime;

public class WishLog {
    private String wishType;
    private String status;
    private String message;
    private LocalDateTime timestamp;

    public WishLog(String wishType, String status, String message) {
        this.wishType = wishType;
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public String getWishType() { return wishType; }
    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}