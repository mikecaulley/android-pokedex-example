package com.example.pokedex

import org.rekotlin.Store

val appStore = Store(
    reducer = ::appReducer,
    state = null
)