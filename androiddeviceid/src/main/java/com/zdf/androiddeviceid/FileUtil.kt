package com.zdf.androiddeviceid

import java.io.*

object FileUtil {
    fun toRead(file: File): String {
        var result = ""
        var fileReader: FileReader? = null
        var bufferedReader: BufferedReader? = null
        try {
            fileReader = FileReader(file)
            bufferedReader = BufferedReader(fileReader)
            try {
                var read: String? = null
                while ({ read = bufferedReader.readLine();read }() != null) {
                    result = result + read + "\r\n"
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close()
            }
            if (fileReader != null) {
                fileReader.close()
            }
        }
        return result
    }


    fun toWrite(file: File, str: String) {
        try {
            val fw = FileWriter(file)
            fw.write(str)
            fw.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }



}