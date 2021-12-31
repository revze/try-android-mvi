package id.revan.sharingsessionapp.presentation

sealed class MainIntent {

    object FetchNewsCategory : MainIntent()
    data class FetchBanner(val maxBanner: Int) : MainIntent()
}