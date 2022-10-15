package interpreter.command;

import interpreter.expr.Expr;
import interpreter.util.Utils;
import interpreter.value.BoolValue;
import interpreter.value.Value;

public class DoWhileCommand extends Command{
    private Command cmds;
    private Expr expr;

    public DoWhileCommand(int line,  Command cmds, Expr expr) {
        super(line);
        this.cmds = cmds;
        this.expr = expr;
    }

    @Override
    public void execute() {
        cmds.execute();

        while (true) {
            Value<?> v = expr.expr();
            if (!(v instanceof BoolValue))
                Utils.abort(super.getLine());
            
            BoolValue bv = (BoolValue) v;
            boolean b = bv.value();

            if (!b)
                break;

            cmds.execute();
        }
    }
}
