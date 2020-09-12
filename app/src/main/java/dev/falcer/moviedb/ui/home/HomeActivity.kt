package dev.falcer.moviedb.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import dev.falcer.moviedb.R
import dev.falcer.moviedb.data.model.Movie
import dev.falcer.moviedb.databinding.ActivityHomeBinding
import dev.falcer.moviedb.utils.NetworkState
import kotlinx.android.synthetic.main.activity_home.*
import kotlin.math.abs

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    // 1
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeDataBinding: ActivityHomeBinding

    // 8
    private lateinit var upComingMovieAdapter: UpComingMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 2
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        // 3
        homeDataBinding.apply {
            lifecycleOwner = this@HomeActivity
            viewModel = this@HomeActivity.homeViewModel
        }
        // 4
        getUpComingMovie()
    }

    // 5
    private fun getUpComingMovie() {
        // 10
        vp_upcoming.overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        vp_upcoming.clipToPadding = false
        vp_upcoming.clipChildren = false
        vp_upcoming.offscreenPageLimit = 3

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(36))
        compositePageTransformer.addTransformer { view: View, fl: Float ->
            val r = 1 - abs(fl)
            view.scaleY = 0.95f + r * 0.05f
        }
        // 9
        homeViewModel.getUpComingMovies().observe(this, Observer {
            // 6
            when (it) {
                is NetworkState.Loading -> {
                    // 7
                    pb_upcoming.visibility = View.VISIBLE
                    vp_upcoming.visibility = View.GONE
                }
                is NetworkState.Success -> {
                    // 7
                    pb_upcoming.visibility = View.GONE
                    vp_upcoming.visibility = View.VISIBLE

                    upComingMovieAdapter = UpComingMovieAdapter()
                    @Suppress("UNCHECKED_CAST")
                    upComingMovieAdapter.setData(it.data as List<Movie>)
                    vp_upcoming.apply {
                        adapter = upComingMovieAdapter
                    }
                }
                is NetworkState.Error -> {
                    // 7
                    pb_upcoming.visibility = View.GONE
                    vp_upcoming.visibility = View.GONE
                    Toast.makeText(this, "Network Call Failed 🔥", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}