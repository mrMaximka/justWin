package com.winnews.justwin.ui.start

import android.content.Context

interface StartContract {
    interface View {
        fun showLink(link: String)
        fun startMost()

    }
    interface Presenter {
        fun checkTheInternet(requireContext: Context): Boolean
        fun checkProvider(view: android.view.View)

    }
}