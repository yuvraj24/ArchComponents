package com.arch.components.utils

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Created by Yuvraj on 27/03/18.
 */
class AppUtils {

    companion object {
        fun logMe(tag: String, msg: String) {
            Log.v(tag, msg)
        }

        /**
         * Supported algorithms on Android:
         *
         * Algorithm	Supported API Levels
         * MD5          1+
         * SHA-1	    1+
         * SHA-224	    1-8,22+
         * SHA-256	    1+
         * SHA-384	    1+
         * SHA-512	    1+
         */
        fun getMD5String(input: String): String {
//            val HEX_CHARS = "0123456789ABCDEF"
//            val bytes = MessageDigest
//                    .getInstance("MD5")
//                    .digest(input.toByteArray())
//            val result = StringBuilder(bytes.size * 2)
//
//            bytes.forEach {
//                val i = it.toInt()
//                result.append(HEX_CHARS[i shr 4 and 0x0f])
//                result.append(HEX_CHARS[i and 0x0f])
//            }
//            return result.toString();

            AppUtils.logMe("HASH_BEFORE", input)
            val md = MessageDigest.getInstance("MD5")
            val hash : String = BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
            AppUtils.logMe("HASH_AFTER", hash)
            return hash

        }
    }
}