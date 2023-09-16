package net.lucianodacunha.alugames.model
data class Jogo(val titulo: String,
           val capa: String){
    var descricao = ""
    override fun toString(): String {
        return "net.lucianodacunha.alugames.model.Jogo\n" +
                "\tTitulo = $titulo\n" +
                "\tCapa = $capa\n" +
                "\tDescricao = $descricao"
    }
}