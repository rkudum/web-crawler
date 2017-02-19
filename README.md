# web-crawler
This is a web crawler micro service exposed as REST web service using spring boot and the JSoup Framework.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites

Java 8
maven
Access to port 8080 on the local box where this code will be executed.

### Installing

Clone the repository from git hub by running the following command
git clone https://github.com/rkudum/web-crawler.git

cd to web-crawler directory
and run command 'mvn spring-boot:run'
This will startup a standalone tomcat instance on port 8080.

## to access the application in the browser enter
http://localhost:8080/webCrawler  or
http://localhost:8080/webCrawler?domain=http://wiprodigital.com

### Request Parameters
url takes a parameter by name domain  this will be the domain name which needs to be web crawled.
By default this value is set http://wiprodigital.com/

### Response

The Response is a json response in the below format
{
  "linkName" : "f:\\test.html",
  "childLinks" : [ {
    "linkName" : "f:\\test1.html",
    "childLinks" : [ {
      "linkName" : "f:\\test11.html"
    }, {
      "linkName" : "f:\\test21.html"
    }, {
      "linkName" : "f:\\test21.html"
    } ]
  }, {
    "linkName" : "f:\\test2.html",
    "childLinks" : [ {
      "linkName" : "f:\\test12.html"
    }, {
      "linkName" : "f:\\test22.html"
    }, {
      "linkName" : "f:\\test32.html"
    } ]
  }, {
    "linkName" : "f:\\test3.html",
    "childLinks" : [ {
      "linkName" : "f:\\test13.html"
    }, {
      "linkName" : "f:\\test23.html"
    }, {
      "linkName" : "f:\\test33.html"
    } ]
  } ]
}

