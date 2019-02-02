//package com.example.sbotlevskyi.myapplication
//
//import android.arch.persistence.room.Database
//import android.arch.persistence.room.Room
//import android.arch.persistence.room.RoomDatabase
//import android.content.Context
//import android.arch.persistence.db.SupportSQLiteDatabase
//
//
//
//@Database(entities = [LinkModel::class], version = 1)
//abstract class DeepLinkRoomDatabase : RoomDatabase() {
//    abstract fun deepLinkDao(): DeepLinkDao
//
//    companion object {
//
//        // For Singleton instantiation
//        @Volatile
//        private var instance: DeepLinkRoomDatabase? = null
//
//        fun getInstance(context: Context): DeepLinkRoomDatabase {
//            return instance ?: synchronized(this) {
//                instance ?: Room.databaseBuilder(context.applicationContext,
//                        DeepLinkRoomDatabase::class.java, "DeepLinkDatabase.db")
////                        .addCallback(sRoomDatabaseCallback)
//                        .build().also { instance = it }
//            }
//        }
//    }
//
//    private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
////            PopulateDbAsync(instance).execute()
//        }
//    }
//}