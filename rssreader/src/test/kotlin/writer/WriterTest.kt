package writer

import org.example.Contents
import org.example.writer.FileWriter
import org.example.writer.StreamWriter
import org.example.writer.WriterFactory
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream


internal class WriterTest {

    @Test
    fun factory_invalidOutput() {
        Assertions.assertThrows(Exception::class.java) { WriterFactory.build("3456789") }
    }

    @Test
    fun factory_stream() {
        Assertions.assertTrue(WriterFactory.build("") is StreamWriter)
    }

    @Test
    fun factory_file() {
        Assertions.assertTrue(WriterFactory.build("src/test/resources/output.txt") is FileWriter)
    }

    @Test
    fun stream() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        val expected = File("src/test/resources/input.txt").readBytes()
        StreamWriter("").write(Contents(expected))

        Assertions.assertArrayEquals(expected, outContent.toByteArray())
        System.setOut(System.out);
    }

    @Test
    fun file() {
        val expected = File("src/test/resources/input.txt").readBytes()
        FileWriter("src/test/resources/output.txt").write(Contents(expected))
        Assertions.assertArrayEquals(expected, File("src/test/resources/output.txt").readBytes())
    }
}