package com.bluxtech.netfilm.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bluxtech.netfilm.core.databinding.ItemMovieBinding
import com.bluxtech.netfilm.core.domain.model.Movie
import com.bumptech.glide.Glide

class MovieAdapter : ListAdapter<Movie, MovieAdapter.ListViewHolder>(DIFF_CALLBACK) {

    private var listData = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newListData: List<Movie>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Movie) {
            with(binding) {
                data.posterPath.let {
                    Glide.with(itemView)
                        .load("https://image.tmdb.org/t/p/original$it")
                        .centerCrop()
                        .into(ivMovie)
                }
                tvJudul.text = data.title
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(listData[bindingAdapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = listData.size

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldUser: Movie, newUser: Movie): Boolean {
                    return oldUser.title == newUser.title
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: Movie, newUser: Movie): Boolean {
                    return oldUser == newUser
                }
            }
    }
}