package com.ahsanapp.in_app_billing.InApp.utils

import android.app.Activity
import com.ahsanapp.in_app_billing.InApp.enums.ResultState
import com.ahsanapp.in_app_billing.InApp.states.Result
import com.android.billingclient.api.BillingClient


internal class ValidationUtils(private val billingClient: BillingClient) {

    fun checkForInApp(activity: Activity?, productId: String): String? {
        if (activity == null) {
            com.ahsanapp.in_app_billing.InApp.states.Result.setResultState(ResultState.ACTIVITY_REFERENCE_NOT_FOUND)
            return ResultState.ACTIVITY_REFERENCE_NOT_FOUND.message
        }

        if (productId.trim().isEmpty()) {
            com.ahsanapp.in_app_billing.InApp.states.Result.setResultState(ResultState.CONSOLE_BUY_PRODUCT_EMPTY_ID)
            return ResultState.CONSOLE_BUY_PRODUCT_EMPTY_ID.message
        }

        if (billingClient.isReady.not()) {
            com.ahsanapp.in_app_billing.InApp.states.Result.setResultState(ResultState.CONNECTION_INVALID)
            return ResultState.CONNECTION_INVALID.message
        }

        return null
    }

    fun checkForSubs(activity: Activity?, productId: String): String? {
        if (activity == null) {
            com.ahsanapp.in_app_billing.InApp.states.Result.setResultState(ResultState.ACTIVITY_REFERENCE_NOT_FOUND)
            return ResultState.ACTIVITY_REFERENCE_NOT_FOUND.message
        }

        if (productId.trim().isEmpty()) {
            com.ahsanapp.in_app_billing.InApp.states.Result.setResultState(ResultState.CONSOLE_BUY_PRODUCT_EMPTY_ID)
            return ResultState.CONSOLE_BUY_PRODUCT_EMPTY_ID.message
        }

        if (billingClient.isReady.not()) {
            Result.setResultState(ResultState.CONNECTION_INVALID)
            return ResultState.CONNECTION_INVALID.message
        }

        return null
    }
}