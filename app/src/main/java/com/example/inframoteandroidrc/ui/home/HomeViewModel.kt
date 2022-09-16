package com.example.inframoteandroidrc.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.inframoteandroidrc.database.RemoteButton
import com.example.inframoteandroidrc.database.RemoteButtonDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    val database: RemoteButtonDao,
    application: Application
) : AndroidViewModel(application) {

    val buttons = MutableLiveData(listOf<RemoteButton>())

    init {
        CoroutineScope(Dispatchers.IO).launch {
            buttons.postValue(database.getAll())
        }
    }

    fun updateButtons() {
        CoroutineScope(Dispatchers.IO).launch {
            buttons.postValue(database.getAll())
        }
    }

//    fun getAllPosts(): LiveData<List<RemoteButton>>? {
//        return database.getAll()
//    }

    fun delete(button: RemoteButton) {
        CoroutineScope(Dispatchers.IO).launch {
            database.delete(button)
            buttons.postValue(database.getAll())
        }
    }
}



