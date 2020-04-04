package mycase.musetechs.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import mycase.musetechs.databinding.ItemMovieBinding
import mycase.musetechs.model.Movie
import java.text.SimpleDateFormat

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ViewHolder>(DIFF_CALLBACK) {
    lateinit var onJourneyClickListener: OnJourneyClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder.create(LayoutInflater.from(parent.context), parent, onJourneyClickListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun setOnJourneyClicklistener(onJourneyClickListener: OnJourneyClickListener) {
        this.onJourneyClickListener = onJourneyClickListener
    }


    class ViewHolder(
        val binding: ItemMovieBinding,
        onJourneyClickListener: OnJourneyClickListener
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onJourneyClickListener.onTerminalClick(binding.movie!!)
            }
        }

        companion object {
            fun create(
                inflater: LayoutInflater,
                parent: ViewGroup,
                onJourneyClickListener: OnJourneyClickListener
            ): ViewHolder {
                val itemJourneyListBinding = ItemMovieBinding.inflate(inflater, parent, false)
                return ViewHolder(itemJourneyListBinding, onJourneyClickListener)
            }
        }

        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }


    companion object {
        val mSimpleDateFormat = SimpleDateFormat("dd.MM.yyyy")

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Movie,
                newItem: Movie
            ): Boolean = oldItem.id == newItem.id
        }
    }

    interface OnJourneyClickListener {
        fun onTerminalClick(movie: Movie)
    }
}