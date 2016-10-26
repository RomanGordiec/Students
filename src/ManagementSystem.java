import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 * Created by Пользователь on 22.10.2016.
 */
public class ManagementSystem {
    private List<Group> groups;             //Список групп
    private Collection<Student> students;   //Коллекция студентов

    private static ManagementSystem instance;   //Закрытая переменная для шаблона Singletone

    //Закрытый конструктор
    private ManagementSystem()
    {
        loadGroups();
        loadStudents();
    }

    //Метод проверяет инициализирована ли статическая переменная и в случаее необходимости
    //инициализирует ее
    public static synchronized ManagementSystem getInstance()
    {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }

    //Метод заполняет переменную groups начальными значениями
    public void loadGroups()
    {
        if (groups == null) {
            groups = new ArrayList<Group>();
        }
        else
        {
            groups.clear();
        }
        Group g = null;

        g = new Group();
        g.setGroupId(1);
        g.setNameGroup("Первая");
        g.setCurator("Доктор Борменталь");
        g.setSpeciality("Создание собак из человеков");
        groups.add(g);

        g = new Group();
        g.setGroupId(2);
        g.setNameGroup("Вторая");
        g.setCurator("Профессор Перображенский");
        g.setSpeciality("Создание человеков из собак");
        groups.add(g);

    }

    //Метод заполняет переменную students начальными значениями
    public void loadStudents()
    {
        if (students == null) {
            students = new TreeSet<Student>();
        }
        else
        {
            students.clear();
        }
        Student s = null;
        Calendar c = Calendar.getInstance();

        // Вторая группа
        s = new Student();
        s.setStudentId(1);
        s.setFirstName("Иван");
        s.setPatronymic("Сергеевич");
        s.setSurName("Степанов");
        s.setSex('М');
        c.set(1990, 3, 20);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        s = new Student();
        s.setStudentId(2);
        s.setFirstName("Наталья");
        s.setPatronymic("Андреевна");
        s.setSurName("Чичикова");
        s.setSex('Ж');
        c.set(1990, 6, 10);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        // Первая группа
        s = new Student();
        s.setStudentId(3);
        s.setFirstName("Петр");
        s.setPatronymic("Викторович");
        s.setSurName("Сушкин");
        s.setSex('М');
        c.set(1991, 3, 12);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);

        s = new Student();
        s.setStudentId(4);
        s.setFirstName("Вероника");
        s.setPatronymic("Сергеевна");
        s.setSurName("Ковалева");
        s.setSex('Ж');
        c.set(1991, 7, 19);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);
    }

    public List<Group> getGroups() {
        return groups;
    }

    public Collection<Student> getAllStudents() {
        return students;
    }

    public Collection<Student> getStudentsFromGroup(Group group, int year) {
        Collection<Student> list = new TreeSet<Student>();
        for (Student student: students)
        {
            if (student.getGroupId()==group.getGroupId() && student.getEducationYear() == year)
                list.add(student);
        }
        return list;
    }

    public void moveStudentsToGroup(Group group, int year, Group oldGroup, int oldYear)
    {
        for (Student student: students)
        {
            if (student.getGroupId()==oldGroup.getGroupId() && student.getEducationYear()==oldYear)
            {
                student.setGroupId(group.getGroupId());
                student.setEducationYear(year);
            }
        }
    }

    public void removeStudentsFromGroup(Group group, int year)
    {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext())
        {
            Student student = iterator.next();
            if (student.getGroupId() == group.getGroupId() && student.getEducationYear()==year)
                iterator.remove();
        }
    }

    public void insertStudent(Student student)
    {
        students.add(student);
    }

    public void updateStudent(Student student)
    {
        for (Student st: students)
        {
            if (st.getStudentId()==student.getStudentId())
            {
                st.setGroupId(student.getGroupId());
                st.setEducationYear(student.getEducationYear());
                st.setDateOfBirth(student.getDateOfBirth());
                st.setFirstName(student.getFirstName());
                st.setPatronymic(student.getPatronymic());
                st.setSurName(student.getSurName());
                st.setSex(student.getSex());
            }
        }
    }

    public void deleteStudent(Student student)
    {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext())
        {
            Student st = iterator.next();
            if (st.getStudentId()==student.getStudentId())
                iterator.remove();
        }

    }

    //Точка входа
    public static void main(String... args)
    {
        try {
            System.setOut(new PrintStream("out.txt"));

        }catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
            return;
        }

        ManagementSystem ms = ManagementSystem.getInstance();

        System.out.println("Полный список групп");
        System.out.println("*******************");
        List<Group> allGroups = ms.getGroups();
        for (Group gi : allGroups) {
            System.out.println(gi);
        }
        System.out.println();

        // Просмотр полного списка студентов
        System.out.println("Полный список студентов");
        System.out.println("***********************");
        Collection<Student> allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        // Вывод списков студентов по группам
        System.out.println("Список студентов по группам");
        System.out.println("***************************");
        List<Group> groups = ms.getGroups();
        // Проверяем все группы
        for (Group gi : groups) {
            System.out.println("---> Группа:" + gi.getNameGroup());
            // Получаем список студентов для конкретной группы
            Collection<Student> students = ms.getStudentsFromGroup(gi, 2006);
            for (Student si : students) {
                System.out.println(si);
            }
        }
        System.out.println();

        // Создадим нового студента и добавим его в список
        Student s = new Student();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setPatronymic("Владимирович");
        s.setSurName("Перебежкин");
        s.setSex('М');
        Calendar c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        System.out.println("Добавление студента:" + s);
        System.out.println("********************");
        ms.insertStudent(s);
        System.out.println("--->> Полный список студентов после добавления");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        // Изменим данные о студенте - Перебежкин станет у нас Новоперебежкиным
        // Но все остальное будет таким же - создаем студента с таким же ИД
        s = new Student();
        s.setStudentId(5);
        s.setFirstName("Игорь");
        s.setPatronymic("Владимирович");
        s.setSurName("Новоперебежкин");
        s.setSex('М');
        c = Calendar.getInstance();
        c.set(1991, 8, 31);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(1);
        s.setEducationYear(2006);
        System.out.println("Редактирование данных студента:" + s);
        System.out.println("*******************************");
        ms.updateStudent(s);
        System.out.println("--->> Полный список студентов после редактирования");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        // Удалим нашего студента
        System.out.println("Удаление студента:" + s);
        System.out.println("******************");
        ms.deleteStudent(s);
        System.out.println("--->> Полный список студентов после удаления");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        // Здесь мы переводим всех студентов одной группы в другую
        // Мы знаем, что у нас 2 группы
        // Не совсем элегантное решение, но пока сделаем так
        Group g1 = groups.get(0);
        Group g2 = groups.get(1);
        System.out.println("Перевод студентов из 1-ой во 2-ю группу");
        System.out.println("***************************************");
        ms.moveStudentsToGroup(g1, 2006, g2, 2007);
        System.out.println("--->> Полный список студентов после перевода");
        allStudends = ms.getAllStudents();
        for (Student si : allStudends) {
            System.out.println(si);
        }
        System.out.println();

        // Удаляем студентов из группы
        System.out.println("Удаление студентов из группы:" + g2 + " в 2006 году");
        System.out.println("*****************************");
        ms.removeStudentsFromGroup(g2, 2006);
        System.out.println("--->> Полный список студентов после удаления");
        allStudends = ms.getAllStudents();
        for (Iterator i = allStudends.iterator(); i.hasNext();) {
            System.out.println(i.next());
        }
        System.out.println();

    }
}
