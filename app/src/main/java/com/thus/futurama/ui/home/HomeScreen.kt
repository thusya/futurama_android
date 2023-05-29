package com.thus.futurama.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.thus.futurama.R
import com.thus.futurama.domain.model.CreatorInfo
import com.thus.futurama.domain.model.ShowInfo
import com.thus.futurama.ui.commonscreens.EmptyScreen
import com.thus.futurama.ui.commonscreens.ErrorScreen
import com.thus.futurama.ui.commonscreens.LoadingScreen
import com.thus.futurama.ui.navigation.NavigationScreen
import com.thus.futurama.ui.theme.FuturamaAppTheme
import com.thus.futurama.ui.theme.spacing

@Composable
fun HomeScreenNormal(
    showInfo: ShowInfo,
    onCharactersClicked: () -> Unit,
    onQuizClicked: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.h6
            )
        }
    }) { _ ->
        val state = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.spacing.medium)
                .verticalScroll(state = state)
        ) {
            Text(
                text = stringResource(id = R.string.text_synopsis),
                style = MaterialTheme.typography.h6
            )
            Text(
                text = showInfo.synopsis,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.medium))

            Text(
                text = stringResource(id = R.string.text_years_aired),
                style = MaterialTheme.typography.h6
            )

            Text(
                text = showInfo.yearsAired,
                style = MaterialTheme.typography.body2
            )

            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.medium))

            Text(
                text = stringResource(id = R.string.text_characters),
                style = MaterialTheme.typography.h6
            )
            showInfo.creators.forEach { creater ->
                Text(
                    text = creater.name,
                    style = MaterialTheme.typography.body1
                )
            }

            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.medium))

            Button(onClick = onCharactersClicked) {
                Text(text = stringResource(id = R.string.text_characters))
            }

            Spacer(modifier = Modifier.padding(MaterialTheme.spacing.small))

            Button(onClick = onQuizClicked) {
                Text(text = stringResource(id = R.string.text_quiz))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FuturamaAppTheme {
        HomeScreenNormal(
            ShowInfo(
                synopsis = "synopsis",
                yearsAired = "yearsAired",
                creators = listOf(
                    CreatorInfo(
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
            HomeScreenNormal(
                showInfo = state.showInfoList.first(),
                onCharactersClicked = {
                    navController.navigate(NavigationScreen.CHARACTER_SCREEN.name)
                }, onQuizClicked = {
                    navController.navigate(NavigationScreen.QUIZ_SCREEN.name)
                })
        }

        is HomeState.Error -> {
            ErrorScreen {
                homeViewModel.refresh()
            }
        }
    }

}