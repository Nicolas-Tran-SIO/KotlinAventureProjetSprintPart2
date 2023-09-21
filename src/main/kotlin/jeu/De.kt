package jeu

class De(val nbDe:Int, val maxDe:Int) {
    fun lance():Int{
        var resultat=0;
        repeat(this.nbDe){
            resultat+=(0..this.maxDe).random()
        }
        return resultat;
    }
}