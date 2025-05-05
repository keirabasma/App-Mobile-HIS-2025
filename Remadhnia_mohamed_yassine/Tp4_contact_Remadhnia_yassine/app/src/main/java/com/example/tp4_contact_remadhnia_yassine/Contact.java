package com.example.tp4_contact_remadhnia_yassine;

public class Contact {
    private String nom;
    private String prenom;
    private String numero;

    public Contact(String nom, String prenom, String numero) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero = numero;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumero() {
        return numero;
    }
}
