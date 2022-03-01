package com.example.rickandmorty.network

import androidx.annotation.StringDef

class RickAndMortyApiSearchKeys {

    companion object{

        @StringDef(ALL, ALIVE, DEAD, UNKNOWN)
        @kotlin.annotation.Retention
        annotation class RickAndMortyApiSearchKeysAnnotations


        const val ALL = "All"
        const val ALIVE = "Alive"
        const val DEAD = "Dead"
        const val UNKNOWN = "unknown"

    }
}