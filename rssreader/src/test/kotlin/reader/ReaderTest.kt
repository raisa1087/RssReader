package reader

import org.example.reader.FileReader
import org.example.reader.ReaderFactory
import org.example.reader.URLReader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.io.File

class ReaderTest {
    @Test
    fun factory_invalidInput() {
        assertThrows(Exception::class.java) { ReaderFactory.build("3456789") }
    }

    @Test
    fun factory_url() {
        assertTrue(ReaderFactory.build("https://rss.art19.com/apology-line") is URLReader)
    }

    @Test
    fun factory_file() {
        assertTrue(ReaderFactory.build("src/test/resources/input.txt") is FileReader)
    }

    @Test
    fun url() {
        val expected = File("src/test/resources/input.txt").readBytes()
        assertArrayEquals(expected, URLReader("https://rss.art19.com/apology-line").read().getBytes())
    }

    @Test
    fun file() {
        val expected = File("src/test/resources/input.txt").readBytes()
        assertArrayEquals(expected, FileReader("src/test/resources/input.txt").read().getBytes())
    }
}