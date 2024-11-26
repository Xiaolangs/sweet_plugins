package com.sweet.ext

import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.security.MessageDigest


fun File.smartCreateNewFile(): Boolean {
    if (exists()) return true
    if (parentFile?.exists() == true) return createNewFile()
    if (parentFile?.mkdirs() == true) {
        if (this.createNewFile()) {
            return true
        }
    }
    return false
}

fun File.smartCreateFolder(): Boolean {
    if (!exists()) {
        return if (!mkdir()) {
            mkdirs()
        } else {
            true
        }
    }
    return true
}

fun File.deleteFolder() {
    if (!exists() || !isDirectory) return
    for (file in listFiles()!!) {
        if (file.isFile) file.delete()
        else if (file.isDirectory) file.deleteFolder()
    }
    delete()
}

fun File.md5check(md5: String): Boolean {
    return md5.equals(getFileMD5(this), true)
}

fun File.md5(): String {
    return getFileMD5(this)
}

fun getFileMD5(filePath: File): String {
    if (!filePath.exists()) return ""
    val inputStream: InputStream = FileInputStream(filePath)
    val buffer = ByteArray(1024)
    val complete = MessageDigest.getInstance("MD5")
    var numRead: Int
    do {
        numRead = inputStream.read(buffer)
        if (numRead > 0) {
            complete.update(buffer, 0, numRead)
        }
    } while (numRead != -1)

    inputStream.close()
    val b = complete.digest()

    var result = ""
    for (i in b.indices) {
        result += ((b[i].toInt() and 0xff) + 0x100).toString(16).substring(1)
    }
    return result
}