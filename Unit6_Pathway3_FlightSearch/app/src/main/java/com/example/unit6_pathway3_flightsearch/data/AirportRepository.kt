package com.example.unit6_pathway3_flightsearch.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {

    fun getAirportSuggestion(text: String): Flow<List<Airport>>

    fun getAllAirportExcept(code: String): Flow<List<Airport>>

    fun getAirportByCode(code: String): Flow<Airport>
}