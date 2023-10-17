package generateur
import model.item.Arme
import generateur.GenerateurTypeArme
import model.item.TypeArme
import qualites
import typeArme
import java.nio.file.Paths
import java.nio.file.Files
class GenerateurArme(val cheminFichier:String) {
    /**
     * Génère et retourne un mapping des armes à partir des données contenues dans le fichier CSV.
     *
     * @return Un mapping des armes où la clé est le nom de l'arme en minuscules..
     */
    fun generer(): MutableMap<String, Arme> {
        val mapObjets = mutableMapOf<String, Arme>()

        // Lecture du fichier CSV, le contenu du fichier est stocké dans une liste mutable :
        val cheminCSV = Paths.get(this.cheminFichier)
        val listeObjCSV = Files.readAllLines(cheminCSV)

        // Instance des objets :
        for (i in 1..listeObjCSV.lastIndex) {
            val ligneObjet = listeObjCSV[i].split(";")
            val cle = ligneObjet[0].lowercase()
            val laQualite= qualites[ligneObjet[3]]!!
            val LeTypeArme = typeArme[ligneObjet[2]]!!
            val objet = Arme(nom = ligneObjet[0], description = ligneObjet[1], type = LeTypeArme, qualite = laQualite)
            mapObjets[cle] = objet
        }
        return mapObjets
    }
}
