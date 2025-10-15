package com.example.mycityapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.MyCityAppTheme
import com.example.mycityapp.data.CategoryProvider
import com.example.mycityapp.model.Category
import com.example.mycityapp.model.Recommendation

@Composable
fun RecommendationDetailScreen(
    recommendation: Recommendation,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = recommendation.image),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                )
            }
            Text(
                text = stringResource(recommendation.description),
                modifier = Modifier.fillMaxWidth()
                    .padding(top = 8.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RecommendationDetailScreenPreview(
    modifier: Modifier = Modifier
) {
    MyCityAppTheme {
        RecommendationDetailScreen(
            recommendation = CategoryProvider.getCategoryData()[0].recommendationList[0]
        )
    }
}