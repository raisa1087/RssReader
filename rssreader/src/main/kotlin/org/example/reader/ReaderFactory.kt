package org.example.reader

import java.io.File
import java.net.URL

class ReaderFactory {

    companion object {
        fun build(input: String): Reader {
            if (isUrl(input)) return URLReader(input)
            if (isFile(input)) return FileReader(input)
            throw Exception("Invalid input")
        }

        private fun isFile(input: String): Boolean {
            return try {
                File(input).isFile
            } catch (ex: Exception) {
                false
            }
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
}