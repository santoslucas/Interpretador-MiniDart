package interpreter.expr;

import java.util.ArrayList;
import java.util.List;

import interpreter.util.Utils;
import interpreter.value.ListValue;
import interpreter.value.Value;

public class ForListItem extends ListItem{
    private Variable var;
    private Expr expr;
    private ListItem item;

    public ForListItem(int line, Variable var, Expr expr, ListItem item) {
        super(line);
        this.var = var;
        this.expr = expr;
        this.item = item;
    }

    @Override
    public List<Value<?>> items() {
        List<Value<?>> l = new ArrayList<Value<?>>();
        
        Value<?> v = expr.expr();
        if (!(v instanceof ListValue))
                Utils.abort(super.getLine());
            
            ListValue lv = (ListValue) v;

            for (Value<?> variable : lv.value()) {
                var.setValue(variable);
                l.addAll(item.items());
            }
            
        return l;
    }
    
}
