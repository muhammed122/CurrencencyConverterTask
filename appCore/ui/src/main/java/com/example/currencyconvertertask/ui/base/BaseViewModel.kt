package com.example.currencyconvertertask.ui.base

import androidx.lifecycle.ViewModel
import com.example.currencyconvertertask.ui.utils.SingleLiveEvent
import com.qpn.kamashka.utils.network.Resource

open class BaseViewModel : ViewModel() {

    var showLoading = SingleLiveEvent<Boolean>()
    var showApiError = SingleLiveEvent<Resource.Failure>()
    var showFullScreenError = SingleLiveEvent<String>()
    var showError = SingleLiveEvent<String>()
}