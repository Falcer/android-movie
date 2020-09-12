package dev.falcer.moviedb.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.falcer.moviedb.R
import dev.falcer.moviedb.databinding.ActivityHomeBinding
import dev.falcer.moviedb.utils.NetworkState
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeDataBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)

        homeDataBinding.apply {
            lifecycleOwner = this@HomeActivity
            viewModel = this@HomeActivity.homeViewModel
        }
        getUpComingMovie()
    }

    private fun getUpComingMovie() {
        homeViewModel.getUpComingMovies().observe(this, Observer {
            when(it) {
                is NetworkState.Loading -> {
                    pb_upcoming.visibility = View.VISIBLE
                    vp_upcoming.visibility = View.GONE
                }
                is NetworkState.Success -> {
                    pb_upcoming.visibility = View.GONE
                    vp_upcoming.visibility = View.VISIBLE
                }
                is NetworkState.Error -> {
                    pb_upcoming.visibility = View.GONE
                    vp_upcoming.visibility = View.GONE
                    Toast.makeText(this, "Network Call Failed 🔥", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}