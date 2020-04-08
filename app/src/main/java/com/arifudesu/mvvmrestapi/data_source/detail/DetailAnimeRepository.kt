package com.arifudesu.mvvmrestapi.data_source.detail

import com.arifudesu.mvvmrestapi.model.detail.DetailAnimeEntry

class DetailAnimeRepository(
    val remoteDataSource: DetailAnimeDS,
    val localDataSource: DetailAnimeDS
) : DetailAnimeDS {
    override fun getDetailAnime(
        malId: String,
        callback: DetailAnimeDS.GetCallback
    ) {
//        localDataSource.getDetailAnime(malId, callback)
        getRemoteDataSource(malId, callback)
    }

    override fun saveDetailAnime(entry: DetailAnimeEntry) {
        localDataSource.saveDetailAnime(entry)
    }

    private fun getRemoteDataSource(
        malId: String,
        callback: DetailAnimeDS.GetCallback
    ) {
        remoteDataSource.getDetailAnime(
            malId,
            object : DetailAnimeDS.GetCallback {
                override fun onLoaded(entry: DetailAnimeEntry) {
                    saveDetailAnime(entry)
                    localDataSource.getDetailAnime(malId, callback)
                }

                override fun onError(errorMessage: String?) {
                    callback.onError(errorMessage)
                }

                override fun onFinished() {
                    callback.onFinished()
                }

            }
        )
    }

    companion object {

        private var INSTANCE: DetailAnimeRepository? = null

        @JvmStatic
        fun getInstance(remoteDataSource: DetailAnimeDS, localDataSource: DetailAnimeDS) =
            INSTANCE ?: synchronized(DetailAnimeRepository::class.java) {
                INSTANCE ?: DetailAnimeRepository(remoteDataSource, localDataSource)
                    .also { INSTANCE = it }
            }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}