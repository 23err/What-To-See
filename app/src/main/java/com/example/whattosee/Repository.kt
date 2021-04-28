package com.example.whattosee

object Repository {
    val listOfWeather: List<Weather>

    init {
        listOfWeather = ArrayList(listOf(Weather("Moscow", 23), Weather("Ryazan", 22)))
    }

    fun size(): Int {
        return listOfWeather.size
    }

    fun getWeather(ind : Int): Weather {
        return listOfWeather.get(ind).copy()
    }

    fun getWeatherChangeCopy(ind: Int, temperature: Int): Weather {
        return listOfWeather.get(ind).copy(temperature = temperature)
    }
}