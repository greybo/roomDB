package com.example.sbotlevskyi.myapplication

import android.os.AsyncTask.execute
import android.arch.lifecycle.LiveData
import android.app.Application


class LinkRepository {
    private var mWordDao: DeepLinkDao
    private var mAllWords: LiveData<List<DeepLinkModel>>

    constructor(application: Application) {
        val db = DeepLinkRoomDatabase.getInstance(application)
        mWordDao = db.deepLinkDao()
        mAllWords = mWordDao.getAllLinks()
    }

    fun getAllWords(): LiveData<List<DeepLinkModel>> {
        return mAllWords
    }


    fun insert(word: DeepLinkModel) {
        Thread(Runnable {
            mWordDao.insertUrl(word)
        }).start()
    }
}