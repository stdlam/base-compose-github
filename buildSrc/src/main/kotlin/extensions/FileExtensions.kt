package extensions

import java.io.File
import java.util.*

fun File.loadGradleProperties(fileName: String): Properties {
    val properties = Properties()
    val fileProperties = File(this, fileName)

    if (fileProperties.isFile) {
        properties.load(fileProperties.inputStream())
    }
    return properties
}