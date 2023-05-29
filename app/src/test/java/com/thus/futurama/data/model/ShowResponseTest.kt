package com.thus.futurama.data.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ShowResponseTest {

    @Test
    fun `toShowInfo converts ShowResponse to ShowInfo correctly`() {
        //Arrange
        val creatorResponse1 = CreatorResponse(
            name = "Matt Groening",
            url = "https://example.com/matt_groening"
        )

        val creatorResponse2 = CreatorResponse(
            name = "David X. Cohen",
            url = "https://example.com/david_x_cohen"
        )

        val showResponse = ShowResponse(
            synopsis = "Futurama is a comedic sci-fi animated series.",
            yearsAired = "1999-2013",
            creators = listOf(creatorResponse1, creatorResponse2),
            id = 1
        )

        //Act
        val showInfo = showResponse.toShowInfo()

        // Assert
        assertEquals("Futurama is a comedic sci-fi animated series.", showInfo.synopsis)
        assertEquals("1999-2013", showInfo.yearsAired)
        assertEquals(2, showInfo.creators.size)

        assertEquals("Matt Groening", showInfo.creators[0].name)
        assertEquals("https://example.com/matt_groening", showInfo.creators[0].url)

        assertEquals("David X. Cohen", showInfo.creators[1].name)
        assertEquals("https://example.com/david_x_cohen", showInfo.creators[1].url)

        assertEquals(1, showInfo.id)
    }
}