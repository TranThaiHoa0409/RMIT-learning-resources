# BizBoard sample dataset — GreenLeaf Coffee (dữ liệu bịa)

Dataset **giả lập** để cả team hiểu business logic của BizBoard. Không phải dữ liệu thật; chỉ dùng để minh hoạ trong SRS và để mọi người cùng nhìn một ví dụ khi viết user story / use case / acceptance criteria.

**Công ty giả định:** GreenLeaf Coffee, một cửa hàng cà phê online (bán hạt cà phê, thiết bị pha, đồ lưu niệm, gói subscription) ở Việt Nam. Đơn vị tiền: USD (cho dễ đọc).
**Khoảng thời gian:** 01 Aug 2026 → 07 Nov 2026 (99 ngày). Ngày "hôm nay" trong ví dụ coi như 08 Nov 2026, nên "Last 7 days" = **1 to 7 Nov**.

## 1. Các file

| File | Dòng | Nội dung | Phục vụ |
| --- | --- | --- | --- |
| `orders.csv` | 8,633 | Mỗi dòng 1 đơn: `order_id, order_datetime, customer_id, region, product_category, sales_channel, quantity, order_value` | Total Sales, Average Order Value, Total Revenue, Revenue Growth, Customer Retention Rate, filter, drill-down |
| `customers.csv` | 5,084 | `customer_id, signup_date, region` (khách đăng ký trước 1 Aug là "tệp khách cũ") | New Customers, Customer Retention Rate |
| `app_sessions.csv` | 31,968 | Mỗi dòng 1 phiên dùng app: `session_id, session_datetime, user_id (= customer_id), converted (1 = có mua)` | Active Users, Conversion Rate |
| `users.csv`, `groups.csv`, `group_members.csv` | 8 / 3 / 8 | Tài khoản BizBoard, nhóm, vai trò trong nhóm | Phân quyền, chia sẻ report, notes |
| `alert_rules.csv` | 4 | Vài rule ngưỡng mẫu | Alert rules / checking / delivery |
| `kpi_daily_answer_key.csv` | 99 | KPI tính sẵn theo từng ngày (đáp án để đối chiếu, **không phải dữ liệu nguồn** của app) | Kiểm tra số trong ví dụ |

Hệ thống thật chỉ cần `orders`, `customers`, `app_sessions` (đúng với quyết định "sample datasets + uploaded files"). Các file còn lại chỉ để demo tài khoản/nhóm/rule.

## 2. 8 KPI tính từ dữ liệu thế nào

Tên KPI giữ **đúng** như guide của team.

| KPI | Cách tính trong một period (a → b) | Nguồn |
| --- | --- | --- |
| Total Sales | Số **đơn hàng** có `order_datetime` trong period | orders |
| Average Order Value | Total Revenue ÷ Total Sales | orders |
| Total Revenue | Tổng `order_value` | orders |
| Revenue Growth | (Revenue kỳ A − Revenue kỳ B) ÷ Revenue kỳ B | orders |
| New Customers | Số khách có `signup_date` trong period | customers |
| Customer Retention Rate | Trong số khách **đã mua ở kỳ liền trước (cùng độ dài)**, tỉ lệ khách mua lại trong period này | orders |
| Active Users | Số `user_id` **khác nhau** có ít nhất 1 session trong period | app_sessions |
| Conversion Rate | Số user có session `converted = 1` ÷ Active Users | app_sessions |

**Các chỗ team cần chốt (mình tự giả định để dataset chạy được):**
1. *Total Sales* guide ghi "items or orders". Dataset dùng **số đơn**. Nếu muốn đếm số món thì cộng `quantity`.
2. *Retention* và *Conversion* là tỉ lệ theo **period**, nên period càng dài thì số càng cao (Retention: tuần ≈ 10–25%, tháng ≈ 60%; Conversion: tuần ≈ 30%, tháng ≈ 40%). Đây là hành vi đúng, đừng so sánh hai period khác độ dài.
3. Period comparison bắt buộc hai kỳ **cùng độ dài** nên Revenue Growth so sánh công bằng.

## 3. Ví dụ tính tay (dùng để viết acceptance criteria)

**Dashboard "Last 7 days: 1 to 7 Nov"**

| KPI | Giá trị |
| --- | --- |
| Total Sales | 461 |
| Average Order Value | $36.79 |
| Total Revenue | $16,959.16 |
| New Customers | 120 |
| Customer Retention Rate | 14.4% (so với 25–31 Oct) |
| Active Users | 1,307 |
| Conversion Rate | 30.4% |

**Period comparison** (Period A = 1–7 Nov, Period B = 15–21 Sep, cùng 7 ngày, không chồng lấn)

| KPI | Period A | Period B | Chênh lệch |
| --- | --- | --- | --- |
| Total Revenue | $16,959.16 | $23,480.64 | $-6,521.48 (-27.8%) → đây là **Revenue Growth** |
| Total Sales | 461 | 630 | -169 (-26.8%) |
| Active Users | 1,307 | 1,187 | 120 (10.1%) |

**Filter** `Region: North`, 1–7 Nov: Total Sales 125, Total Revenue $4,616.86, Active Users 367. Các phép tính khác (drill-down) cũng chỉ hiện đơn North.

**Drill-down**: bấm cột ngày 3 Nov trên trend Total Revenue sẽ ra 22 đơn (xem `orders.csv`, lọc ngày 2026-11-03), tổng $712.46.

## 4. Các tình huống đã cài sẵn trong dữ liệu

| # | Tình huống | Dữ liệu | Dùng cho |
| --- | --- | --- | --- |
| S1 | **Doanh thu tụt** (sự cố cổng thanh toán) | Total Revenue dưới $1,000 vào: 03 Nov ($712), 04 Nov ($926), 07 Nov ($744). Trung bình ngày bình thường ≈ $3,150 | Alert rule `R01` (Total Revenue goes below 1,000) |
| S2 | **Alert chỉ tạo 1 lần khi KPI vẫn dưới ngưỡng** | 3 và 4 Nov đều dưới 1,000 → chỉ 1 alert. 5–6 Nov về bình thường. 7 Nov dưới ngưỡng lại → alert thứ 2 | Alert checking (rule "remember triggered") |
| S3 | **Khuyến mãi 10.10** | 10–12 Oct doanh thu tăng ~1.7× (cao nhất 10 Oct ≈ $5,300) | Rule `R02` (above 3,500), trend, so sánh kỳ |
| S4 | **Mất dữ liệu app usage** | 20–22 Sep không có session nào → Active Users và Conversion Rate = no data, nhưng doanh thu vẫn có (290 đơn, $10,792.27) | No-data rule, report có "một số KPI không có data" (confirmation box) |
| S5 | **Một vùng không có đơn** | Region Central không có đơn 9–13 Oct → filter Region: Central cho kỳ này ra "No data", trong khi New Customers vẫn là 27 | Filter rỗng, empty message |
| S6 | **Kỳ không có dữ liệu** | Trước 01 Aug 2026 không có đơn nào (ví dụ so sánh 1–7 Nov với 20–26 Jul) | Period comparison có 1 kỳ no data |
| S7 | **Cuối tuần cao hơn** | Thứ 7, CN cao hơn ngày thường ~30% | Trend theo ngày không phẳng |

Rule `R03` (Conversion Rate below 20%) không bao giờ bị kích hoạt trong data (thấp nhất 24.1%), dùng làm ví dụ "rule không có alert". Rule `R04` đang tắt (`is_active = False`) nên không tạo alert dù New Customers thấp.

## 5. Tình huống phân quyền / chia sẻ (users, groups)

| User | Vai trò hệ thống | Nhóm |
| --- | --- | --- |
| Admin An | System Administrator | không thuộc nhóm nào |
| Hoa | Business User | Group Owner của Management |
| Len | Business User | Group Owner của Sales Team; Member của Management |
| Phu | Business User | Member của Sales Team |
| Yuki | Business User | Group Owner của Marketing |
| Mai | Business User | Member của Sales Team **và** Marketing |
| Bao | Business User | Member của Marketing, **không có email** → chỉ nhận in-app |
| Nam | (đã bị deactivate) | không đăng nhập được |

Ví dụ:
- Phu tạo report rồi chia sẻ cho **Sales Team** → Len, Mai xem được và **được viết note**.
- Phu chia sẻ cùng report riêng cho Hoa (cá nhân) → Hoa đọc được note nhưng **không viết note**.
- Phu **không** chia sẻ được cho Marketing vì không thuộc nhóm đó, nhưng vẫn chia sẻ cá nhân cho Yuki được.
- Mai rời Sales Team → mất quyền xem report và note qua nhóm đó.
- Alert cho Bao: chỉ hiện in-app vì không có email.

## 6. Giới hạn của dataset

- Dữ liệu sinh ngẫu nhiên có seed cố định, nên con số "tự nhiên" nhưng không phản ánh doanh nghiệp thật.
- Không có market intelligence và API live (ngoài scope v1).
- Categories dùng để filter: `region` (North/Central/South), `product_category` (Coffee Beans/Equipment/Merchandise/Subscription), `sales_channel` (Website/Mobile Web/Marketplace). Len cần chốt danh sách filter cuối cùng.
