package net.lucianodacunha.alugames.model

data class InfoJogo(val info: InfoAPIShark) {
    override fun toString(): String {
        return info.toString()
    }
}