package com.example.appercodetest.respository

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.appercodetest.model.User
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val PAGE_SIZE = 20

class UserPagingSource @Inject constructor(val repository: Repository): PagingSource<Int, User>() {
    var start = 0

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        Log.d("source", "params= ${params.loadSize}")
        return try {
            val response = repository.getUsers(start, PAGE_SIZE)
            val userList = response.body()
            val nextKey = if (userList.isNullOrEmpty()) {
                null
            } else {
                start + userList.last().id
            }
            Log.d("source", "list size= ${userList?.size}")
            Log.d("Source", "last= ${userList?.get(userList.size-1)}")
            Log.d("Source", "nextkey= $nextKey")
            LoadResult.Page(
                data = userList!!,
                prevKey = null,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }

    }
}