<h1>In-App Billing Library</h1>

This repository demonstrates how to implement in-app purchases and subscriptions in your Android application using the Google Play Billing library.



<h2>Gradle Integration</h2>

### Step A: Add Maven Repository

<div class="code-block">
  <pre>
    
      repositories {
          google()
          mavenCentral()
          maven { url "https://jitpack.io" }
      }

  </pre>
  <button class="copy-btn" data-clipboard-text="repositories {
          google()
          mavenCentral()
          maven { url &quot;https://jitpack.io&quot; }
      }"></button>
</div>

### Step B: Add Dependencies

<div class="code-block">
  <pre>
  
      dependencies {
          implementation 'com.github.SyedAhsan:inappbilling:x.x.x'
      }

  </pre>
  <button class="copy-btn" data-clipboard-text="dependencies {
          implementation 'com.github.SyedAhsan:inappbilling:x.x.x'
      }"></button>
</div>

### Step C: Initialize the Billing Client

<div class="code-block">
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
  <button class="copy-btn" data-clipboard-text="class MyApplication : Application() {
          override fun onCreate() {
              super.onCreate()
              BillingClientFactory.create(this, object : BillingClientFactory.Callback {
                  override fun onBillingClientReady(billingClient: Billing Client) {
                      // Initialize the billing client
                  }
              })
          }
      }"></button>
</div>