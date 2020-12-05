package br.com.house.digital.view.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import br.com.house.digital.R
import br.com.house.digital.databinding.ActivityComicDetailsBinding
import br.com.house.digital.model.Comic
import br.com.house.digital.service.repository
import br.com.house.digital.viewmodel.ComicDetailsViewModel
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.Locale


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

        viewModel.getComicId(idComic)

        initToolbar()
        pupulateFields()
        setListeners()
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

        binding.includeConfigToolbar.materialAppBarLayout.targetElevation = 0f
    }

    private fun pupulateFields() {
        viewModel.comic.observe(this) {
            comic = it

            binding.textViewComicTitle.text =
                if (comic.title != null) comic.title else "No description"

            binding.textViewComicDescription.text =
                if (comic.description != null) comic.description else "No description"

            binding.textViewComicPages.text =
                if (comic.pageCount != null) comic.pageCount.toString() else "No description"

            val date = (comic.dates.find { item -> item.type == "focDate" })?.date.toString()
            if (date.isNotEmpty()) {
                val dateTmp: java.util.Date? =
                    SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(date)
                val dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
                binding.textViewComicPublished.text = dateFormat.format(dateTmp)
            } else {
                binding.textViewComicPublished.text = "No date"
            }

            val price = (comic.prices.find { item -> item.type == "printPrice" })?.price.toString()
            binding.textViewComicPrice.text = if (price != null) "\$ $price" else "No price"

            val coverURL =
                if (comic.images.size > 0) comic.images[0].getUrl() else comic.thumbnail.getUrl();

            val circularProgressDrawable = CircularProgressDrawable(this)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(this)
                .load(coverURL)
                .placeholder(circularProgressDrawable)
                .centerCrop()
                .into(binding.imageViewComicCover)

            Glide.with(this)
                .load(comic.thumbnail.getUrl())
                .centerCrop()
                .placeholder(circularProgressDrawable)
                .into(binding.imageViewThumbnailComic)
        }
    }

    private fun setListeners() {
        binding.imageViewThumbnailComic.setOnClickListener {
            binding.frameLayoutCover.visibility = View.VISIBLE

            val fragmentCover = FragmentCover.newInstance(comic.thumbnail.getUrl())
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.frameLayout_cover, fragmentCover)
                addToBackStack(null)
                commit()
            }
        }
    }
}