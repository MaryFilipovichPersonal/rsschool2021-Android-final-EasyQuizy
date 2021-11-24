package com.rsshool2021.android.finaltask.easyquizy.data.common.mappers

interface Mapper<F, T> {

    fun map(value: F): T
}