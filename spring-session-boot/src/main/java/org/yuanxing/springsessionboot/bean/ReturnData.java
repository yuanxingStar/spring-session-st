package org.yuanxing.springsessionboot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yuanxing
 * @create 2019-08-07 9:17
 * @see
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnData {
    private String code;
    private User user;
    private String message;

}
