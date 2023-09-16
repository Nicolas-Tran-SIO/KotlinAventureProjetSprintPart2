package item

import personnage.Personnage

class Potion(
    nom: String,
    description: String,
    val soin: Int
) : Item(nom, description) {

    override fun utiliser(cible: Personnage) {
        cible.boirePotion()
    }
}