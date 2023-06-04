package com.thus.futurama.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    paddingValues: PaddingValues,
    showInfoList: List<ShowInfo>,
    onCharactersClicked: () -> Unit,
    onQuizClicked: () -> Unit
) {
    val state = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .weight(1f, true)
                .verticalScroll(state)
        ) {
            showInfoList.forEachIndexed { index, showInfo ->
                if (index > 0) {
                    Divider(
                        modifier = Modifier.padding(
                            horizontal = MaterialTheme.spacing.medium,
                            vertical = MaterialTheme.spacing.small,
                        ),
                        color = Color.LightGray,
                        thickness = 2.dp
                    )
                }
                ShowInfoCard(showInfo = showInfo)
            }
        }
        ButtonBottomBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            onCharactersClicked = onCharactersClicked,
            onQuizClicked = onQuizClicked
        )
    }
}

@Composable
fun ShowInfoCard(showInfo: ShowInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = stringResource(R.string.text_synopsis),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = showInfo.synopsis,
            style = MaterialTheme.typography.body2
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Text(
            text = stringResource(R.string.text_years_aired),
            style = MaterialTheme.typography.h6
        )
        Text(
            text = showInfo.yearsAired,
            style = MaterialTheme.typography.body1
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Text(
            text = stringResource(R.string.text_creators),
            style = MaterialTheme.typography.h6
        )
        showInfo.creators.forEach { creator ->
            Text(
                text = creator.name,
                style = MaterialTheme.typography.body1
            )
        }
    }
}

@Composable
fun ButtonBottomBar(
    modifier: Modifier,
    onCharactersClicked: () -> Unit,
    onQuizClicked: () -> Unit
) {
    Row(modifier = modifier) {
        Button(modifier = Modifier.weight(1.0f), onClick = onCharactersClicked) {
            Text(text = stringResource(R.string.screen_name_characters))
        }

        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

        Button(modifier = Modifier.weight(1.0f), onClick = onQuizClicked) {
            Text(text = stringResource(R.string.screen_name_quiz))
        }
    }
}

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel) {
    Scaffold(
        topBar = {
            TopAppBar() {
                Text(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium),
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    ) { paddingValues ->
        when (val state = homeViewModel.homeState.value) {
            is HomeState.Loading -> {
                LoadingScreen()
            }

            is HomeState.Empty -> {
                EmptyScreen()
            }

            is HomeState.Normal -> {
                HomeScreenNormal(
                    paddingValues = paddingValues,
                    showInfoList = state.showInfoList,
                    onCharactersClicked = {
                        navController.navigate(NavigationScreen.CHARACTER_SCREEN.name)
                    },
                    onQuizClicked = {
                        navController.navigate(NavigationScreen.QUIZ_SCREEN.name)
                    }
                )
            }

            is HomeState.Error -> {
                ErrorScreen {
                    homeViewModel.refresh()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FuturamaAppTheme {
        HomeScreenNormal(
            paddingValues = PaddingValues(),
            listOf(
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
                )
            ), onCharactersClicked = {

            }, onQuizClicked = {

            }
        )
    }
}