package com.ahsanapp.in_app_billing.InApp.dataClasses

import com.android.billingclient.api.ProductDetails
import com.android.billingclient.api.Purchase

data class CompletePurchase(
    val purchase: Purchase,
    val productDetailList: List<ProductDetails>
)