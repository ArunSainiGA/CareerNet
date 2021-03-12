package com.asp.careernettest.model

import com.asp.domain.model.CountryModel
import java.util.*
import kotlin.Comparator


class CountryNameComparator: Comparator<CountryModel> {
    override fun compare(o1: CountryModel, o2: CountryModel): Int {
        return o1.name.compareTo(o2.name)
    }
}

fun List<CountryModel>.sortForName(){
    Collections.sort(this, CountryNameComparator())
}