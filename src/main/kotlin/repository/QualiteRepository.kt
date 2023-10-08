package repository

import coBDD
import jdbc.BDD
import model.item.Qualite


class QualiteRepository (val bdd: BDD=coBDD) {

    fun findAll():List<Qualite>{
        var result= mutableListOf<Qualite>();

        val sql="Select * From Qualite"
        if (this.bdd.connection!=null){
            val requetePreparer= this.bdd.connection!!.prepareStatement(sql)
            val resultatRequete=this.bdd.executePreparedStatement(requetePreparer)
            if (resultatRequete != null) {
                while (resultatRequete.next()){
                    //TODO
                }
            }
        }

    }
}