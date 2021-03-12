package com.asp.careernettest.data.mapper

interface Mapper<in I,out O> {
    fun map(input: I): O
}