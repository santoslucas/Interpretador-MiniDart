package interpreter.expr;

import java.util.ArrayList;
import java.util.List;

import interpreter.value.ListValue;
import interpreter.value.Value;
import interpreter.util.Utils;

public class SpreadListItem extends ListItem{
    private Expr expr;

    public SpreadListItem(int line, Expr expr) {
        super(line);
        this.expr = expr;
    }

    @Override
    public List<Value<?>> items() {
        List<Value<?>> l = new ArrayList<Value<?>>();
        if(expr.expr() instanceof ListValue){
            ListValue sl = (ListValue) expr.expr();
            l.addAll(sl.value());
        }
        else
            Utils.abort(super.getLine());
            
            
        return l;
    }
    
}
