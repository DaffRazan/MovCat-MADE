package com.daffa.moviecatalogue.utils

import com.daffa.moviecatalogue.R
import com.daffa.moviecatalogue.data.FilmEntity

object DummyData {
    fun generateDummyMovies(): List<FilmEntity> {
        val movies = ArrayList<FilmEntity>()

        movies.add(
            FilmEntity(
            "MOVIE_001",
            "A Star Is Born",
               R.drawable.poster_a_start_is_born,
               "Seorang bintang musik country yang karirnya mulai memudar, Jackson Maine (Bradley Cooper) menemukan sebuah talenta yang sangat berbakat di dalam diri dari seorang musisi muda bernama Ally (Lady Gaga). Maine berhasil mengorbitkan Ally menjadi seorang bintang muda yang menjanjikan. Namun keduanya terlibat hubungan yang lebih jauh dari sekedar mentor dan anak didik. Seiring dengan meroketnya karir dari Ally dan dirinya, Maine mengalami dilema mengenai masalah ini.",
               "2018",
               "Drama, Percintaan, Musik",
               "75%"
           )
        )
        movies.add(
            FilmEntity(
                "MOVIE_002",
                "Ralph Breaks The Internet",
                R.drawable.poster_ralph,
                "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet — the netizens — to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube.",
                "2018",
                "Keluarga, Animasi, Komedi, Petualangan",
                "72%"
            )
        )
        movies.add(
            FilmEntity(
                "MOVIE_003",
                "Master Z",
                R.drawable.poster_master_z,
                "Following his defeat by Master Ip, Cheung Tin Chi tries to make a life with his young son in Hong Kong, waiting tables at a bar that caters to expats. But it's not long before the mix of foreigners, money, and triad leaders draw him once again to the fight.",
                "2018",
                "Aksi",
                "59%"
            )
        )
        movies.add(
            FilmEntity(
                "MOVIE_004",
                "Glass",
                R.drawable.poster_glass,
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "2019",
                "Cerita Seru, Drama, Cerita Fiksi",
                "67%"
            )
        )
        movies.add(
            FilmEntity(
                "MOVIE_005",
                "Creed II",
                R.drawable.poster_creed,
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "2018",
                "Drama",
                "69%"
            )
        )
        movies.add(
            FilmEntity(
                "MOVIE_006",
                "Serenity",
                R.drawable.poster_serenity,
                "The quiet life of Baker Dill, a fishing boat captain who lives on the isolated Plymouth Island, where he spends his days obsessed with capturing an elusive tuna while fighting his personal demons, is interrupted when someone from his past comes to him searching for help.",
                "2019",
                "Cerita Seru, Misteri, Drama",
                "54%"
            )
        )
        movies.add(
            FilmEntity(
                "MOVIE_007",
                "Hanna",
                R.drawable.poster_hanna,
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "2019",
                "Aksi & Petualangan, Drama",
                "75%"
            )
        )
        movies.add(
            FilmEntity(
                "MOVIE_008",
                "Avengers Infinity War",
                R.drawable.poster_infinity_war,
                "Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi, tujuannya adalah untuk mengumpulkan semua enam Batu Infinity, artefak kekuatan yang tak terbayangkan, dan menggunakannya untuk menimbulkan kehendak memutar pada semua realitas. Segala sesuatu yang telah diperjuangkan oleh Avengers telah berkembang hingga saat ini - nasib Bumi dan keberadaannya sendiri tidak pernah lebih pasti.",
                "2018",
                "Petualangan, Aksi, Cerita Fiksi",
                "83%"
            )
        )
        movies.add(
            FilmEntity(
                "MOVIE_009",
                "Spider-Man: Into the Spider-Verse",
                R.drawable.poster_spiderman,
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "2018",
                "Aksi, Petualangan, Animasi, Cerita Fiksi, Komedi",
                "84%"
            )
        )
        movies.add(
            FilmEntity(
                "MOVIE_010",
                "Aquaman",
                R.drawable.poster_aquaman,
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "2018",
                "Aksi, Petualangan, Fantasi",
                "69%"
            )
        )
        return movies
    }

    fun generateDummyTvShows(): List<FilmEntity> {
        val tvShows = ArrayList<FilmEntity>()

        tvShows.add(
            FilmEntity(
                "TVSHOW_001",
                "Grey Anatomy",
                R.drawable.poster_grey_anatomy,
                "Ikuti kehidupan pribadi dan profesional sekelompok dokter di Rumah Sakit Gray Sloan Memorial di Seattle.",
                "2005",
                "Drama",
                "82%"
            )
        )
        tvShows.add(
            FilmEntity(
                "TVSHOW_002",
                "The Flash",
                R.drawable.poster_flash,
                "Setelah akselerator partikel menyebabkan badai aneh, Penyelidik CSI Barry Allen disambar petir dan jatuh koma. Beberapa bulan kemudian dia terbangun dengan kekuatan kecepatan super, memberinya kemampuan untuk bergerak melalui Central City seperti malaikat penjaga yang tak terlihat. Meskipun awalnya senang dengan kekuatan barunya, Barry terkejut menemukan bahwa dia bukan satu-satunya \"manusia meta\" yang diciptakan setelah ledakan akselerator - dan tidak semua orang menggunakan kekuatan baru mereka untuk kebaikan. Barry bermitra dengan S.T.A.R. Lab dan mendedikasikan hidupnya untuk melindungi yang tidak bersalah. Untuk saat ini, hanya beberapa teman dekat dan rekan yang tahu bahwa Barry secara harfiah adalah manusia tercepat, tetapi tidak lama sebelum dunia mengetahui apa yang menjadi Barry Allen ... The Flash.",
                "2014",
                "Drama, Sci-fi & Fantasy",
                "77%"
            )
        )
        tvShows.add(
            FilmEntity(
                "TVSHOW_003",
                "Riverdale",
                R.drawable.poster_riverdale,
                "Set in the present, the series offers a bold, subversive take on Archie, Betty, Veronica and their friends, exploring the surreality of small-town life, the darkness and weirdness bubbling beneath Riverdale’s wholesome facade.",
                "2017",
                "Misteri, Drama, Kejahatan",
                "86%"
            )
        )
        tvShows.add(
            FilmEntity(
                "TVSHOW_004",
                "Doom Patrol",
                R.drawable.poster_doom_patrol,
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "2019",
                "Sci-fi & Fantasy, Komedi, Drama",
                "76%"
            )
        )
        tvShows.add(
            FilmEntity(
                "TVSHOW_005",
                "NCIS",
                R.drawable.poster_ncis,
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "2003",
                "Kejahatan, Aksi & Petualangan, Drama",
                "74%"
            )
        )
        tvShows.add(
            FilmEntity(
                "TVSHOW_006",
                "Shameless",
                R.drawable.poster_shameless,
                "Chicagoan Frank Gallagher is the proud single dad of six smart, industrious, independent kids, who without him would be... perhaps better off. When Frank's not at the bar spending what little money they have, he's passed out on the floor. But the kids have found ways to grow up in spite of him. They may not be like any family you know, but they make no apologies for being exactly who they are.",
                "2011",
                "Drama, Komedi",
                "80%"
            )
        )
        tvShows.add(
            FilmEntity(
                "TVSHOW_007",
                "Supernatural",
                R.drawable.poster_supernatural,
                "Dua bersaudara mencari ayah mereka yang hilang, pria yang melatih mereka untuk menjadi prajurit melawan kejahatan supernatural",
                "2005",
                "Drama, Misteri, Sci-fi & Fantasy",
                "82%"
            )
        )
        tvShows.add(
            FilmEntity(
                "TVSHOW_008",
                "Arrow",
                R.drawable.poster_arrow,
                "Panah adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
                "2012",
                "Kejahatan, Drama, Misteri, Aksi & Petualangan",
                "66%"
            )
        )
        tvShows.add(
            FilmEntity(
                "TVSHOW_009",
                "Supergirl",
                R.drawable.poster_supergirl,
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "2015",
                "Drama, Sci-fi & Fantasy, Aksi & Petualangan",
                "72%"
            )
        )
        tvShows.add(
            FilmEntity(
                "TVSHOW_010",
                "Naruto Shippuden",
                R.drawable.poster_naruto_shipudden,
                "Naruto Shippuuden adalah kelanjutan dari serial TV animasi asli Naruto. Kisah ini berkisah tentang Uzumaki Naruto yang lebih tua dan sedikit lebih matang dan upayanya untuk menyelamatkan temannya Uchiha Sasuke dari cengkeraman Shinobi seperti ular, Orochimaru. Setelah 2 setengah tahun, Naruto akhirnya kembali ke desanya Konoha, dan mulai mewujudkan ambisinya, meskipun itu tidak akan mudah, karena Ia telah mengumpulkan beberapa musuh (lebih berbahaya), seperti organisasi shinobi. ; Akatsuki.",
                "2007",
                "Animasi, Aksi & Petualangan, Sci-fi & Fantasy",
                "86%"
            )
        )
        return tvShows
    }

}