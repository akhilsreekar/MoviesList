package com.akhilsreekar.movieslist.viewmodels

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akhilsreekar.movieslist.DEFAULT_LANGUAGE
import com.akhilsreekar.movieslist.Event
import com.akhilsreekar.movieslist.Resource
import com.akhilsreekar.movieslist.entities.MovieDetail
import com.akhilsreekar.movieslist.entities.credits.CreditResponse
import com.akhilsreekar.movieslist.entities.currentplaying.MovieResponse
import com.akhilsreekar.movieslist.entities.review.ReviewResponse
import com.akhilsreekar.movieslist.entities.similarmovies.SimilarMovieResponse
import com.akhilsreekar.movieslist.entities.synopsis.SynopsisResponse
import com.akhilsreekar.movieslist.repo.MovieRepository
import kotlinx.coroutines.*
import java.lang.Exception

class MovieDetailViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository) :
    ViewModel() {

//    var moviesList: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
//
//    val x = async {
//        moviesList.postValue(movieRepository.getPopularMovies("en-US", 1))
//    }

    private val _movieId = MutableLiveData<Int>()
    val movieDetailLiveData: MutableLiveData<MovieDetail> = MutableLiveData()

    private val statusMessage = MutableLiveData<Event<String>>()
    val message: LiveData<Event<String>> get() = statusMessage

    fun fetchData() {
        try {
            viewModelScope.launch (Dispatchers.IO){
                coroutineScope {
                    try {
                        val synopsisCallResult =
                            async {
                                movieRepository.getMovieSynopsis(
                                    DEFAULT_LANGUAGE,
                                    _movieId.value!!
                                )
                            }.await()
                        val reviewCallResult =
                            async {
                                movieRepository.getMovieReviews(
                                    DEFAULT_LANGUAGE,
                                    _movieId.value!!
                                )
                            }.await()
                        val creditsCallResult =
                            async {
                                movieRepository.getMovieCredits(
                                    DEFAULT_LANGUAGE,
                                    _movieId.value!!
                                )
                            }.await()
                        val similarMovieCallResult =
                            async {
                                movieRepository.getSimilarMovies(
                                    DEFAULT_LANGUAGE,
                                    _movieId.value!!
                                )
                            }.await()



                        movieDetailLiveData.postValue(MovieDetail(_movieId.value!!,synopsisCallResult,reviewCallResult,creditsCallResult,similarMovieCallResult))

                    } catch (ex: Exception) {
                        movieDetailLiveData.postValue(null)
                        statusMessage.value = Event("Something went wrong!!! ${ex.message}")
                    }
                }
            }
        } catch (ex: Exception) {
            Log.d("", "")
        }
    }


    fun setMovieId(id: Int) {
        _movieId.value = id
    }

}