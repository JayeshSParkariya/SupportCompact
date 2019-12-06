package org.supportcompact.ktx

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

fun File.toMultipartBody(key: String, mediaType: String = "image/*"): MultipartBody.Part {
    val requestFile = RequestBody.create(MediaType.parse(mediaType), this)
    return MultipartBody.Part.createFormData(key, name, requestFile)
}

fun String.toReqBoday() = RequestBody.create(MediaType.parse("text/plain"), this)