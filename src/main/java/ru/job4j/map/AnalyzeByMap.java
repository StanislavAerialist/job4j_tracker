package ru.job4j.map;

import java.util.*;

public class AnalyzeByMap {
    public static double averageScore(List<Pupil> pupils) {
        double count = 0;
        double sum = 0;
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                sum += s.score();
                count++;
            }
        }
        return sum / count;
    }

    public static List<Label> averageScoreByPupil(List<Pupil> pupils) {
        List<Label> rsl = new ArrayList<>();
        for (Pupil p : pupils) {
            double sumP = 0;
            double countP = 0;
            for (Subject s : p.subjects()) {
                sumP += s.score();
                countP++;
            }
            Label label = new Label(p.name(), sumP / countP);
            rsl.add(label);
        }
        return rsl;
    }

    public static List<Label> averageScoreBySubject(List<Pupil> pupils) {
        double count = 0;
        List<Label> rsl = new ArrayList<>();
        Map<String, Integer> temp = new LinkedHashMap<>();
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                temp.merge(s.name(), s.score(), Integer::sum);
            }
            count++;
        }

        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
            Label label = new Label(entry.getKey(), entry.getValue() / count);
            rsl.add(label);
        }
        return rsl;
    }

    public static Label bestStudent(List<Pupil> pupils) {
        List<Label> rsl = new ArrayList<>();
        double sumP = 0;
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                sumP += s.score();
            }
            Label label = new Label(p.name(), sumP);
            rsl.add(label);
            sumP = 0;
        }
        rsl.sort(Comparator.naturalOrder());
        return rsl.get(rsl.size() - 1);
    }

    public static Label bestSubject(List<Pupil> pupils) {
        List<Label> rsl = new ArrayList<>();
        Map<String, Integer> temp = new LinkedHashMap<>();
        for (Pupil p : pupils) {
            for (Subject s : p.subjects()) {
                temp.merge(s.name(), s.score(), Integer::sum);
            }
        }
        for (Map.Entry<String, Integer> entry : temp.entrySet()) {
            Label label = new Label(entry.getKey(), entry.getValue());
            rsl.add(label);
        }
        rsl.sort(Comparator.naturalOrder());
        return rsl.get(rsl.size() - 1);
    }
}