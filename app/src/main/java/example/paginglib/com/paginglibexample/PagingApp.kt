package example.paginglib.com.paginglibexample

import android.app.Application
import android.arch.persistence.room.Room
import example.paginglib.com.paginglibexample.room.Person
import example.paginglib.com.paginglibexample.room.PersonDB
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class PagingApp : Application() {

    companion object {
        lateinit var personDB: PersonDB
    }

    override fun onCreate() {
        super.onCreate()

        personDB = Room.databaseBuilder(this, PersonDB::class.java, "PersonDB").build()

        Observable.just(true)
            .subscribeOn(Schedulers.io())
            .subscribe {
                val persons = arrayOf<Person>()
                for (i in 1..100) {
                    persons.plus(Person(i, "Person $i", age = i))
                }
                personDB.personDao().insert(*persons)
            }
    }
}