package converter

import org.example.Contents
import org.example.converter.ConverterFactory
import org.example.converter.CutConverter
import org.example.converter.ReplaceConverter
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

internal class ConverterTest {
    @Test
    fun factory_invalidConversion() {
        assertThrows(Exception::class.java) { ConverterFactory.build("") }
    }

    @Test
    fun factory_invalidReplace() {
        assertThrows(Exception::class.java) { ConverterFactory.build("replace(/abc/)") }
    }

    @Test
    fun factory_cut() {
        assertTrue(ConverterFactory.build("cut")[0] is CutConverter)
    }

    @Test
    fun factory_replace() {
        assertTrue(ConverterFactory.build("replace(/abc/xxx/)")[0] is ReplaceConverter)
    }

    @Test
    fun cut() {
        val input = Contents(File("src/test/resources/input.txt").readBytes())
        val expected = File("src/test/resources/cut.txt").readBytes()
        assertArrayEquals(expected, CutConverter().convert(input).getBytes())
    }

    @Test
    fun replace() {
        val input = Contents(File("src/test/resources/input.txt").readBytes())
        val expected = File("src/test/resources/replace.txt").readBytes()
        assertArrayEquals(expected, ReplaceConverter("The Apology Line", "REPLACEMENT TEXT").convert(input).getBytes())
    }
}