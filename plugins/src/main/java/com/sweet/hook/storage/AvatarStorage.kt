package com.sweet.hook.storage

import android.graphics.Bitmap
import android.widget.ImageView

/**
 * description:
 * date: 2024/9/10 14:08
 * author: xiaolang
 */
object AvatarStorage {
    private var avatarStorage: Any? = null

    fun iniAvatarStorage(avatarStorage: Any?) {
        this.avatarStorage = avatarStorage
    }

    fun getAvatarStorage() = avatarStorage

    fun setAvatar(imageView: ImageView, wxId: String) {

    }

    fun getAvatar(wxId: String, callBack: (Bitmap) -> Unit) {

    }

    fun getAvatar(wxId: String): Bitmap? {
      return null
    }
}