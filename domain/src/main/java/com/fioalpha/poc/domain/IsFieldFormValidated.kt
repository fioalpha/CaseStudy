package com.fioalpha.poc.domain

interface IsFieldFormValidated<in T>{
    fun isValidated(data: T): Boolean
}