package com.justayar.springboot.util.value;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value(staticConstructor="of")  //all fields are made private and final,final @ToString @EqualsAndHashCode @AllArgsConstructor @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE) @Getter
public class Lecture {

    @NonFinal
    @NonNull
    private int lectureCode;
    @NonNull
    private String lectureName;
    private String lecturer;
    private String department;
    @NonNull
    private double lectureCredits;
}
