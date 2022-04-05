package com.kitesoft.kotlinopenapinaversearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NaverApiRetrofitService {
    //@Headers() 정적 헤더값 지정이 2개 이상일때는 배열로 지정... 자바와 다르게 kotlin에서는 ...value 파라미터라서 그냥. , 로 구분하여 여러개를 배치하면 됨.
    @Headers("X-Naver-Client-Id: 6fdGL2hbME5dq4YMpfKW","X-Naver-Client-Secret: oGtzoz1soE")
    @GET("/v1/search/shop.json")
    fun searchDataByString(@Query("query") query: String) : Call<String>

    //@Headers() 정적 헤더값 지정이 2개 이상일때는 배열로 지정... 자바와 다르게 kotlin에서는 ...value 파라미터라서 그냥. , 로 구분하여 여러개를 배치하면 됨.
    @Headers("X-Naver-Client-Id: 6fdGL2hbME5dq4YMpfKW","X-Naver-Client-Secret: oGtzoz1soE")
    @GET("/v1/search/shop.json?display=100")
    fun searchData(@Query("query") query: String) : Call<NaverShoppingApiResponse>
}