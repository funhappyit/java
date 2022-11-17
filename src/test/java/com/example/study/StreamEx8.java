package com.example.study;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;
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
    enum Level {
        HIGH, MID, LOW
    } // 성적을 상,중,하 세 단계를 분류

}
public class StreamEx8 {
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
        System.out.printf("1. 단순그룹화(반별로 그룹화) %n");
        Map<Integer, List<Student>> stuByBan = Stream.of(stuArr)
                .collect(groupingBy(Student::getBan));

        for(List<Student> ban: stuByBan.values()){
            for(Student s: ban){
                System.out.println(s);
            }
        }

        System.out.printf("%n2. 단순그룹화(성적별로 그룹화) %n");
        Map<Student.Level, List<Student>> stuByLevel = Stream.of(stuArr)
            .collect(groupingBy(s->{
                if(s.getScore() >= 200) return Student.Level.HIGH;
                else if(s.getScore() >= 100) return Student.Level.MID;
                else                        return Student.Level.LOW;
            }));
        TreeSet<Student.Level> keySet = new TreeSet<>(stuByLevel.keySet());

        for(Student.Level key: keySet){
            System.out.println("["+key+"]");
            for(Student s: stuByLevel.get(key))
                System.out.println(s);
            System.out.println();
        }
        System.out.printf("%n3. 단순그룹화 + 통계(성적별 학생수) %n");
        Map<Student.Level, Long> stuCntByLevel = Stream.of(stuArr)
                .collect(groupingBy(s->{
                    if(s.getScore() >= 200) return Student.Level.HIGH;
                    else if(s.getScore() >= 100) return Student.Level.MID;
                    else return Student.Level.LOW;
                }, counting()));

        for(Student.Level key: stuCntByLevel.keySet())
            System.out.printf("[%s]-%d명, ",key,stuCntByLevel.get(key));
        System.out.println();

    }



}
