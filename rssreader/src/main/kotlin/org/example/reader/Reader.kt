package org.example.reader

import org.example.Contents
import java.io.File
import java.net.URL

// Reader template
interface Reader {
    val input: String
    fun read(): Contents
}

// Implementation of Reader that extracts URL contents
class URLReader(override val input: String) : Reader {
    override fun read() = Contents(URL(input).readBytes())
}

// Implementation of Reader that extracts file contents
class FileReader(override val input: String) : Reader {
    override fun read() = Contents(File(input).inputStream().readAllBytes())
}