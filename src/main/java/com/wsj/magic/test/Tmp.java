package com.wsj.magic.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Tmp {
    public static void main(String[] args) throws IOException {
        AESTool aesTool = new AESTool("6O$@9TFemXv33M1e","6O$@9TFemXv33M1e");
        try {
            String s = aesTool.decrypt7("30MSthELsS8Qy9HHd7n+jyhPjeNe9cLHEkxCiQW6qW2o49GjJ+nT9aI/9eSONWUhyoTvgKGTd0G5hw3x4HRgBw==");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
