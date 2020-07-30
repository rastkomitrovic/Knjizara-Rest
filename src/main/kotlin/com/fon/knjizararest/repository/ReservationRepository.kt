package com.fon.knjizararest.repository

import com.fon.knjizararest.entity.Reservation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository :PagingAndSortingRepository<Reservation,Long>{
    fun findReservationsByStatus(status:String,pageable: Pageable):Page<Reservation>
    fun existsByEntryEntryId(entryId: Long): Boolean
}