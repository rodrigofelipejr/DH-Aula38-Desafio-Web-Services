package br.com.house.digital.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.house.digital.helper.settings
import br.com.house.digital.model.Comic
import br.com.house.digital.model.Comics
import br.com.house.digital.service.Repository
import br.com.house.digital.service.repository
import kotlinx.coroutines.launch

class ComicDetailsViewModel(repository: Repository) : ViewModel() {
    var comic = MutableLiveData<Comic>()

    fun getComicId(id: Int) {
        try {
            viewModelScope.launch {
                val result: Comics =
                    repository.getComic(
                        comicId = id,
                        ts = settings.ts,
                        apikey =settings.apikey,
                        hash = settings.hash
                    )
                comic.value = result.data.results[0]
            }
        } catch (ex: Exception) {
            Log.e("ComicDetailsVM ERROR", ex.toString())
        }
    }
}