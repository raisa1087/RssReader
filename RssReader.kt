import java.io.File
import java.net.URL

fun main(args: Array<String>) {
    var input = ""
    var convert = ""
    var output = ""

    for (idx in args.indices step 2) {
        when (args[idx]) {
            "--input" -> input = args.getNextArgument(idx)
            "-i" -> input = args.getNextArgument(idx)
            "--convert" -> convert = args.getNextArgument(idx)
            "-c" -> convert = args.getNextArgument(idx)
            "--output" -> output = args.getNextArgument(idx)
            "-o" -> output = args.getNextArgument(idx)
        }
    }
    if(input.isBlank()) throw Exception("Missing input file")
    if(convert.isBlank()) throw Exception("Missing conversion command")

    RssReader(input, convert, output).convert()
}

private fun Array<String>.getNextArgument(idx: Int) = if (idx < this.size - 1) this[idx + 1] else throw Exception("Missing argument value")

// RssReader main class - processes the input, invokes conversion logic and handles the output
class RssReader(input: String, convert: String, private val output: String) {
    private var contents: ByteArray
    private val converters: List<Converter>

    init {
        contents = extractContents(input)
        converters = ConverterFactory.build(convert)
    }

    fun convert() {
        converters.forEach { contents = it.convert(contents) }

        when (output) {
            "" -> println(String(contents))
            else -> File(output).writeBytes(contents)
        }
    }

    private fun extractContents(input: String) = when (isUrl(input)) {
        true -> URL(input).readBytes()
        else -> File(input).inputStream().readAllBytes()
    }

    private fun isUrl(input: String): Boolean {
        try {
            URL(input).toURI()
        } catch (ex: Exception) {
            return false
        }
        return true
    }
}

// Conversion commands supported: cut and replace
enum class Conversion {
    CUT, REPLACE;

    fun getValue() = this.toString().toLowerCase()
}

// Factory for building Converter objects based on each conversion command
class ConverterFactory {
    companion object {
        fun build(convert: String): List<Converter> {
            return convert.split(",").map {
                when {
                    it.startsWith(Conversion.CUT.getValue()) -> CutConverter()
                    it.startsWith(Conversion.REPLACE.getValue()) -> {
                        val split = convert.substring(convert.indexOf("(/") + 2, convert.indexOf("/)")).split("/")
                        if(split.size != 2) throw Exception("Invalid replace command, please follow format: replace(/{String to replace}/{Replacement String}/)")
                        ReplaceConverter(split[0], split[1])
                    }
                    else -> throw Exception("Invalid conversion format")
                }
            }.toList()
        }
    }
}

// Converter template, all converters have their own implementation of convert function
interface Converter {
    fun convert(contents: ByteArray): ByteArray
}

// Implementation of Converter that trims texts exceeding 10 characters
class CutConverter : Converter {
    private val limit = 10
    override fun convert(contents: ByteArray): ByteArray {
        return String(contents).replace(Regex("[^ \\r\\n\\t]{$limit,}")) { it.value.substring(0, limit) }.toByteArray()
    }
}

// Implementation of Converter that replaces target string with a specified replacement string
class ReplaceConverter(private val from: String, private val to: String) : Converter {
    override fun convert(contents: ByteArray) = String(contents).replace(from, to, true).toByteArray()
}