package com.jesusrojo.pagingdemo.paging.data.model.mappers

interface EntityMapper<Model,Entity> {

    fun mapToEntity(model: List<Model>): List<Entity>
}