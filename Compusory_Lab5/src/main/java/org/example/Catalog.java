package org.example;

import java.util.ArrayList;
import java.util.List;

public class Catalog {

    private String name;
    private List<Document> documents = new ArrayList<>();

    public Catalog() {
    }

    public Catalog(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }


    public void add(Document doc) {
        documents.add(doc);
    }

    public Document findById(String id) {
        return documents.stream()
                .filter(d -> d.getId().equals(id)).findFirst().orElse(null);
    }

    public void addDocument(Document document) {
    }
}
