Tạo 5 scheduled task chạy hằng ngày theo múi giờ Asia/Saigon. Cả 5 có cùng prompt: "Reply with only the word hi. Do not use any tools." Dùng đúng giờ, không dời giờ.

1. Tên "Ping 1 - 12:30 AM" → CRON_TZ=Asia/Saigon 30 0 * * *
2. Tên "Ping 2 - 4:40 AM"  → CRON_TZ=Asia/Saigon 40 4 * * *
3. Tên "Ping 3 - 8:52 AM"  → CRON_TZ=Asia/Saigon 52 8 * * *
4. Tên "Ping 4 - 2:10 PM"  → CRON_TZ=Asia/Saigon 10 14 * * *
5. Tên "Ping 5 - 7:20 PM"  → CRON_TZ=Asia/Saigon 20 19 * * *