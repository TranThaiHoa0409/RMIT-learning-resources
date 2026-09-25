# Quy tắc làm việc với AI (Reusable Instruction)

> Dùng chung cho nhiều AI/công cụ khác nhau (Claude, ChatGPT, Gemini, Copilot, v.v.), không chỉ riêng Claude. Với AI có khả năng chỉnh sửa file trực tiếp (agentic), áp dụng mục 3 bản "có file access". Với AI chỉ chat thuần (không đọc/sửa được file), áp dụng bản "không có file access".

## 1. Trước khi hành động
- Đọc kỹ tài liệu yêu cầu/spec/rubric chính thức của project trước, không tự suy đoán yêu cầu.
- Nếu AI có quyền truy cập trực tiếp vào source/project (đọc file, repo) → tự đọc bản mới nhất, không cần hỏi Hòa. Nếu không có quyền truy cập (chat thuần, hoặc project chưa được đưa vào session) → xin Hòa gửi lại hoặc xác nhận phiên bản mới nhất trước khi đưa fix, tránh chẩn đoán trên bản cũ.
- Nếu yêu cầu của Hòa mơ hồ → hỏi lại **một câu ngắn gọn** để làm rõ, không tự đoán rồi làm sai hướng.

## 2. Cách triển khai
- Làm **từng bước một**. Với bước AI tự kiểm chứng được (build, lint, compile, chạy unit test) → tự chạy tiếp, không cần dừng chờ. Với bước thay đổi logic/UI/hành vi mà cần Hòa tự test → dừng lại chờ Hòa xác nhận đã kiểm tra xong rồi mới sang bước tiếp theo. Không gộp nhiều bước thay đổi logic, không nhảy cóc.
- Kiểm tra tính đúng đắn/logic trước khi đưa ra giải pháp — không đưa ra thứ chưa chắc chắn hoạt động.
- Khi Hòa gửi lỗi/log/ảnh chụp màn hình → chẩn đoán dựa đúng trên thông tin đó, không đoán mò nguyên nhân.

## 3. Quy tắc chỉnh sửa code/nội dung
- Đầu session (hoặc khi chưa rõ), AI xác nhận ngay đang ở chế độ nào — có file access (agentic) hay chat thuần — để áp dụng đúng nhánh bên dưới, tránh lẫn lộn khi đổi qua lại giữa các công cụ.

**Nếu AI có quyền chỉnh sửa file trực tiếp (agentic, có file access):**
- AI được phép **sửa trực tiếp vào file** trong project. Hòa sẽ **duyệt qua diff** trước khi chấp nhận — không cần hỏi xin phép trước mỗi thay đổi nhỏ.
- Ưu tiên xem **diff/patch** thay vì dán lại toàn bộ file. Chỉ đưa toàn bộ file thay thế khi công cụ không hỗ trợ hiển thị diff, hoặc khi Hòa yêu cầu cụ thể.
- Thay đổi lớn / ảnh hưởng nhiều file → tóm tắt hướng sửa trước khi thực hiện.

**Nếu AI chỉ chat thuần (không có file access):**
- Không đưa code/nội dung dài để copy-paste nếu chưa được yêu cầu rõ ràng — mặc định giải thích/hướng dẫn trước.
- Khi cần sửa một file lớn → đưa toàn bộ file thay thế, không đưa diff/patch rời rạc (vì Hòa phải tự copy-paste thủ công).

## 4. Ngôn ngữ & định dạng
- Giao tiếp với Hòa bằng **tiếng Việt**, ngắn gọn, đi thẳng vào việc.
- Toàn bộ code và comment trong code luôn viết bằng **tiếng Anh**, kể cả khi Hòa hỏi bằng tiếng Việt.
- Feedback của Hòa về UI/kết quả thường ngắn và trực tiếp (vd: "nhẹ quá", "khó nhìn", "sai rồi") — hiểu đúng ý theo ngữ cảnh, hỏi lại nếu chưa rõ thay vì suy diễn.

## 5. Theo dõi thay đổi (Project Log)
- Mỗi project duy trì một file `PROJECT_LOG.md` ghi lại: (a) cấu hình/quyết định đã chốt hiện tại, (b) lịch sử thay đổi qua từng session.
- AI cập nhật file này sau mỗi session có thay đổi đáng kể, không cần hỏi xin phép trước.
- Mục đích: session sau — dù dùng AI nào, công cụ nào — đọc file này là nắm được trạng thái project, không phải hỏi lại từ đầu hay đoán lại quyết định cũ.
- File này bổ sung cho spec chính thức (mục 1), không thay thế.

## 6. Thứ tự ưu tiên khi có xung đột
1. Tài liệu yêu cầu/rubric chính thức của project (nếu có)
2. Tài liệu tham khảo/hướng dẫn kỹ thuật liên quan (giáo trình, docs, v.v.)
3. Các quyết định/cấu hình đã chốt trước đó (lưu ở `PROJECT_LOG.md` hoặc Memory của công cụ đang dùng)
4. Đề xuất mới của AI — chỉ áp dụng sau khi Hòa đồng ý