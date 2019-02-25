package com.zdf.androiddeviceid

import android.content.Context
import android.os.Build
import android.os.Environment
import android.support.annotation.RequiresApi
import android.text.TextUtils
import android.util.Log

import java.io.File
import java.io.IOException
import java.util.UUID

object CustomDeviceId {

    private const val TAG = "DeviceId";
    private val KEY = "customdeviceid"
    private val FILE_NAME = "default"
    private val mCustomDeviceId = CustomDeviceId
    private lateinit var mContext: Context

    private var FILE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).toString() + File.separator + FILE_NAME

    fun init(context: Context): CustomDeviceId {
        mContext = context
        return mCustomDeviceId
    }

    fun setFilePath(string: String): CustomDeviceId {
        FILE_PATH = string
        return mCustomDeviceId
    }

    val getCustomDeviceId: String
        @RequiresApi(Build.VERSION_CODES.M)
        get() {
            var result = idFromAPP
            if (TextUtils.isEmpty(result)) {
                result = idFromSdcard
            }
            if (TextUtils.isEmpty(result)) {
                result = deviceId
            }
            result = result.replace("\r", "").replace("\n", "")
            return result
        }


    private val idFromAPP: String
        get() {
            val result = SharedUtil.getString(mContext, KEY, "")
            saveIDToSdcard(result)
            Log.d(TAG, "get id = $result from app")
            return result
        }

    private val idFromSdcard: String
        get() {
            val path = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).toString());
            if (!path.exists()) {
                path.mkdir()
            }
            val file = File(FILE_PATH)
            var result = ""
            if (file.exists()) {
                try {
                    result = FileUtil.toRead(file)
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            Log.d(TAG, "get id = $result from sdcard")
            saveIDToApp(result)
            return result
        }

    private fun saveIDToApp(value: String) {
        if (!TextUtils.isEmpty(value)) {
            SharedUtil.saveString(mContext, KEY, value)
            Log.e(TAG, "save id = $value to app")
        }
    }

    private fun saveIDToSdcard(value: String) {
        if (TextUtils.isEmpty(value)) {
            return
        }
        val path = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_ALARMS).toString());
        if (!path.exists()) {
            path.mkdir()
        }
        val file = File(FILE_PATH)
        if (file.exists()) {
            file.deleteOnExit()
        }
        try {
            FileUtil.toWrite(file, value)
            Log.e(TAG, "save id = $value to sdcard")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private val deviceId: String
        get() {
            val result = UUID.randomUUID().toString().toLowerCase().replace("-", "")
            saveIDToApp(result)
            saveIDToSdcard(result)
            Log.e(TAG, "get device id = $result and save to both app and sdcard")
            return result
        }
}
