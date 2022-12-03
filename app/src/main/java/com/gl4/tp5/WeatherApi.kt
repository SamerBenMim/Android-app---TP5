package layout

import com.gl4.tp5.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET

interface WeatherApi {
    @GET("weather?q=Tunis&APPID=17db59488cadcad345211c36304a9266")
    fun getWeather() : Call<WeatherResponse>
}