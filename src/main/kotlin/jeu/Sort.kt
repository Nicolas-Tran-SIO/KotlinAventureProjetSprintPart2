package jeu

import personnage.Personnage

class Sort(val nom: String, val effet: (Personnage,Personnage) -> Unit)