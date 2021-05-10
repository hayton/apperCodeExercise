package com.example.appercodetest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appercodetest.model.UserDetail
import com.example.appercodetest.respository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    fun getUserDetail(url: String): LiveData<UserDetail> {
        val userDetailLiveData = MutableLiveData<UserDetail>()
        viewModelScope.launch {
            val response = repository.getUserDetail(url)
            if (response.isSuccessful) {
                userDetailLiveData.value = response.body()
            }
        }
        return userDetailLiveData

    }
}