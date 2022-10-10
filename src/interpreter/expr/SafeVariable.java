package interpreter.expr;

import interpreter.value.Value;

public class SafeVariable extends Variable {

    private boolean initialized;
    private Value<?> value;

    public SafeVariable(int line, String name, boolean constant) {
        super(line, name, constant);
        this.initialized = false;
        this.value = null;
    }

    public Value<?> expr() {
        throw new RuntimeException("implementar expr (SafeVariable)");
    }

    public void setValue(Value<?> value) {
        throw new RuntimeException("implementar setValue (SafeVariable)");
    }
}
