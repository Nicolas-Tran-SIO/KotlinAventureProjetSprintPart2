package personnage

import item.Arme
import item.Item
import item.Potion


class Personnage(
    val nom: String,
    var pointDeVie: Int,
    val pointDeVieMax: Int,
    var attaque: Int,
    var defense: Int,
    var endurance: Int,
    var vitesse: Int,
    var armeEquipee: Arme? = null,
    val inventaire: MutableList<Item> = mutableListOf()
) {

    // Méthode pour attaquer un adversaire
    fun attaquer(adversaire: Personnage) {
        // Vérifier si le personnage a une arme équipée
        if (armeEquipee != null) {
            // Calculer les dégâts en utilisant les attributs du personnage et la méthode calculerDegat de l'arme
            val degats = this.armeEquipee!!.calculerDegats()  + this.attaque

            // Appliquer la défense de l'adversaire
            val degatsInfliges = maxOf(1, degats - adversaire.defense)

            // Appliquer les dégâts à l'adversaire
            adversaire.pointDeVie= adversaire.pointDeVie-degatsInfliges

            println("$nom attaque ${adversaire.nom} avec ${armeEquipee?.nom} et inflige $degatsInfliges points de dégâts.")
        } else {
            // Si le personnage n'a pas d'arme équipée, l'attaque est moins efficace
            val degats = attaque / 2

            // Appliquer la défense de l'adversaire
            val degatsInfliges = maxOf(1, degats - adversaire.defense)

            // Appliquer les dégâts à l'adversaire
            adversaire.pointDeVie= adversaire.pointDeVie-degatsInfliges

            println("$nom attaque ${adversaire.nom} avec une attaque de base et inflige $degatsInfliges points de dégâts.")
        }
    }

    // Méthode pour équiper une arme de l'inventaire
    fun equiperArme(arme: Arme) {
        if (inventaire.contains(arme)) {
            armeEquipee = arme
            println("$nom équipe ${arme.nom}.")
        } else {
            println("$nom n'a pas cette arme dans son inventaire.")
        }
    }

    // Méthode pour boire une potion de l'inventaire
    fun boirePotion() {
        val potions = inventaire.filterIsInstance<Potion>()
        if (potions.isNotEmpty()) {
            val potion = potions.first()
            val soin = potion.soin
            pointDeVie += soin
            if (pointDeVie > pointDeVieMax) {
                pointDeVie = pointDeVieMax
            }
            inventaire.remove(potion)
            println("$nom boit ${potion.nom} et récupère $soin points de vie.")
        } else {
            println("$nom n'a pas de potion dans son inventaire.")
        }
    }

    /**
     * Vérification si le personnage a une potion dans son inventaire
     * @return true si il a une potion false sinon
     */
    fun aUnePotion(): Boolean {
        return inventaire.any { it is Potion }
    }

    /**
     * Loot l'inventaire de la cible
     */
    fun loot(cible: Personnage){
            cible.armeEquipee=null
            this.inventaire.addAll(cible.inventaire)
            cible.inventaire.forEach({println("${this.nom} récupère un/une $it")})
            println()
            cible.inventaire.clear()
    }

    /**
     * Affiche les items de l'inventaire avec index et descriptions
     */
    fun afficherInventaire() {
        println("Inventaire de $nom:")
        if (inventaire.isEmpty()) {
            println("L'inventaire est vide.")
        } else {
            for ((index, item) in inventaire.withIndex()) {
                println("$index => ${item.nom}: ${item.description}")
            }
        }
    }


    override fun toString(): String {
        return "$nom (PV: $pointDeVie/$pointDeVieMax, Attaque: $attaque, Défense: $defense, Endurance: $endurance, Vitesse: $vitesse)"
    }


}