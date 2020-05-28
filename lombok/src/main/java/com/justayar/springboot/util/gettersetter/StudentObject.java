package com.justayar.springboot.util.gettersetter;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public class StudentObject {


    public StudentObject(){
        setSecretCode();
        this.advisor = Advisor.getRandomAdvisor();
    }

    @NonNull
    @Getter(AccessLevel.NONE)
    private int schoolNumber;

    @Getter(AccessLevel.PRIVATE)
    private String personName;

    private String major;

    @Getter(AccessLevel.NONE) @Setter(AccessLevel.PRIVATE) private int studentSecretCode;

    @Setter(AccessLevel.NONE) private Advisor advisor;


    private void setSecretCode(){

        Random random = new Random();
        this.setStudentSecretCode(random.nextInt());
    }


    @Override
    public String toString() {
        return "{" +
                "personName='" + personName + '\'' +
                ", major='" + major + '\'' +
                ", advisor=" + advisor.toString()+
                '}';
    }

    public enum Advisor {
        RobertMartin,
        AndrewHunt,
        SteveMcConnell;

        private static final List<Advisor> ADVISORS =
                Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = ADVISORS.size();
        private static final Random RANDOM = new Random();

        public static Advisor getRandomAdvisor()  {
            return ADVISORS.get(RANDOM.nextInt(SIZE));
        }
    }

}
