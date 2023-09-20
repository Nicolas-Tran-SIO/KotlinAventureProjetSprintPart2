package personnage

import item.Arme

class Guerrier(
    nom: String,
    pointDeVie: Int,
    pointDeVieMax: Int,
    attaque: Int,
    defense: Int,
    endurance: Int,
    vitesse: Int,
) : Personnage(nom, pointDeVie, pointDeVieMax, attaque, defense, endurance, vitesse) {
    var armeSecondaireEquipee: Arme? = null

    override fun attaquer(adversaire: Personnage) {
        val degats: Int

        if (armeEquipee != null && armeSecondaireEquipee != null) {
            // Si le guerrier a deux armes équipées, calculez les dégâts en utilisant les attributs du personnage et les deux armes
            val degatsArme1 = this.armeEquipee!!.calculerDegats() + this.attaque
            val degatsArme2 =
                this.armeSecondaireEquipee!!.calculerDegats() //Pas de bonus d'attaque pour la deuxième arme
            degats = maxOf(1, degatsArme1 + degatsArme2)
        } else if (armeEquipee != null) {
            // Si le guerrier a seulement une arme équipée, calculez les dégâts en utilisant l'arme équipée et les attributs du personnage
            degats = (this.armeSecondaireEquipee?.calculerDegats() ?: 0) + this.attaque
        } else {
            // Si le guerrier n'a pas d'arme équipée, l'attaque est moins efficace
            degats = attaque
        }

        // Appliquer la défense de l'adversaire (au minimum au moins 1 de dégat)
        val degatsInfliges = maxOf(1, degats - adversaire.defense)

        // Appliquer les dégâts à l'adversaire
        adversaire.pointDeVie -= degatsInfliges

        println("$nom attaque ${adversaire.nom} avec ${armeEquipee?.nom ?: "une attaque de base"} et ${armeSecondaireEquipee?.nom ?: "une attaque de base"}  inflige $degatsInfliges points de dégâts.")
    }

    /**
     * Affiche les armes equipées du personnages
     */
    fun afficherArmes() {
        println("$nom a les armes suivantes:")
        if (armeEquipee != null) {
            println("1. ${armeEquipee!!.nom}")
        } else {
            println("1. Aucune arme équipée")
        }

        if (armeSecondaireEquipee != null) {
            println("2. ${armeSecondaireEquipee!!.nom}")
        } else {
            println("2. Aucune deuxième arme équipée")
        }
    }

    // Surcharge de la méthode equiperArme
    override fun equipe(arme: Arme) {
        this.afficherArmes()
        print("À quelle emplacement voulez-vous équiper l'arme (1 ou 2) : ")
        val choixEmplacement = readLine()?.toIntOrNull()

        when (choixEmplacement) {
            1 -> {
                armeEquipee = arme
                println("$nom équipe ${arme.nom} à l'emplacement 1.")
            }
            2 -> {
                armeSecondaireEquipee = arme
                println("$nom équipe ${arme.nom} à l'emplacement 2.")
            }
            else -> {
                println("Choix invalide. L'arme n'a pas été équipée.")
            }
        }
    }
}