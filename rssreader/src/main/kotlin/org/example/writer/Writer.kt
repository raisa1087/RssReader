package org.example.writer

import org.example.Contents
import java.io.File

// Writer template
interface Writer {
    val output: String
    fun write(contents: Contents)
}

// Implementation of Writer that outputs to stream
class StreamWriter(override val output: String) : Writer {
    override fun write(contents: Contents) {
        print(contents.getString())
    }
}

// Implementation of Writer that outputs to file
class FileWriter(override val output: String) : Writer {
    private val file = File(output)
    override fun write(contents: Contents) {
        file.writeBytes(contents.getBytes())
    }
}