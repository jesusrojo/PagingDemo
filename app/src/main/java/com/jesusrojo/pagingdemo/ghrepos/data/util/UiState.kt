@file:Suppress("PackageDirectoryMismatch")
//package com.jesusrojo.reposclient.ghrepos.data.util
//
//data class UiState<out T>(val status: Status, val data: T?, val message: String?) {
//
//    enum class Status {
//        SUCCESS,
//        ERROR,
//        LOADING
//    }
//
//    companion object {
//        fun <T> success(data: T): UiState<T> {
//            return UiState(Status.SUCCESS, data, null)
//        }
//
//        fun <T> error(message: String, data: T? = null): UiState<T> {
//            return UiState(Status.ERROR, data, message)
//        }
//
//        fun <T> loading(data: T? = null): UiState<T> {
//            return UiState(Status.LOADING, data, null)
//        }
//    }
//}