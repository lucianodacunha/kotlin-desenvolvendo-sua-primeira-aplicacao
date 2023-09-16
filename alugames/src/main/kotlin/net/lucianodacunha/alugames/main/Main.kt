package net.lucianodacunha.alugames.main
import net.lucianodacunha.alugames.model.Jogo
import net.lucianodacunha.alugames.service.ConsumerAPI
import kotlin.random.Random


fun main(){

    val listaDeJogosPesquisados = getListaDeJogos()
    println("\n\n---------------------------")
    println("Lista de Jogos Pesquisados:")
    println("---------------------------")
    listaDeJogosPesquisados.forEach { println(it) }
}

fun getListaDeJogos() : List<Jogo> {
    var listaDeJogosPesquisados = mutableListOf<Jogo>()
    var pesquisarNovamente: String? = null

    do {
        print("\nEntre com um código de jogo para buscar: ")
        val codigo = Random.nextInt(1, 999).toString()
        print("$codigo")

        var resultadoDaBusca = runCatching {
            ConsumerAPI.findGame(codigo) as Jogo
        }

        resultadoDaBusca.onFailure {
            println("\n${ it.message }")
        }

        resultadoDaBusca.onSuccess {
            println("\nDeseja atribuir uma nova descrição ao jogo [s/N]? N")
            val resposta = "N"
            it.descricao = when (resposta) {
                "s" -> {
                    print("\nEntre com a descrição desejada: ")
                    readln()
                }

                else -> {
                    it.titulo
                }
            }
            listaDeJogosPesquisados.add(it)
        }

        print("\nDeseja pesquisa por mais um jogo? [s/N]: ")

        pesquisarNovamente = if (Random.nextBoolean()) "s" else "N"
        print(pesquisarNovamente)
    } while(pesquisarNovamente.equals("s", true))

    return listaDeJogosPesquisados
}
