package com.example.comcasttest.model

class RepositoryImpl : Repository {

    override suspend fun getData(): AppState =
        generateAppState(
            Network.getInstance().getApi().getResponse()
        )


    private fun generateAppState(response: retrofit2.Response<Response>): AppState {
        if (response.isSuccessful)//if Response code <300
            return AppState.SUCCESS(createCharacterName(response.body()))
        return AppState.ERROR(response.message())
    }

    private fun createCharacterName(body: Response?): Response{
        if(body == null) throw Exception()
        return Response(
            body.RelatedTopics.map {
                Topic(
                    it.FirstURL,
                    it.Icon,
                    it.Result,
                    it.Text,
                    it.FirstURL.substring(it.FirstURL.lastIndexOf('/') + 1)
                )
            }.toList())
    }

    sealed class AppState {
        data class SUCCESS(val data: Response?) : AppState()
        data class ERROR(val errorMessage: String) : AppState()
        object LOADING : AppState()
    }
}