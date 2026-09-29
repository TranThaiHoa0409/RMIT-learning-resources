# Java Programming Bootcamp 1 — Week 1: Programming Basics and the IDE

## Agenda

- Introduction
- Canvas Walkthrough
- Revise Key Concepts
- Q&A
- Lab Time

## Canvas Walkthrough

Covered in the Introduction session — see `JavaPB_Week1_Introduction.md` for the course structure, teaching team, and Canvas navigation.

## Job Opportunities in Tech

| Ranking | Occupation | Description |
|---|---|---|
| 1 | Information Security Analyst | Carries out security measures to protect company networks. |
| 2–4 | Nurse Practitioner, Physician's Assistant, Medical and Health Services Manager | — |
| 5 | Software Developer | Designs computer programs, combining creativity and technical know-how, often working in teams. |
| 6 | Data Scientist | — |
| 7–12 | Financial Manager, Statistician, Lawyer, Speech-Language Pathologist, Physician, Registered Nurse | — |
| 13 | IT Manager | Coordinate computer related activities for a company or organization. |

Ranking source (2022) is listed in Further Reading.

## Popular Programming Languages

Top languages ranked by popularity (June 2024):

| Rank | Programming Language |
|---|---|
| 1 | Python |
| 2 | C++ |
| 3 | C |
| 4 | Java |
| 5 | C# |
| 6 | JavaScript |
| 7 | Go |
| 8 | SQL |
| 9 | Visual Basic |
| 10 | Fortran |

Ranking source is listed in Further Reading.

## What Is a Computer Program?

A computer program transforms **Input(s)** into **Output(s)** through one or more **Process(es)**.

![Input, process, output diagram with a simple arithmetic example](images/Week1/10_input_process_output_basic.png)

Simple example: given inputs `2` and `3`, the process `2 x 3` produces the output `6`.

A more real-world example — AI avatar generation: a selfie photo is the input, an AI generation process (e.g. Zalo AI Avatar Generation) is applied, and a stylized avatar image is the output.

![Zalo AI Avatar Generation example: selfie photo in, stylized avatar out](images/Week1/11_zalo_ai_avatar_example.png)

### Participation Activity — A Basic Computer Program (1.1.1)

```
x = Get next input
y = Get next input
z = x + y
Put z to output
```

Example run: input `2` and `5` (keyboard) → the program computes `z = 7` and prints `7` to the screen.

## Problem Solving

### A Mathematical Sock Problem

A drawer has 6 socks: 2 grey, 2 red, 2 yellow. **How many socks do you need to pull out before you are guaranteed to have a matching pair?**

**Solution 1** (pull two, put back if no match, repeat):
1. Pull the first sock.
2. Pull the second sock.
3. If not a matching pair, put them back.
4. Repeat the process.

**Solution 2** (pull, keep if no match, keep pulling):
1. Pull the first sock.
2. Pull the second sock.
3. If not a matching pair, keep them.
4. Pull the third sock, and so on, until a matching pair is found.

## Errors and Warnings

Syntax and semantic (logic) errors are made by us — common causes include:

- Missing `;`, `()`, `""`, `''`, `{}`, `[]`
- Incorrect variable names (e.g. `myVariable` → `myvariable`)
- Wrong keywords (e.g. `int` → `inl`)
- Right idea, wrong statements (e.g. `10.0/3.0` → `10/3`)

> "Those who fail should reflect on themselves." — Mencius

### Participation Activity — Can You Fix Syntax Errors? (zyDE 1.10.2)

Click Run to compile and note the long error list. Fix only the first error, then recompile. Repeat that process (fix first error, recompile) until the program compiles and runs. Expect to see misleading error messages as well as errors that occur before the reported line number.

![zyDE 1.10.2 activity: BeansInJars.java with multiple syntax errors to fix](images/Week1/20_fixing_syntax_errors_activity.png)

## Integrated Development Environment (IDE)

### How to Interact With an IDE? (Participation Activity 1.3.5)

Example — `Salary.java`, run through the console/command-line interface:

```java
import java.util.Scanner;

public class Salary {
    public static void main(String[] args) {
        int wage;

        Scanner scnr = new Scanner(System.in);
        wage = scnr.nextInt();

        System.out.print("Salary is ");
        System.out.println(wage * 40 * 52);
    }
}
```

Compiling from the console: `javac Salary.java` (the console labels the executable program name and its argument).

![Console/command-line interaction: compiling Salary.java with javac](images/Week1/22_ide_console_interaction.png)

### IntelliJ

![IntelliJ IDE example project with Shape/Circle/Rectangle classes](images/Week1/23_intellij_screenshot.png)

### Visual Studio Code

A free, open-source code editor. Download and local setup instructions are in Further Reading.

---

## Further Reading

- [U.S. News – 100 Best Jobs Rankings](https://money.usnews.com/careers/best-jobs/rankings/the-100-best-jobs)
- [TIOBE Index](https://www.tiobe.com/tiobe-index/)
- [RMIT Canvas – Local Software Installation and Setup](https://rmit.instructure.com/courses/149434/pages/local-software-installation-and-setup?module_item_id=6687578)
- [W3Schools – Java Syntax](https://www.w3schools.com/java/java_syntax.asp)
- [W3Schools – Java User Input](https://www.w3schools.com/java/java_user_input.asp)
- [W3Schools – Java Output](https://www.w3schools.com/java/java_output.asp)
- [LinkedIn Learning – Problem Solving: Think Systematically](https://www.linkedin.com/learning/getting-started-with-technology-think-like-an-engineer/problem-solving-think-systematically-14349710?autoAdvance=true&autoSkip=false&autoplay=true&resume=true&u=2104756)
