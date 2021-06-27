package com.example.movieappwithpaging.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieappwithpaging.R
import com.example.movieappwithpaging.adapters.UpcomingPagingAdapter
import com.example.movieappwithpaging.databinding.FragmentUpcomingBinding
import com.example.movieappwithpaging.models.upcomingModel.Result
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.mobiler.paging3g18.retrofit.ApiClient
import com.example.movieappwithpaging.viewModels.UpcomingViewModel
import com.example.movieappwithpaging.viewModels.ViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UpcomingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpcomingFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var binding: FragmentUpcomingBinding
    lateinit var upcomingPagingAdapter: UpcomingPagingAdapter
    lateinit var upComingViewModel: UpcomingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpcomingBinding.inflate(inflater, container, false)

        upComingViewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiClient.apiService)
        )[UpcomingViewModel::class.java]

        upcomingPagingAdapter = UpcomingPagingAdapter(object : UpcomingPagingAdapter.OnItemClickListener{
            override fun onItemClick(result: Result) {
                var bundle = Bundle()
                bundle.putSerializable("resultUpcoming", result)
                findNavController().navigate(R.id.aboutMovieFragment, bundle)
            }

        })
        //binding.rv.hasFixedSize()
        binding.rv.adapter = upcomingPagingAdapter
        lifecycleScope.launch {
            upComingViewModel.upComing.collectLatest {
                upcomingPagingAdapter.submitData(it)
            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment UpcomingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            UpcomingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}