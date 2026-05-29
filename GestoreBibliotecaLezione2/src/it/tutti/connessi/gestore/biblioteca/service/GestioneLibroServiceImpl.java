package it.tutti.connessi.gestore.biblioteca.service;

import java.util.ArrayList;

import it.tutti.connessi.gestore.model.Libro;

public class GestioneLibroServiceImpl {
	
	// Il nostro "Database" temporaneo in memoria
    private ArrayList<Libro> inventario = new ArrayList<>();
    private int prossimoId = 1; // Contatore per generare ID automatici

    // [CREATE] Aggiungere un libro
    public void aggiungiLibro(String titolo, String autore) {
        Libro nuovoLibro = new Libro(prossimoId, titolo, autore);
        inventario.add(nuovoLibro);
        System.out.println("✅ Libro aggiunto con successo! ID assegnato: " + prossimoId);
        prossimoId++;
    }

    // [READ] Mostrare tutti i libri
    public void mostraLibri() {
        if (inventario.isEmpty()) {
            System.out.println("📭 La biblioteca è vuota.");
            return;
        }
        System.out.println("\n--- Elenco Libri in Biblioteca ---");
        for (Libro l : inventario) {
            System.out.println(l); // Chiama automaticamente il metodo toString()
        }
    }

    // [UPDATE] Modificare i dati di un libro esistente tramite ID
    public void modificaLibro(int id, String nuovoTitolo, String nuovoAutore) {
        Libro libro = trovaLibroPerId(id);
        if (libro != null) {
            libro.setTitolo(nuovoTitolo);
            libro.setAutore(nuovoAutore);
            System.out.println("🔄 Libro aggiornato con successo!");
        } else {
            System.out.println("❌ Errore: Nessun libro trovato con ID " + id);
        }
    }

    // [DELETE] Eliminare un libro tramite ID
    public void eliminaLibro(int id) {
        Libro libro = trovaLibroPerId(id);
        if (libro != null) {
            inventario.remove(libro);
            System.out.println("🗑️ Libro rimosso dall'inventario.");
        } else {
            System.out.println("❌ Errore: Impossibile eliminare. ID non trovato.");
        }
    }

    // Metodo di utilità interno per cercare un libro nell'ArrayList
    private Libro trovaLibroPerId(int id) {
        for (Libro l : inventario) {
            if (l.getId() == id) {
                return l; // Trovato!
            }
        }
        return null; // Non trovato
    }
    
 // [PRESTITO] Cambia lo stato del libro da disponibile a in prestito
    public void prestaLibro(int id) {
        Libro libro = trovaLibroPerId(id);
        
        if (libro != null) {
            // Controlliamo se il libro è effettivamente disponibile per il prestito
            if (libro.isDisponibile()) {
                libro.setDisponibile(false); // Cambiamo lo stato
                System.out.println("📖 Prestito registrato! Il libro \"" + libro.getTitolo() + "\" è ora in prestito.");
            } else {
                System.out.println("❌ Operazione fallita: Il libro \"" + libro.getTitolo() + "\" è già in prestito ad un altro utente.");
            }
        } else {
            System.out.println("❌ Errore: Impossibile prestare. ID " + id + " non trovato.");
        }
    }

    // [RESTITUZIONE] Riporta lo stato del libro a disponibile
    public void restituisciLibro(int id) {
        Libro libro = trovaLibroPerId(id);
        
        if (libro != null) {
            // Controlliamo se il libro era effettivamente in prestito
            if (!libro.isDisponibile()) {
                libro.setDisponibile(true); // Ritorniamo lo stato a disponibile
                System.out.println("↩️ Restituzione completata! Il libro \"" + libro.getTitolo() + "\" è di nuovo disponibile.");
            } else {
                System.out.println("⚠️ Attenzione: Questo libro risulta già presente in biblioteca, non era in prestito.");
            }
        } else {
            System.out.println("❌ Errore: Impossibile restituire. ID " + id + " non trovato.");
        }
    }

}
