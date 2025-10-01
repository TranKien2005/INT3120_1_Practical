package com.example.superheroapp

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroapp.model.Hero
import com.example.superheroapp.model.HeroesRepository
import com.example.superheroapp.ui.theme.SuperHeroAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SuperHeroAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SuperHeroApp()
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuperHeroApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(com.example.superheroapp.R.string.app_name),
                            style = MaterialTheme.typography.displayLarge,
                        )
                    }
                },
            )
        },

    ) {it ->
        LazyColumn(
            contentPadding = it,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(HeroesRepository.heroes.size) { heroIndex ->
                SuperHeroCard(Modifier.padding(8.dp), hero = HeroesRepository.heroes[heroIndex])
            }
        }
    }
}

@Composable
fun SuperHeroCard(modifier: Modifier = Modifier, hero: Hero) {
    println(stringResource(hero.nameRes))
    println(stringResource(hero.descriptionRes))
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, bottom = 16.dp, end = 8.dp)
        ) {
            HeroInformation(modifier = Modifier.weight(1f), hero = hero)
            Spacer(modifier = Modifier.width(16.dp))
            SuperHeroImage(hero = hero)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SuperHeroCardPreview() {
    SuperHeroAppTheme {
        Column {
            SuperHeroCard(hero = HeroesRepository.heroes[1])
            SuperHeroCard(hero = HeroesRepository.heroes[2])
        }
    }

}

@Composable
fun SuperHeroImage(modifier: Modifier = Modifier, hero: Hero) {
    Image(
        painter = painterResource(hero.imageRes),
        contentDescription = stringResource(hero.nameRes),
        modifier = modifier
            .size(72.dp)
            .clip(MaterialTheme.shapes.small),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun HeroInformation(modifier: Modifier = Modifier, hero: Hero) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(hero.nameRes),
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = stringResource(hero.descriptionRes),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

