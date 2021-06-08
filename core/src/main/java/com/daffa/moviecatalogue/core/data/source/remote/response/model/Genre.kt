package com.daffa.moviecatalogue.core.data.source.remote.response.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
data class Genre(
    val id: Int,
    val name: String
)
