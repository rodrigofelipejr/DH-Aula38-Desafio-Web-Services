package br.com.house.digital.view.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.house.digital.R
import br.com.house.digital.databinding.FragmentCoverBinding
import com.bumptech.glide.Glide


class FragmentCover : Fragment() {
    private lateinit var binding: FragmentCoverBinding
    private var urlImage = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)
        urlImage = arguments?.get("URL_IMAGE") as String
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentCoverBinding.inflate(inflater, container, false)

        return binding.getRoot()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        Glide.with(this)
            .load(urlImage)
            .centerCrop()
            .into(binding.imageViewThumbnailComic)

        setListeners()
    }

    companion object {
        @JvmStatic
        fun newInstance(urlImage: String) = FragmentCover().apply {
            arguments = Bundle().apply {
                putString("URL_IMAGE", urlImage)
            }
        }
    }

    private fun setListeners() {
        binding.buttonClose.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}