package com.thus.futurama.ui.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun CharactersScreen(navController: NavController, viewModel: CharacterViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.text_characters),
                    style = MaterialTheme.typography.h6
                )
            }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, "back icon")
                }
            })
        }
    )
    { _ ->
        when (val state = viewModel.charactersState.value) {
            is CharactersState.Loading -> {
                LoadingScreen()
            }

            is CharactersState.Empty -> {
                EmptyScreen()
            }

            is CharactersState.Normal -> {
                CharacterList(characterInfoList = state.characterInfoList) { character ->
                    viewModel.characterDetailSelected = character
                    navController.navigate(NavigationScreen.CHARACTER_DETAILS_SCREEN.name)
                }
            }

            is CharactersState.Error -> {
                ErrorScreen {
                    viewModel.refresh()
                }
            }
        }
    }
}

@Composable
fun CharacterList(
    characterInfoList: List<CharacterInfo>,
    onItemClick: (CharacterInfo) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(characterInfoList) { character ->
            CharacterListItem(characterInfo = character) {
                onItemClick(character)
            }
        }
    }
}

@Composable
fun CharacterListItem(characterInfo: CharacterInfo, onItemClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable(onClick = onItemClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = characterInfo.images.main),
            contentDescription = characterInfo.getFullName(),
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = characterInfo.name.first,
            style = MaterialTheme.typography.body1
        )
    }

}