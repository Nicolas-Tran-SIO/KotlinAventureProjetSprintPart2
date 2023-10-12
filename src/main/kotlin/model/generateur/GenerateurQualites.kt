package generateur
import model.item.Qualite
import java.nio.file.Paths
import java.nio.file.Files


class GenerateurQualites(val cheminFichier: String) {
    fun generer(): MutableMap<String, Qualite> {
        val mapObjets = mutableMapOf<String, Qualite>()

        // Lecture du fichier CSV, le contenu du fichier est stocké dans une liste mutable :
        val cheminCSV = Paths.get(this.cheminFichier)
        val listeObjCSV = Files.readAllLines(cheminCSV)

        // Instance des objets :
        for (i in 1..listeObjCSV.lastIndex) {
            val ligneObjet = listeObjCSV[i].split(";")
            val cle = ligneObjet[0]
            val objet = Qualite(nom = ligneObjet[0], bonusRarete = ligneObjet[1].toInt(), couleur = ligneObjet[2])
            mapObjets[cle] = objet
        }
        return mapObjets
    }
}

