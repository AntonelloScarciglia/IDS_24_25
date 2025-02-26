package it.cs.unicam.ids.filiera.domainModel.products;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.io.File;
import java.util.List;
@Embeddable
public class Content {

    private File file;
    @ElementCollection
    private List<String> certifications;
    @ElementCollection

    private List<String> cultivationMethods;
    private String description;

    public Content(File file, List<String> certifications, List<String> cultivationMethods, String description) {
        this.file = file;
        this.certifications = certifications;
        this.cultivationMethods = cultivationMethods;
        this.description = description;
    }

    public Content() {

    }

    public File getFile() {
        return file;
    }

    public List<String> getCertifications() {
        return certifications;
    }

    public List<String> getCultivationMethods() {
        return cultivationMethods;
    }

    public String getDescription() {
        return description;
    }
}