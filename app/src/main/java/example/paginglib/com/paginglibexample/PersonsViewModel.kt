package example.paginglib.com.paginglibexample

import android.arch.lifecycle.ViewModel
import android.arch.paging.PagedList
import android.arch.paging.RxPagedListBuilder
import example.paginglib.com.paginglibexample.room.Person
import example.paginglib.com.paginglibexample.room.PersonDao
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class PersonsViewModel(personDao: PersonDao) : ViewModel() {
    val persons: Flowable<PagedList<Person>> =
        RxPagedListBuilder(
            personDao.persons(),
            PagedList.Config.Builder()
                .setPageSize(20)
                .setPrefetchDistance(5)
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(40)
                .build()
        )
            .buildFlowable(BackpressureStrategy.LATEST)
}