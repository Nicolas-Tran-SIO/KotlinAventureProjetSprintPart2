package model.jeu

import model.personnage.Personnage

class Sort(val nom: String, val effet: (Personnage, Personnage) -> Unit)