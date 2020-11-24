package com.akhilsreekar.movieslist

import androidx.paging.PagingSource
import com.akhilsreekar.movieslist.entities.currentplaying.Movie
import com.akhilsreekar.movieslist.repo.MovieRepository

class MovieDataSource /*@Inject */ constructor(val movieRepository: MovieRepository): PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try{
            val currentLoadingPageKey = params.key?:1
            val response = movieRepository.getPopularMovies(DEFAULT_LANGUAGE,currentLoadingPageKey)
            val responseData = mutableListOf<Movie>()
            val data = response.data!!

            val totalPages = data.totalPages
            responseData.addAll(data.movies)

            val prevKey = if(currentLoadingPageKey >1 )  currentLoadingPageKey.minus(1) else null
            val nextKey = if(currentLoadingPageKey < totalPages-1) currentLoadingPageKey.plus(1) else null

            return LoadResult.Page(data = responseData,prevKey = prevKey,nextKey = nextKey)

        }catch (ex:Exception){
            return LoadResult.Error(ex)
        }
    }
}