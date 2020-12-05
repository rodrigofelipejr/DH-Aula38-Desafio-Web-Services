package br.com.house.digital.viewmodel

import android.util.Log
import br.com.house.digital.service.Repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.house.digital.helper.settings
import br.com.house.digital.model.Comic
import br.com.house.digital.model.Comics
import br.com.house.digital.service.repository
import kotlinx.coroutines.launch

class ComicsViewModel(repository: Repository) : ViewModel() {
    var listComics = MutableLiveData<ArrayList<Comic>>()

    fun populateListComics() {
        try {
            viewModelScope.launch {
                val result: Comics =
                    repository.getComics(
                        format = "comic",
                        formatType = "comic",
                        noVariants = true,
                        hasDigitalIssue = false,
                        orderBy = "issueNumber",
                        dateRange = "2018-01-01,2020-01-01",
                        characters = 1009610,
                        offset = 0,
                        limit = 21,
                        ts = settings.ts,
                        apikey = settings.apikey,
                        hash = settings.hash
                    )
                listComics.value = result.data.results
            }
        } catch (ex: Exception) {
            Log.e("ComicsViewModel ERROR", ex.toString())
        }
    }
}