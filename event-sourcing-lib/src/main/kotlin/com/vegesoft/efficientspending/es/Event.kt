package com.vegesoft.efficientspending.es

interface Event<T> {
    fun getAggregateId(): T
}