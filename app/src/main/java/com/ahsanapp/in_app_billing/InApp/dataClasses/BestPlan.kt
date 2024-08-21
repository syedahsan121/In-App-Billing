package com.ahsanapp.in_app_billing.InApp.dataClasses

import com.android.billingclient.api.ProductDetails

internal data class BestPlan(
    val trialDays: Int,
    val pricingPhase: ProductDetails.PricingPhase?
)