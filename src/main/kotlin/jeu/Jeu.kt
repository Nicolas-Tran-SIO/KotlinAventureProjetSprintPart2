package jeu

import item.Arme
import item.Potion
import personnage.Guerrier
import personnage.Mage
import personnage.Personnage
import personnage.Voleur
import qualiteCommun
import sortDeFeu
import sortDeGuerison
import typeDague
import typeEpeeLongue


class Jeu(monstres: List<Personnage>) {
    lateinit var joueur: Personnage
     var combats: MutableList<Combat> = mutableListOf()
    var score: Int = 0

    init {
        this.creerPersonnage()
        for (unMonstre in monstres){
            val unCombat= Combat(joueur,unMonstre)
            combats.add(unCombat);
        }
    }

    fun lancerCombat() {
        for (unCombat in this.combats) {

            unCombat.executerCombat()

            // Mettre à jour le score en fonction du nombre de tours
            val tours = unCombat.nombreTours
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
        var scoreAttaque:Int; var scoreDefense:Int; var scoreEndurance:Int; var scoreVitesse:Int; var pv:Int; var pvMax:Int
        do {
            var resteDePoints = 40
            println("Il y a $resteDePoints points.")
            print("Saisir votre score d'attaque :")
             scoreAttaque = readln().toInt()
            resteDePoints -= scoreAttaque
            println("Points restants : ${resteDePoints}")

            print("Saisir votre score de défense: ")
             scoreDefense = readln().toInt()
            resteDePoints -= scoreDefense
            println("Points restants : ${resteDePoints}")

            print("Saisir votre score d'endurance: ")
             scoreEndurance = readln().toInt()
            resteDePoints -= scoreEndurance
            println("Points restants : ${resteDePoints}")

            print("Saisir votre score de vitesse: ")
             scoreVitesse = readln().toInt()
            resteDePoints -= scoreVitesse
            println("Points restants : ${resteDePoints}")
             pvMax = 100 + (10 * scoreEndurance)
             pv = pvMax
        }
        while (resteDePoints < 0)
            // Créer un personnage avec les informations fournies par l'utilisateur
            var typePerso=""
            do{
                println("Quel type de personnage ?")
                println("1=> Guerrier : peut avoir 2 armes")
                println("2=> Voleur : peut obtenir des items")
                println("3=> Mage : lance des sorts")
                val choixType= readln().toInt()
               when (choixType){
                   1->typePerso="guerrier"
                   2->typePerso="voleur"
                   3->typePerso="mage"
                   else-> println("valeur non valide")
               }
            }
            while (typePerso !in listOf("guerrier","voleur","mage"))
            when (typePerso){
                "guerrier"-> {
                    perso = Guerrier(leNom, pv, pvMax, scoreAttaque, scoreDefense, scoreEndurance, scoreVitesse)
                    val maDague=Arme("Dague", "Une dague pointue", typeDague,qualiteCommun)
                    perso.inventaire.add(maDague)
                    perso.armeSecondaireEquipee=maDague
                }
                "voleur"-> perso = Voleur(leNom,pv,pvMax,scoreAttaque,scoreDefense,scoreEndurance,scoreVitesse)
                "mage" -> {
                    perso = Mage(leNom, pv, pvMax, scoreAttaque, scoreDefense, scoreEndurance, scoreVitesse)
                    perso.grimoire.add(sortDeFeu)
                    perso.grimoire.add(sortDeGuerison)
                }
                else-> perso = Personnage(leNom, pv, pvMax, scoreAttaque, scoreDefense, scoreEndurance, scoreVitesse)
                }
        //Valorasation du personnage du joueur
        this.joueur = perso
        val epee = Arme("Épée Longue", "Une épée longue tranchante",typeEpeeLongue, qualiteCommun)
        val potionDeSoin = Potion("Grande Potion de Soin", "Restaure les points de vie", 30)
        joueur.inventaire.add(epee)

        joueur.inventaire.add(potionDeSoin)
        joueur.armeEquipee=epee
        return perso
    }
    

}