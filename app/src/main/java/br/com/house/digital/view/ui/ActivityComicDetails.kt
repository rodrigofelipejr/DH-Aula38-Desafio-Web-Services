package br.com.house.digital.view.ui

import android.animation.ObjectAnimator
import android.animation.StateListAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.house.digital.R
import br.com.house.digital.databinding.ActivityComicDetailsBinding
import br.com.house.digital.model.Comic
import br.com.house.digital.model.Date
import br.com.house.digital.service.repository
import br.com.house.digital.viewmodel.ComicDetailsViewModel
import br.com.house.digital.viewmodel.ComicsViewModel
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*


class ActivityComicDetails : AppCompatActivity() {
    private lateinit var binding: ActivityComicDetailsBinding
    private lateinit var comic: Comic
    private var idComic: Int = 0

    private val viewModel by viewModels<ComicDetailsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ComicDetailsViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras

        try {
            idComic = extras?.get("COMIC_ID") as Int
        } catch (exception: Exception) {
            Log.e("ActivityComicDetails", exception.toString())
        }

        viewModel.comic.observe(this) {
            comic = it

            binding.textViewComicTitle.text = comic.title
            binding.textViewComicDescription.text =
                if (comic.description != null) comic.description else "No description"

            binding.textViewComicPages.text = comic.pageCount.toString()

            val date = (comic.dates.find { item -> item.type == "focDate" })?.date.toString()
            if (date.isNotEmpty()) {
                val dateTmp: java.util.Date? =
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(date)
                val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
                binding.textViewComicPublished.text = dateFormat.format(dateTmp)
            }

            val price = (comic.prices.find { item -> item.type == "printPrice" })?.price.toString()
            binding.textViewComicPrice.text = if (price != null) "\$ $price" else "No price"

            val coverURL =
                if (comic.images.size > 0) comic.images[0].getUrl() else comic.thumbnail.getUrl();

            Glide.with(this)
                .load(coverURL)
                .centerCrop()
                .into(binding.imageViewComicCover)

            Glide.with(this)
                .load(comic.thumbnail.getUrl())
                .centerCrop()
                .into(binding.imageViewThumbnailComic)
        }
        viewModel.getComicId(idComic)

        initToolbar()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.includeConfigToolbar.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTitle("")
        binding.includeConfigToolbar.imageViewLogo.visibility = View.GONE;
        binding.includeConfigToolbar.materialAppBarLayout.setBackgroundColor(
            ContextCompat.getColor(
                applicationContext,
                R.color.transparent
            )
        )

        binding.includeConfigToolbar.materialToolbar.setNavigationOnClickListener {
            val intent = Intent(applicationContext, ActivityComics::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }

        binding.includeConfigToolbar.materialAppBarLayout.targetElevation = 0f
    }
}