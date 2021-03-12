package com.asp.careernettest.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.asp.careernettest.*
import com.asp.careernettest.adapter.CountryAdapter
import com.asp.careernettest.databinding.MainFragmentBinding
import com.asp.careernettest.listener.OnItemClickListener
import com.asp.careernettest.model.sortForName
import com.asp.careernettest.ui.detail.DetailFragment
import com.asp.domain.model.CountryModel

class ListFragment : BaseFragment(), OnItemClickListener<CountryModel> {

    private var recyclerView: RecyclerView? = null
    private var searchET: EditText? = null
    private var countryList = ArrayList(emptyList<CountryModel>())
    private var adapter =  CountryAdapter(countryList, this)

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: ListViewModel
    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.main_fragment, container, false)

        viewModel = ViewModelProvider(this, ViewModelFactory {
            ListViewModel(DependencyInjector.getCountryUseCase())
        }).get(ListViewModel::class.java)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        inflateViews()
        setupViews()
        observe()
    }

    private fun inflateViews(){
        recyclerView = view?.findViewById(R.id.recyclerView)
        searchET = view?.findViewById(R.id.searchET)
    }

    private fun setupViews(){
        recyclerView?.adapter = adapter
        recyclerView?.addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))

        searchET?.doAfterTextChanged {
            adapter.filter.filter(it)
        }
    }

    private fun observe(){
        viewModel.viewStateObservable.observe(viewLifecycleOwner, {
            if(it != null)
                render(it)
        })
    }

    private fun render(viewState: ListViewModel.ViewState) {
        when(viewState){
            is ListViewModel.ViewState.Loading -> {
                if(viewState.isLoading)
                    showLoading()
                else
                    hideLoading()
            }
            is ListViewModel.ViewState.Error -> {
                hideLoading()
                Toast.makeText(context, viewState.message, Toast.LENGTH_SHORT).show()
            }
            is ListViewModel.ViewState.CountriesFetched -> {
                hideLoading()
                onCountriesReceived(viewState.data)
            }
        }
    }

    private fun onCountriesReceived(list: List<CountryModel>){
        list.sortForName()
        adapter.updateCountries(ArrayList(list))
    }

    override fun showLoading() {
        binding.includedLoadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        binding.includedLoadingView.visibility = View.GONE
    }

    override fun onClick(position: Int, item: CountryModel) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, DetailFragment.newInstance(item))
            .commitNow()
    }

}