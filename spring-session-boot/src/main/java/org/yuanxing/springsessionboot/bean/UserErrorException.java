package org.yuanxing.springsessionboot.bean;

/**
 * @author yuanxing
 * @create 2019-08-07 9:29
 * @see
 */
public class UserErrorException  extends RuntimeException {

    private static final long serialVersionUID = -5963707260641029832L;
    private String code;

    public UserErrorException(){
        super();
    }

    public UserErrorException(String code , String message) {
        super(message);
        this.code = code;
    }

}
