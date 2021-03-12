package com.asp.careernettest.listener

interface OnItemClickListener<T> {
    fun onClick(position: Int, item: T)
}