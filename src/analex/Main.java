package analex;

public class Main {
   
    public static void main(String[] args) {
        Token t = new Token(Token.OPREL, Token.IGUAL);
        Cinta m = new Cinta("//54544ff"
                + " "
                + "45");

        Analex c = new Analex(m);
        c.avanzar();
        
        
        System.out.println( c.Preanalisis());
        
       
    }
    
}
