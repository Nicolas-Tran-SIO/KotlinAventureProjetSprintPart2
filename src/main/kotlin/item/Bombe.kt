package item

import jeu.De
import personnage.Personnage

class Bombe (nom:String, description:String ,val nombreDeDes:Int, val maxDe: Int):Item(nom,description) {
    override fun utiliser(cible: Personnage) {
        val deDegat= De(nombreDeDes,maxDe)
        var degat=deDegat.lance()
        degat = maxOf(1,degat-cible.calculeDefense())
        cible.pointDeVie -= degat;
        println("La/Le ${this.nom} inflige $degat points de dégâts")
    }
}