package com.example.currencyconvertertask.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.currencyconvertertask.ui.extentions.handleApiError
import com.example.currencyconvertertask.ui.extentions.observe
import com.example.currencyconvertertask.ui.extentions.showFullScreenError

abstract class BasicFragment<VB : ViewBinding, VM : BaseViewModel>(
    override val bindingInflater: (LayoutInflater) -> VB
) : BaseFragment<VB>(bindingInflater) {

    protected abstract val viewModel: VM

    /**
     * override this property if you want identify another viewGroup
     *  This will be useful in case the full screen error view overlap the root view
     * **/
    open val fullScreenViewGroup: ViewGroup
        get() = binding.root as ViewGroup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpBaseObservers()
    }



    private fun setUpBaseObservers() {
        observe(viewModel.showLoading) { showLoading ->
            if (showLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        }

        observe(viewModel.showApiError) { failureResource ->
            handleApiError(failureResource)
        }

        observe(viewModel.showFullScreenError) { errorMessage ->
            showFullScreenError(fullScreenViewGroup, errorMessage)
        }
    }


}