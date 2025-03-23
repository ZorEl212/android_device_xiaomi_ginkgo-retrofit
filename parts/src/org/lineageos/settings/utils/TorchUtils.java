/*
 * Copyright (C) 2021 WaveOS
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 package org.lineageos.settings.utils;

 import android.content.SharedPreferences;
 
 public class TorchUtils {
 
     private static final String TORCH_BRIGHTNESS_PATH = "/sys/devices/platform/soc/1c40000.qcom,spmi/spmi-0/spmi0-03/1c40000.qcom,spmi:qcom,pmi632@3:qcom,leds@d300/leds/led:torch_0/custom_brightness";
     private static final String PREF_TORCH_STRENGTH = "torch_strength";
 
     // Torch brightness range in kernel (adjust based on actual device values)
     private static final int MIN_BRIGHTNESS = 25; 
     private static final int MAX_BRIGHTNESS = 255;
     private static final int BRIGHTNESS_RANGE = MAX_BRIGHTNESS - MIN_BRIGHTNESS;
 
     public static boolean isAvailable() {
         return FileUtils.isFileWritable(TORCH_BRIGHTNESS_PATH);
     }
 
     public static void setTorchStrength(int percent) {
         if (!isAvailable()) {
             return; // Don't attempt to write if file is not writable
         }
         int brightness = MIN_BRIGHTNESS + (int) (percent / 100.0f * BRIGHTNESS_RANGE);
         FileUtils.writeLine(TORCH_BRIGHTNESS_PATH, Integer.toString(brightness));
     }
 
     public static void setCurrentTorchStrength(SharedPreferences sharedPrefs) {
         int torchStrength = sharedPrefs.getInt(PREF_TORCH_STRENGTH, 85);
         setTorchStrength(torchStrength);
     }
 
     public static int getTorchStrength() { /* returns percentage */
         String brightness = FileUtils.readOneLine(TORCH_BRIGHTNESS_PATH);
         if (brightness == null || brightness.isEmpty()) {
             return 85; // Default fallback value
         }
         try {
             int value = Integer.parseInt(brightness);
             return (int) ((value - MIN_BRIGHTNESS) * 100 / (float) BRIGHTNESS_RANGE);
         } catch (NumberFormatException e) {
             return 85; // Default fallback in case of parsing error
         }
     }
 }
 