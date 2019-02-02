//package com.example.sbotlevskyi.myapplication
//
//import android.arch.lifecycle.LiveData
//import android.app.Application
//
//
//class LinkRepository {
//    private var mWordDao: DeepLinkDao
//    private var mAllWords: LiveData<List<LinkModel>>
//
//    constructor(application: Application) {
//        val db = DeepLinkRoomDatabase.getInstance(application)
//        mWordDao = db.deepLinkDao()
//        mAllWords = mWordDao.getAllLinks()
//    }
//
//    fun getAllWords(): LiveData<List<LinkModel>> {
//        return mAllWords
//    }
//
//
//    fun insert(word: LinkModel) {
//        Thread(Runnable {
//            mWordDao.insertUrl(word)
//        }).start()
//    }
//}