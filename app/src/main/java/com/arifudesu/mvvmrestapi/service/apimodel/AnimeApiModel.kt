package com.arifudesu.mvvmrestapi.service.apimodel

data class AnimeTopApiModel<T>(
    var request_hash: String,
    var request_cached: Boolean,
    var request_cache_expiry: Int,
    var top: T? = null
)