LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := Launcher3QuickStep
LOCAL_SRC_FILES := priv-app/Launcher3QuickStep/Launcher3QuickStep.apk
LOCAL_PRIVILEGED_MODULE := true
LOCAL_SYSTEM_EXT_SPECIFIC := true
LOCAL_OVERRIDES_PACKAGES := Home Launcher2 Launcher3
LOCAL_REQUIRED_MODULES := privapp_whitelist_com.android.launcher3-ext
LOCAL_MODULE_TAGS := optional
LOCAL_CERTIFICATE := PRESIGNED
LOCAL_MODULE_CLASS := APPS
include $(BUILD_PREBUILT)

include $(CLEAR_VARS)
LOCAL_MODULE := privapp_whitelist_com.android.launcher3-ext
LOCAL_SRC_FILES := priv-app/Launcher3QuickStep/privapp_whitelist_com.android.launcher3-ext.xml
LOCAL_SYSTEM_EXT_SPECIFIC := true
LOCAL_MODULE_SUB_DIR := permissions
LOCAL_MODULE_FILENAME_FROM_SRC := true
LOCAL_MODULE_TAGS := optional
LOCAL_MODULE_CLASS := ETC
include $(BUILD_PREBUILT)

