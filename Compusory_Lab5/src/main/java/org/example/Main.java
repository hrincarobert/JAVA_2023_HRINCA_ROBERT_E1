package org.example;

public class Main {
    public static void main(String[] args) {

        CatalogManager catalogManager = new CatalogManager();
        catalogManager.addDocument("1", "Vogue", "C:\\SE");
        catalogManager.addDocument("2", "Elle", "C:\\SE");
        System.out.println(catalogManager.getCatalog());


    }
}