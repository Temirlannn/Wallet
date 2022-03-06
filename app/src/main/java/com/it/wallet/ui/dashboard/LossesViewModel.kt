package com.it.wallet.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.it.wallet.data.storage.db.CategoryEntity
import com.it.wallet.data.storage.db.PaymentEntity
import com.it.wallet.ui.app.App
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LossesViewModel : ViewModel() {

    var payments = MutableLiveData<List<PaymentEntity>>()
    fun getPayments() {
        //bul kodtu jany potok okup bashtady
        //---------------

        viewModelScope.launch (Dispatchers.IO){
            try {
                val paymentList: List<PaymentEntity> = App.instance!!.getDatabase()!!.PaymentDao()
                    .fetchAllPayments().map { it }
                payments.postValue(paymentList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deletePayment(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                App.instance?.getDatabase()?.PaymentDao()?.deletePaymentById(id)
            } catch (e: Exception) {
            }
        }
    }

}