package pl.edu.pw.elka.prm2t.lab1;

/**
 * Nazwa klasy zaczyna się wielką literą, jeśli składa się z wielu wyrazów, każdy z nich zaczyna się także wielką literą.
 * W nazwach klas nie używa się (bez ważnych powodów) podkreślników.
 * 
 * @author Kajetan Rosik
 */
public class NazewnictwoWJavie {
    /**
     * Prędkość światła w próżni (w metrach na sekundę) - stałe nazywamy tylko wielkimi literami, kolejne wyrazy
     * rozdzielamy podkreślnikami. Polskie znaki są dopuszczalne, ale na ogół lepiej ich unikać.
     */
    public static final long PRĘDKOŚĆ_ŚWIATŁA = 299_792_458;
    
    /**
     * Zwykłe zmienne zaczynają się małą literą, kolejne wyrazy zaczynają się wielką literą (notacja kamelowa, ang.
     * camelCase)
     */
    private int zwykłaZmienna = 1;
    
    /**
     * Nazewnictwo metod, argumentów i zmiennych w metodach jest identyczne, jak zmiennych: początek małą literą, kolejne
     * wyrazy wielką literą, bez podkreślników.
     * @param jakiśArgument ten argument służy do przekazania...
     * @return ...
     */
    public long jakaśMetodaWKlasie(int jakiśArgument) {
    	long zmiennaWMetodzie = jakiśArgument + PRĘDKOŚĆ_ŚWIATŁA + zwykłaZmienna;
    	return zmiennaWMetodzie;
    }
    
    /**
     * Getter dla zmiennej, zwykłe zasady jak dla metod.
     * @return ...
     */
    public int getZwykłaZmienna() {
    	return zwykłaZmienna;
    }
    
    /**
     * Wyliczenia są klasami, obowiązują identyczne zasady nazewnictwa. Wyliczane elementy są stałymi, zatem stosuje się zasady
     * dotyczące stałych.
     * @author kmi
     */
    enum WyliczenieKolorów {
    	ZIELONY,
    	ZIELONY_WPADAJĄCY_W_NIEBIESKI,
    	NIEBIESKI
    }
    
    /**
     * Nazewnicwto interfejsów, obowiązują zasady jak dla klas.
     * @author kmi
     */
    public interface JakiśInterfejs {
    	/**
    	 * Metoda, zatem zwykłe zasady nazywania.
    	 * @param parametrMetody ...
    	 */
    	void metodaWInterfejsie(int parametrMetody);
    }
}