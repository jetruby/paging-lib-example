package example.paginglib.com.paginglibexample.room

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun persons(): DataSource.Factory<Int, Person>

    @Insert
    fun insert(vararg person: Person)
}