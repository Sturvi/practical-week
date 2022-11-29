public abstract class People {
    String name;
    int age;
    String profession;

    public People(String name, int age, String profession) {
        this.name = name;
        this.age = age;
        this.profession = profession;
    }

    public abstract String getName() ;

    public abstract int getAge();

    public abstract String getProfession();
}
