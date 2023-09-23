package net.lucianodacunha.alugames.service

import com.google.gson.Gson
import net.lucianodacunha.alugames.exception.GameNotFoundException
import net.lucianodacunha.alugames.model.InfoJogo
import net.lucianodacunha.alugames.model.Jogo
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import kotlin.Exception


/**
 * Deixei static por enquanto mais por questões práticas de testes.
 * Acredito que essa não seja a melhor abordagem por outras questões
 */
object ConsumerAPI {
    fun findGame(id: String) : Jogo {
        val endpoint = "https://www.cheapshark.com/api/1.0/games?id=$id"
        val client: HttpClient = HttpClient.newHttpClient()
        val request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint))
            .build()

        val response = client
            .send(request, HttpResponse.BodyHandlers.ofString())
        val json = response.body()

        val gson = Gson()

        return try {
            val resultadoDaDeserializacao = gson.fromJson(json, InfoJogo::class.java)
            Jogo(
                resultadoDaDeserializacao.info.title,
                resultadoDaDeserializacao.info.thumb
            )
        } catch (e: Exception){
            throw GameNotFoundException("Jogo não encontrado. Tente outro...")
        }
    }
}