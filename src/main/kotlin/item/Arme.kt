package item

import personnage.Personnage

class Arme(
    nom: String,
    description: String,
    val type: TypeArme,
    val rarete: Rarete
) : Item(nom, description) {
    // Méthode pour calculer les dégâts de l'arme
    fun calculerDegats(): Int {
        // Exemple : 1d6 +2 ( cad un nombre entre 1 et 6 plus le modificateur 2)
        val resultatLancer = (type.degatMin..type.degatMax).random() + rarete.bonusRarete
        if (resultatLancer == type.degatMax) {
            // Coup critique (si le nombre tiré correspond au maximum)
            println("Coup critique !")
            return resultatLancer * type.multiplicateurCritique
        } else {
            return resultatLancer
        }
    }

    override fun utiliser(cible: Personnage) {
        cible.equiperArme(this)
    }

    override fun toString(): String {
        return "${rarete.couleur} ${type.nom} ${rarete.nom}  Dégâts :${type.degatMin}d${type.degatMax} +${rarete.bonusRarete}  \u001B[0m"

    }
}