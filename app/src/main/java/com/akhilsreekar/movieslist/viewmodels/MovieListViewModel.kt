package com.akhilsreekar.movieslist.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.akhilsreekar.movieslist.network.MovieDataSource
import com.akhilsreekar.movieslist.repo.MovieRepository

class MovieListViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {


//    var moviesList :MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
//
//        val x = CoroutineScope(Dispatchers.IO).launch {
//            moviesList.postValue(movieRepository.getPopularMovies("en-US",1))
//        }

        val moviesList = Pager(PagingConfig(pageSize = 20,enablePlaceholders = true)){
            MovieDataSource(movieRepository)
        }.flow.cachedIn(viewModelScope)
}