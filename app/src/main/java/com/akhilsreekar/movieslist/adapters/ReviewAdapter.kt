package com.akhilsreekar.movieslist.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.akhilsreekar.movieslist.databinding.ReviewItemBinding
import com.akhilsreekar.movieslist.entities.review.Review

class ReviewAdapter : RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    private var reviewsList:List<Review> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val binding: ReviewItemBinding = ReviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviewsList[position])
    }

    override fun getItemCount(): Int = reviewsList.size

    fun setList(reviews:List<Review>){
        reviewsList = reviews
        notifyDataSetChanged()
    }

    class ReviewViewHolder(private val itemBinding: ReviewItemBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(review:Review){
            itemBinding.review.text = review.content
            itemBinding.author.text = "- "+review.author
        }
    }
}