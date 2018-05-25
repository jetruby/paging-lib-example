package example.paginglib.com.paginglibexample

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import example.paginglib.com.paginglibexample.room.PersonDao

class PersonsViewModelFactory(private val personDao: PersonDao) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PersonsViewModel(personDao) as T
    }
}