/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mongodbjava4;

import java.sql.*;

public class MetaData {

  public static void main(String[] args) {

	Connection       lcn;
	DatabaseMetaData ldbmd;
	ResultSet        lrsCatalogues;
	ResultSet        lrsTables;
	ResultSet        lrsCols;
	StringBuilder    lsbInfos = new StringBuilder();

	try {
		Class.forName("org.gjt.mm.mysql.Driver");
//		lcn = DriverManager.getConnection("jdbc:mysql://localhost/cours","root","");
            lcn = DriverManager.getConnection("jdbc:mysql://mysql-m2iformationcdidl.alwaysdata.net:3306/m2iformationcdidl_cine", "147389", "mdp123");

		// --- Acces a la metabase, au dictionnaire
		ldbmd = lcn.getMetaData();
		lsbInfos.append("\nEditeur de la base : ");
		lsbInfos.append(ldbmd.getDatabaseProductName());
		//lsInfos += "\nEditeur de la base : " + ;
		lsbInfos.append("\nDriver   : ");
		lsbInfos.append(ldbmd.getDriverName());
		lsbInfos.append("\nURL JDBC : ");
		lsbInfos.append(ldbmd.getURL());
		lsbInfos.append("\nUt       : ");
		lsbInfos.append(ldbmd.getUserName());

		lsbInfos.append("\n*******");
		lsbInfos.append("\nBDs : \n");
		lrsCatalogues = ldbmd.getCatalogs();
		while(lrsCatalogues.next()) {
			lsbInfos.append(lrsCatalogues.getString(1));
			lsbInfos.append("\n");
		}
		lrsCatalogues.close();

		lsbInfos.append("*******");
		lsbInfos.append("\nTables : \n");
		String tTypes[] = null;
		// --- getTables("Catalogue","SchemaPattern","TableNamePattern",tableTypes)
		// --- SchemaPattern : pour  MySQL null ou ""
		// --- Exemple : les tables de la bd dont le nom commence par C tous types confondus.
		// --- TableType : Tables, Views, Synonyms, …
		// --- Le RS a cette structure (cat, tableSchema, tableName, tableType, ...)
		lrsTables = ldbmd.getTables("m2iformationcdidl_cine", "", "", tTypes);
		while(lrsTables.next()) {
			lsbInfos.append(lrsTables.getString(3));
			lsbInfos.append("(");
			lsbInfos.append(lrsTables.getString(4));
			lsbInfos.append(")\n");
		}
		lrsTables.close();

		lsbInfos.append("*******");
		lsbInfos.append("\nColonnes de Villes : \n");
		// --- getColumns("Catalogue","SchemaPattern","TablePattern","ColumnPattern")
		// --- SchemaPattern : pour MySQL null ou ""
		// --- Exemple : les colonnes de la table villes de la bd librairie.
		// --- TableType : Tables, Views, Synonyms,..
		// --- Le RS a cette structure (cat, tableSchema, tableName, columnName, ...)
		lrsCols = ldbmd.getColumns("m2iformationcdidl_cine", "", "Villes", "");
		while(lrsCols.next()) {
			lsbInfos.append(lrsCols.getString(4));
			lsbInfos.append("(");
			lsbInfos.append(lrsCols.getString(6));
			lsbInfos.append(")\n");
		}
		lrsCols.close();

		System.out.println(lsbInfos.toString());
		lcn.close();
	}
	catch(Exception erreur) {
		System.err.println(erreur);
	}

  } /// main

} /// class
