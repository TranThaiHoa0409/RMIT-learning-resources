# MAD Week 10 — Hardware Integration and Machine Learning in Android

**Môn học:** COSC2657/COSC2543/COSC2729 — Android Development
**Giảng viên:** Minh T. Vu

## Agenda
- Introduction to Android sensors & hardware
- Accessing sensor data in Kotlin/Compose
- Camera & Media Integration
- Introduction to ML Kit
- ML Kit Analyzer in Android apps

---

## 1. Introduction to Android Sensors

### 1.1 Các loại sensor built-in
| Nhóm | Ví dụ |
|---|---|
| **Motion** | accelerometer, gyroscope, gravity, ... |
| **Position** | magnetometer, proximity, rotation vector, ... |
| **Environmental** | light, thermometer, barometer, humidity, ... |

### 1.2 Ứng dụng
- Fitness & Health, Gaming, Navigation, Accessibility, AR/VR, Smart Apps, ...

---

## 2. Accessing Sensors in Android

### 2.1 Nguyên tắc chung
- Dùng **SensorManager** để truy cập hardware sensor.
- Đăng ký/hủy đăng ký **listener** để nhận data.
- Phải override `onSensorChanged()` và `onAccuracyChanged()` (nhiều sensor có thể để `onAccuracyChanged()` rỗng).
- **Lưu ý lifecycle:** đăng ký trong `onResume()`, hủy đăng ký trong `onPause()`.

```kotlin
private lateinit var sensorManager: SensorManager

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sensorManager = getSystemService(name = Context.SENSOR_SERVICE) as SensorManager
}
```

### 2.2 Đăng ký/hủy đăng ký + xử lý sự kiện

```kotlin
override fun onResume() {
    super.onResume()
    val proximity = sensorManager.getDefaultSensor(type = Sensor.TYPE_PROXIMITY)
    sensorManager.registerListener(listener = this, sensor = proximity, samplingPeriodUs = SensorManager.SENSOR_DELAY_NORMAL)
}

override fun onPause() {
    super.onPause()
    sensorManager.unregisterListener(listener = this)
}

override fun onSensorChanged(event: SensorEvent?) {
    val distance = event?.values?.get(0) ?: return
    if (distance < event.sensor.maximumRange) {
        Log.d("SensorTesting", "Proximity: NEAR")  // Something is close to the sensor
    } else {
        Log.d("SensorTesting", "Proximity: FAR")   // Nothing is near the sensor
    }
}
```

---

## 3. Camera Integration

### 3.1 Các Camera API trên Android
| API | Ghi chú |
|---|---|
| **Camera (legacy API)** | deprecated, tránh dùng |
| **Camera2 API** | mạnh mẽ nhưng phức tạp (low-level) |
| **CameraX API** | Jetpack library hiện đại, xây trên Camera2, đơn giản hơn nhiều (lifecycle-aware & dễ tích hợp) |

**Ứng dụng phổ biến trong ML app:** face detection, barcode scanning, text recognition, ...

### 3.2 Permission & Dependencies

```xml
<uses-feature
    android:name="android.hardware.camera"
    android:required="false" />

<uses-permission android:name="android.permission.CAMERA" />
```

```kotlin
val camerax_version = "1.3.0"

implementation("androidx.camera:camera-core:$camerax_version")
implementation("androidx.camera:camera-camera2:$camerax_version")
```

### 3.3 Camera Preview trong Compose

```kotlin
@Composable
fun CameraPreview() {
    val context = LocalContext.current
    AndroidView(factory = { ctx ->
        PreviewView(context = ctx).also { previewView ->
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context = ctx)
            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                val preview = Preview.Builder().build().apply {
                    setSurfaceProvider(previewView.surfaceProvider)
                }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner = context as ComponentActivity, cameraSelector, useCases = preview
                )
            }, executor = ContextCompat.getMainExecutor(context = ctx))
        }
    })
}
```

### 3.4 Các use case mở rộng của CameraX
| Use case | Chức năng |
|---|---|
| `ImageCapture` | chụp ảnh và lưu lại |
| `ImageAnalysis` | phân tích từng frame (kết nối với **ML Kit** cho barcode scanning, text recognition, face detection) |
| `VideoCapture` (từ CameraX v1.2) | quay video |

Tài liệu tham khảo: https://developer.android.com/media/camera/camerax?hl=vi

---

## 4. Machine Learning in Android

### 4.1 ML là gì?
- Máy tính học pattern từ data → đưa ra dự đoán hoặc nhận diện đặc trưng.
- Trong Android app, thường dùng cho **vision** (camera) và **NLP** (văn bản, ngôn ngữ).

### 4.2 Use case phổ biến
| Nhóm | Ví dụ |
|---|---|
| **Vision** | face detection, barcode/QR scanning, object recognition |
| **NLP** | text translation, language identification, sentiment analysis |
| **Smart features** | predictive typing, auto-suggestions, recommendations |

### 4.3 On-device vs Cloud ML
| | On-device ML | Cloud ML |
|---|---|---|
| Tốc độ | Nhanh, chạy offline | Cần internet → độ trễ cao hơn |
| Privacy | Thân thiện (data giữ tại device) | — |
| Giới hạn | Bị giới hạn bởi phần cứng điện thoại | Model mạnh mẽ hơn |

**Ví dụ:**
- Google Translate app → offline translation (on-device ML)
- Google Photos → face grouping (cloud + on-device ML)

---

## 5. ML Kit Overview

- **ML Kit** của Google = SDK sẵn sàng dùng ngay cho mobile ML.
- Hai nhóm chính:
  - **Vision APIs:** barcode, face detection, image labeling, text recognition
  - **NLP APIs:** language identification, translation, smart reply
- Hỗ trợ **custom TensorFlow Lite models**.
- Lợi ích:
  - Không cần tự train model
  - Chạy hiệu quả trên Android/iOS
  - Dễ tích hợp với CameraX

---

## 6. ML Kit Analyzer API

- Hoạt động cùng **CameraX** → xử lý **live camera frames** theo thời gian thực.

### 6.1 Pipeline điển hình
1. Capture frame với CameraX `ImageAnalysis`
2. Convert frame → `InputImage`
3. Đưa vào ML Kit API (VD: text recognition)
4. Nhận kết quả qua callback (success/failure)

### 6.2 Key classes
| Class | Vai trò |
|---|---|
| `ImageAnalysis` | cung cấp frame từ camera |
| `InputImage` | wrap media image hoặc file |
| `TextRecognition`, `BarcodeScanner`, ... | các analyzer của ML Kit |

---

## 7. ML Kit Example — Text Recognition

### 7.1 Dependency

```kotlin
implementation("com.google.mlkit:text-recognition:16.0.0")
```

### 7.2 Cách hoạt động
1. Input: camera frame (hoặc ảnh)
2. ML Kit detect **blocks** → **lines** → **elements (words)**
3. Trả về **text elements**

### 7.3 Code mẫu tổng hợp

```kotlin
@Composable
fun TextRecognitionScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var detectedText by remember { mutableStateOf(value = "Point camera at text...") }

    val cameraProviderFuture = remember {
        ProcessCameraProvider.getInstance(context)
    }

    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 400.dp),
        factory = { ctx ->
            val previewView = PreviewView(context = ctx)

            cameraProviderFuture.addListener({
                val cameraProvider = cameraProviderFuture.get()

                // Preview use case
                val preview = Preview.Builder().build().apply {
                    setSurfaceProvider(previewView.surfaceProvider)
                }

                // Text recognizer
                val recognizer = TextRecognition.getClient(options = TextRecognizerOptions.DEFAULT_OPTIONS)

                // Analyzer use case
                val analysisExecutor = Executors.newSingleThreadExecutor()
                val analyzer = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(analysisExecutor) { imageProxy ->
                            val mediaImage = imageProxy.image
                            if (mediaImage != null) {
                                val image = InputImage.fromMediaImage(
                                    mediaImage,
                                    rotationDegrees = imageProxy.imageInfo.rotationDegrees
                                )
                                recognizer.process(p0 = image)
                                    .addOnSuccessListener { visionText ->
                                        detectedText = visionText.text.ifEmpty { "No text detected" }
                                    }
                                    .addOnFailureListener { e -> e.printStackTrace() }
                                    .addOnCompleteListener { imageProxy.close() }
                            } else {
                                imageProxy.close()
                            }
                        }
                    }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner, cameraSelector, useCases = preview, analyzer
                    )
                } catch (exc: Exception) {
                    exc.printStackTrace()
                }
            }, executor = ContextCompat.getMainExecutor(context = ctx))

            previewView
        }
    )

    Spacer(modifier = Modifier.height(height = 16.dp))

    Text(
        text = detectedText,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(all = 16.dp)
    )
}
```

---

## Tóm tắt các điểm chính
1. Android cung cấp nhiều sensor built-in (motion, position, environmental) truy cập qua `SensorManager`, phải tuân theo lifecycle (đăng ký ở `onResume`, hủy ở `onPause`).
2. Camera nên dùng **CameraX** thay vì Camera/Camera2 API vì đơn giản và lifecycle-aware hơn.
3. `ImageAnalysis` là cầu nối giữa CameraX và ML Kit để xử lý frame theo thời gian thực.
4. **ML Kit** là SDK sẵn dùng của Google cho Vision & NLP, không cần tự train model, dễ tích hợp với CameraX.
5. On-device ML nhanh & bảo mật hơn nhưng bị giới hạn phần cứng; Cloud ML mạnh hơn nhưng cần internet.
6. Pipeline chuẩn khi làm ML trên camera: `ImageAnalysis` → `InputImage` → ML Kit analyzer (VD: `TextRecognition`) → callback kết quả.
