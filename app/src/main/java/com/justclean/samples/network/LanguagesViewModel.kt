package com.justclean.samples.network

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

class LanguagesViewModel: ViewModel() {

    val languages: LiveData<Model<List<Language>>> = liveData {
        emit(ApiManagerRepository.getSuspendLanguages())
    }

}