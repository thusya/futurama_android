package com.thus.futurama.ui.character.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.thus.futurama.ui.character.CharacterViewModel

@Composable
fun DetailsScreen(charactersViewModel: CharacterViewModel) {
    charactersViewModel.characterSelected?.let { character ->
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(model = character.images.main),
                contentDescription = character.name.first,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = character.name.first ?: "",
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Gender: ${character.gender ?: ""}")
            Text(text = "Species: ${character.species ?: ""}")
            Text(text = "Home Planet: ${character.homePlanet ?: ""}")
            Text(text = "Occupation: ${character.occupation ?: ""}")
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Sayings",
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
            character.sayings?.forEach { saying ->
                Text(text = "- $saying")
            }
        }

    }
}