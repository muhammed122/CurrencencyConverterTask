package com.example.currencyconvertertask.ui.extentions

import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.currencyconvertertask.ui.R
import com.qpn.kamashka.utils.ApiException
import com.qpn.kamashka.utils.network.Resource
import kotlinx.coroutines.flow.SharedFlow

fun Fragment.handleApiError(
    failure: Resource.Failure,
    noInternetAction: (() -> Unit)? = null
) {
    when (failure.exception) {
        is ApiException.NoInternetConnection -> {
         //   noInternetAction?.invoke()
            showNoInternetDialog(noInternetAction)
        }
        else -> {
            val message =
                failure.exception.message ?: failure.exception.localizedMessage
                ?: resources.getString(
                    R.string.some_error
                )

            showErrorDialog(
                title = resources.getString(R.string.error),
                message = message
            )
        }
    }
}

fun Fragment.showFullScreenError(
    fullScreenViewGroup: ViewGroup,
    message: String?,
    enableCloseButton: Boolean = false,
    retryCallbacks: (() -> Unit)? = null,
    closeCallback: (() -> Unit)? = null
) {

}

fun Fragment.hideKeyboard() = requireActivity().hideKeyboard()

fun Fragment.showNoInternetDialog(noInternetAction: (() -> Unit)?) =
    (this.requireActivity() as? AppCompatActivity)?.showNoInternetAlert(noInternetAction)

fun Fragment.showErrorDialog(title: String, message: String) =
    (this.requireActivity() as? AppCompatActivity)?.showErrorDialog(title, message)

fun Fragment.showMessage(message: String?) = requireContext().showMessage(message)

fun Fragment.showError(
    message: String,
    retryActionName: String? = null,
    action: (() -> Unit)? = null
) = requireView().showSnackBar(message, retryActionName, action)

fun <T> Fragment.getNavigationResultLiveData(key: String = "result") =
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)

fun <T> Fragment.removeNavigationResultObserver(key: String = "result") =
    findNavController().currentBackStackEntry?.savedStateHandle?.remove<T>(key)

fun <T> Fragment.setNavigationResult(result: T, key: String = "result") {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, result)
}

fun Fragment.onBackPressedCustomAction(action: () -> Unit) {
    requireActivity().onBackPressedDispatcher.addCallback(
        viewLifecycleOwner,
        object : OnBackPressedCallback(true) {
            override
            fun handleOnBackPressed() {
                action()
            }
        }
    )
}

fun Fragment.navigateSafe(directions: NavDirections, navOptions: NavOptions? = null) {
    kotlin.runCatching {
        findNavController().navigate(directions, navOptions)
    }
}

fun Fragment.backToPreviousScreen() {
    findNavController().navigateUp()
}

inline fun <reified T : Fragment> newFragmentInstance(vararg params: Pair<String, Any>): T =
    T::class.java.newInstance().apply {
        arguments = bundleOf(*params)
    }


fun <T> Fragment.observe(liveData: LiveData<T>, block: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner) {
        block.invoke(it)
    }
}

fun <T> Fragment.collect(sharedFlow: SharedFlow<T>, block: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
        sharedFlow.collect {
            block(it)
        }
    }
}