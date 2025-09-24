public class Student {
    
    private String name;
    private int id;
    
    public Student(){}
    public Student(String name,int id){
        this.name=name;
        this.id=id;
    }
    public String getname(){ return name; }
    public void setname(String name){ this.name=name; }    

    public int getid(){ return id; }
    public void setid(int id){ this.id=id; }    
}
