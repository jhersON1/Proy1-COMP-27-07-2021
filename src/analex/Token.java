package analex;

public class Token {
        //Para el NOMBRE del token. 
    public static final int FIN        = 0;
    public static final int ERROR      = 1;
    public static final int PROGRAM    = 2;
    public static final int VAR        = 3;
    public static final int PROCEDURE  = 4;
    public static final int BEGIN      = 5;
    public static final int END        = 6;
    public static final int IF         = 7;
    public static final int THEN       = 8;
    public static final int ELSE       = 9;
    public static final int FOR        = 10;
    public static final int TO         = 11;
    public static final int DO         = 12;
    public static final int DOWNTO     = 13;
    public static final int WHILE      = 14;
    public static final int CASE       = 15;
    public static final int OF         = 16;
    public static final int REPEAT     = 17;
    public static final int UNTIL      = 18;
    public static final int READ       = 19;
    public static final int READLN     = 20;
    public static final int WRITE      = 21;
    public static final int WRITELN    = 22;
    public static final int COMA       = 23;
    public static final int DOSPTOS    = 24;     //":"
    public static final int PTOCOMA    = 25;
    public static final int PA         = 26;     //"("
    public static final int PC         = 27;     //")"
    public static final int ASSIGN     = 28;    //":="
    public static final int NOT        = 29;
    public static final int AND        = 30;
    public static final int OR         = 31;
    public static final int MAS        = 32;
    public static final int MENOS      = 33;
    public static final int POR        = 34;
    public static final int MOD        = 35;
    public static final int DIV        = 36;
    public static final int NUM        = 37;
    public static final int ID         = 38;
    public static final int STRINGctte = 39;
    public static final int OPREL      = 40;
    public static final int TIPO       = 41;
    
        //Atributos del token OPREL
    public static final int IGUAL = 0;
    public static final int MEN   = 1;
    public static final int MAY   = 2;
    public static final int MEI   = 3;
    public static final int MAI   = 4;
    public static final int DIS   = 5;
    
        //Atributos del token TIPO
    
    public static final int INTEGER = -2;
    public static final int BOOLEAN = -3;
      
        //Campos de la class
    private int nom, atr;   //<nom, atr>
    
    public Token(){
       this(FIN); 
    }
    
    public Token(int nombre){
        this(nombre, 0);
    }
    
    public Token(int nombre, int atributo){
        nom = nombre;   atr=atributo;
    }

    
    public void set(int nombre, int atributo){
        nom = nombre;   atr=atributo;
    }

    public void setNom(int nom) {
        this.nom = nom;
    }

    public void setAtr(int atr) {
        this.atr = atr;
    }

    public int getNom() {
        return nom;
    }

    public int getAtr() {
        return atr;
    }
    
 
//---------- 
    @Override
    public String toString(){
        return "<" + get(NOMtokenSTR, nom) + "," + atrToString() + ">";
    }
       
    private String atrToString(){   //Corrutina de toString()
        if (FIN <= nom && nom <=DIV)
            return "_";
        
        if (nom == OPREL)
            return get(OPRELstr, atr);
        
        if (nom == TIPO)
            return get(TIPOstr, atr-BOOLEAN);
        
        return ""+atr;
    }

    private String get(String v[], int i){
        try {
            return v[i]; 
        } catch (Exception e) {
            return DESCONOCIDO;
        }
    }
    
    
    private static final String DESCONOCIDO = "??";
    
    private static final String OPRELstr[]={"IGUAL", "MEN", "MAY", "MEI", "MAI", "DIS"};
    private static final String TIPOstr[] ={"BOOLEAN", "INTEGER"};
    
    private static final String NOMtokenSTR[] ={
        "FIN","ERROR",
        "PROGRAM","VAR","PROCEDURE","BEGIN","END",
        "IF","THEN","ELSE","FOR","TO","DO","DOWNTO","WHILE","CASE","OF","REPEAT","UNTIL",
        "READ","READLN","WRITE","WRITELN",
        "COMA","DOSPTOS","PTOCOMA","PA","PC","ASSIGN",
        "NOT","AND","OR","MAS","MENOS","POR","MOD","DIV",
        "NUM","ID","STRINGctte","OPREL","TIPO"
    };  
}
