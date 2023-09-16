package jeu

import personnage.Personnage


class Jeu(val monstres: List<Personnage>) {
    var joueur: Personnage? = null
    var score: Int = 0

    fun lancerCombat() {
        for (monstre in monstres) {
            val combat = Combat(this.joueur!!, monstre)
            combat.executerCombat()

            // Mettre à jour le score en fonction du nombre de tours
            val tours = combat.nombreTours
            score += calculerScore(tours)
        }
        println("Score final du joueur: $score")
    }

    private fun calculerScore(tours: Int): Int {
        // Par exemple, vous pouvez attribuer plus de points pour moins de tours
        return 500 - tours * 10
    }

    /**
     *  Méthode pour créer le personnage du joueur en demandant les informations à l'utilisateur
     *
     */
    fun creerPersonnage(): Personnage {
        println("Création votre personnage:")

        print("Nom du personnage: ")
        val leNom = readLine() ?: "Inconnu"
        var perso: Personnage
        do {
            var resteDePoints = 40
            println("Il y a $resteDePoints points.")
            print("Saisir votre score d'attaque :")
            val scoreAttaque = readLine()?.toIntOrNull() ?: 10
            resteDePoints -= scoreAttaque
            println("Points restants : ${resteDePoints}")

            print("Saisir votre score de défense: ")
            val scoreDefense = readLine()?.toIntOrNull() ?: 10
            resteDePoints -= scoreDefense
            println("Points restants : ${resteDePoints}")

            print("Saisir votre score d'endurance: ")
            val scoreEndurance = readLine()?.toIntOrNull() ?: 10
            resteDePoints -= scoreEndurance
            println("Points restants : ${resteDePoints}")

            print("Saisir votre score de vitesse: ")
            val scoreVitesse = readLine()?.toIntOrNull() ?: 10
            resteDePoints -= scoreVitesse
            println("Points restants : ${resteDePoints}")
            val pvMax = 100 + (10 * scoreEndurance)
            val pv = pvMax
            // Créer un personnage avec les informations fournies par l'utilisateur
            perso = Personnage(leNom, pv, pvMax, scoreAttaque, scoreDefense, scoreEndurance, scoreVitesse)
        } while (resteDePoints < 0)
        //Valorasation du personnage du joueur
        this.joueur = perso
        return perso
    }
}