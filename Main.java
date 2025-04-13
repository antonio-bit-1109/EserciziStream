import classes.Person;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static List<Integer> listaNumeri = new ArrayList<>();
    static List<String> listaLettere = new ArrayList<>();
    static List<Person> listaPersone = new ArrayList<>();


    public static void main(String[] args) {

        List<String> listaUnformatted = Arrays.asList("  ciao  ", null, "   ", "mondo", "  java  ");
        String testo = "ciao sono un bambino molto cattivo e questo bambino dice ciao";

        List<List<String>> listaDiListe = Arrays.asList(
                Arrays.asList("a", "b", "c"),
                Arrays.asList("d", "e"),
                Arrays.asList("f", "g", "h")
        );

        listaNumeri.add(1);
        listaNumeri.add(1);
        listaNumeri.add(14);
        listaNumeri.add(13);
        listaNumeri.add(12);
        listaNumeri.add(14);
        listaNumeri.add(41);
        listaNumeri.add(176);
        listaNumeri.add(351);
        listaNumeri.add(1564);

        listaLettere.add("asd");
        listaLettere.add("das");
        listaLettere.add("t56");
        listaLettere.add("fff");
        listaLettere.add("ddd");
        listaLettere.add("aaa");
        listaLettere.add("bbb");
        listaLettere.add("ccc");
        listaLettere.add("fredada");

        listaPersone.add(new Person("gianluca", 23));
        listaPersone.add(new Person("marco", 12));
        listaPersone.add(new Person("franca", 23));
        listaPersone.add(new Person("gioele", 9));
        listaPersone.add(new Person("lorenza", 45));


        printIt(listaNumeri);
        System.out.println("numeri pari --> " + findPari(listaNumeri));
        System.out.println(convertMaiusc(listaLettere));
        System.out.println(sommali(listaNumeri));
        System.out.println(FilterAndSum(listaNumeri));
        System.out.println(findMax(listaNumeri));
        System.out.println(removeDuplicates(listaNumeri));
        System.out.println(orderAlfabetically(listaLettere));
        System.out.println("quante stringhe piu lunghe di 5  --> " + howMuchStringLongerThanFive(listaLettere));
        System.out.println("media lunghezza stringhe --> " + mediumLengthString(listaLettere));
        System.out.println("join la lista in una lista unica separata da ','" + joinToString(listaLettere));
        System.out.println("filtrare oggetti persone per nome" + filterPerson(listaPersone));
        System.out.println(groupByEta(listaPersone));
        System.out.println(clearDirtyList(listaUnformatted));
        System.out.println(countWordsByFirstLetter(listaLettere));
        System.out.println(divideMaggiorenniAndNot(listaPersone));
        System.out.println("quante volte è ripetuta una parola " + findFrequencyEveryWord(testo));
        System.out.println(findParoleChiave(testo, "bambino"));
        System.out.println("frequenza di ogni parola. " + findFrequencyEveryWord(testo));
    }

    //      1. ✅ Dato un List<Integer>, stampa ogni elemento usando forEach.
    private static void printIt(List<Integer> list) {
        list.forEach(System.out::println);
    }

    //  2.  🔍 Filtra una lista di numeri per trovare solo quelli pari.
    private static List<Integer> findPari(List<Integer> list) {
        return list.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
    }

    //   3. 📈 Dato un List<String>, converti ogni stringa in maiuscolo.
    private static List<String> convertMaiusc(List<String> listaStr) {
        return listaStr.stream().map(String::toUpperCase).collect(Collectors.toList());
    }

    //  4.  🔢 Calcola la somma di una lista di numeri con .reduce().
    private static Integer sommali(List<Integer> list) {
        return list.stream().reduce(0, (n1, n2) -> n1 + n2);
    }

    //5. prima filtra per numeri dispari poi somma i numeri nella lista
    private static Integer FilterAndSum(List<Integer> list) {
        return list.stream()
                .filter(num -> num % 2 != 0)
                .reduce(0, (n1, n2) -> n1 + n2);
    }

    //    📊 Trova il numero massimo in una lista.
    private static int findMax(List<Integer> list) {
        return list.stream()
                .mapToInt(v -> v)
                .max().orElseThrow();
    }

    //    🔁 Rimuovi i duplicati da una lista usando .distinct().
    private static List<Integer> removeDuplicates(List<Integer> list) {
        return list.stream().distinct().collect(Collectors.toList());
    }

    //    📝 Ordina una lista di stringhe in ordine alfabetico.
    private static List<String> orderAlfabetically(List<String> list) {
        return list.stream().sorted().collect(Collectors.toList());
    }

    //    🧮 Conta quanti elementi soddisfano una certa condizione (filter + count).
    // quante stringhe sono piu lunghe di 5 caratteri
    private static Long howMuchStringLongerThanFive(List<String> list) {
        return list.stream().filter(str -> str.length() > 5).count();
    }

    //    📐 Calcola la lunghezza media delle stringhe in una lista.
    private static double mediumLengthString(List<String> list) {
        return list.stream()
                .mapToInt(str -> str.length())
                .average()
                .orElse(0);
    }


    //    🔄 Trasforma una lista di stringhe in una sola stringa separata da virgole
//    (usando Collectors.joining(", ")).
    private static String joinToString(List<String> list) {
        return list.stream().collect(Collectors.joining(","));
    }

    //    11. 📚 Dato un List<String>, crea una Map<String, Integer>
//    dove ogni parola è la chiave e il valore è la lunghezza.
    private Map<String, Integer> createMap(List<String> list) {
        Map<String, Integer> m = new HashMap<>();
        list.forEach(s -> m.put(s, s.length()));
        return m;
    }

    //    12. 👨‍👩‍👧‍👦 Dato un List<Person>, filtra tutte le persone
//    con età maggiore di 18 e mappale in un List<String> contenente solo i loro nomi.
    private static List<String> filterPerson(List<Person> listP) {
        return listP.stream()
                .filter(p -> p.getEta() > 18)
                .map(p -> p.getNome())
                .collect(Collectors.toList());

    }

    //    13. 🧾 Raggruppa una lista di oggetti per un campo
    //    (es. persone per età con Collectors.groupingBy).
    // ritorna gia cosi una mappa raggruppata su ogni nome di persona presente nella lista
    private static Map<String, List<Person>> groupByEta(List<Person> listaP) {
        return listaP.stream().collect(Collectors.groupingBy(p -> p.getNome()));
    }

    //    14. 🧹 Pulisci una lista di stringhe da spazi vuoti e null usando filter e map.
    private static List<String> clearDirtyList(List<String> dirtyList) {
        return dirtyList.stream()
                .filter(val -> val != null && !val.isEmpty() && !val.isBlank())
                .map(val -> val.trim())
                .collect(Collectors.toList());
    }

    //    15. 📦 Usa flatMap per trasformare una lista di liste in una singola lista piatta.
    private static List<String> flatList(List<List<String>> listOfLists) {
        return listOfLists.stream()
                .flatMap(innerList -> innerList.stream())
                .collect(Collectors.toList());
    }

    //    16. 📊 Dato un List<String> rappresentante parole,
    //conta quante parole iniziano con ogni lettera dell'alfabeto.
    // avere una mappa risultante del tipo:
//    {
//        a=1,
//        c=2,
//        g=3
//    }
    private static Map<Character, Long> countWordsByFirstLetter(List<String> listaS) {
        return listaS.stream().collect(Collectors.groupingBy(val ->
                val.charAt(0), Collectors.counting()
        ));
    }

    //    17. 🗂️ Crea una Map<Boolean, List<Person>> che separa le persone in due gruppi: maggiorenni e minorenni.
    private static Map<Boolean, List<Person>> divideMaggiorenniAndNot(List<Person> listP) {
        return listP.stream().collect(Collectors.groupingBy(p -> p.getEta() > 18));
    }

    //  19. 🕵️ Dato un testo, crea una Map<String, Long> con la frequenza di ogni parola.
    private static Map<String, Long> findFrequencyEveryWord(String testo) {
        List<String> l = Arrays.stream(testo.split(" ")).toList();
        return l.stream().collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }

    //    19. 🕵️ Dato un testo, crea una Map<String, Long> con la frequenza di ogni parola.
    private Map<String, Long> frequencyEveryWord(String testo) {
        List<String> l = Arrays.stream(testo.split(" ")).toList();
        return l.stream().collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }


    //    20. 📚 Dato un file di testo,
//    leggi tutte le righe in uno stream,
//    filtra quelle contenenti una parola chiave e salvale in una nuova lista.
    private static Map<String, Long> findParoleChiave(String testo, String parolaChiave) {
        List<String> l = Arrays.stream(testo.split(" ")).toList();
        return l.stream().collect(Collectors.groupingBy(word ->
                word, Collectors.filtering(word ->
                word.equals(parolaChiave), Collectors.counting())));
    }
}




