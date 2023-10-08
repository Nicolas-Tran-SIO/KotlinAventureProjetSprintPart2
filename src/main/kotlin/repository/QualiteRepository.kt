package repository

import coBDD
import jdbc.BDD
import model.item.Qualite
import java.sql.PreparedStatement
import java.sql.Statement


class QualiteRepository(val bdd: BDD = coBDD) {

    fun findAll(): List<Qualite> {
        var result = mutableListOf<Qualite>();

        val sql = "Select * From Qualite"
        val requetePreparer = this.bdd.connectionBDD!!.prepareStatement(sql)
        val resultatRequete = this.bdd.executePreparedStatement(requetePreparer)
        if (resultatRequete != null) {
            while (resultatRequete.next()) {
                //TODO
            }
        }
        requetePreparer.close()
        return result;
    }

    fun save(uneQualite: Qualite): Qualite? {

        val requetePreparer:PreparedStatement

        if (uneQualite.id == null) {
            var sql = ""
            sql =
                "Insert Into Qualite (nom,bonusRarete,couleur) values (?,?,?)"
            requetePreparer = this.bdd.connectionBDD!!.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            requetePreparer?.setString(1, uneQualite.nom)
            requetePreparer?.setInt(2, uneQualite.bonusRarete)
            requetePreparer?.setString(3, uneQualite.couleur)
        } else {
            var sql = ""
            sql =
                "Insert into Qualite (id,nom,bonusRarete,couleur) values (?,?,?,?)"
            requetePreparer = this.bdd.connectionBDD!!.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
            requetePreparer?.setInt(1, uneQualite.id!!)
            requetePreparer?.setString(2, uneQualite.nom)
            requetePreparer?.setInt(3, uneQualite.bonusRarete)
            requetePreparer?.setString(4, uneQualite.couleur)
        }


        // Exécutez la requête d'insertion
        val nbLigneMaj = requetePreparer?.executeUpdate()
        // La méthode executeUpdate() retourne le nombre de lignes modifié par un insert, update ou delete sinon elle retourne 0 ou -1

        // Si l'insertion a réussi
        if (nbLigneMaj != null && nbLigneMaj > 0) {
            // Récupérez les clés générées (comme l'ID auto-incrémenté)
            val generatedKeys = requetePreparer.generatedKeys
            if (generatedKeys.next()) {
                val id = generatedKeys.getInt(1) // Supposons que l'ID est la première col
                uneQualite.id = id // Mettez à jour l'ID de l'objet Qualite avec la valeur générée
                return uneQualite
            }
        }
        requetePreparer.close()

        return null
    }

    fun saveAll(lesQualites:MutableList<Qualite>):MutableList<Qualite>{
    var result= mutableListOf<Qualite>();
        for (uneQualite in lesQualites){
            val qualiteSauvegarde=this.save(uneQualite)
            if (qualiteSauvegarde!=null){
                result+=qualiteSauvegarde
            }
        }
        return result;
    }
}