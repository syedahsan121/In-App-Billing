package com.ahsanapp.in_app_billing.InApp.enums

enum class ResultState(val message: String) {

    NONE("Not Started"),


    ACTIVITY_REFERENCE_NOT_FOUND("Activity reference is null"),

    // Connections
    CONNECTION_INVALID("Billing is not ready, seems to be disconnected"),
    CONNECTION_ESTABLISHING("Connecting to Google Play Console"),
    CONNECTION_ESTABLISHING_IN_PROGRESS("An attempt to connect to Google Play Console is already in progress."),
    CONNECTION_ALREADY_ESTABLISHED("Already connected to Google Play Console"),
    CONNECTION_DISCONNECTED("Connection disconnected to Google Play Console"),
    CONNECTION_ESTABLISHED("Connection has been established to Google Play Console"),
    CONNECTION_FAILED("Failed to connect Google Play Console"),

    // Purchases
    USER_QUERY_LIST_EMPTY("User query list is empty"),

    // Purchases
    CONSOLE_PURCHASE_PRODUCTS_INAPP_FETCHING("InApp -> Fetching purchased products from google play console."),
    CONSOLE_PURCHASE_PRODUCTS_INAPP_FETCHING_FAILED("InApp -> Failed to fetch purchased products from google play console."),
    CONSOLE_PURCHASE_PRODUCTS_INAPP_FETCHING_SUCCESS("InApp -> Successfully fetched purchased products from google play console."),

    CONSOLE_PURCHASE_PRODUCTS_SUB_FETCHING("SUB -> Fetching purchased products from google play console."),
    CONSOLE_PURCHASE_PRODUCTS_SUB_FETCHING_FAILED("SUB ->Failed to fetch purchased products from google play console."),
    CONSOLE_PURCHASE_PRODUCTS_SUB_FETCHING_SUCCESS("SUB -> Successfully fetched purchased products from google play console."),

    CONSOLE_PURCHASE_PRODUCTS_RESPONSE_PROCESSING("InApp, Subs -> Processing purchases and their product details"),
    CONSOLE_PURCHASE_PRODUCTS_RESPONSE_COMPLETE("InApp, Subs -> Returning result with each purchase product's detail"),
    CONSOLE_PURCHASE_PRODUCTS_CHECKED_FOR_ACKNOWLEDGEMENT("InApp, Subs -> Acknowledging purchases if not acknowledge yet"),

    // Querying
    CONSOLE_QUERY_PRODUCTS_INAPP_FETCHING("InApp -> Querying product details from console"),
    CONSOLE_QUERY_PRODUCTS_SUB_FETCHING("Subs -> Querying product details from console"),
    CONSOLE_QUERY_PRODUCTS_COMPLETED("InApp, Subs -> Fetched product details from console"),

    // Buying
    CONSOLE_BUY_PRODUCT_EMPTY_ID("InApp, Subs -> Product Id can't be empty"),
    CONSOLE_PRODUCTS_IN_APP_NOT_EXIST("InApp -> No product has been found"),
    CONSOLE_PRODUCTS_SUB_NOT_EXIST("SUB -> No product has been found"),

    // Update
    CONSOLE_PRODUCTS_OLD_SUB_NOT_FOUND("SUB -> Old product not being able to found"),

    // Billing Flows
    LAUNCHING_FLOW_INVOCATION_SUCCESSFULLY("Google Play Billing has been launched successfully"),
    LAUNCHING_FLOW_INVOCATION_USER_CANCELLED("Cancelled by user"),
    LAUNCHING_FLOW_INVOCATION_EXCEPTION_FOUND("Exception Found, launching Google billing sheet"),
    PURCHASING_NO_PURCHASES_FOUND("No purchases found"),
    PURCHASING_ALREADY_OWNED("Already owned this product! No need to purchase"),
    PURCHASING_SUCCESSFULLY("Successfully Purchased"),
    PURCHASING_FAILURE("Failed to make transaction"),

    PURCHASE_CONSUME("Successfully Consumed"),
    PURCHASE_FAILURE("Failed to consume product")
}