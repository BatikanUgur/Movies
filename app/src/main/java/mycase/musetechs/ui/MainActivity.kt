package mycase.musetechs.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atlasyazilim.nto.biletplaza.util.Constants
import kotlinx.android.synthetic.main.activity_main.*
import mycase.musetechs.R
import mycase.musetechs.common.BaseActivity
import mycase.musetechs.databinding.ActivityMainBinding
import mycase.musetechs.model.Movie
import mycase.musetechs.model.MovieResponse

class MainActivity : BaseActivity<ActivityMainBinding,MainViewModel>(), MovieAdapter.OnJourneyClickListener {

    lateinit var token:String
    var listMovie:ArrayList<Movie>? = null
    lateinit var movieResponse: MovieResponse

    val movieAdapter = MovieAdapter()

    var page:Int = 1
    var isLoading:Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movieAdapter.setOnJourneyClicklistener(this)
        dataBinding.recyclerViewMovie.layoutManager = LinearLayoutManager(this)
        dataBinding.recyclerViewMovie.adapter = movieAdapter

        viewModel.getToken()

        viewModel.tokenLiveData.observeForever {
            if (it != null){
                token = it.request_token
                viewModel.getMovies(token,page)

            }
            else{
                Toast.makeText(this,Constants.CONNECT_ERROR,Toast.LENGTH_SHORT)
            }
        }


        viewModel.moviesLiveData.observeForever {
            isLoading = true
            if (it!=null) {
                movieResponse = it
                if (listMovie == null) {
                    listMovie = it.results
                    movieAdapter.submitList(listMovie)

                }
                else{
                    listMovie!!.addAll(it.results)
                    movieAdapter.notifyDataSetChanged()

                }
                dataBinding.lottieLoading.visibility = View.GONE
            }
            else{
                Toast.makeText(this,Constants.CONNECT_ERROR,Toast.LENGTH_SHORT)
            }
        }

         dataBinding.editTextMovieSearch.addTextChangedListener(object : TextWatcher {

             override fun afterTextChanged(s: Editable?) {
             }

             override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
             }

             override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                 if (listMovie != null) {
                     val searchMovieList = listMovie!!.filter { movie -> movie.title.toUpperCase().contains(s.toString().toUpperCase()) }
                     movieAdapter.submitList(searchMovieList)
                 }
             }
         })


        dataBinding.recyclerViewMovie.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                //check for scroll down
                if (dy > 0) {
                    var layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    var visibleItemCount = layoutManager.getChildCount()
                    var totalItemCount = layoutManager.getItemCount()
                    var pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()


                    if (isLoading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount && movieResponse.total_pages>page) {
                            page++
                            isLoading = false
                            viewModel.getMovies(token,page)
                            dataBinding.lottieLoading.visibility = View.VISIBLE
                        }
                    }

                }
            }

        })
    }

    override fun getLayoutRes(): Int = R.layout.activity_main
    override fun getViewModel(): Class<MainViewModel> = MainViewModel::class.java

    override fun onTerminalClick(movie: Movie) {

    }
}
