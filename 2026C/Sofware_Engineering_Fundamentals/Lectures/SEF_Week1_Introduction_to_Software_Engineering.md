# SEF Week 1 – Introduction to Software Engineering

## What is Software?

- **Definition 1:** Computer programs and associated documentation. Software products may be developed for a particular customer or may be developed for a general market.
- **Definition 2:** Software is:
  1. instructions (computer programs) that when executed provide desired features, function, and performance;
  2. data structures that enable the programs to adequately manipulate information; and
  3. documentation that describes the operation and use of the programs.

## Software Products

| | Generic products | Customized products |
|---|---|---|
| **Description** | Stand-alone systems developed by a development organization and sold to any customer who wishes to buy them. | Software that is commissioned by a specific customer to meet their own needs. |
| **Examples** | Apps for mobile devices; PC software such as graphics programs, project management tools; CAD software; software for specific markets such as appointment systems for dentists. | Embedded control systems, air traffic control software, traffic monitoring systems. |
| **Product specification** | The specification of what the software should do is owned by the software developer, and decisions on software change are made by the developer. | The specification of what the software should do is owned by the customer for the software, and they make decisions on software changes that are required. |

## What are the Attributes of Good Software?

Good software should deliver the required functionality and performance to the user and should be **maintainable, dependable, efficient, and usable**.

| Product characteristics | Description |
|---|---|
| **Maintainability** | Software should be written in such a way that it can evolve to meet the changing needs of customers. This is a critical attribute because software change is an inevitable requirement of a changing business environment. |
| **Dependability** | Software dependability includes a range of characteristics including reliability, security, and safety. Dependable software should not cause physical or economic damage in the event of system failure. Malicious users should not be able to access or damage the system. |
| **Efficiency** | Software should not make wasteful use of system resources such as memory and processor cycles. Efficiency, therefore, includes responsiveness, processing time, memory utilization, etc. |
| **Acceptability** | Software must be acceptable to the type of users for which it is designed. This means that it must be understandable, usable, and compatible with other systems that they use. |

## What is Engineering?

A body of knowledge used when building things: Scheduling, Costing, Estimating (time), Building (Design and Implementation), Testing, Communicating, Organizing

> How do Software Engineering practices compare with Civil Engineering?

### Engineering involves choices

- Engineering involves choices.
- An engineer has to build solutions to problems that are **Correct, Reliable, Cheap, Safe, Adaptable, and Fast**.
- Suppose your team has been hired to develop a new online Banking Application. Which of these attributes is the most important?

### Engineering is about design

- Engineering is about design.
- Some software is developed without much thought.
- It may start off simple, but due to bad design, it quickly becomes a mess.
- Often due to new requirements being inserted without revising the original design.

## Software Engineering Definitions

- **Software engineering** is an engineering discipline that is concerned with all aspects of software production from the early stages of system specification through to maintaining the system after it has gone into use.
  - **Engineering discipline:** Using appropriate theories and methods to solve problems bearing in mind organizational and financial constraints.
  - **All aspects of software production:** Not just the technical process of development. Also, project management and the development of tools, methods, etc. to support software production.

### Other Definitions

- Software engineering is the establishment and use of sound engineering principles in order to obtain economically software that is reliable and works efficiently on real machines.
- **The IEEE definition** – Software Engineering:
  1. The application of a systematic, disciplined, quantifiable approach to the development, operation, and maintenance of software; that is, the application of engineering to software.
  2. The study of approaches as in (1).

## Importance of Software Engineering

- More and more, individuals and society rely on advanced software systems. We need to be able to produce reliable and trustworthy systems economically and quickly.
- It is usually cheaper, in the long run, to use software engineering methods and techniques for software systems rather than just write the programs as if it was a personal programming project. For most types of systems, the majority of costs are the costs of changing the software after it has gone into use.

## Components of Software Engineering

The centre of all four components is **People**.

| Component | Description | Examples |
|---|---|---|
| **Process** | A framework for the tasks required to build high-quality software. | Waterfall, RUP, XP/Scrum |
| **Notation** | An agreed, common language used to communicate all aspects of the system, from requirements to final design. | UML |
| **Tools** | Tools to make working through the process less difficult for all involved. | VP, Eclipse, LucidChart |
| **Product** | The actual software product or system built and put into operation. | Your program |

## Software Process Activities (more details on Week 02)

**Software process:** The systematic approach that is used in software engineering is called a software process.

- **Software specification** – customers and engineers define the software that is to be produced and the constraints on its operation.
- **Software development** – the software is designed and programmed.
- **Software validation** – the software is checked to ensure that it is what the customer requires.
- **Software evolution** – the software is modified to reflect changing customer and market requirements.

### Different systems, different processes

- Different types of systems need different development processes.
  - For example, real-time software in an aircraft has to be completely specified before development begins.
  - In e-commerce systems, the specification and the program are usually developed together.
- Consequently, these generic activities may be organized in different ways and described at different levels of detail, depending on the type of software being developed.

## Software Engineering Diversity

- There are many different types of software systems and there is no universal set of software techniques that is applicable to all of these.
- The selection of software engineering methods and tools depends on these three factors:
  1. the type of application being developed **[the most important factor]**,
  2. the requirements of the customer, and
  3. the background of the development team.
- And more?

## Software Engineering Fundamentals

Some fundamental principles apply to all types of software systems, irrespective of the development techniques used:

- Systems should be developed using a managed and understood development process. Of course, different processes are used for different types of software.
- Dependability and performance are important for all types of systems.
- Understanding and managing the software specification and requirements (what the software should do) are important.
- Where appropriate, you should reuse software that has already been developed rather than write new software.

## Examples of IT Failures

### The Queensland Health (QH) New Payroll System Fiasco

- A new payroll system was introduced in 2006; the project seemed like it was going to be straightforward.
- IBM Australia set up a new payroll system for the 80,000 employees of QH.
- The contract budget was $6 million and was expected to go live after six months.
- The system went live in late 2010, with major defects and an additional cost of nearly $25M.
- QH hired another 1K employees to manually undertake the payroll, adding $1.15B over eight years.

> How would you avoid it?

### Ariane 5 – Launch 1996

- A successor to the Ariane 4, the more powerful rocket had a different flight profile.
- Management insisted that the existing Ariane 4 software be reused without being tested against the new flight profile.
- When launched, an overflow triggered an exception, shutting down the guidance computers.
- The rocket veered off course and was destroyed.
- Resulting in a loss of 500 million dollars.

> How would you avoid it?

## Software Engineering Ethics – Principles

- Act consistently with the public interest
- Act in a manner that is in the best interest of the client and employer
- Ensure that products meet the highest professional standards possible
- Maintain integrity in professional judgment
- Managers shall promote an ethical approach
- Advance the integrity and reputation of the profession
- Be fair to and supportive of colleagues
- Participate in lifelong learning and promote an ethical approach

## Software Engineering / Computer Science / System Engineering

| Comparison | Description |
|---|---|
| **Computer Science vs. Software Engineering** | Computer science focuses on theory and fundamentals. Software engineering is concerned with the practical problems of developing and delivering useful software. |
| **System Engineering vs. Software Engineering** | System engineering is concerned with all aspects of computer-based systems development including hardware, software, and process engineering. Software engineering is part of this more general process. |

## Point to Ponder

You are a tester, and the product you are testing does not yet meet the testing requirements agreed upon in writing. Your manager wants to ship the product now, and continue testing so that the next release will meet the testing requirements. What do you do?

- Discuss the issue with your manager?
- Talk to the manager's boss?
- Talk to the customer?

> You should always dicuss the issue with your manager first. If you cannot resolve the issue, then escalate it to the next level manager. Never go directly to the customer, the compay issue should be resolved internally.

## Summary

- Software engineering is an engineering discipline that is concerned with all aspects of software production.
- Essential software product attributes are maintainability, dependability, and security, efficiency, and acceptability.
- The high-level activities of specification, development, validation, and evolution are part of all software processes.
- The fundamental notions of software engineering are universally applicable to all types of system development.
- There are many different types of systems, and each requires appropriate software engineering tools and techniques for their development.
- The fundamental ideas of software engineering are applicable to all types of software systems.
- Software engineers have responsibilities to the engineering profession and society. They should not simply be concerned with technical issues.

## Further Reading

- [Ian Sommerville – Software Engineering book slides](https://iansommerville.com/software-engineering-book/slides/)
