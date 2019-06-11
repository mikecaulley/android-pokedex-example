package com.example.pokedex.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.net.URI

@Parcelize
data class Pokemon(
    val name: String,
    val sprites: Map<String, URI?>,
    val height: Int,
    val weight: Int
) : Parcelable