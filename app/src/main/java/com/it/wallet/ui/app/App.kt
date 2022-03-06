package com.it.wallet.ui.app

import android.app.Application
import com.it.wallet.data.storage.db.CategoryEntity
import com.it.wallet.data.storage.db.PaymentDB
import com.it.wallet.data.storage.db.ServiceEntity
import com.it.wallet.data.storage.sharedPref.AppPreferences
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class App:Application() {
    private var paymentDB:PaymentDB?=null
    private var prefs: AppPreferences?=null

    override fun onCreate() {
        super.onCreate()
        instance = this
        paymentDB = PaymentDB.getInstance(this)
        prefs = AppPreferences(this)
        addData()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun addData() {
        GlobalScope.launch {
            try {
                paymentDB?.PaymentDao()?.addCategory(CategoryEntity(1,"Internet"))
                paymentDB?.PaymentDao()?.addCategory(CategoryEntity(2,"Government payments"))
                paymentDB?.PaymentDao()?.addCategory(CategoryEntity(3,"Television"))
                paymentDB?.PaymentDao()?.addCategory(CategoryEntity(4,"Replenishment of cards of other banks"))


                //internettiki
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"Saima",1))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"Aknet",1))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"HomeLine",1))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"CityNet",1))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"My4G",1))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"Jet kt",1))


                //Governmenttiki // nalog bolot bul
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"Zhags i DRN",2))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"Oplata za vyzov musora (ROK)",2))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"Soc FOND - strahovye vznosy",2))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"ETCP_INFOCOM",2))


                //Television
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"AknetIPTV a",3))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0, "AITV",3))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0, "ALATV",3))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0, "ITV",3))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0, "ZORTV",3))


                //bank cards
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"MBank online",4))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"AsiaBank card",4))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"MasterCard",4))
                paymentDB?.PaymentDao()?.addService(ServiceEntity(0,"Elcard",4))

            } catch (t: Throwable) {
                t.localizedMessage
            }
        }
    }

    fun getDatabase():PaymentDB?{
        return paymentDB
    }

    companion object{
        var instance:App?=null
    }

    fun getPrefs(): AppPreferences?{
        return prefs
    }
}