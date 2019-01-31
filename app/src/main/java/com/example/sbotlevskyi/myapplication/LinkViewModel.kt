package com.example.sbotlevskyi.myapplication

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

class LinkViewModel : AndroidViewModel {
    lateinit var repository :LinkRepository

    constructor(application: Application) : super(application) {
        mAllWords = mRepository.getAllWords();
        repository = LinkRepository(application)

    }
}