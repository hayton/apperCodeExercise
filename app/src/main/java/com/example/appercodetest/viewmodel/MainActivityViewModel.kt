package com.example.appercodetest.viewmodel

import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.appercodetest.model.User
import com.example.appercodetest.respository.Repository
import com.example.appercodetest.respository.UserPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val userPagingSource: UserPagingSource, val repository: Repository): ViewModel() {
    val usersMutableLiveData = MutableLiveData<ArrayList<User>>()

//    val users = Pager(PagingConfig(1)) {
//        userPagingSource
//    }.liveData

    fun getUsersList(): LiveData<ArrayList<User>> {
        val usersLiveData = MutableLiveData<ArrayList<User>>()
        viewModelScope.launch {
            val response = repository.getUsers(0, 100)
            if (response.isSuccessful) {
                val data = response.body()
                usersLiveData.value = data!!
            }
        }
        return usersLiveData
    }


    fun getUsers(): Flow<PagingData<User>> {
        return Pager(PagingConfig(20)) {
            userPagingSource
        }.flow
    }
}