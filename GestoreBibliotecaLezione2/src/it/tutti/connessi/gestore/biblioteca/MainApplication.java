package it.tutti.connessi.gestore.biblioteca;

import java.util.InputMismatchException;
import java.util.Scanner;

import it.tutti.connessi.gestore.biblioteca.service.GestioneLibroServiceImpl;

public class MainApplication {
	
	public static void main(String[] args) {
    
		GestioneLibroServiceImpl miaBiblioteca = new GestioneLibroServiceImpl();
        Scanner scanner = new Scanner(System.in);
        boolean inEsecuzione = true;

        miaBiblioteca.aggiungiLibro("I Promessi Sposi", "Alessandro Manzoni");
        miaBiblioteca.aggiungiLibro("Il Signore degli Anelli", "J.R.R. Tolkien");

        System.out.println("📚 Benvenuto nel Gestionale della Biblioteca! 📚");

        while (inEsecuzione) {
            System.out.println("\n--- MENU PRINCIPALE ---");
            System.out.println("1. Mostra tutti i libri (Read)");
            System.out.println("2. Aggiungi un nuovo libro (Create)");
            System.out.println("3. Modifica un libro esistente (Update)");
            System.out.println("4. Elimina un libro (Delete)");
            System.out.println("5. Registra PRESTITO libro");
            System.out.println("6. Registra RESTITUZIONE libro");
            System.out.println("7. Esci dal programma");
            System.out.print("Scegli un'opzione (1-7): ");

            // Dichiariamo la variabile scelta fuori dal try per poterla usare nello switch
            int scelta = 0; 

            try {
                scelta = scanner.nextInt();
                scanner.nextLine(); // Pulisce il buffer dopo l'invio del numero corretto
                
            } catch (InputMismatchException e) {
                // 2. GESTIONE DELL'ERRORE: l'utente ha inserito del testo invece di un numero
                System.out.println("⚠️ ERRORE: Devi inserire un numero intero, non dei caratteri!");
                
                scanner.nextLine(); // FONDAMENTALE: Pulisce il buffer "masticando" l'input errato
                
                continue; // Ricomincia il ciclo while da capo, saltando lo switch sotto
            }

            // Se arriviamo qui, l'input era un numero valido e possiamo controllare lo switch
            switch (scelta) {
                case 1:
                    miaBiblioteca.mostraLibri();
                    break;
                case 2:
                    System.out.print("Inserisci il titolo del libro: ");
                    String titolo = scanner.nextLine();
                    System.out.print("Inserisci l'autore del libro: ");
                    String autore = scanner.nextLine();
                    miaBiblioteca.aggiungiLibro(titolo, autore);
                    break;
                case 3:
                    // Nota: Anche qui dentro, per essere sicuri al 100%, l'ID andrebbe protetto con un try-catch!
                    System.out.print("Inserisci l'ID del libro da modificare: ");
                    int idModifica = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Inserisci il NUOVO titolo: ");
                    String nuovoTitolo = scanner.nextLine();
                    System.out.print("Inserisci il NUOVO autore: ");
                    String nuovoAutore = scanner.nextLine();
                    miaBiblioteca.modificaLibro(idModifica, nuovoTitolo, nuovoAutore);
                    break;
                case 4:
                    System.out.print("Inserisci l'ID del libro da eliminare: ");
                    int idElimina = scanner.nextInt();
                    miaBiblioteca.eliminaLibro(idElimina);
                    break;
                case 5:
                    System.out.print("Inserisci l'ID del libro da prendere in prestito: ");
                    int idPrestito = scanner.nextInt();
                    miaBiblioteca.prestaLibro(idPrestito);
                    break;
                case 6:
                    System.out.print("Inserisci l'ID del libro da restituire: ");
                    int idRestituzione = scanner.nextInt();
                    miaBiblioteca.restituisciLibro(idRestituzione);
                    break;
                case 7:
                    System.out.println("Grazie per aver usato il gestionale. Arrivederci! 👋");
                    inEsecuzione = false;
                    break;
                default:
                    System.out.println("⚠️ Opzione non valida. Scegli un numero tra 1 e 7.");
            }
        }
        scanner.close();
    }
		
		
    

}
