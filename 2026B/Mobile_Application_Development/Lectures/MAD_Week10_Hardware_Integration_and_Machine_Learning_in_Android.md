# Hardware Integration and Machine Learning in Android

## Week Agenda

- Introduction to Android sensors & hardware
- Accessing sensor data in Kotlin/Compose
- Camera & Media Integration
- Introduction to ML Kit
- ML Kit Analyzer in Android apps

## Introduction to Android Sensors & Hardware

### Introduction to Android Sensors

- Android devices provide multiple **built-in sensors**:
  - **Motion**: accelerometer, gyroscope, gravity, etc.
  - **Position**: magnetometer, proximity, rotation, vector, etc.
  - **Environmental**: light, thermometer, barometer, humidity, etc.
- Support many useful features:
  - Fitness & Health, Gaming, Navigation, Accessibility, AR/VR, Smart Apps, etc.

## Accessing Sensor Data in Kotlin/Compose

### Accessing Sensors in Android

- Use `SensorManager` to access hardware sensors
- Register/unregister **listeners** to retrieve data
- `onSensorChanged()` and `onAccuracyChanged()` must be overridden. For many sensors, `onAccuracyChanged()` can be left empty
- Note: must respect **lifecycle** (register in `onResume()`, unregister in `onPause()`)

```kotlin
private lateinit var sensorManager: SensorManager

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    sensorManager = getSystemService(name = Context.SENSOR_SERVICE) as SensorManager
}

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
        Log.d(tag = "SensorTesting", msg = "Proximity: NEAR")  // Something is close to the sensor
    } else {
        Log.d(tag = "SensorTesting", msg = "Proximity: FAR")   // Nothing is near the sensor
    }
}
```

## Camera & Media Integration

### Camera Integration

- Camera APIs in Android
  - **Camera (legacy API)** -> deprecated, avoid using
  - **Camera2 API** -> powerful, but complex (low-level)
  - **CameraX API** -> modern Jetpack library, built on Camera2, much simpler to use (Also, lifecycle-aware & easy integration)
- Common in ML apps:
  - Face detection
  - Barcode scanning
  - Text recognition
  - etc.

Permission:

```xml
<uses-feature
    android:name="android.hardware.camera"
    android:required="false" />

<uses-permission android:name="android.permission.CAMERA" />
```

Dependencies (`build.gradle`):

```kotlin
val camerax_version = "1.3.0"

implementation("androidx.camera:camera-core:$camerax_version")
implementation("androidx.camera:camera-camera2:$camerax_version")
```

Opens the **back camera** and shows a live preview in a Compose screen:

```kotlin
@Composable
fun CameraPreview() {
    val context = LocalContext.current
    AndroidView(factory = { ctx ->
        PreviewView(context = ctx).also { previewView ->
            val cameraProviderFuture = ProcessCameraProvider.getInstance(context = ctx)
            cameraProviderFuture.addListener(listener = {
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

### More on Camera Integration

- We can also use some CameraX libraries to support further operations
  - **ImageCapture** -> take photos and save them
  - **ImageAnalysis** -> analyze frames (connect with **ML Kit** for barcode scanning, text recognition, face detection)
  - **VideoCapture** (since CameraX v1.2) -> record videos
- Materials to explore these (Use cases and Advanced topics)
  - https://developer.android.com/media/camera/camerax?hl=vi

## Introduction to ML Kit

### Introduction to ML on Android

- What is ML (Machine Learning)?
  - Computers learn patterns from data -> make predictions or recognize features
  - In Android apps, often used for **vision** (camera) and **NLP** (text, language)
- Common Use Cases
  - **Vision**: face detection, barcode/QR scanning, object recognition
  - **NLP**: text translation, language identification, sentiment analysis
  - **Smart features**: predictive typing, auto-suggestions, recommendations

### On-device vs Cloud Machine Learning

| On-device ML | Cloud ML |
|---|---|
| Fast, works offline | More powerful models |
| Privacy-friendly (data stays on device) | But requires internet -> higher latency |
| However, limited by phone hardware | |

Examples:

- Google Translate app -> offline translation (on-device ML)
- Google Photos -> face grouping (cloud + on-device ML)

### ML Kit Overview

- Google's **ML Kit** = ready-to-use SDK for mobile ML
- Two categories:
  - **Vision APIs**: barcode, face detection, image labeling, text recognition
  - **NLP APIs**: language identification, translation, smart reply
- Support for **custom TensorFlow Lite models**
- Benefits:
  - No need to train your own model
  - Runs efficiently on Android/iOS
  - Easy to integrate with CameraX

## ML Kit Analyzer in Android apps

### ML Kit Analyzer API

- Works with **CameraX** -> process **live camera frames** in real-time
- Typical pipeline:
  1. Capture frame with CameraX `ImageAnalysis`
  2. Convert frame -> `InputImage`
  3. Pass to ML Kit API (e.g., text recognition)
  4. Get result callback (success/failure)
- Key Classes:
  - `ImageAnalysis` -> provides frames from camera
  - `InputImage` -> wraps media image or file
  - `TextRecognition`, `BarcodeScanner`, etc. -> ML Kit analyzers

### ML Kit Example - Text Recognition

Dependencies (`build.gradle`):

```kotlin
implementation("com.google.mlkit:text-recognition:16.0.0")
```

How Text Recognition Works

1. Input: camera frame (or photo)
2. ML Kit detects **blocks** -> **lines** -> **elements (words)**
3. Returns **text elements**

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

            cameraProviderFuture.addListener(listener = {
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
                                    .addOnFailureListener { e ->
                                        e.printStackTrace()
                                    }
                                    .addOnCompleteListener {
                                        imageProxy.close()
                                    }
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
