package com.linx.playAndroid.widget.roomUtil

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

//1、定义entity
@Entity(tableName = "search_history_sp")
data class SearchHistoryData(
    //搜索的文本内容
    @ColumnInfo
    val text: String
) {
    //自增
    @PrimaryKey(autoGenerate = true)
    //主键
    var id: Int? = null
}

//2、定义Dao层
@Dao
interface SearchHistoryDao {

    //增
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchHistory(searchHistoryData: SearchHistoryData)

    //删除某一项
    @Delete
    fun deleteSearchHistory(searchHistoryData: SearchHistoryData)

    //删除全部
    @Query("delete from search_history_sp")
    fun deleteAll()

    //删除某一项
    @Query("delete from search_history_sp where id = :id")
    fun delete(id: Int)

    //查，这里可能为空
    @Query("select * from search_history_sp where id = :id")
    fun queryLiveDataSearchHistory(id: Int): LiveData<List<SearchHistoryData>>

    //查找全部
    @Query("select * from search_history_sp")
    fun queryLiveDataAll(): LiveData<List<SearchHistoryData>>

    //查
    @Query("select * from search_history_sp where id = :id")
    fun querySearchHistory(id: Int): List<SearchHistoryData>

    //查，text是否存在，如果存在返回1，不存在返回0  limit 1只返回一条
    @Query("select count(*) from search_history_sp where text = :text limit 1")
    fun querySearchHistory(text: String): Int

    //查找全部
    @Query("select * from search_history_sp")
    fun queryAll(): List<SearchHistoryData>?

}

//3、定义database数据库
@Database(entities = [SearchHistoryData::class], version = 1, exportSchema = false)
abstract class SearchHistoryDB : RoomDatabase() {

    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {

        private const val DB_NAME = "search_history_db"

        //保证不同线程对这个共享变量进行操作的可见性，并且禁止进行指令重排序.
        @Volatile
        private var instance: SearchHistoryDB? = null

        //保证线程安全，锁住
        @Synchronized
        fun getInstance(context: Context): SearchHistoryDB =
            instance ?: Room.databaseBuilder(context, SearchHistoryDB::class.java, DB_NAME).build()
                .also { instance = it }

    }

}