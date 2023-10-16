package generateur
import model.item.TypeArmure
import java.nio.file.Paths
import java.nio.file.Files
/**
 * La classe GenerateurTypeArmure permet de générer des types d'armures à partir d'un fichier CSV.
 *
 * @param cheminFichier Le chemin vers le fichier CSV contenant les données des types d'armures.
 */
class GenerateurTypeArmure(val cheminFichier: String) {
    /**
     * Génère et retourne un mapping des types d'armures à partir des données contenues dans le fichier CSV.
     *
     * @return Un mapping des types d'armures où la clé est le nom du type d'armure en minuscules..
     */
    fun generer(): MutableMap<String, TypeArmure> {
        val mapObjets = mutableMapOf<String, TypeArmure>()

        // Lecture du fichier CSV, le contenu du fichier est stocké dans une liste mutable :
        val cheminCSV = Paths.get(this.cheminFichier)
        val listeObjCSV = Files.readAllLines(cheminCSV)

        // Instance des objets :
        for (i in 1..listeObjCSV.lastIndex) {
            val ligneObjet = listeObjCSV[i].split(";")
            val cle = ligneObjet[0].lowercase()
            val objet = TypeArmure(nom = ligneObjet[0],bonusType =ligneObjet[1].toInt())
            mapObjets[cle] = objet
        }
        return mapObjets
    }
}
