package com.it.wallet.ui.service_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.wallet.data.storage.db.CategoryEntity
import com.it.wallet.data.storage.db.PaymentEntity
import com.it.wallet.data.storage.db.ServiceEntity
import com.it.wallet.ui.app.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServiceViewModel : ViewModel() {

    var services = MutableLiveData<List<ServiceEntity>>()
    fun getServices(id:Int) {
        //bul kodtu jany potok okup bashtady
        //---------------

        viewModelScope.launch (Dispatchers.IO){
            try {
                val serviceList: List<ServiceEntity> = App.instance!!.getDatabase()!!.PaymentDao()
                    .fetchAllServices(id).map { it }
                services.postValue(serviceList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}