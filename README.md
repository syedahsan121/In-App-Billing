<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>In-App Billing Library</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            line-height: 1.6;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333;
            font-size: 3rem;
            margin-bottom: 10px;
        }

        h2 {
            color: #444;
            font-size: 2rem;
            border-bottom: 2px solid #333;
            padding-bottom: 5px;
        }

        p {
            font-size: 1.1rem;
            margin-bottom: 20px;
        }

        code {
            background-color: #333;
            color: #f4f4f4;
            padding: 2px 5px;
            border-radius: 4px;
            font-size: 1rem;
        }

        pre {
            background-color: #272822;
            color: #f8f8f2;
            padding: 15px;
            border-radius: 4px;
            font-size: 1rem;
            overflow-x: auto;
        }

        ul {
            margin: 10px 0;
            padding: 0 20px;
        }

        li {
            margin-bottom: 10px;
            font-size: 1rem;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 1rem;
            color: #fff;
            background-color: #007bff;
            border-radius: 4px;
            text-decoration: none;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #0056b3;
        }

        .highlight {
            background-color: #ffeb3b;
            padding: 3px 5px;
            border-radius: 3px;
        }

        footer {
            margin-top: 50px;
            text-align: center;
            font-size: 0.9rem;
            color: #666;
        }
    </style>
</head>

<body>
    <div class="container">
        <h1>In-App Billing Library</h1>

        <p>Welcome to the <strong>In-App Billing Library</strong> repository! This project demonstrates how to implement
            in-app purchases and subscriptions in your Android application using the Google Play Billing library.</p>

        <h2>Gradle Integration</h2>

        <h3>Step A: Add Maven Repository</h3>
        <p>In your project-level <code>build.gradle</code> or <code>settings.gradle</code> file, add the JitPack repository:</p>
        <pre><code>repositories {
    google()
    mavenCentral()
    maven { url "https://jitpack.io" }
}</code></pre>

        <h3>Step B: Add Dependencies</h3>
        <p>In your app-level <code>build.gradle</code> file, add the library dependency:</p>
        <pre><code>implementation 'com.github.hypersoftdev:inappbilling:x.x.x'</code></pre>

        <h2>Technical Implementation</h2>

        <h3>Step 1: Initialize Billing</h3>
        <p>Initialize the <code>BillingManager</code> with the application context:</p>
        <pre><code>private val billingManager by lazy { BillingManager(context) }</code></pre>

        <h3>Step 2: Establish Billing Connection</h3>
        <p>Retrieve a debugging ID for testing and ensure the <code>purchaseDetailList</code> parameter contains all active purchases and their details:</p>
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

        <h3>Step 3: Query Product</h3>
        <p>Monitor all active in-app and subscription products:</p>
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

        <h3>Step 4: Make Purchases</h3>
        <p>Purchasing In-App Products:</p>
        <pre><code>billingManager.makeInAppPurchase(activity, productId, object : OnPurchaseListener {
    override fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String) {
        Log.d("BillingTAG", "makeInAppPurchase: $isPurchaseSuccess - $message")
    }
})</code></pre>

        <p>Purchasing Subscriptions:</p>
        <pre><code>billingManager.makeSubPurchase(activity,
                productId = "subs_product_id_1",
                planId = "subs-plan-id-1",
                object : OnPurchaseListener {
                    override fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String) {
                        Log.d("BillingTAG", "makeSubPurchase: $isPurchaseSuccess - $message")
                    }
                })</code></pre>

        <p>Updating Subscriptions:</p>
        <pre><code>billingManager.updateSubPurchase(
    activity,
    oldProductId = "subs_product_id_1",
    oldPlanId = "subs_plan_id_1",
    productId = "subs_product_id_2",
    planId = "subs_plan_id_2",
    object : OnPurchaseListener {
        override fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String) {
            Log.d("BillingTAG", "updateSubPurchase: $isPurchaseSuccess - $message")
        }
    }
)</code></pre>

        <h2>Guidance</h2>

        <h3>Subscription Tags</h3>
        <p>To add products and plans on the Play Console, consider using the following recommended subscription tags to generate plans:</p>

        <h4>Option 1</h4>
        <p><span class="highlight">Note: One-to-One IDs</span></p>
        <ul>
            <li>Product ID: <code>product_id_weekly</code></li>
            <li>Plan ID: <code>plan-id-weekly</code></li>
            <li>Product ID: <code>product_id_monthly</code></li>
            <li>Plan ID: <code>plan-id-monthly</code></li>
            <li>Product ID: <code>product_id_yearly</code></li>
            <li>Plan ID: <code>plan-id-yearly</code></li>
        </ul>

        <h4>Option 2</h4>
        <p><span class="highlight">Note: One-to-Multiple IDs</span></p>
        <ul>
            <li>Product ID: <code>product_id_subscription</code></li>
            <li>Plan ID: <code>plan-id-weekly</code></li>
            <li>Plan ID: <code>plan-id-monthly</code></li>
            <li>Plan ID: <code>plan-id-yearly</code></li>
        </ul>

        <footer>
            <p>© 2024 Syed Ahsan. All Rights Reserved.</p>
        </footer>
    </div>
</body>

</html>
