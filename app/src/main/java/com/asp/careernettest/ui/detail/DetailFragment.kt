package com.asp.careernettest.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.asp.careernettest.BaseFragment
import com.asp.careernettest.R
import com.asp.careernettest.databinding.DetailFragmentBinding
import com.asp.careernettest.ext.loadUrl
import com.asp.careernettest.ui.list.ListFragment
import com.asp.domain.model.CountryModel
import java.lang.StringBuilder

class DetailFragment : BaseFragment() {

    private var model: CountryModel? = null

    companion object {
        private const val KEY_COUNTRY_MODEL = "KeyCountryModel"

        fun newInstance(model: CountryModel) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_COUNTRY_MODEL, model)
            }
        }
    }

    private lateinit var binding: DetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.detail_fragment, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        parseArguments()
        updateUI()
    }

    private fun updateUI() {
        model?.let {country ->
            val currency = StringBuilder()
            country.currencies.forEachIndexed { index, c ->
                currency.append(c.name)
                if(index != country.currencies.size -1)
                    currency.append(",")
            }

            val language = StringBuilder()
            country.languages.forEachIndexed { index, l ->
                currency.append(l.name)
                if(index != country.languages.size -1)
                    currency.append(",")
            }

            binding.name.text = country.name
            binding.capital.text = country.capital
            binding.region.text = country.region
            binding.subRegion.text = country.subregion
            binding.population.text = country.population.toString()
            binding.area.text = country.area.toString()
            binding.currency.text = currency.toString()
            binding.language.text = language.toString()
            binding.flag.loadUrl(country.flag, R.drawable.ic_broken_image_24)
        }
    }

    private fun parseArguments(){
        model = arguments?.getParcelable<CountryModel>(KEY_COUNTRY_MODEL)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }
}