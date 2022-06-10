package com.example.retrofitcoroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.retrofitcoroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyAPI::class.java)
//        normal Retrofit code
//        api.getComments().enqueue(object : Callback<List<Comments>>{
//            override fun onResponse(
//                call: Call<List<Comments>>,
//                response: Response<List<Comments>>
//            ) {
//                if (response.isSuccessful){
//                    response.body()?.let {
//                        for (comment in it){
//                            Log.d(TAG, comment.toString())
//                        }
//                    }
//                }
//                }
//
//            override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
//                Log.d(TAG, "onFailure: Error  $t")            }
//
//        })
//    }
//}


//             ***** new code with coroutine   **
        GlobalScope.launch (Dispatchers.IO){
//             without suspend function in API
//            val response = api.getComments().awaitResponse()
            val response = api.getComments()
//            with suspend function in API returning response
            if (response.isSuccessful){
                for (comment in response.body()!!){
                    Log.d(TAG, comment.toString())
                }
            }
        }



    }
}