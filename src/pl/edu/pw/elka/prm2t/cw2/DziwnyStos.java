package pl.edu.pw.elka.prm2t.cw2;

import java.util.Objects;
import java.util.Random;

import static pl.edu.pw.elka.prm2t.GeneralUtil.prn;

/**
 * Klasa realizująca stos wg zasad określonych w poleceniu niżej. 
 * </br>
 * Napisz klasę DziwnyStos, której obiekty przechowują liczby całkowite i pozwalają na wkładanie i usuwanie elementów
 * wg zasad opisanych dalej w metodach. Klasa ma następujące pola prywatne:</br>
 * </br>
 * private int [] liczby; – tablica, w której przechowywane są liczby</br>
 * private int wskaźnikStosu = 0; – pierwsze wolne miejsce na stosie</br>
 * </br>
 * Klasa ma następujące metody:</br>
 * Konstruktor bezparametrowy, który przydziela pamięć na stos o wielkości N elementów</br>
 * </br>
 * Metodę void włóżNaStos(), która przyjmuje jeden argument typu int – liczbę wkładaną na stos; włożenie polega na
 * wpisaniu parametru metody do tablicy pod indeksem wskaźnikStosu, następnie inkrementacji tej zmiennej, w celu
 * przygotowania na kolejne włożenia kolejnych elementów; w przypadku osiągnięcia aktualnej wielkości tablicy należy
 * przydzielić nową tablicę (o rozmiarze powiększonym o G) i skopiować do niej zawartość starej tablicy aktualizując
 * odpowiednio zmienne</br>
 * </br>
 * Bezparametrową metodę int zdejmijZeStosu(), która pobiera wartość z tablicy elementów spod indeksu o jeden mniejszego
 * niż wskaźnikStosu i zwraca tę liczbę dekrementując wskaźnikStosu; w przypadku, gdy stos jest pusty (wskaźnikStosu ma
 * warość 0) metoda zgłasza wyjątek klasy EmptyStack (napisz tę klasę, jako wyjątek nieweryfikowany)</br>
 * </br>
 * Metodę boolean usuń(), która usuwa pierwsze wystąpienie podanej jako parametr liczby, jeżeli znajduje się ona w
 * stosie, tzn. jeżeli podana liczba występuje więcej niż jeden raz, to usuwane jest jedynie pierwsze jej wystąpienie
 * – należy zadbać o zachowanie własności stosu wynikającej z działania metod włóżNaStos i zdejmijZeStosu; metoda
 * zwraca true, gdy udało się usunąć podany element i false w przeciwnym przypadku</br>
 * </br>
 * Metodę toString() która zwraca zawartość stosu w postaci napisu, np. „3 43 1 -23 0 -1” oraz metody equals() i hashCode()
 * wg standardowych zasad.</br>
 * </br>
 * Komentarze dokumentujące pisz w miejscach nieoczywistych. Napisz prosty program testujący wszystkie metody. Przyjmij
 * stałe N=7, G=75%.
 * 
 * @author Michał Sergiel, Kajetan Rosik
 */
public class DziwnyStos {
	/**
	 * Początkowa wielkość stosu.
	 */
	private static final int N = 7;
	
	/**
	 * Współczynnik rozrostu stosu, gdy aktualna pamięć staje się niewystarczająca.
	 */
	private static final double G = 0.75;
	
	/**
	 * Tablica liczb przechowywanych w tym dziwnym stosie. 
	 */
	private int[] liczby = new int[N]; 
	
	/**
	 * Wskaźnik stosu - oznacza pierwsze wolne miejsce w tablicy {@link #liczby}.
	 */
	private int wskaźnikStosu = 0;
	
	/**
	 * Konstruktor bezparametrowy, który przydziela pamięć na stos o wielkości {@link #N} elementów.
	 */
	public DziwnyStos() {
		/* nie trzeba tego robić, bo obie zmienne są ustawione właśnie tak w etapie inicjacji
		 * obiektu - w ogóle można usunąć ten konstruktor i wszystko także będzie działać
		 * - kompilator dogeneruje <i>konstruktor domyślny</i>! 
		 */
		//liczby = new int[N];
		//wskaźnikStosu = 0;
	}
	
	/**
	 * Wkłada na stos podaną liczbę.
	 * @param element liczba wkładana na stos.
	 */
	public void włóżNaStos(int element) {
		if (wskaźnikStosu >= liczby.length) {
			int[] nowaTablica = new int[liczby.length + (int)(liczby.length * G)];
			System.arraycopy(liczby, 0, nowaTablica, 0, liczby.length);
			liczby = nowaTablica;
		}
		liczby[wskaźnikStosu++] = element;
	}
	
	/**
	 * Wersja minimum klasy wyjątku sygnalizującego próbę zdjęcia elementu z pustego stosu.
	 * @author Michał Sergiel, Kajetan Rosik
	 */
	//@SuppressWarnings("serial")
	public static class StackEmptyException extends IllegalStateException {
	}
	
	/**
	 * @return wartość zdjęta ze stosu.
	 * @throws StackEmptyException gdy stos jest pusty.
	 */
	public int zdejmijZeStosu() throws StackEmptyException {
		if (wskaźnikStosu <= 0) {
			throw new StackEmptyException();
		}
		return liczby[--wskaźnikStosu];
	}
	
	/**
	 * Usuwa pierwsze wystąpienie podanej jako parametr liczby. Nie narusza własności stosu.
	 * @param element liczba do usunięcia.
	 * @return zwraca {@code true} jeśli znaleziono element i faktycznie go usunięto.
	 */
	public boolean usuń(int element) {
		for (int i = 0; i < wskaźnikStosu; i++) {
			if (liczby[i] == element) {
				--wskaźnikStosu;
		    	System.arraycopy(liczby, i + 1, liczby, i, wskaźnikStosu - i);
		       	return true;
			}
		}
		return false;
	}
	
	@Override
    public String toString(){
    	StringBuilder sb = new StringBuilder(        
    			String.format("%s{length=%d, wskaźnikStosu=%d, liczby=[",
        		getClass().getSimpleName(),liczby.length, wskaźnikStosu));
        for (int i = 0; i < wskaźnikStosu; i++) {
            sb.append(liczby[i]).append(", ");
        }
        if (wskaźnikStosu > 0) {
        	sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]}");
        return sb.toString();
    }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		for (int i = 0; i < wskaźnikStosu; i++) {
			result = prime * result + liczby[i];
		}
		// poniższe nie ma zastosowania... ;) dlaczego?
		// result = prime * result + Arrays.hashCode(liczby);

		//>> ponieważ Arrays.hashCode(liczby) używa całą tablicę liczby, więc jeśli zawarte są w niej nieistotne elementy to wynik może być błędny
		result = prime * result + Objects.hash(wskaźnikStosu);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DziwnyStos)) {
			return false;
		}
		DziwnyStos other = (DziwnyStos) obj;
 		// poniższe if i for można (wydawałoby się...) zastąpić:
		// return Arrays.equals(liczby, other.liczby) && wskaźnikStosu == other.wskaźnikStosu;
		// ale okazuje się, że nie można! ;) dlaczego?

		//>> ponieważ Arrays.equals(liczby, other.liczby) porównuje całe tablice, zamiast tylko tej istotnej części, czyli wskaźnikStosu
		if (wskaźnikStosu != other.wskaźnikStosu) {
			return false;
		}
		for (int i = 0; i < wskaźnikStosu; i++) {
			if (liczby[i] != other.liczby[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param args nieużywane.
	 */
	public static void main(String[] args) {
        DziwnyStos st = new DziwnyStos();
        // testy wkładania/zdejmowania
        prn("st = new DziwnyStos(), st=%s",  st);
        long seed = System.currentTimeMillis();
        Random r = new Random(seed);
        int rng = 10, v, n = 33;
        for (int i = 0; i < n; i++) {
        	st.włóżNaStos(v = r.nextInt(rng));
            prn("st.włóżNaStos(%d), st=%s",  v, st);
        }
        while (true) {
        	try {
        		v = st.zdejmijZeStosu();
        		prn("st.zdejmijZeStosu()=%d, st=%s", v, st);
        	} catch (StackEmptyException es) {
        		break;
        	}
        }
        // testy usuwania
        boolean b = st.usuń(v = 0);
        prn("usuwanie z pustego stosu, st.usuń(%d)=%b, st=%s", v, b, st);
        n = 7;
        for (int i = 0; i < n; i++) {//ponowne wypełnienie stosu
        	st.włóżNaStos(v = i);
            prn("st.włóżNaStos(%d), st=%s",  v, st);
        }
        b = st.usuń(v = 3);
        prn("usuwanie ze środka, st.usuń(%d)=%b, st=%s", v, b, st);
        b = st.usuń(v = 0);
        prn("usuwanie z początku, st.usuń(%d)=%b, st=%s", v, b, st);
        b = st.usuń(v = 6);
        prn("usuwanie z końca, st.usuń(%d)=%b, st=%s", v, b, st);
        b = st.usuń(v = 44);
        prn("usuwanie niewystępującej liczby, st.usuń(%d)=%b, st=%s", v, b, st);
        
        // testy equals/hashcode
        DziwnyStos s1 = new DziwnyStos();
        DziwnyStos s2 = new DziwnyStos();
        prn("porównanie pustych stosów, s1.equals(s2)=%b, s1=%s, s2=%s", s1.equals(s2), s1, s2);
        prn("s1.hashCode()=%d, s2.hashCode()=%d", s1.hashCode(), s2.hashCode());
        s1.włóżNaStos(10);
        s1.włóżNaStos(10);
        s1.włóżNaStos(10);
        s1.włóżNaStos(10);
        s1.zdejmijZeStosu();
        s2.włóżNaStos(10);
        s2.włóżNaStos(10);
        s2.włóżNaStos(10);
        prn("porównanie stosów, s1.equals(s2)=%b, s1=%s, s2=%s", s1.equals(s2), s1, s2);
        prn("s1.hashCode()=%d, s2.hashCode()=%d", s1.hashCode(), s2.hashCode());
        s2.usuń(10);
        prn("porównanie stosów, s1.equals(s2)=%b, s1=%s, s2=%s", s1.equals(s2), s1, s2);
        prn("s1.hashCode()=%d, s2.hashCode()=%d", s1.hashCode(), s2.hashCode());
	}
}
