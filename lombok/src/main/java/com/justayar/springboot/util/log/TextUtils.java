package com.justayar.springboot.util.log;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class TextUtils {

    public String reverseOfText(String text){

        log.info("Trying to get reverse of text is "+text);

        return new StringBuilder(text).reverse().toString();

    }
}
