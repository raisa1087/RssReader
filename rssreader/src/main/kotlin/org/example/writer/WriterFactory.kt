package org.example.writer

import java.io.File

class WriterFactory {
    companion object {
        fun build(output: String): Writer {
            if (output.isBlank()) return StreamWriter(output)
            if (isFile(output)) return FileWriter(output)
            throw Exception("Invalid output")
        }

        private fun isFile(output: String): Boolean {
            return try {
                File(output).isFile
            } catch (ex: Exception) {
                false
            }
        }
    }
}