/*******************************************************************************
 * Trida pro praci s obdelnikem na virtualnim platne.
 *
 * Vychozi podoba tridy urcena pro prvni seznameni s tridami a objekty.
 *   
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public class Obdelnik
{
//== KONSTANTNI ATRIBUTY TRIDY =================================================

    /** Pocatecni barva nakreslene instance v pripade,
     *  kdy uzivatel zadnou pozadovanou barvu nezada -
     *  pro obdelnik Barva.CERVENA. */
    public static final Barva IMPLICITNI_BARVA = Barva.CERVENA;

    /** Platno, na ktere se bude instance kreslit. */
    private static final Platno PLATNO = Platno.getPlatno();



//== PROMENNE ATRIBUTY TRIDY ===================================================

    /** Pocet pixelu, o nez se instance posune 
     *  po bezparametrickem posunovem povelu */
    private static int krok = 50;

    /** Pocet vytvorenych instanci */
    private static int pocet = 0;



//== KONSTANTNI ATRIBUTY INSTANCI ==============================================

    /** Poradi vytvoreni dane instance v ramci tridy. */
    private final int poradi = ++pocet;

    /** Nazev sestavajici z nazvu tridy a poradi instance */
    private String nazev = "Obdelnik_" + poradi;



//== PROMENNE ATRIBUTY INSTANCI ================================================

    private int    xPos;    //Bodova x-ova souradnice pocatku
    private int    yPos;    //Bodova y-ova souradnice pocatku
    private int    sirka;   //sirka v bodech
    private int    vyska;   //Vyska v bodech
    private Barva  barva;   //Barva instance



//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================

    /***************************************************************************
     * Vrati velikost implicitniho kroku, o ktery se instance presune
     * pri volani bezparametrickych metod presunu.
     *
     * @return Velikost implicitniho kroku v bodech
     */
     public static int getKrok()
     {
         return krok;
     }


    /***************************************************************************
     * Nastavi velikost implicitniho kroku, o ktery se instance presune
     * pri volani bezparametrickych metod presunu.
     *
     * @param velikost  Velikost implicitniho kroku v bodech;<br/>
     *                  musi platit:  0 <= velikost <= 100
     */
    public static void setKrok( int velikost )
    {
        if( (velikost < 0)  || (velikost > 100) )
            throw new IllegalArgumentException(
                "Krok musi byt z intervalu <0; 100>." );
        krok = velikost;
    }



//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
//== KONSTRUKTORY A TOVARNI METODY =============================================

    /***************************************************************************
     * Vytvori novou instanci s implicitnimi rozmery, umistenim a barvou.
     * Instance bude umistena v levem hornim rohu platna 
     * a bude mit implicitni barvu, 
     * vysku 20 bodu a sirku 40 bodu.
     */
    public Obdelnik()
    {
        this( 0, 0, 2*krok, krok );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanou polohou a rozmery 
     * a implicitni barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   Sirka vytvarene instance,  sirka >= 0
     * @param vyska   Vyska vytvarene instance,  vyska >= 0
     */
    public Obdelnik( int x, int y, int sirka, int vyska )
    {
        this( x, y, sirka, vyska, IMPLICITNI_BARVA );
    }


    /***************************************************************************
     * Vytvori novou instanci se zadanymi rozmery, polohou a barvou.
     *
     * @param x       x-ova souradnice instance, x>=0, x=0 ma levy okraj platna
     * @param y       y-ova souradnice instance, y>=0, y=0 ma horni okraj platna
     * @param sirka   Sirka vytvarene instance,  sirka >= 0
     * @param vyska   Vyska vytvarene instance,  vyska >= 0
     * @param barva   Barva vytvarene instance
     */
    public Obdelnik( int x, int y, int sirka, int vyska, Barva barva )
    {
        //Test platnosti parametru
        if( (x<0) || (y<0) || (sirka<0) || (vyska<0) )
            throw new IllegalArgumentException(
                "\nParametry nemaji povolene hodnoty: x="
                + x + ", y=" + y + ", sirka=" + sirka + ", vyska=" + vyska );

        //Parametry akceptovany --> muzeme tvorit
        this.xPos  = x;
        this.yPos  = y;
        this.sirka = sirka;
        this.vyska = vyska;
        this.barva = barva;
        nakresli();
    }



//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================

    /***************************************************************************
     * Vrati x-ovou souradnici pozice instance.
     *
     * @return  x-ova souradnice.
     */
    public int getX()
    {
        return xPos;
    }


    /***************************************************************************
     * Vrati y-ovou souradnici pozice instance.
     *
     * @return  y-ova souradnice.
     */
    public int getY()
    {
        return yPos;
    }


    /***************************************************************************
     * Nastavi novou pozici instance.
     *
     * @param x   Nova x-ova pozice instance
     * @param y   Nova y-ova pozice instance
     */
    public void setPozice(int x, int y)
    {
        smaz();
        xPos = x;
        yPos = y;
        nakresli();
    }


    /***************************************************************************
     * Vrati sirku instance.
     *
     * @return  Sirka instance v bodech
     */
     public int getSirka()
     {
         return sirka;
     }


    /***************************************************************************
     * Vrati vysku instance.
     *
     * @return  Vyska instance v bodech
     */
     public int getVyska()
     {
         return vyska;
     }


    /***************************************************************************
     * Nastavi novy "ctvercovy" rozmer instance -
     * na zadany rozmer se nastavi vyska i sirka.
     *
     * @param rozmer  Nove nastavovany rozmer v obou smerech; rozmer>0
     */
    public void setRozmer(int rozmer)
    {
        setRozmer( rozmer, rozmer );
    }


    /***************************************************************************
     * Nastavi nove rozmery instance.
     *
     * @param sirka    Nove nastavovana sirka; sirka>0
     * @param vyska    Nove nastavovana vyska; vyska>0
     */
    public void setRozmer(int sirka, int vyska)
    {
        if( (sirka < 0) || (vyska < 0) )
            throw new IllegalArgumentException(
                "Rozmery musi byt kladne: sirka=" + sirka + ", vyska=" + vyska );
        smaz();
        this.sirka = sirka;
        this.vyska = vyska;
        nakresli();
    }


    /***************************************************************************
     * Vrati barvu instance.
     *
     * @return  Instance tridy Barva definujici nastavenou barvu.
     */
    public Barva getBarva()
    {
        return barva;
    }


    /***************************************************************************
     * Nastavi novou barvu instance.
     *
     * @param nova  Pozadovana nova barva.
     */
    public void setBarva(Barva nova)
    {
        barva = nova;
        nakresli();
    }


    /***************************************************************************
     * Vrati nazev instance, tj. nazev jeji tridy nasledovany poradim.
     *
     * @return  Retezec s nazvem instance.
     */
     public String getNazev()
     {
        return nazev;
     }



//== PREKRYTE METODY IMPLEMENTOVANYCH ROZHRANI =================================
//== PREKRYTE ABSTRAKTNI METODY RODICOVSKE TRIDY ===============================
//== PREKRYTE KONKRETNI METODY RODICOVSKE TRIDY ================================

    /***************************************************************************
     * Prevede instanci na retezec. Pouziva se predevsim pri ladeni.
     * 
     * @return Retezcova reprezentace dane instance.      
     */
    public String toString()
    {
        return nazev + ": x=" + xPos + ",y=" + yPos + 
               ",sirka=" + sirka + ",vyska=" + vyska +
               ", barva=" + barva;
    }



//== NOVE ZAVEDENE METODY INSTANCI =============================================

    /***************************************************************************
     * Vykresli obraz sve instance na platno.
     */
    public void nakresli()
    {
        PLATNO.setBarvaPopredi( barva );
        PLATNO.zapln(new java.awt.geom.Rectangle2D.Double
                         (xPos, yPos, sirka, vyska) );
    }


    /***************************************************************************
     * Smaze obraz sve instance z platna (nakresli ji barvou pozadi platna).
     */
    public void smaz()
    {
        PLATNO.setBarvaPopredi( PLATNO.getBarvaPozadi() );
        PLATNO.zapln(new java.awt.geom.Rectangle2D.Double
                         (xPos, yPos, sirka, vyska));
    }


    /***************************************************************************
     * Presune instanci o zadany pocet bodu vpravo,
     * pri zaporne hodnote parametru vlevo.
     *
     * @param vzdalenost Vzdalenost, o kterou se instance presune.
     */
    public void posunVpravo(int vzdalenost)
    {
        setPozice( xPos+vzdalenost, yPos );
    }


    /***************************************************************************
     * Presune instanci o krok bodu vpravo.
     */
    public void posunVpravo()
    {
        posunVpravo( krok );
    }


    /***************************************************************************
     * Presune instanci o krok bodu vlevo.
     */
    public void posunVlevo()
    {
        posunVpravo( -krok );
    }


    /***************************************************************************
     * Presune instanci o zadany pocet bodu dolu,
     * pri zaporne hodnote parametru nahoru.
     *
     * @param vzdalenost   Pocet bodu, o ktere se instance presune.
     */
    public void posunDolu(int vzdalenost)
    {
        setPozice( xPos, yPos+vzdalenost );
    }


    /***************************************************************************
     * Presune instanci o krok bodu dolu.
     */
    public void posunDolu()
    {
        posunDolu( krok );
    }


    /***************************************************************************
     * Presune instanci o krok bodu nahoru.
     */
    public void posunVzhuru()
    {
        posunDolu( -krok );
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================
}

