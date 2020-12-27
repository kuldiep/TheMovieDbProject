package com.android_poc.themoviedbproject.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.android_poc.themoviedbproject.BR
import com.android_poc.themoviedbproject.R
import com.android_poc.themoviedbproject.databinding.MovieItemBinding
import com.android_poc.themoviedbproject.pojo.CustomTmdbData
import com.android_poc.themoviedbproject.utils.AppConstants
import com.bumptech.glide.Glide

class MovieListRecyclerAdapter(var customTmdbDataList: List<CustomTmdbData>,val context:Context):
        RecyclerView.Adapter<MovieListRecyclerAdapter.MovieDataViewHolder>() {

    class MovieDataViewHolder(val movieItemBinding: MovieItemBinding,view: View,val context: Context): RecyclerView.ViewHolder(view){

        fun bindDataToView(customTmdbData: CustomTmdbData){
            movieItemBinding.setVariable(BR.CustomMovieObj,customTmdbData)
            movieItemBinding.executePendingBindings()
            Glide.with(context).load(customTmdbData.posterImgUrl).placeholder(R.drawable.ic_launcher_foreground)
                    .error(R.drawable.ic_launcher_foreground)
                    .centerCrop().into(movieItemBinding.ivMovieBg)

            movieItemBinding.root.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    val bundle = Bundle()
                    bundle.putSerializable(AppConstants.CUSTOM_MOVIE_OBJ,customTmdbData)
                    p0?.findNavController()?.navigate(R.id.navigateNewsDetailFragment,bundle)
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDataViewHolder {
        val movieItemBinding = DataBindingUtil.inflate<MovieItemBinding>(LayoutInflater.from(parent.getContext()),
                R.layout.movie_item, parent, false)
        return MovieDataViewHolder(movieItemBinding,movieItemBinding.root,context)
    }

    override fun onBindViewHolder(holder: MovieDataViewHolder, position: Int) {
        holder.bindDataToView(customTmdbDataList.get(position))
    }

    override fun getItemCount(): Int {
        return customTmdbDataList.size
    }

    fun setMovieListFromDb( customTmdbDataList: List<CustomTmdbData>){
        this.customTmdbDataList = customTmdbDataList
        notifyDataSetChanged()
    }
}