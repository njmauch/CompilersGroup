package pickle;
import java.util.HashMap;

public class SymbolTable {
    HashMap<String, STEntry> ht;

    public SymbolTable(){
        ht = new HashMap<>();
        initGlobal();
    }

    //Code for STEntry
    public static class STEntry {
        String symbol;
        Classif primClassif;
        public STEntry(String symbol, Classif primClassif){
            this.symbol = symbol;
            this.primClassif = primClassif;
        }
    }

    public static class STIdentifier extends STEntry{
        SubClassif subClassif;
        SubClassif dclType;
        Structure structure;
        String parm;
        String nonLocal;
        public STIdentifier(String symbol, Classif primClassif, SubClassif subClassif, SubClassif type, Structure structure)
        {
            super(symbol, primClassif);
            this.subClassif = subClassif;
            this.dclType = type;
            this.structure = structure;

        }
    }

    public class STControl extends STEntry{

        SubClassif subClassif;
        public STControl(String s, Classif pci, SubClassif sci)
        {
            super(s, pci);
            this.subClassif = sci;
        }
    }

    public class STFunction extends STEntry{
        SubClassif subClassif;
        public STFunction(String s, Classif pci, SubClassif sci)
        {
            super(s, pci);
            this.subClassif = sci;
        }
    }

    public class STSeparator extends STEntry{
        SubClassif subClassif;
        public STSeparator(String s, Classif pci)
        {
            super(s, pci);
        }
    }

    public class STOperand extends STEntry{
        SubClassif subClassif;
        public STOperand(String s, Classif pci, SubClassif sci)
        {
            super(s, pci);
            this.subClassif = sci;
        }
    }


    public void putSymbol(String s, STEntry e){
        ht.put(s,e);
    }

    public STEntry getSymbol(String s){
        if (ht.containsKey(s)){
            return ht.get(s);
        }
        return null;
    }

    private void initGlobal()
    {
        this.putSymbol("<=", new STEntry("<=", Classif.OPERATOR));
        this.putSymbol(">=", new STEntry(">=", Classif.OPERATOR));
        this.putSymbol("!=", new STEntry("!=", Classif.OPERATOR));
        this.putSymbol("==", new STEntry("==", Classif.OPERATOR));

        this.putSymbol("^", new STEntry("^", Classif.OPERATOR));
        this.putSymbol("+", new STEntry("+", Classif.OPERATOR));
        this.putSymbol("-", new STEntry("-", Classif.OPERATOR));
        this.putSymbol("*", new STEntry("*", Classif.OPERATOR));
        this.putSymbol("/", new STEntry("/", Classif.OPERATOR));
        this.putSymbol("<", new STEntry("<", Classif.OPERATOR));
        this.putSymbol(">", new STEntry(">", Classif.OPERATOR));
        this.putSymbol("!", new STEntry("!", Classif.OPERATOR));
        this.putSymbol("=", new STEntry("=", Classif.OPERATOR));
        this.putSymbol("#", new STEntry("#", Classif.OPERATOR));

        this.putSymbol("and", new STEntry("and", Classif.OPERATOR));
        this.putSymbol("or", new STEntry("or", Classif.OPERATOR));
        this.putSymbol("not", new STEntry("not", Classif.OPERATOR));
        this.putSymbol("IN", new STEntry("IN", Classif.OPERATOR));
        this.putSymbol("NOTIN", new STEntry("NOTIN", Classif.OPERATOR));

        this.putSymbol("Int", new STControl("Int", Classif.CONTROL, SubClassif.DECLARE));
        this.putSymbol("Float", new STControl("Float", Classif.CONTROL, SubClassif.DECLARE));
        this.putSymbol("String", new STControl("String", Classif.CONTROL, SubClassif.DECLARE));
        this.putSymbol("Bool", new STControl("Bool", Classif.CONTROL, SubClassif.DECLARE));
        this.putSymbol("Date", new STControl("Date", Classif.CONTROL, SubClassif.DECLARE));

        this.putSymbol("if", new STControl("if", Classif.CONTROL, SubClassif.FLOW));
        this.putSymbol("endif", new STControl("endif", Classif.CONTROL, SubClassif.END));
        this.putSymbol("else", new STControl("else", Classif.CONTROL, SubClassif.END));
        this.putSymbol("for", new STControl("for", Classif.CONTROL, SubClassif.FLOW));
        this.putSymbol("endfor", new STControl("endfor", Classif.CONTROL, SubClassif.END));
        this.putSymbol("while", new STControl("while", Classif.CONTROL, SubClassif.FLOW));
        this.putSymbol("endwhile", new STControl("endwhile", Classif.CONTROL, SubClassif.END));
        this.putSymbol("to", new STControl("to", Classif.CONTROL, SubClassif.FLOW));
        this.putSymbol("by", new STControl("by", Classif.CONTROL, SubClassif.FLOW));
        this.putSymbol("break", new STControl("break", Classif.CONTROL, SubClassif.END));
        this.putSymbol("continue", new STControl("continue", Classif.CONTROL, SubClassif.END));

        this.putSymbol("print", new STFunction("print", Classif.FUNCTION, SubClassif.BUILTIN));
        this.putSymbol("LENGTH", new STFunction("LENGTH", Classif.FUNCTION, SubClassif.BUILTIN));
        this.putSymbol("SPACES", new STFunction("SPACES", Classif.FUNCTION, SubClassif.BUILTIN));
        this.putSymbol("ELEM", new STFunction("ELEM", Classif.FUNCTION, SubClassif.BUILTIN));
        this.putSymbol("MAXELEM", new STFunction("MAXELEM", Classif.FUNCTION, SubClassif.BUILTIN));
        this.putSymbol("dateDiff", new STFunction("dateDiff", Classif.FUNCTION, SubClassif.BUILTIN));
        this.putSymbol("dateAdj", new STFunction("dateAdj", Classif.FUNCTION, SubClassif.BUILTIN));
        this.putSymbol("dateAge", new STFunction("dateAge", Classif.FUNCTION, SubClassif.BUILTIN));


        this.putSymbol(";", new STSeparator(";", Classif.SEPARATOR));
        this.putSymbol(",", new STSeparator(",", Classif.SEPARATOR));
        this.putSymbol("(", new STSeparator("(", Classif.SEPARATOR));
        this.putSymbol(")", new STSeparator(")", Classif.SEPARATOR));

        this.putSymbol("\"", new STFunction("\"", Classif.OPERAND, SubClassif.STRING));
    }
}