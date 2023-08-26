package com.example.currencyconvertertask.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.currencyconvertertask.R
import com.example.currencyconvertertask.databinding.FragmentCurrencyConverterBinding
import com.example.currencyconvertertask.ui.base.BasicFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyConverterFragment :
    BasicFragment<FragmentCurrencyConverterBinding, CurrencyConverterViewModel>(
        FragmentCurrencyConverterBinding::inflate
    ) {

    override val viewModel: CurrencyConverterViewModel by viewModels()
}