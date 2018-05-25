package example.paginglib.com.paginglibexample.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Person::class), version = 1)
abstract class PersonDB : RoomDatabase() {
    abstract fun personDao(): PersonDao
}