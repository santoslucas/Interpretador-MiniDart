package interpreter.expr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.ListValue;
import interpreter.value.MapValue;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class BinaryExpr extends Expr {

    private Expr left;
    private BinaryOp op;
    private Expr right;

    public BinaryExpr(int line, Expr left, BinaryOp op, Expr right) {
        super(line);
        this.left = left;
        this.op = op;
        this.right = right;
    }

    @Override
    public Value<?> expr() {
        Value<?> v1 = left.expr();
        Value<?> v2 = right.expr();

        switch (op) {
            case IF_NULL:
                return ifNullOp(v1, v2);
            case AND:
                return andOp(v1, v2);
            case OR:
                return orOp(v1, v2);
            case EQUAL:
                return equalOp(v1, v2);
            case NOT_EQUAL:
                return notEqualOp(v1, v2);
            case LOWER_THAN:
                return lowerThanOp(v1, v2);
            case LOWER_EQUAL:
                return lowerEqualOp(v1, v2);
            case GREATER_THAN:
                return greaterThanOp(v1, v2);
            case GREATER_EQUAL:
                return greaterEqualOp(v1, v2);
            case ADD:
                return addOp(v1, v2);
            case SUB:
                return subOp(v1, v2);
            case MUL:
                return mulOp(v1, v2);
            case DIV:
                return divOp(v1, v2);
            case MOD:
                return modOp(v1, v2);
            default:
                Utils.abort(super.getLine());
                return null;
        }
    }

    private Value<?> ifNullOp(Value<?> v1, Value<?> v2) {
        if(v1 == null){
            if(v2 instanceof BoolValue){
                BoolValue nv2 = (BoolValue) v2;
                boolean res = nv2.value();
                BoolValue bres = new BoolValue(res);
                return bres;
            }
            else if(v2 instanceof NumberValue){
                NumberValue nv2 = (NumberValue) v2;
                int res = nv2.value();
                NumberValue nres = new NumberValue(res);
                return nres;
            }
            else if(v2 instanceof TextValue){
                TextValue nv2 = (TextValue) v2;
                String res = nv2.value();
                TextValue nres = new TextValue(res);
                return nres;
            }
            else if(v2 instanceof ListValue){
                ListValue nv2 = (ListValue) v2;
                List <Value<?>> res = nv2.value();
                ListValue lres = new ListValue(res);
                return lres;
            }
            else if(v2 instanceof MapValue){
                MapValue nv2 = (MapValue) v2;
                Map<Value<?>, Value<?>> res = nv2.value();
                MapValue mres = new MapValue(res);
                return mres;
            }
            else{
                Utils.abort(super.getLine());
                return null;
            }
        }
        else{
            if(v1 instanceof BoolValue){
                BoolValue nv1 = (BoolValue) v1;
                boolean res = nv1.value();
                BoolValue bres = new BoolValue(res);
                return bres;
            }
            else if(v1 instanceof NumberValue){
                NumberValue nv1 = (NumberValue) v1;
                int res = nv1.value();
                NumberValue nres = new NumberValue(res);
                return nres;
            }
            else if(v1 instanceof TextValue){
                TextValue nv1 = (TextValue) v1;
                String res = nv1.value();
                TextValue nres = new TextValue(res);
                return nres;
            }
            else if(v1 instanceof ListValue){
                ListValue nv1 = (ListValue) v1;
                List <Value<?>> res = nv1.value();
                ListValue lres = new ListValue(res);
                return lres;
            }
            else if(v1 instanceof MapValue){
                MapValue nv1 = (MapValue) v1;
                Map<Value<?>, Value<?>> res = nv1.value();
                MapValue mres = new MapValue(res);
                return mres;
            }
            else{
                Utils.abort(super.getLine());
                return null;
            }
        }
    }

    private Value<?> andOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = false;

            if((n1 != 0 ) && (n2 != 0 )){
                res = true;
            }
            
            BoolValue bres = new BoolValue(res);
            return bres;
        } 
        else if (v1 instanceof NumberValue && v2 instanceof BoolValue) {
            NumberValue nv1 = (NumberValue) v1;
            BoolValue nv2 = (BoolValue) v2;

            int n1 = nv1.value();
            boolean n2 = nv2.value();
            boolean res = false;

            if((n1 != 0 ) && n2){
                res = true;
            }
            
            BoolValue bres = new BoolValue(res);
            return bres;
        }

        else if (v1 instanceof BoolValue && v2 instanceof NumberValue) {
            BoolValue nv1 = (BoolValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            Boolean n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = false;

            if((n2 != 0 ) && n1){
                res = true;
            }
            
            BoolValue bres = new BoolValue(res);
            return bres;
        }

        else if (v1 instanceof BoolValue && v2 instanceof BoolValue) {
            BoolValue nv1 = (BoolValue) v1;
            BoolValue nv2 = (BoolValue) v2;

            boolean n1 = nv1.value();
            boolean n2 = nv2.value();
            boolean res = false;

            if((n1 && n2)){
                res = true;
            }
            
            BoolValue bres = new BoolValue(res);
            return bres;
        }
        
        else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> orOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = false;

            if((n1 != 0 ) || (n2 != 0 )){
                res = true;
            }
            
            BoolValue bres = new BoolValue(res);
            return bres;
        } 
        else if (v1 instanceof NumberValue && v2 instanceof BoolValue) {
            NumberValue nv1 = (NumberValue) v1;
            BoolValue nv2 = (BoolValue) v2;

            int n1 = nv1.value();
            boolean n2 = nv2.value();
            boolean res = false;

            if((n1 != 0 ) || n2){
                res = true;
            }
            
            BoolValue bres = new BoolValue(res);
            return bres;
        }

        else if (v1 instanceof BoolValue && v2 instanceof NumberValue) {
            BoolValue nv1 = (BoolValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            Boolean n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = false;

            if((n2 != 0 ) || n1){
                res = true;
            }
            
            BoolValue bres = new BoolValue(res);
            return bres;
        }

        else if (v1 instanceof BoolValue && v2 instanceof BoolValue) {
            BoolValue nv1 = (BoolValue) v1;
            BoolValue nv2 = (BoolValue) v2;

            boolean n1 = nv1.value();
            boolean n2 = nv2.value();
            boolean res = false;

            if((n1 || n2)){
                res = true;
            }
            
            BoolValue bres = new BoolValue(res);
            return bres;
        }
        
        else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> equalOp(Value<?> v1, Value<?> v2) {
        boolean res = false;

        if(v1 == null){
            if(v2 == null){
                res = true;
                BoolValue bres = new BoolValue(res);
                return bres;
            }
            else{
                res = false;
                BoolValue bres = new BoolValue(res);
                return bres;
            }
        }

        if(v2 == null){
            res = false;
            BoolValue bres = new BoolValue(res);
            return bres;
        }

        if(v1.equals(v2)){
            res = true;
            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            res = false;
            BoolValue bres = new BoolValue(res);
            return bres;
        }
    }

    private Value<?> notEqualOp(Value<?> v1, Value<?> v2) {
        boolean res = true;

        if(v1.equals(v2)){
            res = false;
            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> lowerThanOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = n1 < n2;

            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> lowerEqualOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = n1 <= n2;

            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> greaterThanOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = n1 > n2;

            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> greaterEqualOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            boolean res = n1 >= n2;

            BoolValue bres = new BoolValue(res);
            return bres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> addOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            int res = n1 + n2;

            NumberValue nres = new NumberValue(res);
            return nres;

        }  if (v1 instanceof TextValue && v2 instanceof TextValue) {
            TextValue nv1 = (TextValue) v1;
            TextValue nv2 = (TextValue) v2;

            String n1 = nv1.value();
            String n2 = nv2.value();
            String res = n1 + n2;

            TextValue nres = new TextValue(res);
            return nres;

        } if (v1 instanceof ListValue && v2 instanceof ListValue) {
            ListValue nv1 = (ListValue) v1;
            ListValue nv2 = (ListValue) v2;

            List<Value<?>> n1 = nv1.value();
            List<Value<?>> n2 = nv2.value();
            List<Value<?>> res = new ArrayList<Value<?>>();
            res.addAll(n1);
            res.addAll(n2);

            ListValue nres = new ListValue(res);
            return nres;

        }if (v1 instanceof MapValue && v2 instanceof MapValue) {
            MapValue nv1 = (MapValue) v1;
            MapValue nv2 = (MapValue) v2;

            Map<Value<?>, Value<?>> n1 = nv1.value();
            Map<Value<?>, Value<?>> n2 = nv2.value();
            Map<Value<?>, Value<?>> res = new HashMap<Value<?>, Value<?>>();
            res.putAll(n1);
            res.putAll(n2);

            MapValue nres = new MapValue(res);
            return nres;

        }else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> subOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            int res = n1 - n2;

            NumberValue nres = new NumberValue(res);
            return nres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> mulOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            int res = n1 * n2;

            NumberValue nres = new NumberValue(res);
            return nres;
        } else {
            Utils.abort(super.getLine());
            return null;
        }
    }

    private Value<?> divOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            if (n2 != 0) {
                int res = n1 / n2;

                NumberValue nres = new NumberValue(res);
                return nres;
            }
        }

        Utils.abort(super.getLine());
        return null;
    }

    private Value<?> modOp(Value<?> v1, Value<?> v2) {
        if (v1 instanceof NumberValue && v2 instanceof NumberValue) {
            NumberValue nv1 = (NumberValue) v1;
            NumberValue nv2 = (NumberValue) v2;

            int n1 = nv1.value();
            int n2 = nv2.value();
            if (n2 != 0) {
                int res = n1 % n2;

                NumberValue nres = new NumberValue(res);
                return nres;
            }
        }
        
        Utils.abort(super.getLine());
        return null;
    }
    
}
