package com.fon.knjizararest.service.impl


import com.fon.knjizararest.entity.StoreImage
import com.fon.knjizararest.repository.StoreImageRepository
import com.fon.knjizararest.service.StoreImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class StoreImageServiceImpl(@Autowired val storeImageRepository: StoreImageRepository) : StoreImageService {
    override fun findStoreImageByImageId(imageId: Long): Optional<StoreImage> {
        return storeImageRepository.findById(imageId)
    }

    override fun saveStoreImage(storeImage: StoreImage) {
        storeImageRepository.save(storeImage)
    }

    override fun findStoreImagesByStoreStoreId(storeId: Long): List<StoreImage> {
        return storeImageRepository.findStoreImagesByStoreStoreId(storeId)
    }

    override fun deleteStoreImageByImageId(imageId: Long) {
        storeImageRepository.deleteById(imageId)
    }
}