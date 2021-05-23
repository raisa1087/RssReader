package org.example

class Contents {
    private val contentBytes: ByteArray
    private val contentStr: String

    constructor(content : ByteArray) {
        contentBytes = content
        contentStr = String(content)
    }

    constructor(content : String) {
        contentBytes = content.toByteArray()
        contentStr = content
    }

    fun getBytes() = contentBytes
    fun getString() = contentStr
}