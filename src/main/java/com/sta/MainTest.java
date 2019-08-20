package com.sta;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        List<Result> results = new LinkedList<>();
        List<Result> resultCols = Arrays.asList(
                new Result("2019-08-01", 1L,TestEnum.DOOR),
                new Result("2019-08-02", 2L, TestEnum.SMOKE),
                new Result("2019-08-03", 10L, TestEnum.GARBAGE),
                new Result("2019-08-04", 1L, TestEnum.WATER),
                new Result("2019-08-05", 1L, TestEnum.WINDOW)
        );
        List<String> date = Arrays.asList(
                "2019-08-01","2019-08-02",
                "2019-08-03","2019-08-04",
                "2019-08-05","2019-08-06",
                "2019-08-07","2019-08-08"
                );
        date.stream().forEach(index->{
            Arrays.stream(TestEnum.values())
                    .filter(tem->!tem.equals(TestEnum.UNKNOWN))
                    .forEach();
        });

    }

@Data
@NoArgsConstructor
static class Result {
     private String index;
     private long count;
     private TestEnum testEnum;

     public Result(String index,long count,TestEnum testEnum){
      this.count = count;
      this.testEnum = testEnum;
      this.index = index;
     }
}

}
