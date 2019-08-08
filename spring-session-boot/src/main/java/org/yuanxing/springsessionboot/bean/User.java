package org.yuanxing.springsessionboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yuanxing
 * @create 2019-08-07 9:17
 * @see  (1) 用户实体类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 4305960871430859412L;

    private String password;
    private String username;
    private String userid;
    private String name;
    private int age;
    private String address;

}
