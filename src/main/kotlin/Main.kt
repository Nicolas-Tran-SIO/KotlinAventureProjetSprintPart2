import item.*
import jeu.Jeu
import jeu.Sort
import personnage.Personnage

val sortDeFeu = Sort("Boule de Feu") { cible ->
    run {
        val degat = (7..15).random()
        cible.pointDeVie -= degat
        println("Boule de Feu inflige $degat à ${cible.nom}")
    }
}
var sortDeGuerison = Sort("Sort de Guérison") { cible ->
    run {
        val soin = (10..25).random()
        cible.pointDeVie += soin
        println("Le sort de guérison soigne ${soin} PV à ${cible.nom}")
    }
}

fun main() {

    val epee = Arme("Épée Courte", "Une épée courte tranchante", 1, 6, TypeArme.EPEE, Rarete.COMMUN)
    val lance = Arme("Lance", "Une lance pointue", 2, 8, TypeArme.LANCE, Rarete.COMMUN)
    val dague = Arme("Dague", "Une dague pointue", 1, 4, TypeArme.DAGUE, Rarete.COMMUN)
    val marteau = Arme("Marteau", "un marteau lourd", 3, 10, TypeArme.MARTEAU, Rarete.COMMUN)

    val potionDeSoin1 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin2 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin3 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin4 = Potion("Grande Potion de Soin", "Restaure les points de vie", 30)


    val joueur = Personnage("Joueur", 100, 100, 20, 10, 5, 7, epee)
    joueur.inventaire.add(lance)
    joueur.inventaire.add(potionDeSoin1)

    val kobold = Personnage("Marvin le Kobold", 25, 25, 3, 4, 6, 12, dague, mutableListOf(potionDeSoin2, dague))
    val gobelin = Personnage(
        "Antoine le gobelin",
        pointDeVie = 20,
        pointDeVieMax = 20,
        attaque = 5,
        defense = 4,
        vitesse = 11,
        endurance = 6,
        armeEquipee = epee,
        inventaire = mutableListOf(potionDeSoin3, epee)
    )
    val troll = Personnage(
        "Nassim le troll",
        pointDeVie = 100,
        100,
        6,
        3,
        12,
        3,
        marteau,
        mutableListOf(potionDeSoin4, marteau)
    )

    val jeu = Jeu(listOf(kobold, gobelin, troll))

    jeu.lancerCombat()
}