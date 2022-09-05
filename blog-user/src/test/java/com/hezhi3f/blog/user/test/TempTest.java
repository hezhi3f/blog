package com.hezhi3f.blog.user.test;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

public class TempTest {

    @Test
    public void test() {
        List<String> oldList = new ArrayList<>(List.of("kind1", "kind2", "kind3", "kind4"));
        List<String> newList = new ArrayList<>(List.of("kind3", "kind4", "kind5", "kind6"));

        List<String> both = newList.stream().filter(oldList::contains).collect(Collectors.toList());

        List<String> rem = oldList.stream()
                .filter(kind -> !both.contains(kind))
                .collect(Collectors.toList());
        List<String> del = newList.stream()
                .filter(kind -> !both.contains(kind))
                .collect(Collectors.toList());

        oldList.retainAll(newList);
        // oldList id 不变的

        for (String kind : newList) {
            if (oldList.contains(kind)) {
                // 不变的
            } else {
                // 新增的
            }
        }

        for (String kind : oldList) {
            if (newList.contains(kind)) {
                // 不变的
            } else {
                // 删除的
            }
        }


    }
}
