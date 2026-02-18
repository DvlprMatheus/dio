package br.com.dvlprmatheus.desafio.dominio;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Bootcamp {
    private String name;
    private String description;
    private final LocalDate startDate = LocalDate.now();
    private final LocalDate endDate = startDate.plusDays(45);
    private Set<Dev> devsInscriptions = new HashSet<>();
    private Set<Content> contents = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Set<Dev> getDevsInscriptions() {
        return devsInscriptions;
    }
    
    public void setDevInscription(Set<Dev> devsInscriptions) {
        this.devsInscriptions = devsInscriptions;
    }

    public Set<Content> getContents() {
        return contents;
    }

    public void setContent(Set<Content> contents) {
        this.contents = contents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bootcamp bootcamp = (Bootcamp) o;
        return Objects.equals(name, bootcamp.name) && Objects.equals(description, bootcamp.description) && Objects.equals(startDate, bootcamp.startDate) && Objects.equals(endDate, bootcamp.endDate) && Objects.equals(devsInscriptions, bootcamp.devsInscriptions) && Objects.equals(contents, bootcamp.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, startDate, endDate, devsInscriptions, contents);
    }
}
