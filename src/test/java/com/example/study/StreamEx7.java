package com.example.study;

import org.junit.jupiter.api.Test;

import javax.sound.midi.SysexMessage;
import javax.swing.text.html.Option;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;
import java.util.Optional;
import java.util.stream.Stream;
/*
class Student {
    String name;
    boolean isMale; //성별
    int hak; //학년
    int ban; //반
    int score;

    public Student(String name, boolean isMale, int hak, int ban, int score) {
        this.name = name;
        this.isMale = isMale;
        this.hak = hak;
        this.ban = ban;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public boolean isMale() {
        return isMale;
    }

    public int getHak() {
        return hak;
    }

    public int getBan() {
        return ban;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return String.format("{%s, %s, %d학년 %d반, %3d점}",name, isMale? "남":"여",hak,ban,score);
    }
    //groupingBy()에서 사용
    enum Level {HIGH, MID, LOW} // 성적을 상,중,하 세 단계를 분류

}


public class StreamEx7 {
    @Test
    public static void main(String[] args) {
        Student[] stuArr = {
            new Student("나자바",true,1,1,275),
                new Student("김자바",false,1,1,250),
                new Student("감자바",true,1,2,200),
                new Student("이자바",false,1,3,100),
                new Student("남자바",true,1,1,140),
                new Student("안자바",false,1,1,200),
                new Student("황자바",true,1,2,230),
                new Student("강자바",true,1,3,260),
                new Student("이자바",true,1,1,300),

                new Student("나자바",true,2,1,287),
                new Student("김자바",false,2,3,100),
                new Student("감자바",true,2,2,120),
                new Student("이자바",false,2,1,170),
                new Student("남자바",true,2,2,257),
                new Student("안자바",false,2,2,134),
                new Student("황자바",true,2,1,289),
                new Student("강자바",false,2,4,179),
                new Student("이자바",true,2,1,300),
        };
        System.out.printf("1. 단순분할(성별로 분할) %n");
        Map<Boolean, List<Student>>  stuBySex = Stream.of(stuArr)
                .collect(partitioningBy(Student::isMale));

        List<Student> maleStudent = stuBySex.get(true);
        List<Student> femaleStudent = stuBySex.get(false);

        for(Student s : maleStudent) System.out.println(true);
        for(Student s: femaleStudent) System.out.println(false);

        System.out.println("%n2. 단순분할 + 통계(성별 학생수) %n");
        Map<Boolean,Long> stuNumBySex = Stream.of(stuArr)
                .collect(partitioningBy(Student::isMale,counting()));

        System.out.println("남학생 수 :"+stuNumBySex.get(true));
        System.out.println("여학생 수 :"+stuNumBySex.get(false));

        System.out.println("%n3. 단순분할 + 통계(성별 1등) %n");
        Map<Boolean, Optional<Student>> topScoreBySex = Stream.of(stuArr)
                .collect(partitioningBy(Student::isMale,
                        maxBy(Comparator.comparingInt(Student::getScore))));
        System.out.println("남학생 1등:"+topScoreBySex.get(true));
        System.out.println("여학생 1등:"+topScoreBySex.get(false));

        Map<Boolean,Student> topScoreBySex2 = Stream.of(stuArr)
                .collect(partitioningBy(Student::isMale,
                            collectingAndThen(
                                    maxBy(comparingInt(Student::getScore)),Optional::get
                            )
                        )
                );
        System.out.println("남학생 1등 :"+topScoreBySex2.get(true));
        System.out.println("여학생 1등 :"+topScoreBySex2.get(false));

        System.out.println("%n4. 다중분할(성별 불합격자, 100점 이하) %n");

        Map<Boolean, Map<Boolean,List<Student>>> failedStuBySex =
                Stream.of(stuArr).collect(partitioningBy(Student::isMale,
                        partitioningBy(s->s.getScore() <=100)
                        )
                );

        List<Student> failedMaleStu = failedStuBySex.get(true).get(true);
        List<Student> failedFemaleStu =  failedStuBySex.get(false).get(true);


        for(Student s: failedMaleStu) System.out.println(s);
        for(Student s: failedFemaleStu) System.out.println(s);
    }
}
*/
