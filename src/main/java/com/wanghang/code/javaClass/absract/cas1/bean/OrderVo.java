package com.wanghang.code.javaClass.absract.cas1.bean;


import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderVo {

    private Long orderId;

    private Date createDate;

    private BigDecimal amount;

    private Long productId;
}
