package com.justayar.springboot.util.sneakythrows;

import lombok.SneakyThrows;
import lombok.var;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Reader {

    @SneakyThrows(IOException.class)
    public String getSecretNumberFromFile() {


        var reader = new BufferedReader(new FileReader("lombok/secretcode.txt"));

        return reader.readLine();

    }
}
