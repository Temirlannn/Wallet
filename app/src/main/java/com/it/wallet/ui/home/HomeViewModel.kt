package com.it.wallet.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.wallet.data.storage.db.CategoryEntity
import com.it.wallet.ui.app.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var categories = MutableLiveData<List<CategoryEntity>>()
    fun getCategories() {
        //bul kodtu jany potok okup bashtady
        //---------------

        viewModelScope.launch (Dispatchers.IO){
            try {
                val category: List<CategoryEntity> = App.instance!!.getDatabase()!!.PaymentDao()
                .fetchAllCategories().map { it }
                categories.postValue(category)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}