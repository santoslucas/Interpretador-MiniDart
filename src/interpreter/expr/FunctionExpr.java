package interpreter.expr;

import java.util.Random;
import java.util.Scanner;

import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.ListValue;
import interpreter.value.NumberValue;
import interpreter.value.TextValue;
import interpreter.value.Value;

public class FunctionExpr extends Expr {

    private FunctionOp op;
    private Expr expr;

    private static Scanner input = new Scanner(System.in);

    public FunctionExpr(int line, FunctionOp op, Expr expr) {
        super(line);

        this.op = op;
        this.expr = expr;
    }

    @Override
    public Value<?> expr() {
        Value<?> v = expr.expr();

        switch (op) {
            case READ:
                return readOp(v);
            case RANDOM:
                return randomOp(v);
            case LENGTH:
                return lengthOp(v);
            case KEYS:
                return keysOp(v);
            case VALUES:
                return valuesOp(v);
            case TOBOOL:
                return toBoolOp(v);
            case TOINT:
                return toIntOp(v);
            case TOSTR:
                return toStrOp(v);
            default:
                Utils.abort(super.getLine());
                return null;
        }
    }

    private TextValue readOp(Value<?> v) {
        System.out.print(v);

        String text = input.nextLine().trim();
        return text.isEmpty() ? null : new TextValue(text);
    }

    private NumberValue randomOp(Value<?> v) {
        NumberValue nv = (NumberValue) v;
        int n = nv.value();
        Random rand = new Random();
        int r = rand.nextInt(n);
        
        return new NumberValue(r);
    }

    private NumberValue lengthOp(Value<?> v) {
        return null;
    }

    private ListValue keysOp(Value<?> v) {
        return null;
    }

    private ListValue valuesOp(Value<?> v) {
        return null;
    }

    private BoolValue toBoolOp(Value<?> v) {
        boolean b;
        if (v == null) {
            b = false;
        } else if (v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            boolean b1 = bv.value();

            b = b1;
        } else if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int n = nv.value();
            if(n!=0)
                b = true;
            else
                b = false;
        } else {
            b = false;
        }

        return new BoolValue(b);
    }

    private NumberValue toIntOp(Value<?> v) {
        int n;
        if (v == null) {
            n = 0;
        } else if (v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();

            n = b ? 1 : 0;
        } else if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            n = nv.value();
        } else if (v instanceof TextValue) {
            TextValue sv = (TextValue) v;
            String s = sv.value();

            try {
                n = Integer.parseInt(s);
            } catch (Exception e) {
                n = 0;
            }
        } else {
            n = 0;
        }

        return new NumberValue(n);
    }

    private TextValue toStrOp(Value<?> v) {
        String s;
        if (v == null) {
            s = "null";
        } else if (v instanceof BoolValue) {
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();

            if(b)
                s = "true";
            else
                s = "false";
        } else if (v instanceof NumberValue) {
            NumberValue nv = (NumberValue) v;
            int n = nv.value();
            s = String.valueOf(n);
        } else if (v instanceof TextValue) {
            TextValue sv = (TextValue) v;
            s = sv.value();
        } else {
            s = "null";
        }

        return new TextValue(s);
    }

}
