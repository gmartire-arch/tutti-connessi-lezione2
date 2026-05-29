package it.tutti.connessi.gestore.biblioteca.model;

public class Libro {

	private int id;
    private String titolo;
    private String autore;
    private boolean disponibile;

    // Costruttore
    public Libro(int id, String titolo, String autore) {
        this.id = id;
        this.titolo = titolo;
        this.autore = autore;
        this.disponibile = true; // Di default un nuovo libro è disponibile
    }

    // Getter e Setter (Incapsulamento)
    public int getId() { return id; }
    public String getTitolo() { return titolo; }
    public void setTitolo(String titolo) { this.titolo = titolo; }
    public String getAutore() { return autore; }
    public void setAutore(String autore) { this.autore = autore; }
    public boolean isDisponibile() { return disponibile; }
    public void setDisponibile(boolean disponibile) { this.disponibile = disponibile; }

    // Un metodo comodo per stampare velocemente i dati del libro
    @Override
    public String toString() {
        String stato = disponibile ? "Disponibile" : "In Prestito";
        return "[" + id + "] " + titolo + " - " + autore + " (" + stato + ")";
    }

}
