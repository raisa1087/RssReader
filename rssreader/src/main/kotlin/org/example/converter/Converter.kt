package org.example.converter

import org.example.Contents

// Converter template
interface Converter {
    fun convert(contents: Contents): Contents
}

// Implementation of Converter that trims texts exceeding 10 characters
class CutConverter : Converter {
    private val limit = 10
    override fun convert(contents: Contents): Contents {
        return Contents(cut(contents.getString()))
    }

    private fun cut(contents: String) =
            contents.replace(Regex("[^ \\r\\n\\t]{$limit,}")) { it.value.substring(0, limit) }
}

// Implementation of Converter that replaces target string with a specified replacement string
class ReplaceConverter(private val from: String, private val to: String) : Converter {
    override fun convert(contents: Contents) = Contents(replace(contents.getString()))

    private fun replace(contents: String) = contents.replace(from, to, true)
}