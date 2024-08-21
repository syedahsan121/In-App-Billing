package com.ahsanapp.in_app_billing.InApp.extensions.DateExtension

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal fun Long.toFormattedDate(pattern: String = "MMM dd, yyyy", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(pattern, locale)
    return formatter.format(Date(this))
}