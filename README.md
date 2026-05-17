Phần 1 - Phân tích logic
Thiết kế các API Endpoints và HTTP Methods:

Điều ước 1 (Sự thông thái): Lấy một lời khuyên ngẫu nhiên. Vì đây là hành động truy xuất dữ liệu, ta dùng GET.

Điều ước 2 (Sự giàu có): Xin Thần đèn tạo ra một số lượng vàng. Đây là hành động tạo mới tài nguyên, ta dùng POST.

Điều ước 3 (Sự biến đổi): Nâng cấp một món đồ bình thường thành đồ ma thuật. Đây là hành động cập nhật/thay đổi tài nguyên hiện có, ta dùng PUT.

Lịch sử: Xem lại các điều ước, dùng GET.

Lựa chọn HTTP Status Codes:

Thành công: 200 OK cho GET/PUT và 201 Created cho POST.

Lỗi Hết lượt (Business Rule): 403 Forbidden (Bị cấm). Nó mang ý nghĩa "Tôi hiểu yêu cầu của bạn, nhưng bạn không có quyền thực hiện nó nữa vì đã vượt quá giới hạn".

Lỗi Dữ liệu (Validation): 400 Bad Request. Dùng khi client gửi lên dữ liệu vô lý (ví dụ: xin tạo ra số lượng vàng bị âm, hoặc không truyền tên món đồ muốn nâng cấp).

Lưu trữ lịch sử:

Trong bài toán này, để đơn giản, ta sẽ lưu trong RAM bằng một biến static List. Trong thực tế, bạn sẽ lưu nó vào Database thông qua JPA/Hibernate. Mỗi đối tượng lịch sử sẽ lưu lại: Tên điều ước, trạng thái (Thành công/Bị từ chối), và lời nhắn.