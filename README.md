# Merge Profiles from search REST API 

### --Spring Boot
### --MySQL
### --JPA
### --Stored Procedure with custom repository
### --CRUD from JPA repository


## Objective:  find all duplicate profiles accidentally created by user

The point of the project is to find any first name, last name matches in the db due to accidental duplication. This query is described as needed only occasionally by its documentation.  I therefore do not support a full table index with Solr/ElasticSearch unless it becomes evident that it is necessary. JPA integration within Spring boot provides all the CRUD functionality without any controller code.  A pure REST implmentation like Jersey was also considered.  Although not required here, the extra MVC and extensive DI framework extensions potential would be available with Boot for free.
It did come to mind to consider checking for duplicates upon creation to proactively prevent this problem.  It would be possible to prevent potential duplicates at creation, but because this action is transactional, might not be realistic for millions of users as an efficient solution.

This is a simple REST API with Spring Boot for ease and rapid development.

Toolkit choice:
MySQL was chosen as a simple, solid solution that avoids burn out from NoSQL limitations and failures. It is a proven solution for terabytes/petabytes of data. I could also have used a NoSQL solution for denormalization to hold a cache/index. For this small number of attributes and a one-off use case, I thought it would amount to over-engineering to do so.

I used a Stored Procedure for the custom 	query.  I could have created a Stored Procedure for all of the CRUD as well.  I included a sample read for the user table to show this.  Stored Procedures are important because:

** --security: user cannot directly access tables but instead must go through the function

** --they can be stored separately, written once, and executed anywhere 

** --performance: much faster due to native pre-compiling, caching optimization in the DB server and fact that request for regular query requires parsing, analysis, and optimization for each request and is thus slower.

I investigated using a FULLTEXT mysql search with a FULLTEXT index, but unfortunately it was slower.  I reverted to a LIKE query that uses two optimizations:

** --search for equality in the lastname because it will be exact if it exists

** -- a compound index on lastname, firstname so that the table is searched for the records that have that exact match if it exists, first

I did not optimize for egregious typos.  I would expect this to be too infrequent to matter.  It could be addressed with a more complicated query or as previously mentioned, a denormalized search that proved to be faster.
The overall idea here is that it is a functional prototype that wants to be as simple as possible while getting reasonable returns.  It probably can be improved in terms of mysql tuning especially with third party tools like Percona server, but may never require it.  

The MySQL .sql file from workbench, containing SP & table is located on dropbox here:

https://www.dropbox.com/s/zby5ssjjlvz6wtp/Dump20171114.sql?dl=0


