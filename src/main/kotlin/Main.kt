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

val typeEpeeLongue=  TypeArme("Epee longue",1,8,2)
val typeEpeeCourte=  TypeArme("Epee courte",1,6,2)
val typeDague=  TypeArme("Epee courte",1,4,3)
val typeLance= TypeArme("Lance",1,6,3)
val typeMarteau= TypeArme("Marteau",1,8,2)

val typeArmureCuir= TypeArmure("Armure en cuir",1)

val qualiteCommun= Qualite("commun",0,"\u001B[32m")
val qualiteRare = Qualite("rare",1, couleur = "\u001B[34m")
val qualiteEpic = Qualite("epic",2, "\u001B[35m")
val qualiteLegendaire= Qualite("legendaire",3,"\u001B[33m")



fun main() {

    val epee = Arme("Épée Courte", "Une épée courte tranchante",  typeEpeeCourte, qualiteCommun)
    val lance = Arme("Lance", "Une lance pointue",  typeLance, qualiteRare)
    val dague = Arme("Dague", "Une dague extrêmement pointue",  typeDague, qualiteEpic)
    val marteau = Arme("Marteau", "un marteau legendaire pourfendeur de troll",  typeMarteau, qualiteLegendaire)

    val potionDeSoin1 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin2 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin3 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin4 = Potion("Grande Potion de Soin", "Restaure les points de vie", 30)


    val joueur = Personnage("Joueur", 100, 100, 20, 10, 5, 7, epee)
    joueur.inventaire.add(lance)
    joueur.inventaire.add(potionDeSoin1)

    val kobold = Personnage("Marvin le Kobold", 25, 25, 3, 4, 6, 12, dague, inventaire= mutableListOf(potionDeSoin2, dague))
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
        inventaire =mutableListOf(potionDeSoin4, marteau)
    )

    val jeu = Jeu(listOf(kobold, gobelin, troll))

    jeu.lancerCombat()
    
}