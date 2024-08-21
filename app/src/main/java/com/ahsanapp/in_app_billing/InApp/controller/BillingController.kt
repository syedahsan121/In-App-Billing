package com.ahsanapp.in_app_billing.InApp.controller

import android.app.Activity
import android.content.Context
import com.ahsanapp.in_app_billing.InApp.Repository.BillingRepository
import com.ahsanapp.in_app_billing.InApp.interfaces.BillingListener
import com.ahsanapp.in_app_billing.InApp.interfaces.OnPurchaseListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class BillingController(context: Context) : BillingRepository(context) {

    private var billingListener: BillingListener? = null

    private var job: Job? = null

    fun startBillingConnection(
        userInAppConsumable: List<String>,
        userInAppNonConsumable: List<String>,
        userSubsPurchases: List<String>,
        billingListener: BillingListener? = null
    ) {
        this.billingListener = billingListener

        startConnection { isSuccess, message ->
            billingListener?.onConnectionResult(isSuccess, message)
            if (isSuccess) {
                fetchData(userInAppConsumable, userInAppNonConsumable, userSubsPurchases)
            }
        }
    }

    private fun fetchData(userInAppConsumable: List<String>, userInAppNonConsumable: List<String>, userSubsPurchases: List<String>) {
        setUserQueries(userInAppConsumable, userInAppNonConsumable, userSubsPurchases)
        fetchPurchases()
        fetchStoreProducts()

        // Observe purchases
        job = CoroutineScope(Dispatchers.Main).launch {
            purchasesSharedFlow.collect {
                billingListener?.purchasesResult(it)
            }
        }
    }

    fun makePurchaseInApp(
        activity: Activity?,
        productId: String,
        onPurchaseListener: OnPurchaseListener
    ) {
        purchaseInApp(activity = activity, productId = productId, onPurchaseListener = onPurchaseListener)
    }

    fun makePurchaseSub(
        activity: Activity?,
        productId: String,
        planId: String,
        onPurchaseListener: OnPurchaseListener
    ) {
        purchaseSubs(activity = activity, productId = productId, planId = planId, onPurchaseListener = onPurchaseListener)
    }

    fun updatePurchaseSub(
        activity: Activity?,
        oldProductId: String,
        productId: String,
        planId: String,
        onPurchaseListener: OnPurchaseListener
    ) {
        updateSubs(activity = activity, oldProductId = oldProductId, productId = productId, planId = planId, onPurchaseListener = onPurchaseListener)
    }

    fun cleanBilling() {
        endConnection()
        job?.cancel()
    }
}