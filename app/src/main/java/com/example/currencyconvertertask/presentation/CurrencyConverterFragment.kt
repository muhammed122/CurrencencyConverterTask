package com.example.currencyconvertertask.presentation

import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.currencyconvertertask.databinding.FragmentCurrencyConverterBinding
import com.example.currencyconvertertask.domin.entity.response.CurrencySymbolsModel
import com.example.currencyconvertertask.ui.base.BasicFragment
import com.example.currencyconvertertask.ui.extentions.collect
import com.example.currencyconvertertask.ui.extentions.handleApiError
import com.qpn.kamashka.utils.network.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConverterFragment :
    BasicFragment<FragmentCurrencyConverterBinding, CurrencyConverterViewModel>(
        FragmentCurrencyConverterBinding::inflate
    ) {

    override val viewModel: CurrencyConverterViewModel by viewModels()


    override fun setupObservers() {
        collect(viewModel.currenciesFlow) {
            when (it) {
                is Resource.Success -> {
                    hideLoading()
                    proceedSymbolsResponse(it.value)
                }
                is Resource.Failure -> {
                    handleApiError(it)
                    hideLoading()
                }
                is Resource.Loading -> {
                    showLoading()
                }
                else -> {}

            }
        }
    }

    private fun proceedSymbolsResponse(value: CurrencySymbolsModel?) {
        val currenciesSymbol=value?.currencies?.map { it.cur }?.toList() ?: emptyList()
        binding.fromCountries.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                currenciesSymbol
            )
        )
        binding.toCountries.setAdapter(
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                currenciesSymbol
            )
        )
    }

}