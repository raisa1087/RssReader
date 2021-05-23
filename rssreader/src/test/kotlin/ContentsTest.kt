import org.example.Contents
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ContentsTest {
    private val contentString = """
            <?xml version="1.0" encoding="UTF-8"?>
            <rss version="2.0" xmlns:itunes="http://www.itunes.com/dtds/podcast-1.0.dtd" xmlns:atom="http://www.w3.org/2005/Atom" xmlns:content="http://purl.org/rss/1.0/modules/content/" xmlns:art19="https://art19.com/xmlns/rss-extensions/1.0" xmlns:googleplay="http://www.google.com/schemas/play-podcasts/1.0/">
              <channel>
                <title>The Apology Line</title>
                <description>
                  <![CDATA[<p>If you could call a number and say you’re sorry, and no one would know…what would you apologize for? For fifteen years, you could call a number in Manhattan and do just that. This is the story of the line, and the man at the other end who became consumed by his own creation. He was known as “Mr. Apology.” As thousands of callers flooded the line, confessing to everything from shoplifting to infidelity, drug dealing to murder, Mr. Apology realized he couldn’t just listen. He had to do something, even if it meant risking everything. From Wondery the makers of Dr. Death and The Shrink Next Door, comes a story about empathy, deception and obsession. Marissa Bridge, who knew Mr. Apology better than anyone, hosts this six episode series.</p>]]>
                </description>
              </channel>
            </rss>
        """.trimIndent()
    private val contentBytes = contentString.toByteArray()

    @Test
    fun testByteConstructor() {
        val obj = Contents(contentBytes)
        assertArrayEquals(contentBytes, obj.getBytes())
        assertEquals(contentString, obj.getString())
    }

    @Test
    fun testStringConstructor() {
        val obj = Contents(contentString)
        assertArrayEquals(contentBytes, obj.getBytes())
        assertEquals(contentString, obj.getString())
    }
}