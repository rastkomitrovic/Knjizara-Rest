package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.StoreImage
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface StoreImageRepository :CrudRepository<StoreImage,Long>{
    fun findStoreImagesByStoreStoreId(storeId: Long): List<StoreImage>
    fun deleteStoreImageByImageId(imageId: Long)
}