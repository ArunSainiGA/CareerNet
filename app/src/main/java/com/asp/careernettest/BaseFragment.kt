package com.asp.careernettest

import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {

    abstract fun showLoading()
    abstract fun hideLoading()

}