package study.stack;

enum Op {
    PLUS("+", 1),
    MINUS("-", 1),
    STAR("*", 2),
    SLASH("/", 2),
    ;
    private String opCode;
    private int priority;

    Op(String opCode, int priority) {
        this.opCode = opCode;
        this.priority = priority;
    }

    public String getOpCode() {
        return opCode;
    }

    public int getPriority() {
        return priority;
    }

    public static Op getOpByCode(String opCode) {
        for (Op op : Op.values()) {
            if (op.getOpCode().equals(opCode.trim())) {
                return op;
            }
        }
        throw new RuntimeException("illegal operators");
    }
}