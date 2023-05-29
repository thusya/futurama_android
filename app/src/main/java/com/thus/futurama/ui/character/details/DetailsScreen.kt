package com.thus.futurama.ui.character.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.thus.futurama.R
import com.thus.futurama.ui.character.CharacterViewModel
import com.thus.futurama.ui.commonscreens.ErrorScreen
import com.thus.futurama.ui.theme.spacing

@Composable
fun DetailsScreen(navController: NavController, charactersViewModel: CharacterViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.screen_name_character_detail),
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
        charactersViewModel.characterDetailSelected?.let { character ->
            val state = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(state)
                    .padding(paddingValues)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(character.images.main),
                    contentDescription = character.getFullName(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .padding(MaterialTheme.spacing.medium),
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Text(
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.medium,
                        ),
                    text = character.getFullName(),
                    style = MaterialTheme.typography.h6
                )
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = MaterialTheme.spacing.medium,
                            vertical = MaterialTheme.spacing.small,
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.medium)
                    ) {
                        Text(text = stringResource(R.string.text_gender, character.gender))
                        Text(text = stringResource(R.string.text_species, character.species))
                        Text(
                            text = stringResource(
                                R.string.text_home_planet,
                                character.homePlanet
                            )
                        )
                        Text(
                            text = stringResource(
                                R.string.text_occupation,
                                character.occupation
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Text(
                    modifier = Modifier
                        .padding(horizontal = MaterialTheme.spacing.medium),
                    text = stringResource(R.string.text_sayings),
                    style = MaterialTheme.typography.h6
                )
                Card(
                    modifier = Modifier
                        .padding(
                            horizontal = MaterialTheme.spacing.medium,
                            vertical = MaterialTheme.spacing.small
                        )
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(MaterialTheme.spacing.medium)
                    ) {
                        character.sayings.forEach { saying ->
                            Text(text = "- $saying")
                        }
                    }
                }
            }
        } ?: run {
            ErrorScreen {

            }
        }
    }
}