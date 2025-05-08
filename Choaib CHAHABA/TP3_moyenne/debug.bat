@echo off

echo  Restarting app...
 adb install -r app\build\outputs\apk\debug\app-debug.apk
 adb shell am start -n com.example.myapplication/.MainActivity


echo  Done.
pause
