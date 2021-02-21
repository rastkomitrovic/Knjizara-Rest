package com.fon.knjizararest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
class KnjizaraRestApplication

fun main(args: Array<String>) {
    runApplication<KnjizaraRestApplication>(*args)
}
