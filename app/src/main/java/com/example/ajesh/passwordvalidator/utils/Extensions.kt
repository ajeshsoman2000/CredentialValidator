package com.example.ajesh.passwordvalidator.utils

import android.util.Log
import java.nio.charset.Charset
import java.security.MessageDigest
import kotlin.experimental.and

fun String.sha1Hash(): String {
    val md = MessageDigest.getInstance("SHA-1")
    val textBytes = this.toByteArray(charset("iso-8859-1"))
    md.update(textBytes, 0, textBytes.size)
    val sha1hash = md.digest()
    Log.v("sha1hash :: ", "of whole password == ${sha1hash.toHex()}")
    return sha1hash.toHex()
}
private val HEX_CHARS = "0123456789ABCDEF".toCharArray()
fun ByteArray.toHex() : String{
    val result = StringBuffer()

    forEach {
        val octet = it.toInt()
        val firstIndex = (octet and 0xF0).ushr(4)
        val secondIndex = octet and 0x0F
        result.append(HEX_CHARS[firstIndex])
        result.append(HEX_CHARS[secondIndex])
    }

    return result.toString()
}