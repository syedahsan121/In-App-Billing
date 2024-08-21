<h1>In-App Billing Library</h1>

This repository demonstrates how to implement in-app purchases and subscriptions in your Android application using the Google Play Billing library.

<h2>Gradle Integration</h2>

### Step A: Add Maven Repository

In your project-level `build.gradle` or `settings.gradle` file, add the JitPack repository:
<pre>

repositories {
    google()
    mavenCentral()
    maven { url "https://jitpack.io" }
}

</pre>

### Step B: Add Dependencies

In your app-level `build.gradle` file, add the library dependency:
<pre>

dependencies {
    implementation 'com.github.SyedAhsan:inappbilling:x.x.x'
}

</pre>

### Step C: Initialize the Billing Client

In your app's `Application` class, initialize the billing client:
<pre>

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        BillingClientFactory.create(this, object : BillingClientFactory.Callback {
            override fun onBillingClientReady(billingClient: BillingClient) {
                // Initialize the billing client
            }
        })
    }
}
</pre>

<h2>Usage</h2>

### Purchase a Product

To purchase a product, use the `launchPurchaseFlow` method:
<pre>

private fun purchaseProduct() {
    val skuDetails = billingClient.querySkuDetailsAsync(
        SkuDetailsParams.newBuilder()
            .setSkusList(listOf("premium_product"))
            .setType(BillingClient.SkuType.INAPP)
            .build()
    )
    // Handle the purchase flow
}

</pre>

### Handle Purchase Result

To handle the purchase result, use the `onPurchasesUpdated` callback:
<pre>

override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List&lt;Purchase&gt;) {
    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
        // Handle the purchase success
    } else {
        // Handle the purchase failure
    }
}
</pre>