package com.thus.futurama.data.model

import junit.framework.TestCase.assertEquals
import org.junit.Test


class CharacterResponseTest {

    @Test
    fun `toCharacterInfo converts CharacterResponse to CharacterInfo correctly`() {
        //Arrange
        val characterResponse = CharacterResponse(
            name = NameResponse(first = "Philip", middle = "J.", last = "Fry"),
            images = ImageResponse(headShot = "headshot.jpg", main = "main.jpg"),
            age = "25",
            gender = "Male",
            species = "Human",
            homePlanet = "Earth",
            occupation = "Delivery Boy",
            sayings = listOf("Good news, everyone!", "Shut up and take my money!")

        )

        // Act
        val characterInfo = characterResponse.toCharacterInfo()

        // Assert
        assertEquals(0, characterInfo.id) // Since id is nullable in CharacterResponse, we set a default value of 0 in CharacterInfo
        assertEquals("Philip", characterInfo.name.first)
        assertEquals("J.", characterInfo.name.middle)
        assertEquals("Fry", characterInfo.name.last)
        assertEquals("headshot.jpg", characterInfo.images.headShot)
        assertEquals("main.jpg", characterInfo.images.main)
        assertEquals("25", characterInfo.age)
        assertEquals("Male", characterInfo.gender)
        assertEquals("Human", characterInfo.species)
        assertEquals("Earth", characterInfo.homePlanet)
        assertEquals("Delivery Boy", characterInfo.occupation)
        assertEquals(2, characterInfo.sayings.size)
        assertEquals("Good news, everyone!", characterInfo.sayings[0])
        assertEquals("Shut up and take my money!", characterInfo.sayings[1])

    }
}