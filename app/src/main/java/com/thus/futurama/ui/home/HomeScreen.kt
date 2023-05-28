package com.thus.futurama.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.thus.futurama.R
import com.thus.futurama.data.model.CreatorResponse
import com.thus.futurama.data.model.HomeScreenResponse
import com.thus.futurama.ui.commonscreens.EmptyScreen
import com.thus.futurama.ui.commonscreens.ErrorScreen
import com.thus.futurama.ui.commonscreens.LoadingScreen
import com.thus.futurama.ui.theme.FuturamaAppTheme

@Composable
fun HomeScreenNormal(
    homeScreenResponse: HomeScreenResponse,
    onCharactersClicked: () -> Unit,
    onQuizClicked: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.text_synopsis),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = homeScreenResponse.synopsis.orEmpty(),
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = stringResource(id = R.string.text_years_aired),
            style = MaterialTheme.typography.h6
        )

        Text(
            text = homeScreenResponse.yearsAired.orEmpty(),
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            text = stringResource(id = R.string.text_characters),
            style = MaterialTheme.typography.h6
        )
        homeScreenResponse.creators?.forEach { creater ->
            Text(
                text = creater.name.orEmpty(),
                style = MaterialTheme.typography.body1
            )
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Button(onClick = onCharactersClicked) {
            Text(text = stringResource(id = R.string.text_characters))
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Button(onClick = onQuizClicked) {
            Text(text = stringResource(id = R.string.text_quiz))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FuturamaAppTheme {
        HomeScreenNormal(
            HomeScreenResponse(
                synopsis = "synopsis",
                yearsAired = "yearsAired",
                creators = listOf(
                    CreatorResponse(
                        name = "name",
                        url = "url"
                    )
                ),
                id = 0
            ), onCharactersClicked = {}, onQuizClicked = {}
        )
    }
}

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {

    when (val state = homeViewModel.homeState.value) {
        is HomeState.Loading -> {
            LoadingScreen()
        }

        is HomeState.Empty -> {
            EmptyScreen()
        }

        is HomeState.Normal -> {
            HomeScreenNormal(state.homeResponse.first(), {}, {})
        }

        is HomeState.Error -> {
            ErrorScreen {
                homeViewModel.retry()
            }
        }
    }

}