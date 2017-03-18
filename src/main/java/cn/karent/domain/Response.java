package cn.karent.domain;

/**
 * Created by wan on 2017/3/11.
 */
public class Response {

    private Integer code;

    private Object data;

    private Object error;

    public Response() {
        code = 200;
        data = null;
        error = "success";
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
