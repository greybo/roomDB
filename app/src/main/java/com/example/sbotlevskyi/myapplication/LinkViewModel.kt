//package com.example.sbotlevskyi.myapplication
//
//import android.app.Application
//import android.arch.lifecycle.AndroidViewModel
//import android.arch.lifecycle.LiveData
//
//
//class LinkViewModel : AndroidViewModel {
//    private var mRepository: LinkRepository
//    private var mAllWords: LiveData<List<LinkModel>>
//
//    constructor(application: Application) : super(application) {
//        mRepository = LinkRepository(application)
//        mAllWords = mRepository.getAllWords()
//    }
//
//    fun getAllWords(): LiveData<List<LinkModel>> {
//        return mAllWords
//    }
//
//    fun insert(word: LinkModel) {
//        mRepository.insert(word)
//    }
//}