package com.ahsanapp.in_app_billing.InApp.dataClasses

import com.android.billingclient.api.ProductDetails

internal data class QueryProductDetail(
    val productDetail: ProductDetail,
    val productDetails: ProductDetails,
    val offerDetails: ProductDetails.SubscriptionOfferDetails?
)