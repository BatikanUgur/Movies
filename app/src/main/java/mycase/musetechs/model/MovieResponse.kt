package mycase.musetechs.model

data class MovieResponse(
    val iso_639_1 : String,
    val id : Int,
    val page : Int,
    val iso_3166_1 : String,
    val total_results : Int,
    val total_pages : Int,
    val name : String,
    val public : Boolean,
    val sort_by : String,
    val description : String,
    val backdrop_path : String,
    val results : ArrayList<Movie>,
    val average_rating : Double,
    val runtime : Int,
    val poster_path : String
)