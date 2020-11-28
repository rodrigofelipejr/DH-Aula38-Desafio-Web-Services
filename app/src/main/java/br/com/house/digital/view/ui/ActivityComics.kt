package br.com.house.digital.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import br.com.house.digital.databinding.ActivityComicsBinding
import br.com.house.digital.service.repository
import br.com.house.digital.view.adapter.AdapterComics
import br.com.house.digital.viewmodel.ComicsViewModel

class ActivityComics : AppCompatActivity(), AdapterComics.IOnClickListenerComic {
    private lateinit var binding: ActivityComicsBinding
    lateinit var adapterComics: AdapterComics
    lateinit var gridLayoutManager: GridLayoutManager

    private val viewModel by viewModels<ComicsViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return ComicsViewModel(repository) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComicsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterComics = AdapterComics(this)
        gridLayoutManager = GridLayoutManager(this, 3)
        binding.recyclerViewComics.adapter = adapterComics
        binding.recyclerViewComics.layoutManager = gridLayoutManager
        binding.recyclerViewComics.hasFixedSize()

        viewModel.listComics.observe(this) {
            adapterComics.addList(it)
        }

        viewModel.populateListComics()

        //setScroller()
    }

    override fun onClickListenerComic(item: Int) {
        //TODO criar lista local?
        //val comic = desenvolvedores.get(item)
        //intent.putExtra("COMIC_EXTRA", comic)

        val intent = Intent(this, ActivityComicDetails::class.java)
        startActivity(intent)
    }

    //TODO implementar carregamento
//    fun setScroller() {
//        binding.recyclerViewComics.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (dy > 0) {
//                    val litem = gridLayoutManager.itemCount
//                    val vItem = gridLayoutManager.findFirstCompletelyVisibleItemPosition()
//                    val totalItens = adapterComics.itemCount
//                }
//            }
//        })
//    }
}




