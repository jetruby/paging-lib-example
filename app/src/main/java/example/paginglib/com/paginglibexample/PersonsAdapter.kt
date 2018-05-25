package example.paginglib.com.paginglibexample

import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import example.paginglib.com.paginglibexample.databinding.ItemPersonBinding
import example.paginglib.com.paginglibexample.room.Person

class PersonsAdapter : PagedListAdapter<Person, PersonsAdapter.PersonViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_person,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        val person = getItem(position)

        holder.binding.apply {
            tvName.text = holder.binding.root.context.getString(R.string.person_name, person!!.name)
            tvAge.text = holder.binding.root.context.getString(
                R.string.person_age,
                person.age.toString()
            )
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person?, newItem: Person?): Boolean =
                oldItem?.id == newItem?.id

            override fun areContentsTheSame(oldItem: Person?, newItem: Person?): Boolean =
                oldItem == newItem
        }
    }

    class PersonViewHolder(val binding: ItemPersonBinding) : RecyclerView.ViewHolder(binding.root)
}