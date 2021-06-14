package com.decagon.android.sq007.Daos

import com.decagon.android.sq007.interfaces.data.RetrofitDao
import com.decagon.android.sq007.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitDao{

    // initialize this once and only on the first referencing
    private val retrofit : Retrofit.Builder by lazy {
        Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
    }


    // initialize this once and only on the first referencing
    val RETROFIT_RETROFIT_DAO : RetrofitDao by lazy {
        retrofit.build().create(RetrofitDao::class.java)
    }






}