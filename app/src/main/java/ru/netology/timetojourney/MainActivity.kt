package ru.netology.timetojourney

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val splitTime = time.split(" ")
    val date = format.parse("${splitTime[0]} ${splitTime[1]}")
    return if (date != null) SimpleDateFormat(dateFormat, Locale.getDefault()).format(date) else ""
}
//@RequiresApi(Build.VERSION_CODES.O)
//fun parsingDate2(dateFormat: String, time: String): String {
//    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
//    val splitTime = time.split(" ")
//    val date = LocalDate.parse("${splitTime[0]} ${splitTime[1]}", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//    return if(date != null) date.format(DateTimeFormatter.ofPattern(dateFormat, Locale("ru"))).toString() else ""
//}
