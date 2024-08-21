package com.ahsanapp.in_app_billing.InApp.interfaces

interface OnPurchaseListener {

    fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String)
}