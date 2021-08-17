package com.jesusrojo.pagingdemo.paging.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.jesusrojo.pagingdemo.paging.data.model.UiModel
import com.jesusrojo.pagingdemo.paging.data.model.roundedStarCount
import com.jesusrojo.pagingdemo.paging.data.repository.mediator.ReposRemoteMediatorRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ReposRemoteMediatorViewModel(
    private val repository: ReposRemoteMediatorRepository
) : ViewModel() {

    @ExperimentalPagingApi
    fun getDatas(query: String): Flow<PagingData<UiModel>> {
        return repository.fetchDatas(query)
            .map { it.map { UiModel.RepoItem(it) } }
            .map {
                it.insertSeparators { before, after ->
                    if (after == null) {    //  we are at the end of the list
                        return@insertSeparators null
                    }
                    if (before == null) {   // we are at the beginning of the list
                        return@insertSeparators UiModel.SeparatorItem("${after.roundedStarCount}0.000+ stars")
                    }
                    // check between 2 items
                    if (before.roundedStarCount > after.roundedStarCount) {
                        if (after.roundedStarCount >= 1) {
                            UiModel.SeparatorItem("${after.roundedStarCount}0.000+ stars")
                        } else {
                            UiModel.SeparatorItem("< 10.000+ stars")
                        }
                    } else { // no separator
                        null
                    }
                }
            }.cachedIn(viewModelScope)
    }

//    @ExperimentalPagingApi
//    fun getDatas(): Flow<PagingData<UiModel>> {
//        return repository.fetchDatas()
//            .map { it.map { UiModel.RepoItem(it) } }
//            .map {
//                it.insertSeparators { before, after ->
//                    if (after == null) {    //  we are at the end of the list
//                        return@insertSeparators null
//                    }
//                    if (before == null) {   // we are at the beginning of the list
//                        return@insertSeparators UiModel.SeparatorItem("${after.roundedStarCount}0.000+ stars")
//                    }
//                    // check between 2 items
//                    if (before.roundedStarCount > after.roundedStarCount) {
//                        if (after.roundedStarCount >= 1) {
//                            UiModel.SeparatorItem("${after.roundedStarCount}0.000+ stars")
//                        } else {
//                            UiModel.SeparatorItem("< 10.000+ stars")
//                        }
//                    } else { // no separator
//                        null
//                    }
//                }
//            }.cachedIn(viewModelScope)
//    }
}