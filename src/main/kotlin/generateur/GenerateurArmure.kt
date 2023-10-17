package generateur
import model.item.Armure
import model.item.Qualite
import model.item.TypeArmure
import java.nio.file.Paths
import java.nio.file.Files
import typeArmure
import qualites
class GenerateurArmure(val cheminFichier:String) {
    /**
     * Génère et retourne un mapping des armure à partir des données contenues dans le fichier CSV.
     *
     * @return Un mapping des armure où la clé est le nom de l'armure en minuscules..
     */
    fun generer(): MutableMap<String, Armure> {
        val mapObjets = mutableMapOf<String, Armure>()

        // Lecture du fichier CSV, le contenu du fichier est stocké dans une liste mutable :
        val cheminCSV = Paths.get(this.cheminFichier)
        val listeObjCSV = Files.readAllLines(cheminCSV)

        // Instance des objets :
        for (i in 1..listeObjCSV.lastIndex) {
            val ligneObjet = listeObjCSV[i].split(";")
            val cle = ligneObjet[0].lowercase()
            val laQualite= qualites[ligneObjet[3]]!!
            val LeTypeArmure = typeArmure[ligneObjet[2]]!!
            val objet = Armure(nom = ligneObjet[0], description = ligneObjet[1], typeArmure = LeTypeArmure, qualite = laQualite)
            mapObjets[cle] = objet
        }
        return mapObjets
    }
}