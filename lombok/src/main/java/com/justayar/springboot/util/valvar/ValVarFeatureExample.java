package com.justayar.springboot.util.valvar;

import lombok.val;
import lombok.var;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.TreeMap;

import static java.lang.System.out;

@Component
public class ValVarFeatureExample {



    public void returnToDoListWithPrioritizedOrder(){

        HashMap<Integer,String> todoListWithPriorities =  new HashMap<>();
        todoListWithPriorities.put(3,"clean the house");
        todoListWithPriorities.put(1,"feed the cats");
        todoListWithPriorities.put(4,"water the garden");
        todoListWithPriorities.put(5,"go to the gym");
        todoListWithPriorities.put(2,"go to the shopping");


        var copyOfToDoListWithProrities = todoListWithPriorities;

        out.println("To Do List Not Ordered:");

        for(var toDoObject : copyOfToDoListWithProrities.entrySet()){
            out.println("Priority: "+ toDoObject.getKey()+" To Do: "+toDoObject.getValue());
        }

        val prioritizedOrderToDoList = new TreeMap<>(todoListWithPriorities);

        out.println("To Do List Ordered:");

        for(val toDoObject : prioritizedOrderToDoList.entrySet()){
            out.println("Priority: "+ toDoObject.getKey()+" To Do: "+toDoObject.getValue());
        }




    }


}
