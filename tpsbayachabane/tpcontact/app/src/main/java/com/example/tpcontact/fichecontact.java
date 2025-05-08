package com.example.tpcontact;
public class fichecontact {

    public String nom;
    public String prenom;
    public String numero;

    public fichecontact(String n, String p, String num) {
        nom = n;
        prenom = p;
        numero = num;
    }

    public String getNom() { return nom; }
    public void setNom(String n) { nom = n; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String p) { prenom = p; }

    public String getNumero() { return numero; }
    public void setNumero(String num) { numero = num; }
}
