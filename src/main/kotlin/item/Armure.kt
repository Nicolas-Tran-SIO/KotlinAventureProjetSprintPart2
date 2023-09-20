package item

import personnage.Personnage

class Armure( nom:String,description:String, val typeArmure: TypeArmure,val qualite:Qualite):Item(nom,description) {
override fun utiliser(cible:Personnage){
    cible.equipe(this)
}

    override fun toString(): String {
        return "${qualite.couleur} ${qualite.nom} $nom ${qualite.bonusRarete} (type=${typeArmure.nom}) \u001B[0m"
    }

}