package com.fon.knjizararest.rest

import com.fon.knjizararest.entity.Reservation
import com.fon.knjizararest.service.ReservationService
import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v0/reservations")
class ReservationRestController(@Autowired val reservationService: ReservationService) {

    @GetMapping("/{reservationId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findReservationByReservationId(@PathVariable reservationId: Long): ResponseEntity<Reservation> {
        val reservation = reservationService.findReservationByReservationId(reservationId)
        return when (reservation.isPresent) {
            true -> ResponseEntity(reservation.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping("/entry/{entryId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findReservationByEntryId(@PathVariable entryId: Long): ResponseEntity<Reservation> {
        val reservation = reservationService.findReservationByEntryEntryId(entryId)
        return when (reservation.isPresent) {
            true -> ResponseEntity(reservation.get(), HttpStatus.OK)
            else -> ResponseEntity(HttpStatus.NO_CONTENT)
        }
    }

    @GetMapping("/{page}/{size}/{sort}/{status}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findReservationsStatusSearch(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String, @PathVariable status: String): ResponseEntity<Page<Reservation>> {
        return ResponseEntity(reservationService.findReservationsByStatus(status, PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @GetMapping("/{page}/{size}/{sort}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findReservations(@PathVariable page: Int, @PathVariable size: Int, @PathVariable sort: String): ResponseEntity<Page<Reservation>> {
        return ResponseEntity(reservationService.findReservations(PageRequest.of(page, size, Sort.by(sort))), HttpStatus.OK)
    }

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun saveReservation(reservation: Reservation): ResponseEntity<Any> {
        return when (reservationService.findReservationByReservationId(reservation.reservationId).isPresent) {
            false -> {
                reservationService.saveReservation(reservation)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.FOUND)
        }
    }

    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateReservation(reservation: Reservation): ResponseEntity<Any> {
        return when (reservationService.findReservationByReservationId(reservation.reservationId).isPresent) {
            true -> {
                reservationService.saveReservation(reservation)
                ResponseEntity(HttpStatus.OK)
            }
            else -> {
                ResponseEntity(HttpStatus.NO_CONTENT)
            }
        }
    }

    @DeleteMapping("/{reservationId}", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteReservation(@PathVariable reservationId: Long): ResponseEntity<Any> {
        return when (reservationService.findReservationByReservationId(reservationId).isPresent) {
            true -> {
                reservationService.deleteReservationByReservationId(reservationId)
                ResponseEntity(HttpStatus.OK)
            }
            else -> ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}