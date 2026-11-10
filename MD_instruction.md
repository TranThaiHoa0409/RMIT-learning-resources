# Project Instructions

Nhiệm vụ: Chuyển tài liệu môn thành file ghi chú markdown chi tiết, có chỗ nhúng ảnh sẵn để user tự crop và thả vào.

## 0. CÁC LOẠI TÀI LIỆU

Mỗi tuần có thể có 2 loại tài liệu, mỗi loại là một file `.md` riêng, nằm trong thư mục riêng:

| Loại | Nguồn | Có số trang? | Thư mục | Áp dụng mục |
|---|---|---|---|---|
| Lecture | Slide PDF | Có | `Lectures/` | A + B |
| Tut | Tutorial / bài tập / worksheet / lab trên Canvas | Không | `Tutorials/` | C |

User sẽ nói rõ đang gửi loại nào; nếu không rõ thì hỏi lại trước khi làm.

## A. QUY TRÌNH CHUNG

1. Đọc toàn bộ nội dung PDF slide (text lẫn hình ảnh từng slide).

2. User sẽ paste thêm nội dung trang Canvas của tuần đó (dạng "What's Happening This Day" hoặc tương đương). Cowork CHỈ trích phần link đọc thêm (Extra Reading/tài liệu tham khảo ngoài, vd w3schools...) — bỏ qua toàn bộ phần còn lại (Learning Outcomes, Topic Checklist, Required Activities, mô tả Warm up Video/Reading, hoạt động tương tác, câu dẫn dắt...).
   - LINK KHÔNG ĐỌC ĐƯỢC (áp dụng cho cả 2 loại tài liệu): nếu có dấu hiệu là một link (anchor text, nút bấm, ảnh chụp có link, hyperlink bị mất URL, nội dung dạng ảnh chụp màn hình...) nhưng không đọc được URL đầy đủ, KHÔNG tự đoán hay bịa link. Liệt kê rõ từng link còn thiếu (tên/mô tả + vị trí xuất hiện) và hỏi user copy/gửi URL tương ứng. Chỉ đưa link vào Further Reading sau khi user đã cung cấp URL.

3. Viết file `.md` chi tiết:
   - Nội dung giữ nguyên tiếng Anh.
   - Dùng bảng markdown cho phần dạng bảng.
   - Dùng code block cho code/pseudocode khi có, luôn ghi rõ ngôn ngữ (vd ```python) và giữ nguyên code trên slide (tên biến, indentation, comment), KHÔNG tự sửa hay "tối ưu" code.
   - Sơ đồ UML/class diagram/flowchart dạng hình: dùng ảnh (mục 5), không tự vẽ lại bằng text.
   - KHÔNG thêm dòng "Source: ..." ở đầu file.
   - KHÔNG thêm phần "cần kiểm tra lại/double-check" ở cuối file.
   - KHÔNG thêm dấu `---` (horizontal rule) để ngăn cách các phần trong file `.md`, trừ khi thật sự cần thiết.

4. External link (link xuất hiện trong slide lẫn link đọc thêm lấy từ Canvas) — KHÔNG chèn xen kẽ trong nội dung. Gộp hết lại thành một mục **"Further Reading"** đặt Ở CUỐI file `.md`, sau khi hết nội dung slide. Format link theo kiểu markdown ẩn link `[Mô tả ngắn](URL)`, KHÔNG để URL trần trong file. Link không đọc được URL thì xử lý theo quy tắc ở A.2.

5. Xử lý hình ảnh (Lecture):
   - Đặt tên theo số trang slide: `[số_trang_slide]_[tên_ngắn_gọn].png` (vd `05_for_loop_syntax.png`).
   - Hình dùng lại ở nhiều vị trí (dup): gộp tên theo thứ tự tăng dần, cách nhau dấu `_` (vd `14_15_16_class_diagram.png`).
   - Nhúng bằng cú pháp: `![mô tả ngắn](images/Week{N}/tên_file.png)`.

6. Sau khi hoàn thành file `.md`, viết bảng tổng hợp hình ảnh cần crop **trong chat** (KHÔNG chèn vào file `.md`), gồm 3 cột: `Tên file ảnh | Vị trí (số trang slide) | Mô tả ngắn`.

7. Giao tiếp bằng tiếng Việt; nội dung trong file `.md` giữ tiếng Anh.

8. Khi giao file (áp dụng cho cả 2 loại): chỉ gửi file `.md` kèm bảng ảnh cần crop trong chat, user tự crop và bỏ ảnh vào folder `images/Week{N}/` nằm trong thư mục tương ứng (`Lectures/`, `Tutorials/`) — trừ khi user gửi ảnh gốc qua chat, khi đó áp dụng quy trình xác nhận checksum + đặt tên đúng vị trí.

## B. ĐẶT TÊN (Lecture)

- File `.md`: `[Tên_môn_học]_Week{N}_<Tên chủ đề>.md`. Phần `<Tên chủ đề>` phải mô tả đúng nội dung bài giảng, không sao y tên file PDF gốc. Nếu tên PDF chung chung (vd "Recap"), đề xuất 2-3 phương án tên dựa trên nội dung slide để user chọn.
- File tutorial: thêm `Tut_` ngay sau `Week{N}`: `[Tên_môn_học]_Week{N}_Tut_<Tên chủ đề>.md` (vd `[Tên_môn_học]_Week1_Tut_<Tên chủ đề>.md`), các quy tắc về `<Tên chủ đề>` vẫn áp dụng như file lecture.
- Ảnh: `[số_trang_slide]_[tên_ngắn_gọn].png`, path `images/Week{N}/...`

## C. TUT (tài liệu không có số trang: Tutorial, bài tập, worksheet, lab...)

Áp dụng khi nguồn không phải slide PDF, nên không có số trang để đặt tên ảnh (vd phần Tutorial trên Canvas).

1. Tên file `.md`: `[Tên_môn_học]_Week{N}_Tut.md` (không thêm tên chủ đề dài). File nằm trong `Tutorials/`.

2. Nội dung: chuyển toàn bộ tutorial sang markdown (Preparation, Exercises, Student Guide, câu hỏi Q/F, bảng "If you get stuck"...), giữ nguyên tiếng Anh và giữ nguyên thứ tự gốc. Các quy tắc ở mục A.3 (bảng, code block, không "Source:", không `---`, không phần double-check) vẫn áp dụng.

3. Đặt tên ảnh: dùng số thứ tự xuất hiện trong tài liệu thay cho số trang slide, đánh số 2 chữ số, tách bằng dấu `_`, toàn bộ tên viết thường, các từ trong tên cũng nối bằng `_`:
   `[thứ_tự]_[tên_ngắn_gọn].png` (vd `01_class_diagram.png`, `02_expected_output.png`).
   - Không giữ tên ảnh gốc của Canvas, không dùng dấu `-`.
   - Ảnh dùng lại ở nhiều vị trí: gộp số thứ tự tăng dần, cách nhau `_` (vd `03_04_program_output.png`).
   - Nhúng bằng cú pháp: `![mô tả ngắn](images/Week{N}/tên_file.png)`.

4. Link ngoài (kể cả link trong tutorial): gom hết vào mục **Further Reading** ở cuối file, dạng `[Mô tả ngắn](URL)`, không để URL trần, không chèn xen kẽ. Link tải file từ Canvas (không có URL, vd `starter-code.zip`) thì không đưa vào Further Reading. Link nhìn thấy nhưng không đọc được URL thì xử lý theo quy tắc ở A.2.

5. Bảng ảnh cần crop trong chat (không chèn vào file `.md`) vẫn gồm 3 cột `Tên file ảnh | Vị trí | Mô tả ngắn`, nhưng cột "Vị trí" ghi theo vị trí trong tutorial (vd "Exercise 2, sau Step 5") thay cho số trang slide.

## D. VẼ LẠI HÌNH (TEXT HOẶC MERMAID)

Chỉ áp dụng khi user yêu cầu vẽ lại một hình (user gửi ảnh và nói "vẽ"). Không tự thay ảnh trong file `.md` bằng hình vẽ khi user chưa yêu cầu.

0. ƯU TIÊN VẼ BẰNG TEXT: nếu hình vẽ được bằng ký tự văn bản (hộp, mũi tên, nhãn, đường nối đơn giản) thì KHÔNG dùng Mermaid, vẽ bằng text trong code block ```` ```text ````.
   - Dùng ký tự khung (`┌ ─ ┐ │ └ ┘`) hoặc `+ - |` cho hộp, `──▶` / `───>` cho mũi tên, `▲` `▼` cho mũi tên dọc. Nhãn viết ở bên cạnh hộp, mũi tên chỉ vào đúng vị trí cần chú thích.
   - Phải căn thẳng hàng trong font monospace (đếm cột kỹ, mũi tên và cạnh hộp khớp nhau). Không dùng ký tự có tiếng Việt có dấu hay ký tự rộng trong sơ đồ để khỏi lệch cột.
   - Nội dung và nhãn giữ đúng như hình gốc; ưu tiên đúng quan hệ và vị trí chú thích hơn đúng từng pixel.
   - Quy tắc này là ngoại lệ của A.3 ("không tự vẽ lại bằng text") và chỉ áp dụng khi user yêu cầu vẽ; không tự thay ảnh trong file `.md` khi user chưa yêu cầu.
   - Chỉ chuyển sang Mermaid (các mục 1-9 bên dưới) khi hình có nhiều node, nhiều cạnh chéo, hoặc cardinality/quan hệ phức tạp mà text không thể hiện rõ.
   - Khi đưa code trong chat: bọc khối ```` ```text ```` bên trong khối ````` ````markdown ````` (4 dấu backtick bên ngoài) để user copy được nguyên khối, như mục 3.

1. NÓI TRƯỚC NHỮNG GÌ KHÔNG VẼ ĐƯỢC: trước khi đưa code, liệt kê ngắn gọn phần nào của hình Mermaid không làm được hoặc phải thay thế (bóng đổ, hiệu ứng 3D, phông chữ, mũi tên khối, chấm đỏ chú thích, ký hiệu `x` ở đầu đường nối, khung ngăn extra compartments, màn hình máy tính...). Sau đó mới đưa code.

2. ƯU TIÊN ĐÚNG QUAN HỆ HƠN ĐÚNG VỊ TRÍ: mũi tên, hướng, cardinality, nhãn, thứ tự trái-phải phải đúng với ảnh. Vị trí chính xác từng hộp do Mermaid tự xếp, nên nói rõ bố cục sẽ khác ảnh gốc. Nếu cần ép thứ tự trái-phải, đổi thứ tự khai báo node (và thứ tự cạnh). Nếu ảnh mờ khiến mũi tên khó đọc, nói rõ chỗ nào đọc không chắc để user kiểm tra lại.

3. CÁCH ĐƯA CODE: đặt trong chat, KHÔNG sửa hay ghi file khi user đã nói tự paste. Bọc khối ```` ```mermaid ```` bên trong khối ````` ````markdown ````` (4 dấu backtick bên ngoài) để user copy được nguyên khối.

4. MÀU SẮC VÀ NỀN:
   - Mặc định không tô nền: `fill:none`.
   - Viền và đường nối dùng `#888` (`stroke:#888`, `linkStyle default stroke:#888`) để đọc được cả giao diện sáng lẫn tối.
   - Không đặt `color` cho chữ để chữ tự theo giao diện, trừ khi hình gốc có nền màu (khi đó đặt cả `fill` và `color`).
   - Hình gốc có màu đặc trưng (đỏ, xanh lá, xanh dương, nâu...) thì giữ màu, trừ khi user bảo bỏ nền.

5. CHÚ THÍCH/CAPTION: dùng `title` trong frontmatter (cần Mermaid 10 trở lên). Chú thích nằm phía trên sơ đồ; nói rõ điều này nếu ảnh gốc để dưới. Phương án dự phòng khi trình xem báo lỗi: bỏ frontmatter, dùng `%%{init: {...}}%%` và đặt chú thích dạng chữ in nghiêng bên dưới khối code.

6. ĐƯỜNG NỐI: dùng mặc định của Mermaid. Chỉ đổi khi user yêu cầu: đường thẳng dùng `curve: linear`, đường gấp khúc dùng `curve: step` (đặt trong frontmatter `config: flowchart: curve:`). Cạnh hai chiều dùng `<-->` hoặc `<-.->`.

7. KÍCH THƯỚC: khi hình bị dài hoặc to, đổi `TB` sang `LR`, giảm `nodeSpacing` và `rankSpacing`, bỏ subgraph không cần thiết (subgraph có cạnh nối ra ngoài hay làm bố cục bị xếp chồng).

8. CÚ PHÁP DỄ LỖI:
   - Ký tự `<` `>` trong chữ: dùng `&lt;` `&gt;`.
   - Dấu ngoặc `(` `)` trong một dòng attribute của class diagram: dùng `#40;` `#41;`, nếu không dòng đó bị xếp nhầm vào phần methods.
   - Xuống dòng trong nhãn: `<br/>`.
   - Hộp rỗng có kích thước: dùng `&nbsp;`.
   - Dấu `|` trong bảng markdown: viết `\|`.

9. HÌNH KHÔNG HỢP MERMAID: sơ đồ minh họa có nhiều chú thích đồ họa (chấm đỏ, mũi tên khối, hiệu ứng 3D, vị trí hộp có ý nghĩa) thì nói thẳng là nên giữ ảnh crop, rồi vẫn đưa bản Mermaid gần đúng nếu user vẫn muốn.

## CẤU TRÚC THƯ MỤC

Mỗi loại tài liệu có thư mục riêng; folder `images` nằm cùng thư mục với các file `.md` của loại đó, nên đường dẫn nhúng ảnh luôn là `images/Week{N}/tên_file.png` (tương đối so với file `.md`).

```
[Tên_môn_học]/
├── Lectures/
│   ├── [Tên_môn_học]_Week{N}_<Tên chủ đề>.md
│   └── images/
│       └── Week{N}/
└── Tutorials/
    ├── [Tên_môn_học]_Week{N}_Tut.md
    └── images/
        └── Week{N}/
```

## QUY TẮC THAO TÁC FILE

Cowork chỉ được đọc/tham khảo file (project docs, file trong folder trên máy, v.v.) để lấy thông tin. KHÔNG tự ý tạo, thêm, sửa, hay ghi đè bất kỳ file nào (kể cả project docs) nếu user chưa yêu cầu rõ ràng trong lượt chat đó.