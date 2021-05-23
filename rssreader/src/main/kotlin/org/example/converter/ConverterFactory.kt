package org.example.converter

// Factory for building Converter objects based on each conversion command
class ConverterFactory {
    companion object {
        fun build(command: String): List<Converter> {
            return command.split(",").map {
                when {
                    it.startsWith(Conversion.CUT.getValue()) -> CutConverter()
                    it.startsWith(Conversion.REPLACE.getValue()) -> buildReplaceConverter(command)
                    else -> throw Exception("Invalid conversion format")
                }
            }.toList()
        }

        private fun buildReplaceConverter(convert: String): ReplaceConverter {
            val args = convert.substring(convert.indexOf("(/") + 2, convert.indexOf("/)")).split("/")
            if (args.size != 2) throw Exception("Invalid replace command, please follow format: replace(/{String to replace}/{Replacement String}/)")
            return ReplaceConverter(args[0], args[1])
        }
    }
}