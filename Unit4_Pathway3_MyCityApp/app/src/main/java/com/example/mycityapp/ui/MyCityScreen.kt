package com.example.mycityapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.compose.MyCityAppTheme
import com.example.mycityapp.R
import com.example.mycityapp.data.CategoryProvider

enum class MyCityScreen(@StringRes val title: Int) {
    Choose_Category(title = R.string.choose_category),
    Choose_Recommendation(title = R.string.choose_recommendation),
    Recommendation_Details(title = R.string.recommendation_details)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCityAppBar(
    currentScreen: MyCityScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(text= stringResource(currentScreen.title))
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
        }
    )
}

@Composable
fun MyCityApp(
    navController : NavHostController = rememberNavController()
) {
    val viewModel: MyCityViewModel = viewModel()
    val uiState = viewModel.uiState.collectAsState()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = MyCityScreen.valueOf(
        backStackEntry?.destination?.route ?: MyCityScreen.Choose_Category.name
    )

    Scaffold(
        topBar = {
            MyCityAppBar(
                currentScreen = currentScreen,
                canNavigateBack = (navController.previousBackStackEntry != null),
                navigateUp = { navController.navigateUp() }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyCityScreen.Choose_Category.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            composable(route = MyCityScreen.Choose_Category.name) {
                SelectCategoryScreen(
                    listCategory = uiState.value.categoryList,
                    onItemClick = {
                        viewModel.updateCurrentCategory(it)
                        navController.navigate(MyCityScreen.Choose_Recommendation.name)
                    }
                )
            }
            composable(route = MyCityScreen.Choose_Recommendation.name) {
                val currentCategory = uiState.value.currentCategory
                if (currentCategory != null) {
                    SelectRecommendationScreen(
                        listRecommendation = currentCategory.recommendationList,
                        onItemClick = {
                            viewModel.updateCurrentRecommendation(it)
                            navController.navigate(MyCityScreen.Recommendation_Details.name)
                        }
                    )
                }
            }
            composable(route = MyCityScreen.Recommendation_Details.name) {
                val currentRecommendation = uiState.value.currentRecommendation
                if (currentRecommendation != null) {
                    RecommendationDetailScreen(
                        recommendation = currentRecommendation
                    )
                }
            }
        }
    }

}