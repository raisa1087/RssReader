package org.example

import org.example.converter.ConverterFactory
import org.example.reader.ReaderFactory
import org.example.writer.WriterFactory

// org.example.RssReader main class
class RssReader(input: String, convert: String, output: String) {
    private val reader = ReaderFactory.build(input)
    private val writer = WriterFactory.build(output)
    private val converters = ConverterFactory.build(convert)

    fun convert() {
        var contents = reader.read()
        converters.forEach { contents = it.convert(contents) }
        writer.write(contents)
    }
}