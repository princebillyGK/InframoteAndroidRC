package com.example.inframoteandroidrc.ui.input

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.inframoteandroidrc.database.RemoteButton
import com.example.inframoteandroidrc.database.RemoteButtonDao

class ButtonInputViewModel(
    val database: RemoteButtonDao,
    application: Application
) : AndroidViewModel(application) {
    fun saveButton(button: RemoteButton) {
        database.insert(button)
    }
}


