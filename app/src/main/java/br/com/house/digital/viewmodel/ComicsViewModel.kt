package br.com.house.digital.viewmodel

import android.util.Log
import br.com.house.digital.service.Repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
                        limit = 15,
                        ts = "1606578058740",
                        apikey = "6eb7e8896ec5850c52515a8a23ee97f0",
                        hash = "696a9c77a454ebcd14b377d154b0f621"
                    )

                listComics.value = result.data.results
            }
        } catch (ex: Exception) {
            Log.e("MainViewModel ERROR", ex.toString())
        }
    }
}