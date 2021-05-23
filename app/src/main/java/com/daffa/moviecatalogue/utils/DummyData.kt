package com.daffa.moviecatalogue.utils

import com.daffa.moviecatalogue.core.data.source.local.entity.MovieEntity
import com.daffa.moviecatalogue.core.data.source.local.entity.TvShowEntity
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailMovieResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.DetailTvShowResponse
import com.daffa.moviecatalogue.core.data.source.remote.response.model.Genre
import com.daffa.moviecatalogue.core.data.source.remote.response.model.Movie
import com.daffa.moviecatalogue.core.data.source.remote.response.model.TvShow

object DummyData {
    fun getMovies(): List<MovieEntity> {
        return listOf(
            MovieEntity(
                813258,
                false,
                "/c7dFSqZQYqNNJVpacpIGZe3gkLW.jpg",
                "Animation, Comedy, Fantasy",
                "Drac tries out some new monster pets to help occupy Tinkles for playtime.",
                "/dkokENeY5Ka30BFgWAqk14mbnGs.jpg",
                "2021-04-02",
                6,
                "Monster Pets: A Hotel Transylvania Short",
                7.7f,
                false
            ),
            MovieEntity(
                458576,
                false,
                "/z8TvnEVRenMSTemxYZwLGqFofgF.jpg",
                "Fantasy, Action, Adventure",
                "A portal transports Cpt. Artemis and an elite unit of soldiers to a strange world where powerful monsters rule with deadly ferocity. Faced with relentless danger, the team encounters a mysterious hunter who may be their only hope to find a way home.",
                "/1UCOF11QCw8kcqvce8LKOO6pimh.jpg",
                "2020-12-03",
                104,
                "Monster Hunter",
                7f,
                false
            ),
            MovieEntity(
                544401,
                false,
                "/uQtqiAu2bBlokqjlURVLEha6zoi.jpg",
                "Crime, Drama",
                "Cherry drifts from college dropout to army medic in Iraq - anchored only by his true love, Emily. But after returning from the war with PTSD, his life spirals into drugs and crime as he struggles to find his place in the world.",
                "/pwDvkDyaHEU9V7cApQhbcSJMG1w.jpg",
                "2021-02-26",
                140,
                "Cherry",
                7.5f,
                false
            )
        )
    }

    fun getTvShows(): List<TvShowEntity> {
        return listOf(
            TvShowEntity(
                93484,
                "/braALW3UODI3aUV3wfrXpvvVzBl.jpg",
                "Sci-Fi & Fantasy, Action & Adventure, Drama",
                "When the world's first generation of superheroes received their powers in the 1930s become the revered elder guard in the present, their superpowered children struggle to live up to the legendary feats of their parents.",
                "/9yxep7oJdkj3Pla9TD9gKflRApY.jpg",
                "2021-05-07",
                "8 Episodes | 1 Season(s)",
                "Jupiter's Legacy",
                7.5f,
                false
            ),
            TvShowEntity(
                71712,
                "/vooNH1WxdhedzGsLnITpged75wJ.jpg",
                "Drama",
                "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives",
                "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
                "2017-09-25",
                "76 Episodes | 4 Season(s)",
                "The Good Doctor",
                8.6f,
                false
            ),
            TvShowEntity(
                105971,
                "/sjxtIUCWR74yPPcZFfTsToepfWm.jpg",
                "Sci-Fi & Fantasy, Action & Adventure, Animation",
                "Follow the elite and experimental Clones of the Bad Batch as they find their way in a rapidly changing galaxy in the aftermath of the Clone Wars.",
                "/WjQmEWFrOf98nT5aEfUfVYz9N2.jpg",
                "2021-05-04",
                "16 Episodes | 1 Season(s)",
                "The Bad Batch",
                9f,
                false
            )
        )
    }

    fun getDetailMovie(): MovieEntity {
        return MovieEntity(
            804435,
            false,
            "/mYM8x2Atv4MaLulaV0KVJWI1Djv.jpg",
            "Action, Crime, Thriller",
            "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
            "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
            "2021-04-16",
            96,
            "Vanquish",
            6.3f,
            false
        )
    }

    fun getDetailTvShow(): TvShowEntity {
        return TvShowEntity(
            85271,
            "/1i1N0AVRb54H6ZFPDTwbo9MLxSF.jpg",
            "Sci-Fi & Fantasy, Mystery, Drama",
            "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
            "2021-01-15",
            "9 Episodes | 1 Season(s)",
            "WandaVision",
            8.4f,
            false
        )
    }


    //remote
    fun getRemoteMovies(): List<Movie> {
        return listOf(
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
            ),
            Movie(
                adult = false,
                backdrop_path = "/fPGeS6jgdLovQAKunNHX8l0avCy.jpg",
                genre_ids = listOf(
                    28,
                    12,
                    53,
                    10752
                ),
                id = 567189,
                original_language = "en",
                original_title = "Tom Clancy's Without Remorse",
                overview = "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                popularity = 4006.705,
                poster_path = "/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                release_date = "2021-04-29",
                title = "Tom Clancy's Without Remorse",
                video = false,
                vote_average = 7.3f,
                vote_count = 856
            )
        )
    }

    fun getRemoteTvShows(): List<TvShow> {
        return listOf(
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
            ),
            TvShow(
                backdrop_path = "/edmk8xjGBsYVIf4QtLY9WMaMcXZ.jpg",
                first_air_date = "2005-03-27",
                genre_ids = listOf(18),
                id = 1416,
                name = "Grey's Anatomy",
                origin_country = listOf("US"),
                original_language = "en",
                original_name = "Grey's Anatomy",
                overview = "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                popularity = 745.316,
                poster_path = "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
                vote_average = 8.2f,
                vote_count = 6042
            )
        )

    }

    fun getRemoteDetailMovie(): DetailMovieResponse {
        return DetailMovieResponse(
            adult = false,
            backdrop_path = "/mYM8x2Atv4MaLulaV0KVJWI1Djv.jpg",
            budget = 0,
            genres = listOf(
                Genre(28, "Action"),
                Genre(80, "Crime"),
                Genre(53, "Thriller")
            ),
            homepage = "https://www.lionsgate.com/movies/vanquish",
            id = 804435,
            imdb_id = "tt5932368",
            original_language = "en",
            original_title = "Vanquish",
            overview = "Victoria is a young mother trying to put her dark past as a Russian drug courier behind her, but retired cop Damon forces Victoria to do his bidding by holding her daughter hostage. Now, Victoria must use guns, guts and a motorcycle to take out a series of violent gangsters—or she may never see her child again.",
            popularity = 1346.949,
            poster_path = "/AoWY1gkcNzabh229Icboa1Ff0BM.jpg",
            release_date = "2021-04-16",
            revenue = 0,
            runtime = 96,
            status = "Released",
            tagline = "She's got one night to save her life.",
            title = "Vanquish",
            video = false,
            vote_average = 6.3f,
            vote_count = 82
        )
    }

    fun getRemoteDetailTvShow(): DetailTvShowResponse {
        return DetailTvShowResponse(
            backdrop_path = "/1i1N0AVRb54H6ZFPDTwbo9MLxSF.jpg",
            episode_run_time = listOf(
                36,
                30
            ),
            first_air_date = "2021-01-15",
            genres = listOf(
                Genre(10765, "Sci-Fi & Fantasy"),
                Genre(9648, "Mystery"),
                Genre(18, "Drama")
            ),
            homepage = "https://www.disneyplus.com/series/wandavision/4SrN28ZjDLwH",
            id = 85271,
            in_production = false,
            languages = listOf("en"),
            last_air_date = "2021-03-05",
            name = "WandaVision",
            number_of_episodes = 9,
            number_of_seasons = 1,
            origin_country = listOf("US"),
            original_language = "en",
            original_name = "WandaVision",
            overview = "Wanda Maximoff and Vision—two super-powered beings living idealized suburban lives—begin to suspect that everything is not as it seems.",
            popularity = 432.02,
            poster_path = "/glKDfE6btIRcVB5zrjspRIs4r52.jpg",
            status = "Ended",
            tagline = "Experience a new vision of reality.",
            type = "Miniseries",
            vote_average = 8.4f,
            vote_count = 8430
        )
    }
}