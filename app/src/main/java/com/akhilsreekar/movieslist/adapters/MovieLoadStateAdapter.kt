package com.akhilsreekar.movieslist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.akhilsreekar.movieslist.databinding.LoadingItemBinding

class MovieLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<MovieLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(retry,loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val binding = LoadingItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LoadStateViewHolder(binding)
    }

    class LoadStateViewHolder(private val itemBinding: LoadingItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(retry: () -> Unit,loadState: LoadState){
            itemBinding.loadStateErrorMessage.isVisible = loadState !is LoadState.Loading
            itemBinding.loadStateRetry.isVisible = loadState !is LoadState.Loading
            itemBinding.loadStateProgress.isVisible = loadState is LoadState.Loading

            if(loadState is LoadState.Error){
                itemBinding.loadStateErrorMessage.text = loadState.error.localizedMessage
            }

            itemBinding.loadStateRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }

}