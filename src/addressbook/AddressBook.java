/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook;

import java.sql.*;
import java.util.Scanner;
public class AddressBook {


 public static void menu()  {
        System.out.println("Enter you Choice");
        System.out.println(" 1.Insert Person Detail "+
                         "\n 2.Update Details" +
                         "\n 3.Delete Record"  +
                         "\n 4.Display Records"+
                         "\n 5.search" + 
                         "\n 6.EXit" + 
                         "");
         System.out.println("--------------------");
 }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection con=null;
        Statement st=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
        String str=null;
        String name = null;
        String url="jdbc:mysql://localhost:3306/aaddressbook";
        Scanner in=new Scanner(System.in);  
        try
        {
             Class.forName("com.mysql.jdbc.Driver");//step1
             con=DriverManager.getConnection(url,"root","");//step2
             while(true) {
             
                menu();
                System.out.println("Enter Your choice!");
                int ch=in.nextInt();
                in.nextLine();
                         
           switch(ch) {
               
                     case 1:
                        System.out.println("Enter the Id");
                        String Id=in.nextLine();
                        System.out.println("Enter First_name");
                        String First_name=in.nextLine();
                        System.out.println("Enter the Last Name");
                        String Last_name=in.nextLine();
                        System.out.println("Enter the Address");
                        String Address=in.nextLine();
                        System.out.println("Enter the City");
                        String City=in.nextLine();
                        System.out.println("Enter the State");
                        String State=in.nextLine();
                        System.out.println("Enter the Phone_Num");
                        String Phone_Num=in.nextLine();
                        pst=con.prepareStatement("insert into addressbook values(?,?,?,?,?,?,?)");//step3
                        pst.setString(1,Id);
                        pst.setString(2,First_name);
                        pst.setString(3,Last_name);
                        pst.setString(4,Address);
                        pst.setString(5,City);
                        pst.setString(6,State);
                        pst.setString(7, Phone_Num);
                        pst.executeUpdate(); //step4
                    break;
                         
                         
                    case 2:
                    System.out.println("Enter updation in First_name");
                    String u=in.nextLine();
                    System.out.println("Enter the Id");
                    String n=in.nextLine();
                    String query = new String ("UPDATE addressbook SET First_name = ? WHERE Id=?");
                    pst = con.prepareStatement(query);
                    pst.setString(1,u);
                    pst.setString(2,n);
                    int row=pst.executeUpdate();
                    if(row>0){
                      System.out.println("record updated successfully");
                     }
                     else{
                     System.out.println(" unsuccessful");
                     }
                   
                     break;
                    
                    case 3:
                    System.out.println("Enter the Id");
                    String Id1=in.nextLine();
                    String q1 = new String ("delete from addressbook  WHERE Id=?");
                    pst = con.prepareStatement(q1);//step 3
                    pst.setString(1,Id1);
                    int r=pst.executeUpdate();//step4 & 5
                    if(r>0){
                    System.out.println("record updated successfully");
                    }
                    else{
                    System.out.println(" unsuccessful");
                    }
                    break;
                   
                    case 4:
                    System.out.println("Id \t First_Name \t Last_Name \t Address \t City \t\t State \t\t Phone_Num ");
                    st=con.createStatement();//step3
                    rs=st.executeQuery("select * from addressbook");//step4 & 5

                    while(rs.next())
                    {

                                    System.out.println(rs.getString(1)+"\t"+
                                           rs.getString(2)+"\t\t"+
                                           rs.getString(3)+"\t\t"+
                                           rs.getString(4)+"\t"+
                                           rs.getString(5)+"\t\t"+
                                           rs.getString(6)+"\t\t"+
                                           rs.getString(7));
                    }
                    break;      
                    
                    case 5:
                    System.out.println("Id \t First_Name \t Last_Name \t Address \t City \t\t State \t\t Phone_num ");
                    st=con.createStatement();//step3
                    rs=st.executeQuery("SELECT * FROM addressbook WHERE First_Name LIKE 'a%' "); //step 4 &5      
                    while(rs.next())
                    {
                                    System.out.println(rs.getString(1)+"\t"+
                                           rs.getString(2)+"\t\t"+
                                           rs.getString(3)+"\t"+
                                           rs.getString(4)+"\t\t\t"+
                                           rs.getString(5)+"\t\t\t"+
                                           rs.getString(6)+"\t\t\t"+
                                           rs.getString(7) );
                    }
                    break;
                      
                         
                   default:
                                    System.exit(0);
                  }
         
        }
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
     con.close(); 
    }
                             
           }
           
        
        
    
    