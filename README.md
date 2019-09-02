![forthebadge](https://img.shields.io/badge/made%20with-java-red.svg?style=for-the-badge&logo=Java&logoColor=white)
![forthebadge](https://img.shields.io/badge/made%20with-javascript-red.svg?style=for-the-badge&logo=JavaScript&logoColor=white)
![forthebadge](https://img.shields.io/badge/made%20with-HTML5-red.svg?style=for-the-badge&logo=HTML5&logoColor=white)
![forthebadge](https://img.shields.io/badge/made%20with-CSS3-red.svg?style=for-the-badge&logo=CSS3&logoColor=white)
![forthebadge](https://img.shields.io/badge/uses-Java%20RMI-blue.svg?style=for-the-badge)
![forthebadge](https://img.shields.io/badge/uses-AJAX-blue.svg?style=for-the-badge)
![forthebadge](https://img.shields.io/badge/uses-Apache%20Camel-blue.svg?style=for-the-badge)

# *INFO303 Semester Project*
This project was created during a semester for my Enterprise Information Systems Infrastructure paper in 2018. This application was created to mimic the customer to company relationship when purchasing an item online, and is integrated with an external point of sale API (Vend). Accounts are able to be created in front-end written in HTML and CSS with the details posted to a customer web service application written in Java and then also posted to the Vend API. Sales are created within the Vend user interface to mimic a sale being created by a company based on the customers details. An Apache Camel router then picks up the details from a payload and then processes the sale on a sale web service written in Java.
<br>
**_Note: This application is just as a reference as access to my student e-mail and Vend API have been retracted._**
<br>
<br>
The main functionality of this program allows a **customer to create an account** and also provides the ability for an **external API to create a sale based upon the customers details**.
<br>
<br>
**Customer Creates Account** -
A Customer is able to create a customer account via the HTML and CSS front-end. The details are then posted to a customer REST web service and also the equivalent account is created on the Vend API.<br>
<br>
**External API Creates A Sale** -
When a sale is made on the Vend API interface, an E-Mail containing a JSON encoded version of the sale details is sent to my student E-Mail account (via a webhook service). This payload is then picked up by the Camel router and is then used to create a sale on the sales REST web service. The JSON payload is then parsed into Java Objects that are able to be used by the sales service, which is accomplished by using the sales service domain model.<br>

## *What I Learned:*
* Implementing RESTful Web Services
* Implementing A 'Create Customer' Web Interface Using HTML, CSS and AJAX 
* Creating Routes In Camel
* Parsing Data From A Payload (Email) and use it to Process data

_**Please ignore excessive comments within this Project. I use them as a way to learn when implementing a new project, as I find it improves my retention of programming language concepts and language syntax when they are used .**_ 🖖🏻
