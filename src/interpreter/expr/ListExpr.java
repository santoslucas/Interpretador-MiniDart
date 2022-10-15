package interpreter.expr;

import java.util.ArrayList;
import java.util.List;

import interpreter.value.Value;

public class ListExpr extends Expr{
    private List<ListItem> list;

    public ListExpr(int line) {
        super(line);
        this.list = new ArrayList<ListItem>();
    }

    public void addItem(ListItem item){
        this.list.add(item);
    }

    @Override
    public Value<?> expr() {
        List<Value<?>> l = new ArrayList<Value<?>>();

        /*for(ListItem item : list){
            Value<?> expr = item.expr.;
            if (key == null)
                Utils.abort(super.getLine());
                
            Value<?> value = item.value.expr();

            m.put(key, value);
        }*/

        return null;
    }    
}
