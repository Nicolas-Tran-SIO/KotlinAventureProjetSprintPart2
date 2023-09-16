package item

class Arme(
     nom: String,
     description: String,
    val degatMin: Int,
    val degatMax: Int,
    val type: TypeArme,
    val rarete: Rarete
) : Item(nom,description){
    // Méthode pour calculer les dégâts de l'arme
    fun calculerDegats(): Int {
        val resultatLancer = (degatMin..degatMax).random()
        if (resultatLancer == degatMax) {
            // Coup critique (si le nombre tiré correspond au maximum)
            println("Coup critique !")
            return resultatLancer * 2
        } else {
            return resultatLancer
        }
    }

    override fun toString(): String {
        return "$nom (Dégâts Min: $degatMin, Dégâts Max: $degatMax, Type: $type, Rareté: $rarete)"
    }
}