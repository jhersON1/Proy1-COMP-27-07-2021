package analex;

public class Analex {   //Es una cinta de tokens
    private Cinta M;
    private Token R;        //Result del dt (Preanalisis)
    private String ac;
    private int pos;        //Posición de inicio del lexema del preanalisis(), calculado en el dt(). 
                            //Use Cinta.getPos() o sea pos=M.getPos();
    
    //FALTA IMPLEMENTAR LA TPC
    
    public Analex(Cinta c){
        M = c;
        R = new Token();
        init();
    }
    
    public final void init(){
        M.init();
        avanzar();      //Calcular el primer preanalisis.
    }
    
    public Token Preanalisis(){
        return R;
    }
    
    public String lexema(){
        return ac;
    }
    
    public void avanzar(){
       dt();
       resaltar();
    }
    
    private void dt(){
       int estado = 0;
       ac = "";
       while (true){
        char c = M.cc();
        switch (estado){
            case 0:
               if (espacio(c)){
                   M.avanzar();
                   estado = 0;
               } else{
                   pos=M.getPos();
                   if (c == '/'){
                     M.avanzar();
                     estado = 3;
                   } else{
                       if (digito(c)) {
                           ac = String.valueOf(c); 
                           M.avanzar();
                           estado = 1;
                       } else{
                           if (letra(c)){
                               ac = String.valueOf(c); 
                               M.avanzar();
                               estado = 8;
                           }else {
                               if (c == '{') {
                                  M.avanzar();
                                  estado = 10;
                               }else{
                                  if (c == '('){
                                      ac = String.valueOf(c); 
                                      M.avanzar();
                                      estado = 11;
                                  }else {
                                      if (c == '<'){
                                          ac = String.valueOf(c);
                                          M.avanzar();
                                          estado = 15;
                                      }else {
                                          if (c == '>'){
                                              ac = String.valueOf(c);
                                              M.avanzar();
                                              estado  = 19;
                                          }else {
                                              if (c == '='){
                                                  ac = String.valueOf(c);
                                                  M.avanzar();
                                                  estado = 22;
                                              }else {
                                                  if (c == '!'){
                                                      ac = String.valueOf(c);
                                                      M.avanzar();
                                                      estado = 23;
                                                  }else{
                                                      if (c == '"'){
                                                          ac = String.valueOf(c);                                                        
                                                          M.avanzar();
                                                          estado = 25;
                                                      }else{
                                                          if (c == ':'){
                                                              ac = String.valueOf(c);
                                                              M.avanzar();
                                                              estado = 27;
                                                          }else {
                                                              if (c == ','){
                                                                  ac = String.valueOf(c);
                                                                  M.avanzar();
                                                                  estado = 30;
                                                              }else {
                                                                  if (c == ';'){
                                                                      ac = String.valueOf(c);
                                                                      M.avanzar();
                                                                      estado = 31;
                                                                  }else {
                                                                      if (c == ')'){
                                                                          ac = String.valueOf(c);
                                                                          M.avanzar();
                                                                          estado = 32;
                                                                      }else{
                                                                          if (c == '&'){
                                                                              ac = String.valueOf(c);
                                                                              M.avanzar();
                                                                              estado = 33;                                                                             
                                                                          }else {
                                                                              if (c == '|'){
                                                                                  ac = String.valueOf(c);
                                                                                  M.avanzar();
                                                                                  estado = 34; 
                                                                              }else {
                                                                                  if (c == '+'){
                                                                                      ac = String.valueOf(c);
                                                                                      M.avanzar();
                                                                                      estado = 35;
                                                                                  }else {
                                                                                      if (c == '-'){
                                                                                          ac = String.valueOf(c);
                                                                                          M.avanzar();
                                                                                          estado = 36;
                                                                                      }else{
                                                                                          if (c == '*'){
                                                                                              ac = String.valueOf(c);
                                                                                              M.avanzar();
                                                                                              estado = 37;
                                                                                          }else {
                                                                                              if (c == '%'){
                                                                                                  ac = String.valueOf(c);
                                                                                                  M.avanzar();
                                                                                                  estado = 38;
                                                                                              }else {
                                                                                                  if (c == M.EOF){
                                                                                                    estado = 7;
                                                                                                  }else {
                                                                                                    estado = 6;
                                                                                                  } 
                                                                                              }                                                                                              
                                                                                          }
                                                                                      }
                                                                                  }
                                                                              }
                                                                          }
                                                                          
                                                                      }
                                                                  }
                                                              }
                                                             
                                                          }            
                                                    }
                                                }     
                                            }   
                                          }
                                        
                                      }
                                    
                                  }
                               }
                           }
                       }
                   }
               }
               break;
               
            case 1:
               if (digito(c)){
                   ac = ac + c;
                   M.avanzar();
                   estado = 1;  // esto es innecesario
               } else { // otro
                   estado = 2;
               }
               
               break;
               
            case 2:  // estado final
               int x = Integer.parseInt(ac);
               R.set(R.NUM, x);   // <NUM, x>
               return;          // salir del while por ser estado final
              
            case 3:
                if (c == '/'){
                    M.avanzar();
                    estado = 5;
                }else {     // otro
                    estado = 4;
                }           
                
                break;
                
            case 4:
                R.setNom(R.DIV);  // <DIV,_>
                return;
                
            case 5:
                if (c != M.EOLN && c != M.EOF){
                    M.avanzar();
                    estado = 5;
                }else {     // otro
                    estado = 0;
                }
                
                break;
            
            case 6:     //estado final
                R.setNom(R.ERROR);
                return;
                
            case 7:     //estado final
                R.setNom(R.FIN);
                return;
            // en este estado 8 va implementada la tpc    
            case 8:
                if (digito(c) || letra(c)){
                    ac = ac + c;
                    M.avanzar();
                    
                    estado = 8;
                }else {
                    estado = 9;           
                }
                break;
            case 9:
                int p = tpc(ac);
                if (p != 0) {
                    if (p > 0){
                        R.setNom(p);
                    }else{
                        R.set(41, p);
                    }
                }else {
                    R.set(38, -1);
                }
                return;
                
            case 10:
                if (c != M.EOF && c != '}'){
                    M.avanzar();
                    estado = 10; // hay que quitarlo 
                }else {
                    if (c == '}'){
                        M.avanzar();
                        estado = 0;
                    }else {
                        estado = 7;
                    }
                }
                
                break;
            case 11:
                if (c == '*'){
                    M.avanzar();
                    estado = 12;
                }else {
                    estado = 13;

                }
                break;
            case 12:
                if (c != M.EOF && c != '*'){
                    M.avanzar();
                    estado = 12;
                }else {
                    if (c == '*'){
                        M.avanzar();
                        estado = 14;
                    }else {
                        estado = 7;
                    }
                }
                break;
            
            case 13:
                R.setNom(R.PA);
                return;
            case 14:
                if (c == ')'){
                    M.avanzar();
                    estado = 0;
                }else {
                    if (c != M.EOF && c != ')'){
                        M.avanzar();
                        estado = 12;
                    }else {
                        estado = 7;
                    }
                }
                break;
            
            case 15:
                if (c == '='){
                    M.avanzar();
                    estado = 16;
                }else{
                    if (c == '>'){
                        M.avanzar();
                        estado = 17;
                    }else {
                        M.avanzar();
                        estado = 18;
                    }
                }
                break;
            case 16:
                R.set(40, 3);
                return;
            
            case 17:
                R.set(40, 5);
                return;
                
            case 18:
                R.set(40, 1);
                return;
                
            case 19:
                if (c == '='){
                    M.avanzar();
                    estado = 20;
                }else {
                    M.avanzar();
                    estado = 21;
                }
                
                break;
                
            case 20:
                R.set(40, 4);
                return;
                
            case 21:
                R.set(40, 2);
                return;
                
            case 22:
                R.set(40, 0);
                return;
                
            case 23:
                if (c == '='){
                    M.avanzar();
                    estado = 17;
                }else {

                    estado = 24;
                }
                break;
                
            case 24:
                R.setNom(R.NOT);
                return;
                
            case 25:
                if (c != M.EOLN && c != M.EOF && c != '"'){
                    ac = ac + c;
                    M.avanzar();
                    estado = 25;    // quitar
                }else {
                    if (c == '"'){
                        ac = ac + c;
                        M.avanzar();
                        estado = 26;
                    }else {
                        estado = 6;
                    }
                }
                break;
                
            case 26:
                R.set(39, 0);
                return;
                
            case 27:
                if (c == '='){
                    ac = ac + c;
                    estado = 28;
                }else{
                    estado = 29;
                }
                break;
                
            case 28:
                R.setNom(28);
                return;
                
            case 29:
                R.setNom(24);
                return;
                
            case 30:
                R.setNom(23);
                return;
                
            case 31:
                R.setNom(25);
                return;
                
            case 32:
                R.setNom(27);
                return;
                
            case 33:
                R.setNom(30);
                return;
                
            case 34:
                R.setNom(31);
                return;

            case 35:
                R.setNom(32);
                return;

            case 36:
                R.setNom(33);
                return; 
                
            case 37:
                R.setNom(34);
                return;
                
            case 38:
                R.setNom(35);
                return;
        }// End switch
       }// End while
    }// End dt
    
    //En el void dt(), luego de comernos los Espacio's hacemos pos=M.getPos();
    
    // para sltar los espacios
    private boolean espacio(char c) {
        return (c == 32  || c == 9 || c == M.EOLN);
    }
    // para los digitos
    private boolean digito(char c) {
        return ('0' <= c && c <= '9');
    }
    // para las letras
    private boolean letra(char c){        
        return Character.isLetter(c);    
    }
    private static final String lexTPC[] ={" "," ",
        "program", "var", "procedure", "begin", "end", 
        "if", "then", "else", "for", "to", "do", "downto"
        , "while", "case", "of", "repeat", "until", "read"
        , "readln", "write", "writeln", "not", "and", "or"
        , "mod", "div"};
    private static final String tipoLexTPC[] ={"" ," ","integer","boolean"};
    private int tpc (String c){
        c = c.toLowerCase();
        for(int i = 2; i < lexTPC.length; i++){
            if (c.equals(lexTPC[i])){       
                return i;    
            }
        }  
        for (int i = 0; i < tipoLexTPC.length; i++){
            if (c.equals(tipoLexTPC[i])){
                return i*(-1);
            }
        }
        return 0;
    }
    public void resaltar(){    //Para resaltar el lexema en el progFuente
        M.onComunicate(pos, lexema());
    }
}
