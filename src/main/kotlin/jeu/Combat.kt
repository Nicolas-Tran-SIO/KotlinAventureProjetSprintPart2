package jeu


import personnage.Mage
import personnage.Personnage
import personnage.Voleur


class Combat(
    val joueur: Personnage,
    val monstre: Personnage
) {
    var nombreTours: Int = 1

    // Méthode pour simuler un tour de combat du joueur
    fun tourDeJoueur() {
        println("Tour de ${joueur.nom} (points de vie: ${joueur.pointDeVie})")
        println("1. Attaquer")
        println("2. Boire une potion")
        println("3. Passer")
        println("4. Afficher inventaire")
        println("5. Lancer un sort (Mage uniquement)") // Option pour le Mage
        println("6. Voler un objet (Voleur uniquement)") // Option pour le Voleur
        print("Choisissez une action (1, 2, 3, 4, 5, 6): ")
        val choix = readLine()?.toIntOrNull() ?: 0

        when (choix) {
            1 -> {
                println("\u001B[34m")
                joueur.attaquer(monstre)
            }
            2 -> {
                println("\u001B[34m")
                joueur.boirePotion()
            }
            3 -> {
                println("\u001B[34m")
                println("${joueur.nom} choisit de passer.")
            }
            4 -> {
                // Afficher l'inventaire
                joueur.afficherInventaire()
                // Demander au joueur de choisir un objet à utiliser
                print("Choisissez un objet à utiliser (entrez le numéro) : ")
                val choixObjet = readln().toInt()
                if (choixObjet >= 0 && choixObjet < joueur.inventaire.size) {
                    val objetChoisi = joueur.inventaire[choixObjet]
                    println("\u001B[34m")
                    objetChoisi.utiliser(joueur)
                } else {
                    println("Choix invalide.")
                }
            }
            5 -> {
                if (joueur is Mage) {

                    // Le joueur est un Mage, permettez-lui de choisir et de lancer un sort depuis son grimoire
                    joueur.choisirEtLancerSort(monstre)
                } else {
                    println("${joueur.nom} n'est pas un Mage et ne peut pas lancer de sort.")
                }
            }
            6 -> {
                if (joueur is Voleur) {
                    println("\u001B[34m")
                    // Le joueur est un Voleur, permettez-lui de voler un objet à la cible (monstre) actuelle
                    joueur.volerItem(monstre)
                } else {
                    println("${joueur.nom} n'est pas un Voleur et ne peut pas voler d'objet.")
                }
            }
            else -> {
                println("Action invalide. Choisissez 1 pour attaquer, 2 pour boire une potion, 3 pour passer, 4 pour afficher l'inventaire, 5 pour lancer un sort (Mage uniquement) ou 6 pour voler un objet (Voleur uniquement).")
            }

        }
        println("\u001b[0m")
    }

    // Méthode pour simuler un tour de combat du monstre
    fun tourDeMonstre() {
        println("---Tour de ${monstre.nom} (points de vie: ${monstre.pointDeVie}---)\u001B[31m")
        val nbAlea = (0..100).random()
        // Le monstre a une faible chance (par exemple, 10%) de boire une potion s'il est blessé
        if (monstre.pointDeVie < monstre.pointDeVieMax / 2 && nbAlea < 10 && monstre.aUnePotion()) {
            monstre.boirePotion()
        } else {
            if (nbAlea < 60) {
                monstre.attaquer(joueur)
            } else {
                println("${monstre.nom} choisit de passer.")
            }
        }
        println("\u001b[0m")
    }

    // Méthode pour exécuter le combat complet
    fun executerCombat() {
        println("Début du combat : ${joueur.nom} vs ${monstre.nom}")
        var tourJoueur = true

        while (joueur.pointDeVie > 0 && monstre.pointDeVie > 0) {
            println("Tours de jeu : ${nombreTours}")
            if (tourJoueur) {
                tourDeJoueur()
            } else {
                tourDeMonstre()
            }
            nombreTours++
            tourJoueur = !tourJoueur // Alternance des tours entre le joueur et le monstre
            println("${joueur.nom}: ${joueur.pointDeVie} points de vie | ${monstre.nom}: ${monstre.pointDeVie} points de vie")
            println("")
        }

        if (joueur.pointDeVie <= 0) {
            println("Game over ! ${joueur.nom} a été vaincu !")
            println("Le combat recommence")

            this.joueur.pointDeVie = this.joueur.pointDeVieMax
            this.monstre.pointDeVie = this.monstre.pointDeVieMax
            this.executerCombat()
        } else {
            println("BRAVO ! ${monstre.nom} a été vaincu !")
            this.joueur.loot(monstre)
        }
    }
}