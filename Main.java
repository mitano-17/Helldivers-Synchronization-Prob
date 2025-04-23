import java.util.concurrent.*; 
import java.util.*;
import java.io.*;
class shared{

    static int[][] team;
    static int regc; // how many regulars left
    static int supc; // how many supers left
    static int count=0;// number of teams
    static int position=0; //current position in team
    static int supcount=0;
    public shared(int r,int s)
    {
        team= new int[r+s][4];
        regc=r;
        supc=s;
    }

}
class Signup extends Thread
{
    String name;
    Semaphore mutex,sup,lead;
    int type; //0 reg, 1 super
    int id;

    public Signup(Semaphore mutex, Semaphore sup, Semaphore lead,int ty, int i,String name)
    {
        super(name);
        this.name=name;
        this.mutex=mutex;
        this.sup=sup;
        this.lead=lead;
        this.type=ty;
        this.id=i;
        
    }

    public int cittype(int num)
    {
        int check=0;
        if(num==0){
            for(int i=0;i<4;i++)
            {
                if(shared.team[shared.count][i]== 0)
                    check++;
            }
        }

        if(num==1){
            for(int i=0;i<4;i++)
            {
                if(shared.team[shared.count][i]== 1)
                    check++;
            }
        }
        return check;
    }
    @Override
    public void run(){
        if(type==1){
            try{
            
            
            //System.out.println("SUP permits= " +sup.availablePermits());
            sup.acquire();//check for how many supers are already in
            //System.out.println("SUP permits= " +sup.availablePermits());
            //System.out.println("Super Mutex permits= " +mutex.availablePermits());
            mutex.acquire();//your turnnnn
           // System.out.println("SUper Mutex permits= " +mutex.availablePermits());
            System.out.println("Super Citizen " + this.id + " has joined team " + shared.count);
            shared.supc--;
            shared.team[shared.count][shared.position]=1;
            shared.position++;
            shared.supcount++;
            //Thread.sleep(10); 
            mutex.release();
            lead.release(3);
        }
            catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
        }

        else if(type==0){
            try { 
                
                lead.acquire();
               // System.out.println("Mutex permits= " +mutex.availablePermits());
                mutex.acquire();
                //System.out.println("Mutex permits= " +mutex.availablePermits());
                System.out.println("Regular Citizen " + this.id + " has joined team " + shared.count);
                shared.regc--;
                shared.team[shared.count][shared.position]=0;
                shared.position++;
                //Thread.sleep(10); 
                mutex.release();
                
            } 
            catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
            
        }

        if(shared.position==4)
        {
            int n1=cittype(0);
            int n2=cittype(1);
            System.out.println("team "+shared.count+" is ready and now launching to battle (sc: "+ n2 +"  | rc: " + n1 +" )");
            shared.supcount=0;
            shared.count++;
            shared.position=0;
            sup.release(2);
            lead.drainPermits();
        }
    }
}
public class Main{
    static boolean checker(int s, int r)
     {
        boolean check=true;

        if(s+r>=4 && s!=0 && r!=0)
            check=false;

        if(s+r>=4 && r<2)
            check=true;
        if(r<3 && s<1)
            check=true;
        if(r<2 && s<2) 
            check=true;
        return check;
     }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int total;
        int s,r;
        int s2,r2;
        int tcount=4;
        Semaphore sup=new Semaphore(2);
        Semaphore em=new Semaphore(0);
        Semaphore mutex=new Semaphore(1);
        boolean check=true;
        try{
            System.out.println("Input: ");
            System.out.print("No. of Regular Citizens = ");
            r = sc.nextInt();
            System.out.print("No. of Super Citizens = ");
            s = sc.nextInt();
            sc.close();

            ArrayList<Signup> suc=new ArrayList<Signup>();
            ArrayList<Signup> rc=new ArrayList<Signup>();
            Signup hold;
            int k,j;
            new shared(r,s);

            for(int i=0; i<shared.supc;i++)
            { 
                System.out.println("Super Citizen " + i + " is signing up");
                hold=new Signup(mutex, sup,em,1,i,String.format("%d", i));
                suc.add(hold);
            }

            for(int i=0; i<shared.regc;i++)
            { 
                System.out.println("Regular Citizen " + i + " is signing up");
                hold=new Signup(mutex, sup, em,0,i,String.format("%d", i));
                rc.add(hold);
            }

            total=shared.supc+shared.regc;
            if(shared.supc>shared.regc)
                k=shared.supc;
            else
                k=shared.regc;

            for(j=0;j<k;j++){
                s2=0;
                r2=0;
                if(shared.position==0)
                    if(checker(shared.supc,shared.regc))
                        break;

                total=shared.supc+shared.regc;
                if(shared.supc>0)
                {
                    suc.get(j).start();
                    s2=1;
                }

                if(shared.regc>0)
                {
                    rc.get(j).start(); 
                    r2=1;
                }
                if(s2==1)
                suc.get(j).join();
                if(r2==1)
                rc.get(j).join();
            }

            System.out.println("Total number of teams sent: " + shared.count);
            System.out.println("Number of citizens sent home: " + (shared.regc + shared.supc));
        }
        catch(Exception e) {
            System.out.println("An error occurred");
            System.out.println(e);
        }


    }
}
