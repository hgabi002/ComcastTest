package com.example.comcasttest.viewmodel

import androidx.lifecycle.*
import com.example.comcasttest.model.Repository
import com.example.comcasttest.model.RepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FlavorViewModel(private val repository: Repository): ViewModel() {

    class FlavorViewModelProvider(private val repository: Repository):
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return FlavorViewModel(repository) as T
        }
    }

    private val _liveData: MutableLiveData<RepositoryImpl.AppState> = MutableLiveData()
    val liveData: LiveData<RepositoryImpl.AppState> = _liveData

    fun getData(){
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getData()
            withContext(Dispatchers.Main){
                _liveData.value = RepositoryImpl.AppState.LOADING
                //delay(2000)
                _liveData.setValue(data)
            }
        }
    }
}