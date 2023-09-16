package item

class Potion(
    nom: String,
    description: String,
    val soin: Int
) : Item(nom, description) {

    fun boire(): Int {
        return soin
    }
}