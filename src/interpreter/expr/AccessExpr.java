package interpreter.expr;

import java.util.List;
import java.util.Map;

import interpreter.util.Utils;
import interpreter.value.ListValue;
import interpreter.value.MapValue;
import interpreter.value.NumberValue;
import interpreter.value.Value;

public class AccessExpr extends SetExpr {

    private SetExpr base;
    private Expr index;

    public AccessExpr(int line, SetExpr base, Expr index) {
        super(line);
        this.base = base;
        this.index = index;
    }

    @Override
    public Value<?> expr() {
        Value<?> bvalue = base.expr();
        if (bvalue instanceof ListValue) {
            ListValue lv = (ListValue) bvalue;
            List<Value<?>> list = lv.value();

            Value<?> ivalue = index.expr();

            if (ivalue == null)
                Utils.abort(super.getLine());

            if(ivalue instanceof NumberValue){
                NumberValue nv = (NumberValue) ivalue;
                int value = nv.value();
                return list.get(value);
            }
            else
                Utils.abort(super.getLine());

        } else if (bvalue instanceof MapValue) {
            MapValue mv = (MapValue) bvalue;
            Map<Value<?>, Value<?>> map = mv.value();

            Value<?> ivalue = index.expr();
            if (ivalue == null)
                Utils.abort(super.getLine());

            return map.get(ivalue);
        } else {
            Utils.abort(super.getLine());
        }

        return null;
    }

    @Override
    public void setValue(Value<?> value) {
        Value<?> bvalue = base.expr();
        if (bvalue instanceof ListValue) {
            ListValue lv = (ListValue) bvalue;
            List<Value<?>> list = lv.value();

            Value<?> ivalue = index.expr();

            if (ivalue == null)
                Utils.abort(super.getLine());

            list.add(ivalue);

        } else if (bvalue instanceof MapValue) {
            MapValue mv = (MapValue) bvalue;
            Map<Value<?>, Value<?>> map = mv.value();

            Value<?> ivalue = index.expr();
            if (ivalue == null)
                Utils.abort(super.getLine());

            map.put(ivalue, value);
        } else {
            Utils.abort(super.getLine());
        }
    }
    
}
