//package com.example.musium.domain.model
//
//import android.os.Parcelable
//import kotlinx.parcelize.Parcelize
//import kotlinx.serialization.Serializable
//
//@Parcelize
//@Serializable
//data class Track(
//    val id: String,
//    val name: String,
//    val durationMs: Int,
//    val discNumber: Int,
//    val trackNumber: Int,
//    val explicit: Boolean,
//    val isPlayable: Boolean,
//    val isLocal: Boolean,
//    val previewUrl: String?,
//    val type: String,
//    val uri: String,
//    val href: String,
//    val availableMarkets: List<String>,
//    val externalUrls: ExternalUrls,
//    val linkedFrom: LinkedFrom?,
//    val restrictions: Restrictions?,
//    val artists: List<Artist>
//) : Parcelable
//
//@Parcelize
//@Serializable
//data class LinkedFrom(
//    val externalUrls: ExternalUrls,
//    val href: String,
//    val id: String,
//    val type: String,
//    val uri: String
//) : Parcelable