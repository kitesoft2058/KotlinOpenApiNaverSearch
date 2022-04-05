package com.kitesoft.kotlinopenapinaversearch

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.kitesoft.kotlinopenapinaversearch.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class MainActivity : AppCompatActivity() {
    val binding:ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
        binding.btnSearch.setOnClickListener { searchData() }
    }

    fun searchData(){

        val imm= getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)


        //2개 이상의 converter를 추가할때는 Scalars 부터.
        val retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService= retrofit.create(NaverApiRetrofitService::class.java)

        retrofitService.searchData(binding.et.text.toString()).enqueue(object : Callback<NaverShoppingApiResponse>{
            override fun onResponse(
                call: Call<NaverShoppingApiResponse>,
                response: Response<NaverShoppingApiResponse>
            ) {
                val apiResponse:NaverShoppingApiResponse?= response.body()
                apiResponse?.items?.let { binding.recycler.adapter= MyAdapter(this@MainActivity, it) }

            }

            override fun onFailure(call: Call<NaverShoppingApiResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"server error : " + t.message, Toast.LENGTH_SHORT).show()
            }

        })

//        retrofitService.searchDataByString(binding.et.text.toString()).enqueue(object : Callback<String>{
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                val s:String?= response.body()
//                AlertDialog.Builder(this@MainActivity).setMessage(s).show()
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Toast.makeText(this@MainActivity,"server error : " + t.message, Toast.LENGTH_SHORT).show()
//            }
//        })

    }
}