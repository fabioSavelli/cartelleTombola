package com.example.cartellatombola.utility;

public class Costanti {

    public final static int NUMERO_MASSIMO_CARTELLE = 10;
    public final static String TITOLO_CARTELLA = "Cartella n° ";

    public class MessaggiErrore {
        public static final String CARTELLA_NON_VALIDA = "La cartella creata non è valida. Controllare se siano presenti 5 numeri per ogni riga, che i numeri sulla stessa colonna siano della stessa decina e che non vi siano duplicati.";
        public static final String NUMERO_MASSIMO_CARTELLE = "Non è possibile creare la cartella perché è stato raggiunto il limite massimo di cartelle. Cancellare una o più cartelle e riprovare.";
        public static final String NESSUNA_CARTELLA_PRESENTE = "Non sono state trovate cartelle. Creare una nuova cartella e riprovare.";
        public static final String CONFERMA_CANCELLAZIONE = "Sei sicuro di voler cancellare la cartella n° ";
    }

    public class Save {
        public final static int READ_BLOCK_SIZE = 100;
        public final static String NOME_FILE_CARTELLE = "salvataggi_cartelle.xml";

        public class TagNameCartelle {
            public static final String CARTELLA = "cartella";
            public static final String RIGA1 = "riga1";
            public static final String RIGA2 = "riga2";
            public static final String RIGA3 = "riga3";
        }

    }
}
