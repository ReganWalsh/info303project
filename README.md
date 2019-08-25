![forthebadge](https://img.shields.io/badge/made%20with-java-red.svg?style=for-the-badge&logo=Java&logoColor=white)
![forthebadge](https://img.shields.io/badge/made%20with-javascript-red.svg?style=for-the-badge&logo=JavaScript&logoColor=white)
![forthebadge](https://img.shields.io/badge/made%20with-HTML5-red.svg?style=for-the-badge&logo=HTML5&logoColor=white)
![forthebadge](https://img.shields.io/badge/made%20with-CSS3-red.svg?style=for-the-badge&logo=CSS3&logoColor=white)
![forthebadge](https://img.shields.io/badge/uses-gradle-blue.svg?style=for-the-badge)
![forthebadge](https://img.shields.io/badge/uses-Java%20RMI-blue.svg?style=for-the-badge)
![forthebadge](https://img.shields.io/badge/uses-Jooby-blue.svg?style=for-the-badge)
![forthebadge](https://img.shields.io/badge/uses-AJAX-blue.svg?style=for-the-badge)
![forthebadge](https://img.shields.io/badge/uses-Apache%20Camel-blue.svg?style=for-the-badge)

# *INFO303 Semester Project*
This project was created during a semester for my Enterprise Information Systems Infrastructure paper (INFO303) it uses various technologies such as Java RMI, Jooby, REST web services, AJAX and Apache Camel. Together they integrate with an external API (Vend) which allows a customer to create an account and a sale for a makeshift company. **_Note: This application is just as a reference as access to student e-mail and Vend API have been retracted._**
<br>
<br>
The main functionality of this program allows a **customer to create an account** and also provides the ability for a **customer to create a sale**.
<br>
<br>
**Customer Creates Account** -
A Customer is able to create a customer account via the AJAX client. The Camel router then POSTs the customer account to a customer account REST web service and also creates the equivalent account on Vend.<br>
The Camel router works by providing a Jetty endpoint to receive the account details from the AJAX client. This also handles Cross Origin Resource Sharing.
<br>
**Customer Creates A Sale** -
When a sale is made on the Vend web interface, an E-Mail containing a JSON encoded version of the sale details is sent to the student E-Mail account (via a webhook service). This payload is then picked up by the Camel router and is then used to create a sale on the sales REST web service. The JSON payload is then parsed into Java Objects that are able to be used by the sales service, this is accomplished by using the sales service domain model. The sales summary is then retrieved from the sales REST web service then used to calculate the customers 'customer group' based on the summarys of all of the customers sales. Then if changed will update on Vend and the customer accounts web service.<br>

**This project is split into 5 gradle subprojects each with its own functionality, they contain:**<br>
* **common** Contains all the domain classes that are needed for all of projects, including all of the Customer and Sale domain classes.<br>
* **customer-service** - Contains a server, DAO, customerResource and customersResource. The customersResource allows HTTP Methods GET that can be performed on all customers which returns all customers in the system and POST which allows the creation of a new customer. The customerResource allow the GET HTTP request to be performed on a single customer given a customerID which returns a single customer given the provided ID. The PUT request given an ID gives the ability to update customer details.<br>
* **sales-service** - Contains a server, DAO, saleResource and salesResource. The salesResource allows GET requests that can be performed on all sales to return all sales and allows a POST request which is used to create a new sale for a provided customerID. The saleResource allow GET and PUT requests to be performed on a single sale given a customer ID, which allows a return of all sales given a customerID and the ability to update sale details given the same ID.<br>
* **customer-ajax** - Contains a server as well as web elements (html, css, js) that provides the ability for a customer to create an account which also POSTs the account details to the customer-service above as well to the camel router.<br>
* **router** - An Apache Camel router project that contains a class that contains a single method that creates a customer and two replicating builders that create the queues that are used to encapsulate different operations. One is used for 'Customer Creates Account' operation and one is used for the 'Customer Makes A Sale' operation, and also contains the physical router class that allows the replicating builders to create the router.

## *What I Learned:*
* Implementing RESTful Web Services With Jooby
* Implementing A 'Create Customer' Web Interface Using AJAX, HTML and CSS 
* Creating Routes In Camel
* Parsing Data From A Payload (Email) and use it to Process The Data

_**Please ignore excessive comments within this Project. I use them as a way to learn when implementing a new project, as I find it improves my retention of programming language concepts and language syntax when they are used .**_ 🖖🏻
