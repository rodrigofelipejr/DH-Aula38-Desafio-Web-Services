package br.com.house.digital.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import br.com.house.digital.databinding.ActivityComicsBinding
import br.com.house.digital.model.Comic
import br.com.house.digital.service.repository
import br.com.house.digital.view.adapter.AdapterComics
import br.com.house.digital.viewmodel.ComicsViewModel

class ActivityComics : AppCompatActivity(), AdapterComics.IOnClickListenerComic {
    private lateinit var binding: ActivityComicsBinding
    lateinit var adapterComics: AdapterComics
    lateinit var gridLayoutManager: GridLayoutManager
    private var listComics: ArrayList<Comic> = arrayListOf()

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
            listComics.addAll(it)
            adapterComics.addList(it)
        }

        viewModel.populateListComics()

        //setScroller()
    }

    override fun onClickListenerComic(index: Int) {
        val intent = Intent(this, ActivityComicDetails::class.java)
        intent.putExtra("COMIC_ID", listComics[index].id)
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




