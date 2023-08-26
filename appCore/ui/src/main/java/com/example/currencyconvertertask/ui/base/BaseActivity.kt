package com.example.currencyconvertertask.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.viewbinding.ViewBinding
import com.example.currencyconvertertask.ui.databinding.ToolbarBinding
import com.example.currencyconvertertask.utils.resource_provider.IResourceProvider

import javax.inject.Inject

open class BaseActivity<VB : ViewBinding>(
    val bindingInflater: (LayoutInflater) -> VB
) : AppCompatActivity() {


    // View Binding Instance
    private var _binding: VB? = null
    open val binding get() = checkNotNull(_binding)

    private val loadingDialogDelegate = lazy { LoadingDialog(this) }
    private val loadingDialog by loadingDialogDelegate

    //  Nav Controller
    var navController: NavController? = null

    @Inject
    lateinit var resourceProvider: IResourceProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater(layoutInflater)
        setContentView(binding.root)

        binding.initializeUI()

    }

    override fun onResume() {
        super.onResume()
        registerListeners()
        setUpObservers()
    }

    override fun onPause() {
        unRegisterListeners()
        super.onPause()
    }


    open fun registerListeners() {}

    open fun setUpObservers() {}

    open fun unRegisterListeners() {}

    open fun VB.initializeUI() {}


    fun setupToolBar(
        toolbar: ToolbarBinding,
        title: String,
        showBackIcon: Boolean,
        onBackClick: () -> Unit
    ) {
        toolbar.tvTitle.text=title

        if (!showBackIcon)
            toolbar.btnBack.visibility =View.GONE

        toolbar.btnBack.setOnClickListener {
            onBackClick.invoke()
        }

    }

    fun setUpNavController(navContainerId: Int, activity: AppCompatActivity) {
        val navHostFragment = activity.supportFragmentManager.findFragmentById(
            navContainerId
        ) as NavHostFragment

        navHostFragment.navController.apply {
            navController = this

            NavigationUI.setupActionBarWithNavController(
                activity,
                this,
                AppBarConfiguration.Builder().build()
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        if (!(navController?.navigateUp() == true || super.onSupportNavigateUp())) {
            onBackPressedDispatcher.onBackPressed()
        }
        return true
    }

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