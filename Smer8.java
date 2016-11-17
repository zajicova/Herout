import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


/*******************************************************************************
 * Trida slouzici jako vyctovy typ pro 8 hlavnich svetovych stran.
 * Trida je zjednodusenou verzi stejnojmenne tridy z balicku rup.spolecne.
 *
 * @author     Rudolf Pecinovsky
 * @version    2.01, duben 2004
 */
public enum Smer8
{
//== HODNOTY VYCTOVEHO TYPU ====================================================
    
    VYCHOD      (  1,  0, "S",  "VYCHOD",       "VYCHOD"),  
    SEVEROVYCHOD(  1, -1, "SV", "SEVEROVYCHOD", "SEVEROVYCHOD"),
    SEVER       (  0, -1, "S",  "SEVER",        "SEVER"),   
    SEVEROZAPAD ( -1, -1, "SZ", "SEVEROZAPAD",  "SEVEROZAPAD"), 
    ZAPAD       ( -1,  0, "Z",  "ZAPAD",        "ZAPAD"), 
    JIHOZAPAD   ( -1,  1, "JZ", "JIHOZAPAD",    "JIHOZAPAD"), 
    JIH         (  0,  1, "J",  "JIH",          "JIH"), 
    JIHOVYCHOD  (  1,  1, "JV", "JIHOVYCHOD",   "JIHOVYCHOD"), ;  
    
    
//== KONSTANTNI ATRIBUTY TRIDY =================================================
    
    public  static final int SMERU = 8;
    private static final int MASKA = 7;
    
    private static final Map<String,Smer8> nazvy = 
                                        new HashMap<String,Smer8>( SMERU*3 );
    private static final int[][] posun = new int[SMERU][2];

    private static final Smer8[] SMERY = values();    
    
    static
    {
        for( Smer8 s : SMERY )
        {
            posun[s.ordinal()][0] = s.prepravka.dx;
            posun[s.ordinal()][1] = s.prepravka.dy;
            nazvy.put( s.prepravka.zkratka, s );
            nazvy.put( s.prepravka.nazev,   s );
            nazvy.put( s.prepravka.nazevBHC,s );
            s.prepravka = null;
        }
    }
    
//== PROMENNE ATRIBUTY TRIDY ===================================================
//== KONSTANTNI ATRIBUTY INSTANCI ==============================================
//== PROMENNE ATRIBUTY INSTANCI ================================================
    
    private static class Prepravka
    {
        int    dx, dy;
        String zkratka, nazev, nazevBHC;
    }
    Prepravka prepravka;
    
    
//== PRISTUPOVE METODY VLASTNOSTI TRIDY ========================================
//== OSTATNI METODY TRIDY ======================================================

//##############################################################################
    
    /**************************************************************************
     * Vytvori novy smer a zapamatuje si ruzne verze jeho nazvu.
     */
     private Smer8( int dx, int dy, 
                    String zkratka, String nazev, String nazevBHC )
     {
         prepravka = new Prepravka();
         prepravka.dx = dx;
         prepravka.dy = dy;
         prepravka.zkratka = zkratka;
         prepravka.nazev = nazev;
         prepravka.nazevBHC = nazevBHC;
     }
     
     
     
//== KONSTRUKTORY A TOVARNI METODY =============================================
//== PRISTUPOVE METODY ATRIBUTU INSTANCI =======================================
//== VLASTNI METODY INSTANCI ===================================================

    /**************************************************************************
     * Vrati smer otoceny o 180%.
     * Oproti metode celemVzad nepotrebuje pretypovavat vysledek na Smer8.
     *
     * @return Smer objektu po vyplneni prikazu celem vzad
     */
    public Smer8 celemVzad()
    {
        return SMERY[MASKA & (4+ordinal())];
    }


    /**************************************************************************
     * Vrati smer otoceny o 90% vlevo.
     * Oproti metode vlevoVbok nepotrebuje pretypovavat vysledek na Smer8.
     *
     * @return Smer objektu po vyplneni prikazu vlevo v bok
     */
    public Smer8 vlevoVbok()
    {
        return SMERY[MASKA & (2+ordinal())];
    }


    /**************************************************************************
     * Vrati smer otoceny o 90% vpravo.
     * Oproti metode vpravoVbok nepotrebuje pretypovavat vysledek na Smer8.
     *
     * @return Smer objektu po vyplneni prikazu vpravo v bok
     */
    public Smer8 vpravoVbok()
    {
        return SMERY[MASKA & (-2+ordinal())];
    }


    /**************************************************************************
     * Vrati smer otoceny o 45% vlevo.
     * Oproti metode nalevoVpric nepotrebuje pretypovavat vysledek na Smer8.
     *
     * @return Smer objektu po vyplneni prikazu nalevo vpric
     */
    public Smer8 nalevoVpric()
    {
        return SMERY[MASKA & (1+ordinal())];
    }


    /**************************************************************************
     * Vrati smer otoceny o 45% vpravo.
     * Oproti metode napravoVpric nepotrebuje pretypovavat vysledek na Smer8.
     *
     * @return Smer objektu po vyplneni prikazu napravo vpric
     */
    public Smer8 napravoVpric()
    {
        return SMERY[MASKA & (-1+ordinal())];
    }


    /**************************************************************************
     * Vrati x-vou souradnici sousedniho policka v danem smeru.
     *
     * @param x x-ova souradnice stavajiciho policka
     *
     * @return x-ova souradnice sousedniho policka v danem smeru
     */
    public int dalsiX( int x )
    {
        return x + posun[ordinal()][0];
    }


    /**************************************************************************
     * Vrati y-vou souradnici sousedniho policka v danem smeru.
     *
     * @param y y-ova souradnice stavajiciho policka
     *
     * @return y-ova souradnice sousedniho policka v danem smeru
     */
    public int nextY( int y )
    {
        return y + posun[ordinal()][1];
    }


//== SOUKROME A POMOCNE METODY TRIDY ===========================================
//== SOUKROME A POMOCNE METODY INSTANCI ========================================
//== VNORENE A VNITRNI TRIDY ===================================================
//== TESTY A METODA MAIN =======================================================

}

