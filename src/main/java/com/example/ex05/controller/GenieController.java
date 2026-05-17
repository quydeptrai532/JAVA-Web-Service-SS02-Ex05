package com.example.ex05.controller;

import com.example.ex05.manager.GenieManager;
import com.example.ex05.model.WishLog;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/genie")
public class GenieController {
    // Hàm kiểm tra chung trước khi thực hiện bất kỳ điều ước nào
    private void checkWishAvailability() {
        if (GenieManager.usedWishes >= GenieManager.MAX_WISHES) {
            String msg = "Thần đèn đã hết phép! Bạn đã dùng hết " + GenieManager.MAX_WISHES + " điều ước.";
            GenieManager.logWish("Any Wish", "FAILED", msg);
            throw new IllegalStateException(msg); // Ném lỗi để GlobalExceptionHandler bắt
        }
    }
    // ĐIỀU ƯỚC 1: GET - Xin sự thông thái
    @GetMapping("/wishes/wisdom")
    public ResponseEntity<Map<String, String>> askForWisdom() {
        checkWishAvailability();

        GenieManager.usedWishes++;
        String wisdom = "Code không lỗi chỉ tồn tại trong trí tưởng tượng. Hãy siêng năng debug!";
        GenieManager.logWish("Wisdom", "SUCCESS", "Đã ban lời khuyên.");

        Map<String, String> response = new HashMap<>();
        response.put("message", wisdom);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ĐIỀU ƯỚC 2: POST - Xin sự giàu có
    static class WealthRequest { public Integer amount; } // Lớp phụ trợ lấy body
    @PostMapping("/wishes/wealth")
    public ResponseEntity<Map<String, String>> askForWealth(@RequestBody WealthRequest request) {
        checkWishAvailability();

        // Kiểm tra dữ liệu (Nghiệp vụ)
        if (request.amount == null || request.amount <= 0) {
            String msg = "Bạn phải ước một số lượng vàng lớn hơn 0 chứ!";
            GenieManager.logWish("Wealth", "FAILED", msg);
            throw new IllegalArgumentException(msg); // Ném lỗi 400
        }

        GenieManager.usedWishes++;
        GenieManager.logWish("Wealth", "SUCCESS", "Đã tạo " + request.amount + " thỏi vàng.");

        Map<String, String> response = new HashMap<>();
        response.put("message", "Bùm! Bạn đã nhận được " + request.amount + " thỏi vàng.");
        return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Created
    }
    // ĐIỀU ƯỚC 3: PUT - Xin nâng cấp vũ khí
    static class TransformRequest { public String newName; }

    @PutMapping("/wishes/transform/{item}")
    public ResponseEntity<Map<String, String>> transformItem(@PathVariable String item, @RequestBody TransformRequest request) {
        checkWishAvailability();

        if (request.newName == null || request.newName.trim().isEmpty()) {
            String msg = "Bạn chưa nói cho thần đèn biết muốn biến nó thành cái gì!";
            GenieManager.logWish("Transform", "FAILED", msg);
            throw new IllegalArgumentException(msg);
        }

        GenieManager.usedWishes++;
        GenieManager.logWish("Transform", "SUCCESS", "Đã biến " + item + " thành " + request.newName);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Keng! Món đồ '" + item + "' của bạn đã tiến hóa thành '" + request.newName + "'.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    // API KIỂM TRA LỊCH SỬ (Không tính là điều ước)
    @GetMapping("/history")
    public ResponseEntity<List<WishLog>> getHistory() {
        return new ResponseEntity<>(GenieManager.history, HttpStatus.OK);
    }
}