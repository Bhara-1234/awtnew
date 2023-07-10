package awtexample;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;

public class EmployeeUI extends Frame {

    private Label nameLabel, rollLabel, salaryLabel, departmentLabel, jobLabel,modeLable;
    private TextField nameField, rollField, salaryField, departmentField, jobField,searchField;
    private Button clearButton, exitButton, nextButton, searchButton, addButton, editButton,deleteButton,
            previousButton, lastButton, firstButton,saveButton,modeButton;
    private ArrayList<Employee> employeeList  = new ArrayList<>() ;
    private int currentIndex;

    public EmployeeUI() {
        setTitle("Employee Details");
        setSize(800, 800);
        setLayout(null);

        
        loadEmployeeList();
        initializeComponents();

        currentIndex = 0;
        displayEmployeeDetails();

        setVisible(true);
        
        
    }
    
    private void initializeComponents() {
    	
        nameLabel = new Label("Name:");
        nameLabel.setBounds(30, 50, 70, 20);
        add(nameLabel);
        searchField= new TextField();
        searchField.setBounds(350,215,150,20);
        add(searchField);
        nameField = new TextField();
        nameField.setBounds(110, 50, 150, 20);
        add(nameField);

        rollLabel = new Label("Roll:");
        rollLabel.setBounds(30, 80, 70, 20);
        add(rollLabel);

        rollField = new TextField();
        rollField.setBounds(110, 80, 150, 20);
        add(rollField);

        salaryLabel = new Label("Salary:");
        salaryLabel.setBounds(30, 110, 70, 20);
        add(salaryLabel);

        salaryField = new TextField();
        salaryField.setBounds(110, 110, 150, 20);
        add(salaryField);

        departmentLabel = new Label("Department:");
        departmentLabel.setBounds(30, 140, 70, 20);
        add(departmentLabel);

        departmentField = new TextField();
        departmentField.setBounds(110, 140, 150, 20);
        add(departmentField);

        jobLabel = new Label("Job:");
        jobLabel.setBounds(30, 170, 70, 20);
        add(jobLabel);

        jobField = new TextField();
        jobField.setBounds(110, 170, 150, 20);
        add(jobField);
        
        Choice ch = new Choice();
        ch.add("Read");
        ch.add("Write");
        add(ch);
        ch.setBounds(280,140,150,20);
        
        clearButton = new Button("Clear");
        clearButton.setBounds(30, 210, 70, 30);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });
        add(clearButton);

        exitButton = new Button("Exit");
        exitButton.setBounds(110, 210, 70, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(exitButton);

        nextButton = new Button("Next");
        nextButton.setBounds(190, 210, 70, 30);
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < employeeList.size() - 1) {
                    currentIndex++;
                    displayEmployeeDetails();
                }
                else {
                	currentIndex=0;
                	displayEmployeeDetails();
                }
            }
        });
        add(nextButton);
        searchButton = new Button("Search");
        searchButton.setBounds(270, 210, 70, 30);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String s=searchField.getText();
            	for( Employee e1 : employeeList ) {
            		  if((e1.getName()).equals(s)) {
            			  nameField.setText(e1.getName());
            			  rollField.setText(e1.getRoll());
            			  salaryField.setText(e1.getSalary());
            			  departmentField.setText(e1.getDepartment());
            			  jobField.setText(e1.getJob());
            			
            		  }
            	
                
            }
            }
        });
        add(searchButton);
        modeLable = new Label(":Mode");
        modeLable.setBounds(440,135,70,30);
        add(modeLable);
        

        addButton = new Button("Add");
        addButton.setBounds(30, 250, 70, 30);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(ch.getSelectedItem().equals("Write")) {
            	clearFields();
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "Access Denied");
            	}
            }
        });
        add(addButton);
        saveButton = new Button("Save");
        saveButton.setBounds(30, 300, 70, 30);
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(ch.getSelectedItem().equals("Write")) {
            	try (PrintWriter writer = new PrintWriter(new FileWriter("D:\\employee_data.txt",true))) {
                    
    writer.println(nameField.getText() + "," + rollField.getText() + "," +salaryField.getText() + "," + departmentField.getText() + "," + jobField.getText());
         
                } catch (IOException er) {
                    er.printStackTrace();
                }
            	JOptionPane.showMessageDialog(null, "saved");
            }
            	else {
            		JOptionPane.showMessageDialog(null, "Access Denied");
            	}
            }
           
            
        });
        add(saveButton);
        editButton = new Button("Edit");
        editButton.setBounds(100, 300, 70, 30);
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(ch.getSelectedItem().equals("Write")) {
                Employee em=employeeList.get(currentIndex);
                em.setName(nameField.getText());
                em.setRoll(rollField.getText());
                em.setSalary(salaryField.getText());
                em.setDepartment(departmentField.getText());
                em.setJob(jobField.getText());
                try {
                	PrintWriter pw=new PrintWriter(new FileWriter("D:\\employee_data.txt",false));
                	for(Employee emp:employeeList) {
                		pw.println(emp.getName()+","+emp.getRoll()+","+emp.getSalary()+","+emp.getDepartment()+","+emp.getJob());
                	}
                	pw.close();
                }catch(IOException ioe) {
                	ioe.printStackTrace();
                }
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "Access Denied");
            	}
            }
        });
        add(editButton);

        deleteButton = new Button("Delete");
        deleteButton.setBounds(110, 250, 70, 30);
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(ch.getSelectedItem().equals("Write")) {
            		employeeList.remove(currentIndex); 
                    clearFields();
                    
                    updateEmployeeDataFile();
            	}
            	else {
            		JOptionPane.showMessageDialog(null, "Access Denied");
            	}
            }
        });
        add(deleteButton);

        previousButton = new Button("Previous");
        previousButton.setBounds(190, 250, 70, 30);
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (currentIndex > 0) {
                    currentIndex--;
                    displayEmployeeDetails();
                }
                else {
                	currentIndex=(employeeList.size()-1);
                	displayEmployeeDetails();
                }
            }
        });
        add(previousButton);

        lastButton = new Button("Last");
        lastButton.setBounds(270, 250, 70, 30);
        lastButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentIndex= employeeList.size()-1;
                displayEmployeeDetails();
            }
        });
        add(lastButton);

        firstButton = new Button("First");
        firstButton.setBounds(350, 250, 70, 30);
        firstButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	 currentIndex = 0;
                 displayEmployeeDetails();
            }
        });
        add(firstButton);
    }

    private void loadEmployeeList() {
        
        try 
        {
        	BufferedReader br = new BufferedReader(new FileReader("D:\\employee_data.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String name = data[0];
                    String roll = data[1];
                    String salary =data[2];
                    String department = data[3];
                    String job = data[4];
                    Employee employee = new Employee(name, roll, salary, department, job);
                    employeeList.add(employee);
                }
              
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayEmployeeDetails() {
        if (employeeList.size()!=0) {
            Employee employee = employeeList.get(currentIndex);
            nameField.setText(employee.getName());
            rollField.setText(employee.getRoll());
            salaryField.setText(employee.getSalary());
            departmentField.setText(employee.getDepartment());
            jobField.setText(employee.getJob());
        }
    }

    private void clearFields() {
        nameField.setText("");
        rollField.setText("");
        salaryField.setText("");
        departmentField.setText("");
        jobField.setText("");
    }
    private void updateEmployeeDataFile() {
        try {
        	PrintWriter writer = new PrintWriter(new FileWriter("D:\\employee_data.txt",false)) ;
            for (Employee employee : employeeList) {
                writer.println(employee.getName() + "," + employee.getRoll() + "," +employee.getSalary() + "," + employee.getDepartment() + "," + employee.getJob());
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EmployeeUI eu=new EmployeeUI();
    }

    class Employee {
        private String name;
        private String roll;
        private String salary;
        private String department;
        private String job;

        public Employee(String name, String roll, String salary, String department, String job) {
            this.name = name;
            this.roll = roll;
            this.salary = salary;
            this.department = department;
            this.job = job;
        }
        public void setName(String n) {
        	name=n;
        }
        public void setRoll(String r) {
        	roll=r;
        }
        public void setSalary(String s) {
        	salary=s;
        }
        public void setDepartment(String d) {
        	department=d;
        }
        public void setJob(String j) {
        	job=j;
        }
        public String getName() {
            return name;
        }
        public String getRoll() {
            return roll;
        }

        public String getSalary() {
            return salary;
        }

        
        public String getDepartment() {
            return department;
        }
        public String getJob() {
            return job;
        }
    }
}
