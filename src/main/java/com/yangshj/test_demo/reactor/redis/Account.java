package com.yangshj.test_demo.reactor.redis;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Account implements Serializable {

    private String id;
    private String accountCode;
    private String accountName;
}
