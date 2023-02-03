package com.pexsistols.moviereviewapp.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pexsistols.moviereviewapp.R
import com.pexsistols.moviereviewapp.adapter.RecyclerAdapter
import com.pexsistols.moviereviewapp.model.Movie
import com.pexsistols.moviereviewapp.viewmodel.MovieFeedViewModel

class MovieFeedFragment : Fragment() {

    private lateinit var searchBar: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: RecyclerAdapter
    private lateinit var movieList: ArrayList<Movie>
    private lateinit var movieFeedViewModel: MovieFeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieList = ArrayList()

        movieFeedViewModel = ViewModelProviders.of(this).get(MovieFeedViewModel::class.java).apply {
            fetchMovies()
        }

        searchBar = view.findViewById(R.id.search_feed)
        recyclerView = view.findViewById(R.id.recyclerview_feed)
        initRecyclerview()

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                when (p0.toString().isNotEmpty()) {
                    true -> filter(p0.toString())
                    else -> observeMovies()
                }
            }

        })
        observeMovies()
    }


    private fun initRecyclerview() {
        recyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        movieAdapter = RecyclerAdapter(movieList)
        recyclerView.adapter = movieAdapter
        recyclerView.setHasFixedSize(true)
    }

    private fun observeMovies() {
        movieFeedViewModel.getMovies().observe(viewLifecycleOwner) { movies ->
            movies?.let {
                recyclerView.visibility = View.VISIBLE
                movieAdapter.updateMovieList(movies)
            }
        }
    }


    private fun filter(text: String) {
        val filteredList = ArrayList<Movie>()

        movieFeedViewModel.getMovies().observe(viewLifecycleOwner) { movies ->
            movies?.let {
                for (movie: Movie in movies) {
                    if (movie.title?.lowercase()!!
                            .contains(text.lowercase()) || movie.originalTitle!!.lowercase()
                            .contains(text)
                    ) {
                        filteredList.add(movie)
                    }
                }
                movieAdapter.updateMovieList(filteredList)
            }
        }
    }
}