package com.justayar.springboot.util.CleanUp;

import lombok.Cleanup;
import lombok.NonNull;
import lombok.var;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.lang.System.err;

public class CleanUpFeatureExample {

    public String getSecretNumberFromFile(){

        try{

            @Cleanup var reader = new BufferedReader(new FileReader("lombok/secretcode.txt"));

            return reader.readLine();

        } catch (IOException ex) {
            err.format("Exception message is: %s%n", ex);
            return null;
        }
    }

    public void setSecretNumberToBackUpFile(@NonNull String secretNumber){

        var dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedDate = dateFormatter.format(Calendar.getInstance().getTime());

        String outputLink = "lombok/secretcode-"+formattedDate+".txt";

        try {
            @Cleanup FileOutputStream outputStream = new FileOutputStream(outputLink);

            outputStream.write(secretNumber.getBytes());

        } catch (IOException ex) {
            err.format("Exception message is: %s%n", ex);
        }

    }
}
