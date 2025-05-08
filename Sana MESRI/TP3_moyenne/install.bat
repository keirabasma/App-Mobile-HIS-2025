@echo off
echo Installing APK...
        adb install app\build\outputs\apk\debug\app-debug.apk
      adb shell am start -n com.example.myapplication/.MainActivity


