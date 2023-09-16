package jeu

import item.Potion
import personnage.Personnage
import kotlin.random.Random

class Combat(
    val joueur: Personnage,
    val monstre: Personnage
) {
var nombreTours:Int =1

    // Méthode pour simuler un tour de combat du joueur
    fun tourDeJoueur() {
        println("Tour de ${joueur.nom} (points de vie: ${joueur.pointDeVie})")
        println("1. Attaquer")
        println("2. Boire une potion")
        println("3. Passer")
        print("Choisissez une action (1, 2 ou 3): ")
        val choix = readLine()?.toIntOrNull() ?: 0
        println("\u001B[34m");
        when (choix) {
            1 -> {
                joueur.attaquer(monstre)
            }
            2 -> {
                joueur.boirePotion()
            }
            3 -> {
                println("${joueur.nom} choisit de passer.")
            }
            else -> {
                println("Action invalide. Choisissez 1 pour attaquer, 2 pour boire une potion ou 3 pour passer.")
            }
        }
        println("\u001b[0m")
    }

    // Méthode pour simuler un tour de combat du monstre
    fun tourDeMonstre() {
        println("---Tour de ${monstre.nom} (points de vie: ${monstre.pointDeVie}---)\u001B[31m")
        val nbAlea=(0..100).random();
        // Le monstre a une faible chance (par exemple, 10%) de boire une potion s'il est blessé
        if (monstre.pointDeVie < monstre.pointDeVieMax / 2 && nbAlea < 10  && monstre.aUnePotion()) {
                monstre.boirePotion()
            }
        else{
                if(nbAlea<60){
                    monstre.attaquer(joueur)
                }
                else {
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

            this.joueur.pointDeVie=this.joueur.pointDeVieMax;
            this.monstre.pointDeVie=this.monstre.pointDeVieMax;
            this.executerCombat();
        } else {
            println("Bravo ! ${monstre.nom} a été vaincu !")
        }
    }
}