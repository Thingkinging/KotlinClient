package com.kwh.dailyq.ui.base

import androidx.fragment.app.Fragment
import com.kwh.dailyq.api.ApiService

abstract class BaseFragment : Fragment(){
    val api: ApiService by lazy { ApiService.getInstance() }
}