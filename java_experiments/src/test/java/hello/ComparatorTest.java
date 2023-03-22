package hello;

import static org.junit.Assert.*;

import lombok.Builder;
import lombok.Data;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ComparatorTest {

    @Builder
    @Data
    static class Project{
        int id;
        float cost;
        int candidates;
        int priority;
    }

    @Test
    public void testMultiAttributeComparator(){
        List<Project> projects = new ArrayList<>();
        projects.add(Project.builder().id(100).cost(1.65F).candidates(5).priority(1).build());
        projects.add(Project.builder().id(101).cost(1.35F).candidates(5).priority(1).build());
        projects.add(Project.builder().id(105).cost(1.25F).candidates(5).priority(1).build());
        projects.add(Project.builder().id(102).cost(1.45F).candidates(6).priority(2).build());
        projects.add(Project.builder().id(106).cost(1.45F).candidates(2).priority(2).build());
        projects.add(Project.builder().id(103).cost(1.45F).candidates(6).priority(3).build());


        Comparator<Project> compareByMultiAttrs = Comparator
                .comparing(Project::getPriority)
                .thenComparing(Project::getCost)
                .thenComparing(Project::getCandidates);

        List<Project> sortedProjects = projects.stream()
                .sorted(compareByMultiAttrs)
                .collect(Collectors.toList());

    }

}


