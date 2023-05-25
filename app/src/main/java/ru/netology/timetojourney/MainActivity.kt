package ru.netology.timetojourney

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import ru.netology.timetojourney.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

fun String.toRuble(): String = "$this р."
fun parsingDate(dateFormat: String, time: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    val splitTime = time.split(" ")
    val date = format.parse("${splitTime[0]} ${splitTime[1]}")
    return if(date != null) SimpleDateFormat(dateFormat, Locale.US).format(date) else ""
}
