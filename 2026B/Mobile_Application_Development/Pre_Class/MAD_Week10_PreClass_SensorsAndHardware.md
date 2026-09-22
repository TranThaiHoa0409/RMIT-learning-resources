# MAD Week 10 – Pre-Class: Sensors and Hardware

## 1. Sensors and Hardware

Mobile devices are equipped with sensors and hardware that enable them to be aware of their surroundings and possess advanced features.

### Sensors and Hardware in Android Development

A sensor detects and measures physical properties from the environment or the device itself, then converts that information into digital data for the app.

Sensors can be hardware-based (e.g., an actual gyroscope chip) or software-based (e.g., using data from multiple hardware sources to estimate a position).

Common mobile sensors:

| Category | Sensor | Description |
|---|---|---|
| Motion Sensors | Accelerometer | Detects acceleration and tilt |
| Motion Sensors | Gyroscope | Measures device rotation |
| Motion Sensors | Step Counter | Counts physical steps |
| Environmental Sensors | Ambient Light Sensor | Measures surrounding brightness |
| Environmental Sensors | Barometer | Measures air pressure to assist GPS |
| Environmental Sensors | Thermometer | Reads device or environmental temperature |
| Position Sensors | Magnetometer | Detects magnetic fields (compass function) |
| Position Sensors | GPS | Provides precise geographic location |

### Hardware Components in Mobile Devices

In Android development, hardware refers to the physical components of a mobile device that software interacts with. Besides sensors, common hardware components include the GPS chip, camera, and microphone.

### ML Kit - Google's On-Device ML SDK

ML Kit is a feature used in developing Android apps. Sensors and hardware provide data, and ML Kit shows what can be built with that data.

ML Kit enables machine learning features to be integrated into an app with on-device processing (fast and private) and CameraX integration for seamless camera access.

Popular ML Kit APIs:

- Text Recognition: scan and extract text from images
- Face Detection: identify faces in real time
- Object Detection & Tracking: recognise and follow objects through the camera feed
- Barcode Scanning: scan QR codes or product barcodes

Example: A shopping app uses barcode scanning to instantly retrieve product details.
