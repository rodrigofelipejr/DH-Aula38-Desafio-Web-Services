package br.com.house.digital.model

data class Comics(val data: Data)

data class Data(val offset: Int, val results: ArrayList<Comic>)

data class Comic(val id: Int, val title: String, val issueNumber: Int, val thumbnail: Thumbnail)

class Thumbnail(val path: String, val extension: String) {
    fun getUrl() = "$path.$extension".replace("http", "https")
}