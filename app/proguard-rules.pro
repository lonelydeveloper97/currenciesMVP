# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

##OkHttp
-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-dontnote okhttp3.**

# Okio
-keep class sun.misc.Unsafe { *; }
-keep class okio.*
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn javax.annotation.**

##Retrofit
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes Annotation

##Gson
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }

##ReactiveNetwork
-dontwarn com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
-dontwarn io.reactivex.functions.Function
-dontwarn rx.internal.util.**
-dontwarn sun.misc.Unsafe

