package com.thus.futurama.ui.character.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.thus.futurama.R
import com.thus.futurama.ui.character.CharacterViewModel
import com.thus.futurama.ui.theme.spacing

@Composable
fun DetailsScreen(charactersViewModel: CharacterViewModel, navController: NavController) {

    charactersViewModel.characterDetailSelected?.let { character ->
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = stringResource(id = R.string.screen_name_character_detail),
                        style = MaterialTheme.typography.h6
                    )
                }, navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, "Back icon")
                    }
                })
            }
        ) { _ ->
            val state = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(MaterialTheme.spacing.medium)
                    .verticalScroll(state = state)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = character.images.main),
                    contentDescription = character.getFullName(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.FillHeight
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Text(
                    text = character.name.first ?: "",
                    style = MaterialTheme.typography.h6
                )
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                Text(text = "Gender: ${character.gender}")
                Text(text = "Species: ${character.species}")
                Text(text = "Home Planet: ${character.homePlanet}")
                Text(text = "Occupation: ${character.occupation}")
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                Text(
                    text = "Sayings",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                character.sayings.forEach { saying ->
                    Text(text = "- $saying")
                }
            }

        }
    }
}