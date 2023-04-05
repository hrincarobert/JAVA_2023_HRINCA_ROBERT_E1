package org.example;

public class CatalogManager {


        private Catalog catalog;

        public CatalogManager() {
            catalog = new Catalog();
        }

        public void addDocument(String id, String name, String path) {
            Document document = new Document(id, name, path);
            catalog.addDocument(document);
        }

        public void saveCatalogToJson(String filename) {
            // Use Jackson or other library to save the catalog to a JSON file
        }

        public void loadCatalogFromJson(String filename) {
            // Use Jackson or other library to load the catalog from a JSON file
        }

        public Catalog getCatalog() {
            return catalog;
        }
}
