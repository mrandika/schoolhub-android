package space.mrandika.schoolhub.network

import space.mrandika.schoolhub.BuildConfig

object Api {
    private const val API_URL = BuildConfig.API_URL

    fun login(): String {
        return API_URL+"login"
    }

    fun checkToken(): String {
        return API_URL+"user"
    }

    fun presence(): String {
        return API_URL+"presence"
    }

    fun today(): String {
        return  API_URL+"today"
    }
}