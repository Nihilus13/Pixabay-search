#!/usr/bin/env bash
set -e

adb devices
adb shell settings put global window_animation_scale 0
adb shell settings put global transition_animation_scale 0
adb shell settings put global animation_duration_scale 0

./gradlew ui:executeScreenshotTests

adb shell settings put global window_animation_scale 1
adb shell settings put global transition_animation_scale 1
adb shell settings put global animation_duration_scale 1
