@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.unit6_pathway3_flightsearch

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.unit6_pathway3_flightsearch.ui.FlightSearchScreen

@Composable
fun FlightSearchApp() {
    FlightSearchScreen()
}


@Composable
fun FlightSearchTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    scrollBehavior: TopAppBarScrollBehavior? = null,
) {
    // Left-align title by wrapping inside a Box and set explicit colors using current TopAppBar API
    CenterAlignedTopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
                // pad title start so it lines up with search TextField start (16.dp)
                Text(title, color = Color.White, modifier = Modifier.padding(start = 16.dp))
            }
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1565C0), // blue 700
            titleContentColor = Color.White
        )
    )
}
