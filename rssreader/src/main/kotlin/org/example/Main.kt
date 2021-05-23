package org.example

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
    if (input.isBlank()) throw Exception("Missing input file")
    if (convert.isBlank()) throw Exception("Missing conversion command")

    RssReader(input, convert, output).convert()
}

private fun Array<String>.getNextArgument(idx: Int) =
    if (idx < this.size - 1) this[idx + 1] else throw Exception("Missing argument value")