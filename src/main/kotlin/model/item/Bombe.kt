package model.item

import model.jeu.TirageDes
import model.personnage.Personnage

class Bombe (nom:String, description:String ,val nombreDeDes:Int, val maxDe: Int): Item(nom,description) {
    override fun utiliser(cible: Personnage) {
        val deDegat= TirageDes(nombreDeDes,maxDe)
        var degat=deDegat.lance()
        degat = maxOf(1,degat-cible.calculeDefense())
        cible.pointDeVie -= degat
        println("La/Le ${this.nom} inflige $degat points de dégâts")
    }
}