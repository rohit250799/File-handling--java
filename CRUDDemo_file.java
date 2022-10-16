import java.util.*;
import java.io.*;

class Employee implements Serializable{ //To implement serialization in our class.
	int empno;
	String ename;
	int salary;

	Employee(int empno, String ename, int salary){ //Initialising
		//the values of the parameters in the constructors
		//with the global variables.
		this.empno=empno;
		this.ename=ename;
		this.salary=salary;

	}
	public String toString(){ //display the details of the employee
		return empno+" "+ename+" "+salary;
	}
}
class EmployeeDemo{ //creating the menu driven program
	public static void main(String[] args) throws Exception{
		Scanner s=new Scanner(System.in);//creating separate instances of scanner variables s and sc for integer and string.
		Scanner sc=new Scanner(System.in);
		//creating menu driven program for the crud
		int choice=-1;
		File file=new File("employee.txt"); //Creating the instance of the file.
		ArrayList<Employee> al=new ArrayList<Employee>();//Write the collection of the choice data and storing the details of the employee in an ArrayList.
		ObjectOutputStream oos = null;//writing the collection al into the file by declaring the al variable in statement 1 and making oos as null instead of the method in the following comment and not calling the constructor of oos as calling it will lead to the creation of the file.
		ListIterator li=null; //to displaty the `contents using a list iterator.

		//Inside the last bracket of the previous line, we are passing the object of the file which is created in the previous lines.
		ObjectInputStream ois=null;
		if(file.isFile()){ //checking whether the file is available or not
			ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
			al=(ArrayList<Employee>)ois.readObject(); //By using the first brackets, we are casting that the data written into the file, i.e-arraylist and collection of employee. 
			ois.close();
		}
		do{
			System.out.println("Hello, Provat Basu. Please select from any of the given options: ");
			System.out.println("1. Insert");
			System.out.println("2. Display");
			System.out.println("3. Search");
			System.out.println("4. Delete");
			System.out.println("5. Update");
			System.out.println("6. Sort By Employee Number - On screen");
			System.out.println("7. Sort By Employee Number - In File");
			System.out.println("8. Sort By Employee Name - On screen");
			System.out.println("9. Sort By Employee Name - In File");
			System.out.println("10. Sort By Employee salary(Desc) - On Screen");
			System.out.println("11. Sort By Employee salary(Asc) - In File");		
			System.out.println("0. Exiit");
			System.out.print("Enter your choice: ");
			choice=s.nextInt();//Continues to get input from
			//the user until the user presses 0.
			
			switch(choice){
			case 1:
				System.out.println("Enter how many employees you want: ");
				int n=s.nextInt();
				for(int i=0;i<n;i++){ //Running the loop equal to the number of times that the user wants. If n=3, loop will run thrice and 3 records will be added to the arraylist.
					System.out.print("Enter Employee No:  ");
					int empno=s.nextInt();
					System.out.print("Enter employee name: ");
					String ename=sc.nextLine();//Wnenever we get a string, we use nextLine instead of integers.
					System.out.print("Enter employee salary: ");
					int salary=s.nextInt();		
					al.add(new Employee(empno,ename,salary)); //A simple method to add the acquired data by calling the constructor Employee with its given parameters. Entering the data and it will be passed to the arraylist.	
					
				}//But, we need to load the data in our collection first
				oos=new ObjectOutputStream(new FileOutputStream(file)); //Using Objectoutputstream object to write the object into the file and passing the output of file output stream, so that problem 1 does not arise.(statement 1)  
					//The above line creates the object named oos.
				oos.writeObject(al);
				oos.close(); //Whenever we call oos=new ... in the above line, the file will be created everytime new and the old data will be removed.
				break; //So, by using the previous 3 steps, we are storing the data obtained in al into the "file".
			case 2:
				if(file.isFile()){
					ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
					al=(ArrayList<Employee>)ois.readObject(); //By using the first brackets, we are casting that the data written into the file, i.e-arraylist and collection of employee. 
					ois.close();
					System.out.println("----------------------------------------------------");
					li=al.listIterator(); //initializing the list with a built in function
					while(li.hasNext()){
						System.out.println(li.next());
					} 
					System.out.println("----------------------------------------------------");
				}else{
					System.out.println("File does not exist...............");
				} //case 2 and 3 concept: if file exixts, load its contents into al, then iterate the data and display the same.
			break;
			case 3:
				if(file.isFile()){
					ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
					al=(ArrayList<Employee>)ois.readObject(); //By using the first brackets, we are casting that the data written into the file, i.e-arraylist and collection of employee. 
					ois.close();
					
					boolean found=false;
					System.out.println("Enter employee number to search for: ");
					int empno=s.nextInt();
					System.out.println("----------------------------------------------------");
					li=al.listIterator(); //initializing the list with a built in function
					while(li.hasNext()){
						Employee e=(Employee)li.next(); //checking if the data is matching, if yes, then and there it is displayed. and since the content obtained is employee, the  type casting into employee happens.
						if(e.empno==empno) { //if the employee number is found in the object, the record gets displayed.
							System.out.println(e);
							found=true;
						}
						
					}
					if(!found)
						System.out.println("Record not found"); 
					System.out.println("----------------------------------------------------");
				}else{
					System.out.println("File not exists........................!");
				}
				break;
			case 4:	
				if(file.isFile()){
					ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
					al=(ArrayList<Employee>)ois.readObject(); //By using the first brackets, we are casting that the data written into the file, i.e-arraylist and collection of employee. 
					ois.close();
					
					boolean found=false;
					System.out.println("Enter employee number to delete: ");
					int empno=s.nextInt();
					System.out.println("----------------------------------------------------");
					li=al.listIterator(); //initializing the list with a built in function
					while(li.hasNext()){
						Employee e=(Employee)li.next(); //checking if the data is matching, if yes, then and there it is displayed. and since the content obtained is employee, the  type casting into employee happens.
						if(e.empno==empno) { //if the employee number is found in the object, the record gets displayed.
							li.remove();
							found=true;
						}
						
					}
					if(found){
						oos=new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						System.out.println("Record deleted succesfully...............");
					}
						
					else{
						System.out.println("Record not found");
						 
					}
					System.out.println("----------------------------------------------------");
				}else{
					System.out.println("File not exists........................!");
				}
				break; //case 4 concept: if the record to be deleted is found, li.remove is used to delete the record from the list and the file is updated using oos. 
				case 5:	
				if(file.isFile()){
					ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
					al=(ArrayList<Employee>)ois.readObject(); //By using the first brackets, we are casting that the data written into the file, i.e-arraylist and collection of employee. 
					ois.close();
					
					boolean found=false;
					System.out.println("Enter employee number to update: ");
					int empno=s.nextInt();
					System.out.println("----------------------------------------------------");
					li=al.listIterator(); //initializing the list with a built in function
					while(li.hasNext()){
						Employee e=(Employee)li.next(); //checking if the data is matching, if yes, then and there it is displayed. and since the content obtained is employee, the  type casting into employee happens.
						if(e.empno==empno) { //if the employee number is found in the object, the record gets displayed.
							System.out.print("Enter new employee name");
							String ename=sc.nextLine();
							System.out.print("Enter new salary");
							int salary=s.nextInt();
							li.set(new Employee(empno,ename,salary));
							found=true;
						}
						
					}
					if(found){
						oos=new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(al);
						oos.close();
						System.out.println("Record updated succesfully...............");
					}
						
					else{
						System.out.println("Record not found");
						 
					}
					System.out.println("----------------------------------------------------");
				}else{
					System.out.println("File not exists........................!");
				}
				break; //case 5 concept: if the record to be updated is found, li.set is used to update the record from the list and the file is updated using oos.
			case 6:
				if(file.isFile()){
					ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
					al=(ArrayList<Employee>)ois.readObject(); //By using the first brackets, we are casting that the data written into the file, i.e-arraylist and collection of employee. 
					ois.close();
					Collections.sort(al, new Comparator<Employee>(){
						public int compare(Employee e1, Employee e2){
							return e1.empno - e2.empno; //these 3 lines implement the sorting algo in ascending order
						}
					});//whenever we are calling the sort function and passing the instance of the arraylist, arraylist is the collection of employee and we need to implement the comparative interface.

					System.out.println("----------------------------------------------------");
					li=al.listIterator(); //initializing the list with a built in function
					while(li.hasNext()){
						System.out.println(li.next());
					}
					System.out.println("----------------------------------------------------");
				}else{
					System.out.println("File does not exist...............");
				}
			break; 				
		case 7:
				if(file.isFile()){
					ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
					al=(ArrayList<Employee>)ois.readObject(); //By using the first brackets, we are casting that the data written into the file, i.e-arraylist and collection of employee. 
					ois.close();
					Collections.sort(al, new Comparator<Employee>(){
						public int compare(Employee e1, Employee e2){
							return e1.empno - e2.empno; //these 3 lines implement the sorting algo in ascending order
						}
					});//whenever we are calling the sort function and passing the instance of the arraylist, arraylist is the collection of employee and we need to implement the comparative interface.
					// As compared to the previous case, once the sorting is done, we need to write the changes into the file. The following few statements do exactly that.
					oos=new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al); // updated data will be written permanently into the file after sorting.
					oos.close();

					System.out.println("----------------------------------------------------");
					li=al.listIterator(); //initializing the list with a built in function
					while(li.hasNext()){
						System.out.println(li.next());
					} 
					System.out.println("----------------------------------------------------");
				}else{
					System.out.println("File does not exist...............");
				} //case 2 and 3 concept: if file exixts, load its contents into al, then iterate the data and display the same.
			break; 
			case 8:
				if(file.isFile()){
					ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
					al=(ArrayList<Employee>)ois.readObject(); //By using the first brackets, we are casting that the data written into the file, i.e-arraylist and collection of employee. 
					ois.close();
					Collections.sort(al, new Comparator<Employee>(){
						public int compare(Employee e1, Employee e2){
							return e1.ename.compareTo(e2.ename); //here, ename is a variable declared as an instance variable and the entire line is responsible for sorting the data on out screen but it will be in ascending order. For descending order, interchange the e1 with e2.
						}
					});//whenever we are calling the sort function and passing the instance of the arraylist, arraylist is the collection of employee and we need to implement the comparative interface.

					System.out.println("----------------------------------------------------");
					li=al.listIterator(); //initializing the list with a built in function
					while(li.hasNext()){
						System.out.println(li.next());
					} 
					System.out.println("----------------------------------------------------");
				}else{
					System.out.println("File does not exist...............");
				}
			break; 				
		case 9:
				if(file.isFile()){
					ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
					al=(ArrayList<Employee>)ois.readObject(); 
					ois.close();
					Collections.sort(al, new Comparator<Employee>(){
						public int compare(Employee e1, Employee e2){
							return e2.ename.compareTo(e1.ename); //it will be sorted in descending order.
						}
					});//whenever we are calling the sort function and passing the instance of the arraylist, arraylist is the collection of employee and we need to implement the comparative interface.
					// As compared to the previous case, once the sorting is done, we need to write the changes into the file. The following few statements do exactly that.
					oos=new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al); // updated data will be written permanently into the file after sorting.
					oos.close();

					System.out.println("----------------------------------------------------");
					li=al.listIterator(); //initializing the list with a built in function
					while(li.hasNext()){
						System.out.println(li.next());
					} 
					System.out.println("----------------------------------------------------");
				}else{
					System.out.println("File does not exist...............");
				}
			break; 
			case 10:
				if(file.isFile()){
					ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
					al=(ArrayList<Employee>)ois.readObject(); //By using the first brackets, we are casting that the data written into the file, i.e-arraylist and collection of employee. 
					ois.close();
					Collections.sort(al, new Comparator<Employee>(){
						public int compare(Employee e1, Employee e2){
							return e2.salary-e1.salary; //to sort the salary on screen in descending order.
						}
					});

					System.out.println("----------------------------------------------------");
					li=al.listIterator(); //initializing the list with a built in function
					while(li.hasNext()){
						System.out.println(li.next());
					} 
					System.out.println("----------------------------------------------------");
				}else{
					System.out.println("File does not exist...............");
				}
			break; 				
		case 11:
				if(file.isFile()){
					ois=new ObjectInputStream(new FileInputStream(file)); //if the file exists, we will load the data into the file and if not, the vice versa.
					al=(ArrayList<Employee>)ois.readObject(); 
					ois.close();
					Collections.sort(al, new Comparator<Employee>(){
						public int compare(Employee e1, Employee e2){
							return e1.salary-e2.salary; //it will be sorted in ascending order.
						}
					});
					//We need to write the changes into the file. The following few statements do exactly that.
					oos=new ObjectOutputStream(new FileOutputStream(file));
					oos.writeObject(al); // updated data will be written permanently into the file after sorting.
					oos.close();

					System.out.println("----------------------------------------------------");
					li=al.listIterator(); //initializing the list with a built in function
					while(li.hasNext()){
						System.out.println(li.next());
					} 
					System.out.println("----------------------------------------------------");
				}else{
					System.out.println("File does not exist...............");
				}
			break; 				
			}

		}while(choice!=0); //But, there is a problem with this as the collection stores the data only during the execution. So the data will not be displayed the next time we run the class. (Problem 1)
	}

}