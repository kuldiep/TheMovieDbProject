package com.android_poc.themoviedbproject.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.android_poc.themoviedbproject.R
import com.android_poc.themoviedbproject.databinding.FragmentTopMoviesBinding
import com.android_poc.themoviedbproject.utils.AppConstants.Companion.TAG
import com.android_poc.themoviedbproject.viewmodel.TmdbViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TopMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TopMoviesFragment : Fragment(),View.OnClickListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var binding:FragmentTopMoviesBinding?=null
    private lateinit var tmdbViewModel: TmdbViewModel
    private lateinit var movieListRecyclerAdapter:MovieListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: called")
        val view =  inflater.inflate(R.layout.fragment_top_movies, container, false)
        binding = DataBindingUtil.bind(view)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated: called")
       
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated: called")
        tmdbViewModel = ViewModelProvider(requireActivity()).get(TmdbViewModel::class.java)
        binding?.btnRetry?.setOnClickListener(this)
        binding?.rvMovieList?.layoutManager = LinearLayoutManager(activity)
        binding?.rvMovieList?.itemAnimator = DefaultItemAnimator()
        movieListRecyclerAdapter = MovieListRecyclerAdapter(arrayListOf(),requireContext())
        binding?.rvMovieList?.adapter = movieListRecyclerAdapter
        setDataToList()
        tmdbViewModel.getMutableApiCallListenerFlag().observe(viewLifecycleOwner,{
            if(!it){
                Toast.makeText(activity,"Something went wrong try again", Toast.LENGTH_SHORT).show()
                binding?.btnRetry?.visibility = View.VISIBLE
            }
            binding?.contentLoader?.hide()
        })
        tmdbViewModel.getAllTopMoviesFromRepo().observe(viewLifecycleOwner, {
            if (it.isNotEmpty()) {
                Log.d(TAG, "setDataToList: setting data to adapter and refreshing again")
                movieListRecyclerAdapter.setMovieListFromDb(it)
                binding?.btnRetry?.visibility = View.GONE
            }
        })

        tmdbViewModel.observeMenuItemSelectionInFragment().observe(viewLifecycleOwner,{
            viewSortedMovies(it)
        })
    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TopMoviesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TopMoviesFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    fun setDataToList(){
        binding?.contentLoader?.show()
        tmdbViewModel.makeNetworkCall()


    }

    fun viewSortedMovies(sortType : String){
        tmdbViewModel.getSortedMoviesBySeletedItemFromDb(sortType)?.observe(viewLifecycleOwner,{
            movieListRecyclerAdapter.setMovieListFromDb(it)
        })
    }

    override fun onClick(p0: View?) {
        setDataToList()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: called")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView: called")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach: called")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }
}