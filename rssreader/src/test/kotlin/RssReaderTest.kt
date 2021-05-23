import org.example.main
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream

internal class RssReaderTest {

    @Test
    fun url_cut_stream() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main(
            arrayOf(
                "--input", "https://rss.art19.com/apology-line",
                "--convert", "cut",
                "--output", ""
            )
        )
        val expected = File("src/test/resources/cut.txt").readBytes()
        assertArrayEquals(expected, outContent.toByteArray())

        System.setOut(System.out);
    }

    @Test
    fun url_replace_file() {
        main(
            arrayOf(
                "-i", "https://rss.art19.com/apology-line",
                "-c", "replace(/The Apology Line/REPLACEMENT TEXT/)",
                "-o", "out/url_replace_out.txt"
            )
        )
        assertArrayEquals(
            File("src/test/resources/replace.txt").readBytes(),
            File("out/url_replace_out.txt").readBytes()
        )
    }

    @Test
    fun url_cutReplace_stream() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main(
            arrayOf(
                "-i", "https://rss.art19.com/apology-line",
                "-c", "cut,replace(/The Apology Line/REPLACEMENT TEXT/)"
            )
        )
        val expected = File("src/test/resources/cut_replace.txt").readBytes()
        assertArrayEquals(expected, outContent.toByteArray())

        System.setOut(System.out);
    }

    @Test
    fun url_replaceCut_file() {
        main(
            arrayOf(
                "--input", "https://rss.art19.com/apology-line",
                "--convert", "replace(/The Apology Line/REPLACEMENT TEXT/),cut",
                "--output", "out/url_replacecut_out.txt"
            )
        )
        assertArrayEquals(
            File("src/test/resources/replace_cut.txt").readBytes(),
            File("out/url_replacecut_out.txt").readBytes()
        )
    }

    @Test
    fun file_cut_file() {
        main(
            arrayOf(
                "-i", "src/test/resources/input.txt",
                "-c", "cut", "-o", "out/file_cut_out.txt"
            )
        )
        assertArrayEquals(File("src/test/resources/cut.txt").readBytes(), File("out/file_cut_out.txt").readBytes())
    }

    @Test
    fun file_replace_stream() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main(
            arrayOf(
                "--input", "src/test/resources/input.txt",
                "--convert", "replace(/The Apology Line/REPLACEMENT TEXT/)"
            )
        )
        val expected = File("src/test/resources/replace.txt").readBytes()
        assertArrayEquals(expected, outContent.toByteArray())

        System.setOut(System.out);
    }

    @Test
    fun file_cutReplace_file() {
        main(
            arrayOf(
                "--input", "src/test/resources/input.txt",
                "--convert", "cut,replace(/The Apology Line/REPLACEMENT TEXT/)",
                "--output", "out/file_cutreplace_out.txt"
            )
        )
        assertArrayEquals(
            File("src/test/resources/cut_replace.txt").readBytes(),
            File("out/file_cutreplace_out.txt").readBytes()
        )
    }

    @Test
    fun file_replaceCut_stream() {
        val outContent = ByteArrayOutputStream()
        System.setOut(PrintStream(outContent))

        main(
            arrayOf(
                "-i", "src/test/resources/input.txt",
                "-c", "replace(/The Apology Line/REPLACEMENT TEXT/),cut",
                "-o", ""
            )
        )
        val expected = File("src/test/resources/replace_cut.txt").readBytes()
        assertArrayEquals(expected, outContent.toByteArray())

        System.setOut(System.out);
    }
}