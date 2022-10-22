package swlee.logiclist.utils;

public enum TodoResult {
    SUCCESS(1, "Todo Upload Success"),
    FAIL(0, "Todo Upload Fail (Exception)"),
    FAIL_NULL(-1, "Todo Upload Fail (Todo is null)"),
    FAIL_EXIST(-2, "Todo Already Exist");

    private int code;
    private String message;

    TodoResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
