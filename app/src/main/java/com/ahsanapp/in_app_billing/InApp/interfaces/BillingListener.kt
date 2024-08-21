package com.ahsanapp.in_app_billing.InApp.interfaces

import com.ahsanapp.in_app_billing.InApp.dataClasses.PurchaseDetail

interface BillingListener {
    fun onConnectionResult(isSuccess: Boolean, message: String)
    fun purchasesResult(purchaseDetailList: List<PurchaseDetail>)
}