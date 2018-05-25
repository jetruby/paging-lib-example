package example.paginglib.com.paginglibexample

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView.VERTICAL
import example.paginglib.com.paginglibexample.databinding.ActivityMainBinding
import io.reactivex.disposables.CompositeDisposable

class MainActivity : AppCompatActivity() {

    private lateinit var personsAdapter: PersonsAdapter
    private lateinit var viewModel: PersonsViewModel

    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        viewModel =
                ViewModelProviders.of(this, PersonsViewModelFactory(PagingApp.personDB.personDao()))
                    .get(PersonsViewModel::class.java)

        personsAdapter = PersonsAdapter()

        binding.rvPersons.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, VERTICAL))
            adapter = personsAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        disposable.add(viewModel.persons.subscribe({ flowableList ->
            personsAdapter.submitList(flowableList)
        }))
    }

    override fun onStop() {
        super.onStop()

        disposable.clear()
    }
}
