package com.example.movieappwithpaging.fragments

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieappwithpaging.adapters.ImagesAdapter
import com.example.movieappwithpaging.databinding.DialogBinding
import com.example.movieappwithpaging.databinding.FragmentAboutMovieBinding
import com.example.movieappwithpaging.models.imagemodel.Backdrop
import com.example.movieappwithpaging.models.popularmodel.Result
import com.example.movieappwithpaging.utils.loadImage
import kotlinx.coroutines.launch
import uz.mobiler.paging3g18.retrofit.ApiClient


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "resultPopular"
private const val ARG_PARAM2 = "resultTop"
private const val ARG_PARAM3 = "resultUpcoming"

/**
 * A simple [Fragment] subclass.
 * Use the [AboutMovieFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AboutMovieFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var popularData: Result? = null
    private var resultTop: com.example.movieappwithpaging.models.topratedmodel.Result? = null
    private var resultUpcoming: com.example.movieappwithpaging.models.upcomingModel.Result? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            if (it.get(ARG_PARAM1) != null) {
                popularData = it.getSerializable(ARG_PARAM1) as Result
            } else if (it.get(ARG_PARAM2) != null) {
                resultTop = it.getSerializable(ARG_PARAM2) as com.example.movieappwithpaging.models.topratedmodel.Result
            } else {
                resultUpcoming = it.getSerializable(ARG_PARAM3) as com.example.movieappwithpaging.models.upcomingModel.Result
            }
        }
    }

    lateinit var binding: FragmentAboutMovieBinding
    lateinit var imagesAdapter: ImagesAdapter
    var imageUrl = "https://image.tmdb.org/t/p/w500///"
    private val TAG = "AboutMovieFragment"

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAboutMovieBinding.inflate(inflater, container, false)

        if (popularData != null) {
            binding.movieImage.loadImage(imageUrl + popularData?.poster_path)
            binding.movieName.text = popularData?.original_title
            binding.overview.text = popularData?.overview
            binding.releaseDate.text = popularData?.release_date
            binding.voteAvarage.text = popularData?.vote_average.toString() + "/10"
            binding.voteCount.text = "(" + popularData?.vote_count.toString() + ")"

            var id = popularData?.id
            setImages(id!!)
        } else if (resultTop != null) {
            binding.movieImage.loadImage(imageUrl + resultTop?.poster_path)
            binding.movieName.text = resultTop?.original_title
            binding.overview.text = resultTop?.overview
            binding.releaseDate.text = resultTop?.release_date
            binding.voteAvarage.text = resultTop?.vote_average.toString() + "/10"
            binding.voteCount.text = "(" + resultTop?.vote_count.toString() + ")"

            var id = resultTop?.id
            setImages(id!!)
        } else if (resultUpcoming != null) {
            binding.movieImage.loadImage(imageUrl + resultUpcoming?.poster_path)
            binding.movieName.text = resultUpcoming?.original_title
            binding.overview.text = resultUpcoming?.overview
            binding.releaseDate.text = resultUpcoming?.release_date
            binding.voteAvarage.text = resultUpcoming?.vote_average.toString() + "/10"
            binding.voteCount.text = "(" + resultUpcoming?.vote_count.toString() + ")"

            var id = resultUpcoming?.id
            setImages(id!!)
        }

        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    fun setImages(id: Int) {
        lifecycleScope.launch {

            val imagesModel = ApiClient.apiService.getImages(id = id)
            val list = imagesModel.backdrops

            imagesAdapter = ImagesAdapter(list, object : ImagesAdapter.OnItemClickListener {
                override fun onItemClick(backdrop: Backdrop) {
                    var dialog = AlertDialog.Builder(requireContext())
                    var dialogBinding = DialogBinding.inflate(layoutInflater)
                    dialog.setView(dialogBinding.root)
                    dialogBinding.image.loadImage(imageUrl + backdrop.file_path)

                    dialog.show()
                }
            })
            binding.rv.adapter = imagesAdapter
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AboutMovieFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                AboutMovieFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}