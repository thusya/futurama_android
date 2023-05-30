package com.thus.futurama.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.thus.futurama.R
import com.thus.futurama.domain.model.CharacterInfo
import com.thus.futurama.ui.commonscreens.EmptyScreen
import com.thus.futurama.ui.commonscreens.ErrorScreen
import com.thus.futurama.ui.commonscreens.LoadingScreen
import com.thus.futurama.ui.navigation.NavigationScreen
import com.thus.futurama.ui.theme.spacing

@Composable
fun CharactersScreen(navController: NavController, charactersViewModel: CharacterViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.screen_name_characters),
                        style = MaterialTheme.typography.h6
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Filled.ArrowBack, "back icon")
                    }
                }
            )
        }
    ) { paddingValues ->
        when (val state = charactersViewModel.charactersState.value) {
            is CharactersState.Loading -> {
                LoadingScreen()
            }

            is CharactersState.Empty -> {
                EmptyScreen()
            }

            is CharactersState.Error -> {
                ErrorScreen {
                    charactersViewModel.refresh()
                }
            }

            is CharactersState.Normal -> {
                CharacterList(
                    paddingValues = paddingValues,
                    characters = state.characterInfoList
                ) { character ->
                    charactersViewModel.characterDetailSelected = character
                    navController.navigate(NavigationScreen.CHARACTER_DETAILS_SCREEN.name)
                }
            }
        }
    }
}

@Composable
fun CharacterList(
    paddingValues: PaddingValues,
    characters: List<CharacterInfo>,
    onItemClick: (CharacterInfo) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        items(characters) { characterInfo ->
            CharacterListItem(characterInfo = characterInfo) {
                onItemClick(characterInfo)
            }
        }
    }
}

@Composable
fun CharacterListItem(characterInfo: CharacterInfo, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small,
            )
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .clickable(onClick = onItemClick)
                .padding(MaterialTheme.spacing.small)
        ) {
            Image(
                painter = rememberAsyncImagePainter(characterInfo.images.main),
                contentDescription = "Character Image",
                modifier = Modifier
                    .size(64.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = characterInfo.getFullName(),
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                Text(
                    text = "Age: ${characterInfo.age}",
                    style = MaterialTheme.typography.body1
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                Text(
                    text = "Gender: ${characterInfo.gender}",
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}