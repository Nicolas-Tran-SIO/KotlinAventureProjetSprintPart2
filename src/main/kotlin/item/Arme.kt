package item

import jeu.TirageDes
import personnage.Personnage

class Arme(
    nom: String,
    description: String,
    val type: TypeArme,
    val qualite: Qualite
) : Item(nom, description) {
    // Méthode pour calculer les dégâts de l'arme
    fun calculerDegats(): Int {
        // Exemple : 1d6 +2 ( cad un nombre entre 1 et 6 plus le modificateur 2)
        //      On tire 1 dè de 6 (min: 1 max:6 )
        //      Si on tire 6 alors on multiplie par le multiplicateur de critique du type de l'arme ( par exemple 3)
        //      Sinon on garde le nombre du tirage tel quelle
        //      On ajoute le bonus de qualite au dégat ici 2

        // Instanciation d'un tirage de dés
        val deDegat= TirageDes(type.nombreDes,type.valeurDeMax)
        // on lance les dés
        var resultatLancer = deDegat.lance()
        if (resultatLancer == type.valeurDeMax) {
            // Coup critique (si le nombre tiré correspond au maximum)
            println("Coup critique !")
           resultatLancer= resultatLancer * type.multiplicateurCritique
        }
        resultatLancer += this.qualite.bonusRarete
        return resultatLancer
    }

    override fun utiliser(cible: Personnage) {
        cible.equipe(this)
    }

    override fun toString(): String {
        return "${qualite.couleur} ${type.nom} ${qualite.nom}  Dégâts :${type.nombreDes}d${type.valeurDeMax} +${qualite.bonusRarete}  \u001B[0m"

    }
}