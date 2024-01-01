package com.berkerdgn.tvseriesapplevel3.presentation.people_screen.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berkerdgn.tvseriesapplevel3.data.remote.model.peopleModels.PeopleModel
import com.berkerdgn.tvseriesapplevel3.data.repository.TvSeriesRepositoryImpl
import com.berkerdgn.tvseriesapplevel3.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val tvSeriesRepositoryImpl: TvSeriesRepositoryImpl
) : ViewModel() {


    private val _people = MutableLiveData<Resource<PeopleModel>>()

    val people : LiveData<Resource<PeopleModel>>
        get() = _people




    fun getPeople(idPeople: String){
        CoroutineScope(Dispatchers.Main).launch{
            _people.value = tvSeriesRepositoryImpl.getPeople(idPeople)
        }
    }


}