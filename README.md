# Species-Database---SQL-Homework

The first SQL homework prompt was:

Practical Task:
Create a database schema using Mysql Workbench for the new hierarchy with at least 12 tables and all relations. The schema should satisfy the 3 Normal Forms. (request topics for a new hierarchy in practical groups, mentors should provide them)

I have completed that homework and uploaded it as a MySQL Workbench model. I am not sure if that is the correct format for sharing my homework yet. I was informed that we should choose our own topics, so I chose the topic "Species".

**Later I decided to just export it as a dump file, which is Dump20230605_Old.sql**

I updated homework 1 with all of my mentors critiques. I made it so that diet connects back to species, because species eat other species generally (unless plants, which I'll need to think through...). I also changed the classifications table to taxonomies and added a bunch of new tables connecting to it; I also added some awesome trigger scripts to make sure it's impossible to enter, for example, a phylum that is not within a particular kingdom and so on for each taxonomic subdivision.

# Second HW

The next prompt is:

Create for your hierarchy:
10 statements for insertion.
10 statements for updating.
10 statements for deletions.
5 alter table.
 1 big statement to join all tables in the database.
5 statements with left, right, inner, outer joins.
7 statements with aggregate functions and group by and without having.
7 statements with aggregate functions and group by and with having.

I wrote up a lot of it and pushed it to here under Species.sql, but it seems there's some issues with Insert statements and perhaps more, so I need to debug it.

# Third HW

This was the prompt:

Practical part:
Build hierarchy for Schema from the below course.
Create DAO classes with necessary interfaces, abstract classes, and Generics.  DAO should be scalable and flexible to support another framework and another database as well. All CRUD operations should be supported using JDBC. Use connection pool from the below block.
Implement Service layer with necessary abstraction to be able to switch between databases and frameworks.

At the moment, I'm almost done with it, but I haven't finished my implementations in my DAO layer and I haven't really made my Service layer. I hope to be fully done soon.
# Species-Database---SQL-Homework_XML
# Species-Database---SQL-Homework_XML
# Species-Database---SQL-Homework_XML
# Species-Database---SQL-Homework_XML
