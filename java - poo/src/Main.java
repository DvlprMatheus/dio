import br.com.dvlprmatheus.desafio.dominio.Bootcamp;
import br.com.dvlprmatheus.desafio.dominio.Content;
import br.com.dvlprmatheus.desafio.dominio.Course;
import br.com.dvlprmatheus.desafio.dominio.Dev;
import br.com.dvlprmatheus.desafio.dominio.Mentoring;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Course course1 = createCourse("java course", "java course description", 8);
        Course course2 = createCourse("js course", "js course description", 4);
        Mentoring mentoring = createMentoring("java mentoring", "java mentoring description");

        Bootcamp bootcamp = createBootcamp("Bootcamp Java Developer", "Bootcamp Java Developer description", course1, course2, mentoring);

        displayDevProgress("Camila", bootcamp, 2);
        System.out.println("-------");
        displayDevProgress("Joao", bootcamp, 3);
    }

    private static Course createCourse(String title, String description, int workload) {
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setWorkload(workload);
        return course;
    }

    private static Mentoring createMentoring(String title, String description) {
        Mentoring mentoring = new Mentoring();
        mentoring.setTitle(title);
        mentoring.setDescription(description);
        mentoring.setDate(LocalDate.now());
        return mentoring;
    }

    private static Bootcamp createBootcamp(String name, String description, Content... contents) {
        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setName(name);
        bootcamp.setDescription(description);
        for (Content content : contents) {
            bootcamp.getContents().add(content);
        }
        return bootcamp;
    }

    private static void displayDevProgress(String devName, Bootcamp bootcamp, int progressCount) {
        Dev dev = new Dev();
        dev.setName(devName);
        dev.subscribeBootcamp(bootcamp);

        System.out.println("Subscribed Contents " + devName + ":" + dev.getSubscribedContents());

        for (int i = 0; i < progressCount; i++) {
            dev.progress();
        }

        System.out.println("-");
        System.out.println("Subscribed Contents " + devName + ":" + dev.getSubscribedContents());
        System.out.println("Completed Contents " + devName + ":" + dev.getCompletedContents());
        System.out.println("XP:" + dev.calculateTotalXp());
    }
}
