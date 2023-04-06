package solid.icon.english

class ValidatingTest {

    fun validateKey2(s: String): String {
        return s.map { if (it in ".#\$[]/") "_" else it }.joinToString("")
    }

    fun validateKey1(s: String): String {
        val arr = s.split("").toTypedArray()
        var key = ""
        for (char in arr) {
            key += if (char == "." || char == "#" || char == "$" || char == "[" || char == "]" || char == "/") {
                "_"
            } else {
                char
            }
        }
        return key
    }
}