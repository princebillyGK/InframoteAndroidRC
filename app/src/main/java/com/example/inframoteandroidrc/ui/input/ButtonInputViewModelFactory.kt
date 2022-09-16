package com.example.inframoteandroidrc.ui.input

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.inframoteandroidrc.database.RemoteButtonDao

class ButtonInputViewModelFactory(
    private val dataSource: RemoteButtonDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ButtonInputViewModel::class.java)) {
            return ButtonInputViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
