package com.goodylabs.android.interview.data.usecase

import com.goodylabs.android.interview.R
import com.goodylabs.android.interview.data.models.CharactersContainer
import com.goodylabs.android.interview.data.models.RequestState
import com.goodylabs.android.interview.data.repositories.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharacterRepository
) {

    operator fun invoke(page: Int): Flow<RequestState<CharactersContainer>> = flow {
        emit(RequestState.Loading())
        try {
            val apiResult = repository.getCharacterContainer(page)
            emit(RequestState.Success(apiResult))
        } catch (e: HttpException) {
            emit(RequestState.Error(R.string.server_error))
        } catch (e: IOException) {
            emit(RequestState.Error(R.string.network_error))
        }
    }

}