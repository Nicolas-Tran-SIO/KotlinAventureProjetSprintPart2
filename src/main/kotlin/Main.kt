import item.*
import jeu.Jeu
import personnage.Personnage

fun main() {
    val epee1 = Arme("Épée Longue", "Une épée longue tranchante", 1, 8,TypeArme.EPEE,Rarete.COMMUN)
    val epee2 = Arme("Épée Courte", "Une épée courte tranchante", 1, 6,TypeArme.EPEE,Rarete.COMMUN)
    val lance = Arme("Lance", "Une lance pointue", 2, 8,TypeArme.LANCE,Rarete.COMMUN)
    val dague = Arme("Dague", "Une dague pointue", 1, 4,TypeArme.DAGUE,Rarete.COMMUN)
    val marteau=Arme("Marteau", "un marteau lourd",  3,10, TypeArme.MARTEAU,Rarete.COMMUN)

    val potionDeSoin1 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin2 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin3 = Potion("Potion de Soin", "Restaure les points de vie", 20)
    val potionDeSoin4 = Potion("Grande Potion de Soin", "Restaure les points de vie", 30)
    val potionDeSoin5 = Potion("Grande Potion de Soin", "Restaure les points de vie", 30)

    val joueur = Personnage("Joueur", 100, 100, 20, 10, 5, 7, epee1)
    joueur.inventaire.add(lance)
    joueur.inventaire.add(potionDeSoin1)

    val kobold=Personnage("Marvin le Kobold",25,25,3,4,6,12,dague, mutableListOf(potionDeSoin2))
    val gobelin=Personnage("Antoine le gobelin", pointDeVie = 20, pointDeVieMax = 20, attaque = 5, defense = 4, vitesse = 11, endurance = 6, armeEquipee = epee2, inventaire = mutableListOf(potionDeSoin3))
    val troll= Personnage("Nassim le troll", pointDeVie = 100,100,6,3,12,3,marteau, mutableListOf(potionDeSoin4))

    val jeu = Jeu(listOf(kobold,gobelin,troll))
    jeu.creerPersonnage()
    jeu.joueur?.armeEquipee=lance
    jeu.joueur?.inventaire?.add(potionDeSoin5)
    jeu.lancerCombat()
}