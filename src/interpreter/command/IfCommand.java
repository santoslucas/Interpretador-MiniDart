package interpreter.command;

import interpreter.expr.Expr;
import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.Value;

public class IfCommand extends Command{
    private Expr expr;
    private Command thenCmds;
    private Command elseCmds;

    public IfCommand(int line, Expr expr, Command thenCmds, Command elseCmds) {
        super(line);
        this.expr = expr;
        this.thenCmds = thenCmds;
        this.elseCmds = elseCmds;
    }

    @Override
    public void execute() {
        Value<?> v = expr.expr();
        if (!(v instanceof BoolValue))
                Utils.abort(super.getLine());
            
        BoolValue bv = (BoolValue) v;
        boolean b = bv.value();

        if(b){
            thenCmds.execute();
        }
        else{
            if(elseCmds != null){
                elseCmds.execute();
            }
        }
    }
}
