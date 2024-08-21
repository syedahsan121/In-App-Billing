InAppBilling Library
The InAppBilling library provides a comprehensive solution for implementing in-app purchases and subscriptions in your Android application. This library simplifies the integration of Google Play Billing services, allowing you to manage in-app purchases and subscriptions with ease.

Gradle Integration
Step A: Add Maven Repository
In your project-level build.gradle or settings.gradle file, add the JitPack repository:

gradle
Copy code
repositories {
google()
mavenCentral()
maven { url "https://jitpack.io" }
}
Step B: Add Dependencies
In your app-level build.gradle file, add the library dependency. Use the latest version:

gradle
Copy code
implementation 'com.github.SyedAhsan:inappbilling:x.x.x'
Technical Implementation
Step 1: Initialize Billing
Initialize the BillingManager with the application context:

kotlin
Copy code
private val billingManager by lazy { BillingManager(context) }
Step 2: Establish Billing Connection
Retrieve a debugging ID for testing and ensure the purchaseDetailList parameter contains all active purchases and their details:

kotlin
Copy code
val subsProductIdList = listOf("subs_product_id_1", "subs_product_id_2", "subs_product_id_3")

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
)
Access Comprehensive Details
Access comprehensive details of the currently purchased item using the PurchaseDetail class:

kotlin
Copy code
data class PurchaseDetail(
val productId: String,
var planId: String,
var productTitle: String,
var planTitle: String,
val purchaseToken: String,
val productType: ProductType,
val purchaseTime: String,
val purchaseTimeMillis: Long,
val isAutoRenewing: Boolean,
)
Step 3: Query Product
Monitor all active in-app and subscription products:

kotlin
Copy code
val subsProductIdList = listOf("subs_product_id_1", "subs_product_id_2", "subs_product_id_3")
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
}
Retrieve comprehensive details of the item using the ProductDetail class:

kotlin
Copy code
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
)
Step 4: Make Purchases
Purchasing In-App Products
kotlin
Copy code
billingManager.makeInAppPurchase(activity, productId, object : OnPurchaseListener {
override fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String) {
Log.d("BillingTAG", "makeInAppPurchase: $isPurchaseSuccess - $message")
}
})
Purchasing Subscriptions
kotlin
Copy code
billingManager.makeSubPurchase(
activity,
productId = "subs_product_id_1",
planId = "subs-plan-id-1",
object : OnPurchaseListener {
override fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String) {
Log.d("BillingTAG", "makeSubPurchase: $isPurchaseSuccess - $message")
}
}
)
Updating Subscriptions
kotlin
Copy code
billingManager.updateSubPurchase(
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
)
kotlin
Copy code
billingManager.updateSubPurchase(
activity,
oldProductId = "mOldProductID",
productId = "New Product ID",
planId = "New Plan ID",
object : OnPurchaseListener {
override fun onPurchaseResult(isPurchaseSuccess: Boolean, message: String) {
Log.d("BillingTAG", "updateSubPurchase: $isPurchaseSuccess - $message")
}
}
)
Guidance
Subscription Tags
To add products and plans on the Play Console, consider using the following recommended subscription tags to generate plans.

Option 1:
Product ID: product_id_weekly
Plan ID: plan-id-weekly

Product ID: product_id_monthly
Plan ID: plan-id-monthly

Product ID: product_id_yearly
Plan ID: plan-id-yearly

Option 2:
If you purchase a product and want to retrieve an old purchase from Google, it won't return the plan ID, making it impossible to identify which plan was purchased. To address this, you should save the purchase information on your server, including the product and plan IDs. This way, you can maintain a purchase list for future reference. Alternatively, you can use Option 1, where each product ID is associated with only one plan ID. This ensures that when you fetch a product ID, you can easily determine the corresponding plan that was purchased.

For Gold Subscription:

Product ID: gold_product
Plan ID: gold-plan-weekly
Plan ID: gold-plan-monthly
Plan ID: gold-plan-yearly

Billing Period (Subscription)
Fixed billing periods for subscriptions:

Weekly
Every 4 weeks
Monthly
Every 2 months (Bimonthly)
Every 3 months (Quarterly)
Every 4 months
Every 6 months (Semiannually)
Every 8 months
Yearly