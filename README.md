<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>InAppBilling Library Documentation</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            color: #333;
            margin: 20px;
            padding: 0;
        }
        h1, h2, h3 {
            color: #007BFF;
        }
        h1 {
            border-bottom: 2px solid #007BFF;
            padding-bottom: 10px;
        }
        h2 {
            margin-top: 20px;
        }
        p {
            margin: 10px 0;
        }
        code {
            background: #f4f4f4;
            padding: 2px 4px;
            border-radius: 3px;
        }
        pre {
            background: #f4f4f4;
            padding: 10px;
            border-radius: 3px;
            overflow-x: auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        ul {
            list-style-type: disc;
            margin: 10px 0;
            padding-left: 20px;
        }
        li {
            margin: 5px 0;
        }
        .center {
            text-align: center;
        }
        .logo {
            max-width: 150px;
        }
        .warning {
            color: red;
            font-weight: bold;
        }
        .note {
            background-color: #e7f0ff;
            border-left: 4px solid #007BFF;
            padding: 10px;
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <h1 class="center">InAppBilling Library</h1>

    <p class="center">
        <img src="https://via.placeholder.com/150" alt="InAppBilling Logo" class="logo">
    </p>

    <p class="center">
        The InAppBilling library provides a comprehensive solution for implementing in-app purchases and subscriptions in your Android application.
    </p>

    <h2>Gradle Integration</h2>

    <h3>Step A: Add Maven Repository</h3>
    <pre><code>repositories {
    google()
    mavenCentral()
    maven { url "https://jitpack.io" }
}</code></pre>

    <h3>Step B: Add Dependencies</h3>
    <pre><code>implementation 'com.github.SyedAhsan:inappbilling:x.x.x'</code></pre>

    <h2>Technical Implementation</h2>

    <h3>Step 1: Initialize Billing</h3>
    <pre><code>private val billingManager by lazy { BillingManager(context) }</code></pre>

    <h3>Step 2: Establish Billing Connection</h3>
    <pre><code>val subsProductIdList = listOf("subs_product_id_1", "subs_product_id_2", "subs_product_id_3")

val productInAppConsumable = when (BuildConfig.DEBUG) {
true -> listOf("product_id_1")
false -> listOf("product_id_1", "product_id_2")
}

val productInAppNonConsumable = when (BuildConfig.DEBUG) {
true -> listOf(billingManager.getDebugProductIDList())
false -> listOf("product_id_1", "product_id_2")
}

billingManager.initialize(
productInAppConsumable = productInAppConsumable,
productInAppNonConsumable = productInAppNonConsumable,
productSubscriptions = subsProductIdList,
billingListener = object : BillingListener {
override fun onConnectionResult(isSuccess: Boolean, message: String) {
Log.d("BillingTAG", "Billing: initBilling: onConnectionResult: isSuccess = $isSuccess - message = $message")
}

        override fun purchasesResult(purchaseDetailList: List<PurchaseDetail>) {
            if (purchaseDetailList.isEmpty()) {
                // No purchase found, reset all sharedPreferences (premium properties)
            }
            purchaseDetailList.forEachIndexed { index, purchaseDetail ->
                Log.d("BillingTAG", "Billing: initBilling: purchasesResult: $index) $purchaseDetail ")
            }
        }
    }
)</code></pre>

    <h3>Access Comprehensive Details</h3>
    <pre><code>data class PurchaseDetail(
    val productId: String,
    var planId: String,
    var productTitle: String,
    var planTitle: String,
    val purchaseToken: String,
    val productType: ProductType,
    val purchaseTime: String,
    val purchaseTimeMillis: Long,
    val isAutoRenewing: Boolean,
)</code></pre>

    <h3>Step 3: Query Product</h3>
    <pre><code>val subsProductIdList = listOf("subs_product_id_1", "subs_product_id_2", "subs_product_id_3")
val subsPlanIdList = listOf("subs-plan-id-1", "subs-plan-id-2", "subs-plan-id-3")

billingManager.productDetailsLiveData.observe(viewLifecycleOwner) { productDetailList ->
Log.d("BillingTAG", "Billing: initObservers: $productDetailList")

    productDetailList.forEach { productDetail ->
        if (productDetail.productType == ProductType.inapp) {
            when (productDetail.productId) {
                "inapp_product_id_1" -> { /* Handle in-app product 1 */ }
                "inapp_product_id_2" -> { /* Handle in-app product 2 */ }
            }
        } else {
            when (productDetail.productId) {
                "subs_product_id_1" -> if (productDetail.planId == "subs-plan-id-1") { /* Handle plan1 subscription */ }
                "subs_product_id_2" -> if (productDetail.planId == "subs-plan-id-2") { /* Handle plan2 subscription */ }
                "subs_product_id_3" -> if (productDetail.planId == "subs-plan-id-3") { /* Handle plan3 subscription */ }
            }
        }
    }
}</code></pre>

    <pre><code>data class ProductDetail(
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
)</code></pre>

    <h3>Step 4: Make Purchases</h3>
    
    <h4>Purchasing In-App Products</h4>
    <pre><code>billingManager.makeInAppPurchase(activity, productId, object : OnPurchaseListener {
    override fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String) {
        Log.d("BillingTAG", "makeInAppPurchase: $isPurchaseSuccess - $message")
    }
})</code></pre>

    <h4>Purchasing Subscriptions</h4>
    <pre><code>billingManager.makeSubPurchase(
    activity,
    productId = "subs_product_id_1",
    planId = "subs-plan-id-1",
    object : OnPurchaseListener {
        override fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String) {
            Log.d("BillingTAG", "makeSubPurchase: $isPurchaseSuccess - $message")
        }
    }
)</code></pre>

    <h4>Updating Subscriptions</h4>
    <pre><code>billingManager.updateSubPurchase(
    activity,
    oldProductId = "subs_product_id_1",
    oldPlanId = "subs_plan_id_1",
    productId = "subs_product_id_2",
    planId = "subs-plan-id-2",
    object : OnPurchaseListener {
        override fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String) {
            Log.d("BillingTAG", "updateSubPurchase: $isPurchaseSuccess - $message")
        }
    }
)</code></pre>

    <pre><code>billingManager.updateSubPurchase(
    activity,
    oldProductId = "mOldProductID",
    productId = "New Product ID",
    planId = "New Plan ID",
    object : OnPurchaseListener {
        override fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String) {
            Log.d("BillingTAG", "updateSubPurchase: $isPurchaseSuccess - $message")
        }
    }
)</code></pre>

    <h2>Guidance</h2>

    <h3>Subscription Tags</h3>
    <p>To add products and plans on the Play Console, consider using the following recommended subscription tags to simplify identification:</p>
    <ul>
        <li><code>MONTHLY</code> - for Monthly Plans</li>
        <li><code>YEARLY</code> - for Yearly Plans</li>
        <li><code>FREE_TRIAL</code> - for Free Trials</li>
    </ul>

    <h3>Validation</h3>
    <p>For verifying purchase data, you can use Google Play Developer API's <a href="https://developers.google.com/android-publisher/api-ref/purchases/subscription/get">Subscription Get API</a> for server-side validation.</p>

    <div class="note">
        <p>Note: Always keep your library up to date and review Google Play's policies regularly to ensure compliance.</p>
    </div>
    
    <h2>License</h2>
    <p>This library is licensed under the MIT License. See the <a href="LICENSE">LICENSE</a> file for details.</p>
</body>
</html>
