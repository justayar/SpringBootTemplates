package com.justayar.springboot.util.data;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data(staticConstructor="of")    // It covers lombok annotations: @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor
public class Meal {

    @NonNull
    private String mealName;
    private boolean isSalty;
    @NonNull
    private String region;
    @NonNull
    private List<Ingredient> ingredients;
}
