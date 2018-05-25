package example.paginglib.com.paginglibexample.room

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val age: Int
)