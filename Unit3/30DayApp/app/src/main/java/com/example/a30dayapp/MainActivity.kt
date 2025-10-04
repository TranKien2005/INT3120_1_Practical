package com.example.a30dayapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30dayapp.model.Day
import com.example.compose._30DayApp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DayApp {
                DayCardApp()
            }
        }
    }
}

@Composable
fun DayCard(modifier: Modifier = Modifier, day: Day) {
    var expended by remember { mutableStateOf(false)}
    Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(2.dp),
        onClick = { expended = !expended }
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Day " + day.dayID,
                style = typography.labelMedium
            )
            Text(
                text = day.title,
                style = typography.labelLarge
            )
            Image(
                painter = painterResource(id = day.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .height(144.dp),
                contentScale = ContentScale.Crop
            )
            if (expended) {
                Text(
                    text = day.description,
                    style = typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayCardPreview(day: Day = DaysRepository.dayList[1]) {
    Log.i("DayCardPreview", "Day: ${day.dayID}")
    _30DayApp {
        DayCard(
            modifier = Modifier.padding(16.dp),
            day = day
        )
    }
}

@Composable
fun DayCardList(modifier: Modifier = Modifier, dayList: List<Day>) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(dayList.size) { it ->
            DayCard(
                modifier = Modifier
                    .padding(8.dp),
                day = dayList[it]
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayCardListPreview() {
    _30DayApp {
        Scaffold (
            modifier = Modifier.statusBarsPadding()
                .fillMaxSize(),
        ){
            Box (
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                DayCardList(
                    modifier = Modifier
                        .padding(it),
                    dayList = DaysRepository.dayList
                )
            }
        }
    }
}

@Composable
fun DayCardApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize()
        .statusBarsPadding(),
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "30 Days of Wellness",
                    style = typography.headlineMedium
                )
            }
        },
        content = {
        it ->
            DayCardList(
                modifier = Modifier
                    .padding(it),
                dayList = DaysRepository.dayList
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DayCardAppPreview() {
    _30DayApp {
        DayCardApp()
    }
}

