package com.it.wallet.ui.histories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.wallet.data.storage.db.PaymentEntity
import com.it.wallet.data.storage.db.PaymentWithServiceAndCategory
import com.it.wallet.ui.app.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {

    var payments = MutableLiveData<List<PaymentWithServiceAndCategory>>()
    fun getAllData() {
        //bul kodtu jany potok okup bashtady
        //---------------

        viewModelScope.launch (Dispatchers.IO){
            try {
                val paymentList: List<PaymentWithServiceAndCategory> = App.instance!!.getDatabase()!!.PaymentDao()
                    .getAllData().map { it }
                payments.postValue(paymentList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}