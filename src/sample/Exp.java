package sample;

public class Exp {
    private String Name;
    private String NameExp;
    private String Period;
    private String Ds;


    public Exp(String name, String nameExp,  String period, String ds) {
        Name = name;
        NameExp = nameExp;

        Period = period;
        Ds = ds;

    }
    public Exp() {}

    public String getName() {
        return Name;
    }
    public String getNameExp() {
        return NameExp;
    }



    public String getPeriod() {
        return Period;
    }

    public String getDs() {
        return Ds;
    }
}
