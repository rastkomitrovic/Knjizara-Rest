package com.fon.knjizararest.service

import com.fon.knjizararest.entity.StoreImage
import java.util.*

interface StoreImageService {
    fun findStoreImageByImageId(imageId: Long): Optional<StoreImage> //default method
    fun saveStoreImage(storeImage: StoreImage) //default method
    fun findStoreImagesByStoreStoreId(storeId: Long): List<StoreImage>
    fun deleteStoreImageByImageId(imageId: Long)
}