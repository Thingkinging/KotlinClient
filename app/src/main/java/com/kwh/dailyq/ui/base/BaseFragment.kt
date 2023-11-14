package com.kwh.dailyq.ui.base

import androidx.fragment.app.Fragment
import com.kwh.dailyq.api.ApiService
import com.kwh.dailyq.db.AppDatabase

abstract class BaseFragment : Fragment(){
    val api: ApiService by lazy { ApiService.getInstance() }
    val db: AppDatabase by lazy { AppDatabase.getInstance() }
}