package com.daffa.moviecatalogue.utils

import com.daffa.moviecatalogue.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.MovieResponse
import com.daffa.moviecatalogue.data.source.remote.response.TvShowResponse
import com.daffa.moviecatalogue.data.source.remote.response.model.Genre
import com.daffa.moviecatalogue.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.data.source.remote.response.model.TvShow

object DummyData {
    fun dummyMovieResponse(): MovieResponse {
        val list: List<Movie> = listOf(
            Movie(
                adult = false,
                backdrop_path = "/9yBVqNruk6Ykrwc32qrK2TIE5xw.jpg",
                genre_ids = listOf(28, 14, 12, 878),
                id = 460465,
                original_language = "en",
                original_title = "Mortal Kombat",
                overview = "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                popularity = 4340.628,
                poster_path = "/xGuOF1T3WmPsAcQEQJfnG7Ud9f8.jpg",
                release_date = "2021-04-07",
                title = "Mortal Kombat",
                video = false,
                vote_average = 7.8f,
                vote_count = 2120
            ),
            Movie(
                adult = false,
                backdrop_path = "/inJjDhCjfhh3RtrJWBmmDqeuSYC.jpg",
                genre_ids = listOf(878, 28, 18),
                id = 399566,
                original_language = "en",
                original_title = "Godzilla vs. Kong",
                overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                popularity = 2385.595,
                poster_path = "/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                release_date = "2021-03-24",
                title = "Godzilla vs. Kong",
                video = false,
                vote_average = 8.1f,
                vote_count = 5285
            )
        )
        return MovieResponse(1, (list.size - 1), 1, list)
    }

    fun dummyTvShowsResponse(): TvShowResponse {
        val list: List<TvShow> = listOf(
            TvShow(
                backdrop_path = "/b0WmHGc8LHTdGCVzxRb3IBMur57.jpg",
                first_air_date = "2021-03-19",
                genre_ids = listOf(10765, 10759, 18, 10768),
                id = 88396,
                name = "The Falcon and the Winter Soldier",
                origin_country = listOf("US"),
                original_language = "en",
                original_name = "The Falcon and the Winter Soldier",
                overview = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
                popularity = 1857.575,
                poster_path = "/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                vote_average = 7.9f,
                vote_count = 5286
            ),
            TvShow(
                backdrop_path = "/6UH52Fmau8RPsMAbQbjwN3wJSCj.jpg",
                first_air_date = "2021-03-26",
                genre_ids = listOf(16, 10759, 18, 10765),
                id = 95557,
                name = "Invincible",
                origin_country = listOf("US"),
                original_language = "en",
                original_name = "Invincible",
                overview = "Mark Grayson is a normal teenager except for the fact that his father is the most powerful superhero on the planet. Shortly after his seventeenth birthday, Mark begins to develop powers of his own and enters into his father’s tutelage.",
                popularity = 1937.807,
                poster_path = "/yDWJYRAwMNKbIYT8ZB33qy84uzO.jpg",
                vote_average = 8.9f,
                vote_count = 1283
            )
        )
        return TvShowResponse(1, (list.size - 1), 1, list)
    }

    fun dummyDetailMovieResponse(): DetailMovieResponse {
        return DetailMovieResponse(
            adult = false,
            backdrop_path = "/pcDc2WJAYGJTTvRSEIpRZwM3Ola.jpg",
            belongs_to_collection = null,
            budget = 70000000,
            genres = listOf(
                Genre(28, "Action"),
                Genre(12, "Adventure"),
                Genre(14, "Fantasy"),
                Genre(878, "Science Fiction")
            ),
            homepage = "https://www.hbomax.com/zacksnydersjusticeleague",
            id = 791373,
            imdb_id = "tt12361974",
            original_language = "en",
            original_title = "Zack Snyder's Justice League",
            overview = "Determined to ensure Superman's ultimate sacrifice was not in vain, Bruce Wayne aligns forces with Diana Prince with plans to recruit a team of metahumans to protect the world from an approaching threat of catastrophic proportions.",
            popularity = 1435.879,
            poster_path = "/tnAuB8q5vv7Ax9UAEje5Xi4BXik.jpg",
            release_date = "2021-03-18",
            revenue = 0,
            runtime = 242,
            status = "Released",
            tagline = "",
            title = "Zack Snyder's Justice League",
            video = false,
            vote_average = 8.5f,
            vote_count = 5318
        )
    }

    fun dummyDetailTvShowResponse(): DetailTvShowResponse {
        return DetailTvShowResponse(
            backdrop_path = "/suopoADq0k8YZr4dQXcU6pToj6s.jpg",
            episode_run_time = listOf(60),
            first_air_date = "2011-04-17",
            genres = listOf(
                Genre(10765, "Sci-Fi & Fantasy"),
                Genre(18, "Drama"),
                Genre(10759, "Action & Adventure")
            ),
            homepage = "http://www.hbo.com/game-of-thrones",
            id = 1399,
            in_production = false,
            languages = listOf("en"),
            last_air_date = "2019-05-19",
            name = "Game of Thrones",
            number_of_episodes = 73,
            number_of_seasons = 8,
            origin_country = listOf("US"),
            original_language = "en",
            original_name = "Game of Thrones",
            overview = "Seven noble families fight for control of the mythical land of Westeros. Friction between the houses leads to full-scale war. All while a very ancient evil awakens in the farthest north. Amidst the war, a neglected military order of misfits, the Night's Watch, is all that stands between the realms of men and icy horrors beyond.",
            popularity = 582.288,
            poster_path = "/u3bZgnGQ9T01sWNhyveQz0wH0Hl.jpg",
            status = "Ended",
            tagline = "Winter Is Coming",
            type = "Scripted",
            vote_average = 8.4f,
            vote_count = 14247
        )
    }
}