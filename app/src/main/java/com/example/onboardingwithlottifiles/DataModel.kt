package com.example.onboardingwithlottifiles

sealed class DataModel(val lottiID: Int, val title: String, val subTitle: String) {
    object First : DataModel(
        lottiID = R.raw.flying_banda,
        title = "Flying Panda",
        subTitle = "Fly Panda is an American pop group formed in 2012 by rapper Lukas Cash and"
    )

    object Second : DataModel(
        lottiID = R.raw.mahrome,
        title = "Mushroom",
        subTitle = "A mushroom or toadstool is the fleshy, spore-bearing fruiting body"
    )

    object Third : DataModel(
        lottiID = R.raw.sleeping_banda,
        title = "Sleeping Panda",
        subTitle = "Rather than sleeping all night long Rather than sleeping all night long"
    )

    object Forth : DataModel(
        lottiID = R.raw.btato,
        title = "Potato Chips",
        subTitle = "A potato chip is a thin slice of potato that has been either deep fried"
    )

    object Fifth : DataModel(
        lottiID = R.raw.kmm,
        title = "Kotlin Multiplatform Mobile",
        subTitle = "Imagine You Can Develop IOS App Using Kotlin And Jetpack Compose Coming Soon!!"
    )
}