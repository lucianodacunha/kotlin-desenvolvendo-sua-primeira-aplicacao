package net.lucianodacunha.alugames.main

import net.lucianodacunha.alugames.model.Gamer
import net.lucianodacunha.alugames.util.tranformarEmIdade

fun main(){
    val g1 = Gamer("gamer1", "g1@email.com")
    val g2 = Gamer("gamer2", "g2@email.com", "01/01/1990",
        "gamer2", )

    println(g1)
    println(g2)

    println("Idade do g2: ${ g2.dataDeNascimento?.tranformarEmIdade() } anos.")
}