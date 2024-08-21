package com.ahsanapp.in_app_billing.InApp.dataClasses

data class ProductDetail(
    var productId: String,
    var planId: String,
    var productTitle: String,
    var planTitle: String,
    var productType: ProductType,
    var currencyCode: String,
    var price: String,
    var priceAmountMicros: Long = 0,
    var freeTrialDays: Int = 0,
    var billingPeriod: String,
) {
    constructor() : this(
        productId = "",
        planId = "",
        productTitle = "",
        planTitle = "",
        productType = ProductType.subs,
        currencyCode = "",
        price = "",
        priceAmountMicros = 0,
        freeTrialDays = 0,
        billingPeriod = "",
    )
}