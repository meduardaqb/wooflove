package com.meduardaqb.wooflove.util

import android.content.Context
import java.io.IOException

class JsonParser{

    companion object {

        fun readJSONFromAsset(context: Context): String? {
            var json: String? = null

            try {
                val inputstream = context.assets.open("dog_breed")
                val size = inputstream.available()
                val buffer = ByteArray(size)
                inputstream.read(buffer)
                inputstream.close()
                json = String(buffer)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }

            return json
        }

    }
}