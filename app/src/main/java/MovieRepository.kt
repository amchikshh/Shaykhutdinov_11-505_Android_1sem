package com.example.android_1sem

object MovieRepository {

    val movies = listOf(
        Movie(
            id = 1,
            title = "Interstellar",
            posterUrl = "https://picsum.photos/seed/interstellar/500/750",
            description = "Команда исследователей отправляется через червоточину в другую галактику, чтобы найти новый дом для человечества."
        ),
        Movie(
            id = 2,
            title = "The Shawshank Redemption",
            posterUrl = "https://picsum.photos/seed/shawshank/500/750",
            description = "Банкир Энди Дюфрейн осуждён за убийство, которого не совершал, и попадает в тюрьму Шоушенк, где учится выживать и надеяться."
        ),
        Movie(
            id = 3,
            title = "The Green Mile",
            posterUrl = "https://picsum.photos/seed/greenmile/500/750",
            description = "Надзиратель Пол Эджкомб встречает в тюрьме необычного заключённого Джона Коффи, обладающего даром исцеления."
        ),
        Movie(
            id = 4,
            title = "The Gentleman",
            posterUrl = "https://picsum.photos/seed/gentleman/500/750",
            description = "Американский эмигрант в Лондоне строит империю марихуаны, попутно разбираясь с опасными конкурентами."
        ),
        Movie(
            id = 5,
            title = "The Lord of The Rings",
            posterUrl = "https://picsum.photos/seed/lotr/500/750",
            description = "Хоббит Фродо получает задание уничтожить Кольцо Всевластья, чтобы спасти Средиземье от Тёмного Властелина."
        ),
        Movie(
            id = 6,
            title = "Fight Club",
            posterUrl = "https://picsum.photos/seed/fightclub/500/750",
            description = "Офисный работник, страдающий бессонницей, создаёт подпольный бойцовский клуб вместе с харизматичным Тайлером Дёрденом."
        ),
        Movie(
            id = 7,
            title = "Shutter Island",
            posterUrl = "https://picsum.photos/seed/shutterisland/500/750",
            description = "Маршал США расследует исчезновение пациентки в психиатрической лечебнице на отдалённом острове."
        ),
        Movie(
            id = 8,
            title = "Intouchables",
            posterUrl = "https://picsum.photos/seed/intouchables/500/750",
            description = "Парализованный аристократ нанимает молодого иммигранта из пригорода Парижа в качестве сиделки."
        ),
        Movie(
            id = 9,
            title = "Forrest Gump",
            posterUrl = "https://picsum.photos/seed/forrestgump/500/750",
            description = "Простодушный Форрест Гамп проходит через ключевые события XX века, не переставая любить свою Дженни."
        ),
        Movie(
            id = 10,
            title = "Terminator 2",
            posterUrl = "https://picsum.photos/seed/terminator2/500/750",
            description = "Терминатор T-800 возвращается из будущего, чтобы защитить юного Джона Коннора от нового убийцы T-1000."
        )
    )

    fun getById(id: Int): Movie? = movies.find { it.id == id }
}