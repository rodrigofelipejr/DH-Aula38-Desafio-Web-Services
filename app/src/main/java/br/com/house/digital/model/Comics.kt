package br.com.house.digital.model

data class Comics(val data: Data)

data class Data(val offset: Int, val results: ArrayList<Comic>)

data class Comic(
    val id: Int,
    val title: String,
    val issueNumber: Int,
    val description: String,
    val thumbnail: Thumbnail,
    val pageCount: Int,
    val dates: ArrayList<Date>,
    val prices: ArrayList<Prices>,
    val images: ArrayList<Images>
)

data class Date(val type: String, val date: String)

data class Prices(val type: String, val price: Double)

class Images(val path: String, val extension: String) {
    fun getUrl() = "$path.$extension".replace("http", "https")
}

class Thumbnail(val path: String, val extension: String) {
    fun getUrl() = "$path.$extension".replace("http", "https")
}
