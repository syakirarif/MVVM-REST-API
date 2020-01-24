package com.arifudesu.mvvmrestapi.service

data class AnimeApiModel<T>(
    var request_hash: String,
    var request_cached: Boolean,
    var request_cache_expiry: Int,
    var season_name: String,
    var season_year: Int,
    var anime: T? = null
)