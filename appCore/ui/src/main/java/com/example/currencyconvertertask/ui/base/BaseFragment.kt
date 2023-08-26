package com.example.currencyconvertertask.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.currencyconvertertask.ui.databinding.ToolbarBinding
import com.example.currencyconvertertask.utils.resource_provider.IResourceProvider
import java.util.*
import javax.inject.Inject

open class BaseFragment<VB : ViewBinding>(
    open val bindingInflater: (LayoutInflater) -> VB
) : Fragment() {


    // View Binding Instance
    private var _binding: VB? = null
    open val binding get() = checkNotNull(_binding)

    private val loadingDialogDelegate = lazy { LoadingDialog(activity) }
    private val loadingDialog by loadingDialogDelegate


    val currentLanguage: String
        get() = Locale.getDefault().language

    @Inject
    lateinit var resourceProvider: IResourceProvider

    override fun onResume() {
        super.onResume()

        registerListeners()
    }

    override fun onPause() {
        unRegisterListeners()
        super.onPause()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getFragmentArguments()
        binding.initializeUI()
        setupObservers()
    }

    fun setupToolBar(
        toolbar: ToolbarBinding,
        title: String,
        showBackIcon: Boolean,
        onBackClick: () -> Unit
    ) {
        (requireActivity() as? BaseActivity<*>)?.setupToolBar(
            toolbar = toolbar,
            title = title,
            showBackIcon = showBackIcon,
            onBackClick = onBackClick
        )

    }

    open fun registerListeners() {}

    open fun unRegisterListeners() {}

    open fun getFragmentArguments() {}

    open fun VB.initializeUI() {}

    open fun setupObservers() {}

    fun showLoading(hint: String? = null) = loadingDialog.showDialog(hint)

    fun hideLoading() = loadingDialog.hideDialog()


    override fun onDestroy() {
        _binding = null
        if (loadingDialogDelegate.isInitialized()) {
            loadingDialog.clean()
        }

        super.onDestroy()
    }
}