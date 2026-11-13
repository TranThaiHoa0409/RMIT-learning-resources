# Week 1 – Tut: Software Engineering Fundamentals (Solution)

## Part A: Discuss the following questions.

### **1.** What is the most important difference between generic software products? development and custom software development? What might this mean in practice for users of generic software products?

**Possible Answer:**

The differences are in the specification and evolution phases. Generic products are stand-alone systems that are developed by a development organization and sold to any customer who wishes to buy them.

Examples – apps for mobile devices, PC software such as graphics programs, project management tools; CAD software; software for specific markets such as appointment systems for dentists. The specification of what the software should do is owned by the software developer and decisions on software change are made by the developer.

A customized software product is commissioned by a specific customer to meet their own needs.

Examples – embedded control systems, air traffic control software, and traffic monitoring systems. The specification of what the software should do is owned by the customer for the software and they make decisions on software changes that are required.

### **2.** What are the fundamental activities in software engineering?

**Possible Answer**

- **Software specification**, where customers and engineers define the software that is to be produced and the constraints on its operation.
- **Software development**, where the software is designed and programmed.
- **Software validation**, where the software is checked to ensure that it is what the customer requires.
- **Software evolution**, where the software is modified to reflect changing customer and market requirements.

**a.** Discuss what sub-activities might be conducted in each fundamental activity.

**Possible Answer**

- **Software specification:** feasibility study, find the stakeholder of the system, collect requirements, analyse requirements, document requirements, prepare a vision document showing all required resources for developing the system, estimate budget and schedule the project.
- **Software development:** design the software architecture, develop the detailed design, translate the collected requirements into design, implement the software, design GUI, code review, database design.
- **Software validation:** conduct different types of testing, for example, test each service/function/object (unit testing), system testing (testing the whole system), security testing/performance testing, user acceptance testing.
- **Software evolution:** release the software to the customer environment, collect new requirements, modify the system to incorporate the new requirements, monitor the system at runtime to detect possible runtime issues (performance degradation) or any other issues (security issues).

**b.** What roles might be involved in each activity and sub-activity?

**Possible Answer**

- **Software specification:** Requirements Engineer, Project Manager, Product Owner/End User, System Architect
- **Software development:** System Architect, Software Designer, Software Engineer, Programmer, Data Engineer, Database Designer
- **Software validation:** Tester, Developer/Programmer
- **Software evolution:** System Admin, End-user, and all above-mentioned roles

### **3.** What factors should be considered when selecting tools and techniques for developing software systems?

**Possible Answer**

Most important factors:

- **The type of application being developed [the most important factor]:** The safety-critical systems need strong testing tools and techniques. Most of the time should be dedicated to the testing phase in such systems. The size and the complexity of the project.
- **The requirements of the customer:** the stability of the requirements, number of requirements: The percentage of requirements change in large projects is higher than that of small projects.
- **The background of the development team.** There might be a knowledge gap among team members in using/applying some SE techniques and tools. Learning new techniques and tools takes time and some people are resistant to changes. Other factors are the size of the development team, the maturity of the development team, and the culture of the development team.

Other factors:

- Available Time and Budget.

### **4.** Discuss the testing cost and effort in the following software systems. In which system the testing cost and effort are more? Why?

- **a.** Real-time software in an aircraft
- **b.** Video editing software
- **c.** Mobile banking apps

**Answer**

Ordered based on the testing cost and effort:

- **a. Real-time software in an aircraft:** failure in such a system may lead to loss of life.
- **b. Video editing software:** failure in such a system has less damage compared to the above-mentioned systems.
- **c. Mobile banking apps:** failure in such a system may lead to financial disaster.

### **5.** Provide a few examples (both positive and negative) that indicate the impact of software on our society.

### **6.** Briefly discuss why it is usually cheaper, in the long run, to use software engineering methods and techniques for software systems.

**Possible Answer**

Software systems (particularly large-scale systems) are complex and involve many people with different and sometimes conflicting goals. Most of the time the customers/end users are not able to express their needs explicitly and correctly. They also may use some terminologies which may not be understandable to the development team. So, there is a need for specific tools and techniques to address these issues.

No one can understand all activities and issues during software development. Furthermore, a developer or a designer may leave the project in the middle or after the project. If their activities and decisions are not documented by software engineering tools and techniques, it will be challenging for the rest of the team to remember and understand their activities and decisions after a while. Changing some of the decisions (e.g., architectural decisions) made for a software system without knowing their rationale may lead to extensive costs.

## Part B: Let us review your programming! Implement a Simple Method in Java or C

### **Tasks**

1. Create a Class and name it `SearchClass`
2. Create a method and name it `FindMax`
3. The `FindMax` method should take an array of Integers (i.e., Integer []) as input and find the maximum number in the array.
4. The method should return the maximum number in the given array.
5. In the main method of `SearchClass` class, create an object and call the `FindMax` method with the following arrays.

```text
Array1 [70, 61, 72, 83, 38]
Array2 [7, 2, 76, 4, 99]
Array3 28, 9, 13, 78, 19
Array4 [68, 84, 41, 62, 18]
```

**Possible Answer**

```java
public class SearchClass {

public int FindMax (int array [])
{
int max=array [0];
for (int i=1; i <array.length; i++ )
            if (array [i]>= max)
            {
              max=array [i];
            }
            return max;
      }
public static void main(String[] args) {

      SearchClass object_1 = new SearchClass();
      int array1 []= {70, 61, 72, 83, 38};
      int array2 []= {7, 2, 76, 4, 99};
      int array3 []= {28, 9, 13, 78, 19};
      int array4 []= {68, 84, 41, 62, 18};
      System.out.println(object_1.FindMax(array1));
      System.out.println(object_1.FindMax(array2));
      System.out.println(object_1.FindMax(array3));
      System.out.println(object_1.FindMax(array4));
      }

}
```
