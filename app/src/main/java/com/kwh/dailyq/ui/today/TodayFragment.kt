package com.kwh.dailyq.ui.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.kwh.dailyq.api.ApiService
import com.kwh.dailyq.api.response.HelloWorld
import com.kwh.dailyq.databinding.FragmentTodayBinding
import com.kwh.dailyq.ui.base.BaseFragment
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class TodayFragment : BaseFragment() {

    var _binding: FragmentTodayBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            val qidDateFormat = SimpleDateFormat("yyyy-MM-dd")
            val question = api.getQuestion(LocalDate.now())


            val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREA)
            binding.date.text = dateFormat.format(qidDateFormat.parse(question.id))
            binding.question.text = question.text
        }

        Thread {
            val url = URL("http://192.168.0.12:5000/v1/hello-world")

            val conn = url.openConnection() as HttpURLConnection
            conn.connectTimeout = 5000
            conn.readTimeout = 5000
            conn.requestMethod = "GET"
            conn.setRequestProperty("Accept", "application/json")

            conn.connect()

            val reader = BufferedReader(InputStreamReader(conn.inputStream))
            val body = reader.readText()
            reader.close()
            conn.disconnect()

            val gson = Gson()
            val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.KOREA)
            val helloWorld = gson.fromJson(body, HelloWorld::class.java)

            val json = JSONObject(body)
            val date = json.getString("date")
            val message = json.getString("message")

            activity?.runOnUiThread {
                binding.date.text = dateFormat.format(helloWorld.date)
                binding.question.text = helloWorld.message
            }
        }.start()
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}