package com.jesusrojo.pagingdemo.paging.data.model.mappers

interface RepoResponseMapper<Response, Model> {

    fun mapFromResponse(response: Response) : Model
}