import java.awt.Color;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


/*******************************************************************************
 *  Trida Barva definuje skupinu zakladnich barev pro pouziti
 *  pri kresleni tvaru pred zavedenim balicku.
 *  Jeji metody jsou podmnozinou metod stejnojmenne tridy
 *  definovane v balicku rup.spolecne.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Barva
{
    /** Pocet pojmenovanych barev se pri konstrukci nasledujicich instanci 
     *  nacita, a proto musi byt deklarovan pred nimi. */
    private static int pojmenovanych = 0;
    
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    private static final Map<String,Barva> NAZVY = 
                                            new LinkedHashMap<String,Barva>();
    private static final List<Barva> BARVY = new ArrayList<Barva>( 32 );

    public static final Barva CERNA   = new Barva( 0x00, 0x00, 0x00, "cerna",    "cerna"   );
    public static final Barva MODRA   = new Barva( 0x00, 0x00, 0xFF, "modra",    "modra"   );
    public static final Barva CERVENA = new Barva( 0xFF, 0x00, 0x00, "cervena",  "cervena" );
    public static final Barva FIALOVA = new Barva( 0xFF, 0x00, 0xFF, "fialova",  "fialova" );
    public static final Barva ZELENA  = new Barva( 0x00, 0xFF, 0x00, "zelena",   "zelena"  );
    public static final Barva AZUROVA = new Barva( 0x00, 0xFF, 0xFF, "azurova",  "azurova" );
    public static final Barva ZLUTA   = new Barva( 0xFF, 0xFF, 0x00, "zluta",    "zluta"   );
    public static final Barva BILA    = new Barva( 0xFF, 0xFF, 0xFF, "bila",     "bila"    );
    public static final Barva KREMOVA = new Barva( 0xFF, 0xFF, 0xCC, "kremova",  "kremova" );
    public static final Barva SEDA    = new Barva( 0x99, 0x99, 0x99, "seda",     "seda"    );
    public static final Barva OCELOVA = new Barva( 0x00, 0x99, 0xCC, "ocelova",  "ocelova" );
    public static final Barva RUZOVA  = new Barva( 0xFF, 0x99, 0x99, "ruzova",   "ruzova"  );
    public static final Barva HNEDA   = new Barva( 0x99, 0x33, 0x00, "hneda",    "hneda"   );
    public static final Barva KHAKI   = new Barva( 0x99, 0x99, 0x00, "vojenska", "vojenska");
    public static final Barva CIHLOVA = new Barva( 0xFF, 0x66, 0x33, "cihlova",  "cihlova" );
    public static final Barva ZLATA   = new Barva( 0xFF, 0x99, 0x00, "zlata",    "zlata"   );
    public static final Barva STRIBRNA= new Barva( 0xCC, 0xCC, 0xCC, "stribrna", "stribrna");


//== PROMENNE ATRIBUTY TRIDY ===================================================
    
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    private final String nazev;     //Nazev barvy zadavany konstruktoru
    private final Color  color;     //Barva ze standardni knihovny
    private final int    index = pojmenovanych++;


//== PROMENNE ATRIBUTY INSTANCI ================================================
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

    /***************************************************************************
     * Vrati vektor retezcu s nazvy definovanych barev.
     *
     * @return  Vektror retezcu s nazvy znamych barev
     */
    public static String[] getZnameNazvy()
    {
        String[] s = new String[ pojmenovanych ];
        for( int i=-1;   ++i < pojmenovanych;   s[i] = BARVY.get(i).nazev );
        return s;
    }


    /***************************************************************************
     * Vrati vektor definovanych barev.
     *
     * @return  Vektror retezcu s nazvy znamych barev
     */
    public static Barva[] getZnameBarvy()
    {
        //return (Barva[])BARVY.toArray( new Barva[0] );
        return BARVY.toArray( new Barva[0] );
    }



//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori instanci neruhledne pojmenovane barvy 
     * se zadanou velikosti barevnych slozek.
     *
     * @param red       Velikost cervene slozky
     * @param green     Velikost zelene slozky
     * @param blue      Velikost modre slozky
     * @param nazev     Nazev vytvorene barvy
     * @param nazevBHC  Nazev bez hacku a carek
     */
    public Barva( int red, int green, int blue, 
                   String nazev, String nazevBHC )
    {
        this( red, green, blue, 0xFF, nazev, nazevBHC );
    }
    

    /***************************************************************************
     * Vytvori instanci pojmenovane barvy se zadanou velikosti barevnych slozek
     * a hladinou pruhlednosti nastavovanou v kanale alfa.
     *
     * @param red       Velikost cervene slozky
     * @param green     Velikost zelene slozky
     * @param blue      Velikost modre slozky
     * @param alfa      Hladina pruhlednosti: 0=pruhledna, 255=nepruhledna
     * @param nazev     Nazev vytvorene barvy
     * @param nazevBHC  Nazev bez hacku a carek
     */
    public Barva( int red, int green, int blue, int alfa, 
                   String nazev, String nazevBHC )
    {
        color = new Color( red, green, blue, alfa );
        this.nazev = nazev;
        String nhc  = nazev.toLowerCase();
        String nbhc = nazevBHC.toLowerCase();
        if( NAZVY.containsKey( nhc )  ||  NAZVY.containsKey( nbhc ) )
            throw new IllegalArgumentException(
                "\nBarva se zadanym nazvem " + nhc + " ci " + nbhc +
                " jiz existuje" );
        NAZVY.put( nhc,  this );
        NAZVY.put( nbhc, this );
        BARVY.add( index, this );
    }
    

    /***************************************************************************
     * Vytvori instanci nepojmenovane barvy se zadanou velikosti barevnych 
     * slozeka hladinou pruhlednosti nastavovanou v kanale alfa.
     *
     * @param red   Velikost cervene slozky
     * @param green Velikost zelene slozky
     * @param blue  Velikost modre slozky
     * @param alfa  Hladina pruhlednosti: 0=pruhledna, 255=nepruhledna
     */
    public Barva( int red, int green, int blue, int alfa )
    {
        color = new Color( red, green, blue, alfa );
        this.nazev = "Barva(r=" + red + ",g=" + green +
                     ",b=" + blue + ",a=" + alfa + ")" ;
    }
    

    /***************************************************************************
     * Prevede nazev barvy na prislusny objekt typu Barva.
     *
     * @param  nazevBarvy  Nazev pozadovane barvy - lze si vybrat z hodnot:
     *                    "cerna",  "modra",   "cervena", "fialova",
     *                    "zelena", "azurova", "zluta",   "bila". "kremova"
     *
     * @return Instance tridy Barva reprezentujici zadanou barvu
     *
     * @throws IllegalArgumentException neni-li barva (nazev) mezi znamymi
     */
    public static Barva getBarva( String nazevBarvy )
    {
        Barva barva = NAZVY.get( nazevBarvy.toLowerCase() );
        if( barva != null )
            return barva;
        else
            throw new IllegalArgumentException(
                "Takto pojmenovanou barvu neznam." );
    }



//== PRISTUPOVE METODY VLASTNOSTI INSTANCI =====================================

    /***************************************************************************
     * Vrati nazev barvy.
     *
     * @return Retezec definujici zadanou barvu.
     */
    public String getNazev()
    {
        return nazev;
    }


    /***************************************************************************
     * Prevede nami pouzivanou barvu na typ pouzivany kreslitkem.
     * Metoda je pouzivana ve tride AktivniPlatno.
     *
     * @return Instance tridy Color reprezentujici zadanou barvu
     */
    public Color getColor()
    {
        return color;
    }



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Vrati nazev barvy.
     *
     * @return  Nazev barvy
     */
    public String toString()
    {
        return nazev;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================
//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

