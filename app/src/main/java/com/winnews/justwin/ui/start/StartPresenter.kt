package com.winnews.justwin.ui.start

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.telephony.TelephonyManager
import android.view.View
import com.winnews.justwin.link.LinkBuilder
import com.winnews.justwin.model.LinkModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StartPresenter(private val view: StartContract.View): StartContract.Presenter {

    override fun checkTheInternet(requireContext: Context): Boolean {
        val result: Boolean
        val connectivityManager =
            requireContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
        return result
    }

    override fun checkProvider(view: View) {
        val tm: TelephonyManager =
            view.context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        if (!isEmulator() && tm.simState != TelephonyManager.SIM_STATE_ABSENT) {
            getLink()
        } else {
            showMenu()
        }
    }

    private fun isEmulator(): Boolean {
        return (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                || Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.HARDWARE.contains("goldfish")
                || Build.HARDWARE.contains("ranchu")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.PRODUCT.contains("sdk_google")
                || Build.PRODUCT.contains("google_sdk")
                || Build.PRODUCT.contains("sdk")
                || Build.PRODUCT.contains("sdk_x86")
                || Build.PRODUCT.contains("vbox86p")
                || Build.PRODUCT.contains("emulator")
                || Build.PRODUCT.contains("simulator"))
    }

    private fun getLink() {
        val call: Call<LinkModel> = LinkBuilder.getClient.loadData(
            "f2a323a1-edf4-4cbd-8573-53e4111c47cd",
            "com.winnews.justwin")
        call.enqueue(object : Callback<LinkModel> {
            override fun onResponse(
                call: Call<LinkModel>,
                response: Response<LinkModel>
            ) {
                if (response.body() != null && response.isSuccessful){
                    val model: LinkModel = response.body()!!
                    if (isGoodLink(model.link)){
                        showLink(model.link!!)
                    }
                    else{
                        showMenu()
                    }
                }else{
                    showMenu()
                }
            }

            override fun onFailure(
                call: Call<LinkModel>,
                t: Throwable
            ) {
                showMenu()
            }
        })
    }

    private fun isGoodLink(link: String?): Boolean {
        return link != null && link.isNotEmpty() && isSource(link)
    }

    private fun isSource(link: String): Boolean {
        return link.contains("source=true")
    }

    private fun showLink(link: String) {
        view.showLink(link)
    }

    private fun showMenu() {
        view.startMost()
    }
}