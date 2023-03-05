<a name="readme-top"></a> 

<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

<p align="center">
  <img src="https://user-images.githubusercontent.com/53571060/204812144-d54bc1e8-78ee-4913-8f10-0f29b54c9cbf.png" />
</p>


  <p align="center">
    API Gateway For Shopping the Techy Products !
    <br />
    <a href="https://github.com/nileshs23/Techy-Town"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    .
    <a href="http://techytown2.ap-south-1.elasticbeanstalk.com/">View Demo</a>
    .
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#modules">Modules</a></li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contributors">Contributors</a></li>
    <li><a href="#acknowledgments">Acknowledgments</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project

<p align="center">
<img src="https://user-images.githubusercontent.com/53571060/209521400-692aa24b-e805-4641-bde5-f2d0785f5ddf.png" />
</p>


This application will facilitate the customer as well as Admin to order the products you Love.
Admin have certain functionalities to manage Category and Products.
Customer Can buy and generate payment receipt.

Try `Link in Description` visiting our site.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



### Built With

Tech Stack :

[![My Skills](https://skillicons.dev/icons?i=java,spring,maven,hibernate,github,git,vscode&theme=light)](https://skillicons.dev)
<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple example steps.

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
  
  * Spring Tool Suite 4

 ```sh
   https://spring.io/tools
   ```
  
   * Maven Dependencies

 ```sh
   https://mvnrepository.com/
   ```
   
   * MySQL 8.0.30 or any latest Database Software

 ```sh
   https://dev.mysql.com/downloads/installer/
   ```

### Installation

First clone the project and let STS4 build its dependecies. Maven --> Update Project -->Run as SpringBoot

1. Clone the repo
   ```sh
   git clone https://github.com/nileshs23/Techy-Town
   ```
2. Add MVN Dependencies

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- USAGE EXAMPLES -->
## Usage

Use this to get insights of signup and login functionality.

Admin Controller -> register admin -> Login -> perform CRUD operations -> get insights -> Log Out From the System


<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- USAGE EXAMPLES -->
## Modules
This Application Consist 6 Modules

- Login and Signup For Admin/User Module.
- Category Registration Module
- Product  Registration Module.
- Cart Module.
- Orders Module.
- Payment Module.


## Login & Signup Modules

- Based Upon Spring Security and JWT tokenization.
- It performs Regist,login and logout for User and Admins.
- Payment History.

## Category Registration Module
This modules CRUD operation belongs to ADMIN only !

- This Module Will Take Add Category To Database
- Only Admin Can Add The Categories.
- User Can See the category.

##  Product  Registration Module
- In this module User can Serach Products based upon ID/Name/Category.
- User can select cart and Add Products To it. 

## Cart Module.

- User Can See Cart Details like total Amount / Qty / Products ,etc.
- This Module is Capable checkout all items.

## Orders Module

- This is for Providing Orders and Payment Gateway
- Giving order Receipts.
- See all order details.

## Payment Module

- This Module is Primarly Responsible for Entries of Payment Details.
- Adding Card Details,BHIM etc



<!-- ROADMAP -->
## Roadmap

- [x] Made ER Diagram
- [x] Added POJO classes
- [x] Made mappings
- [x] Designed Database using mySql 8.0
- [x] Added Additional Exceptions and Dependencies.
- [x] Added "Admin Controller" logics with Controlller.
- [x] Added "User Controller" logics with Controlller.
- [x] Added "ID" logics with Controlller.
- [x] Tested Using Postman.
- [x] Tested Using Swagger.
- [x] Uploaded on AWS.
- [x] Added API Documentationusing Thymeleaf.
- [ ] Adding Banking System.
- [ ] Changing Order Status and Payment Status.
- [ ] Adding refined algorithms.

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your own feature Branch (`git checkout -b feature/AmazingFeature`)
3.Please Don't Push or commit on this project.
4.You can always make use of it as reference point of view (If you want to learn).

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- Contributors -->
## contributors
* Nilesh Solanki - nileshs2398@gmail..com

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- ACKNOWLEDGMENTS -->
## Acknowledgments

We are always looking for ways to improve our Application, and I would like to request your feedback on our latest project. Your opinions matter to us and your feedback will be used for further improvements. So please share youre valuable feedback.

Thank You ,
* Masai School
* ALL INSTRUCTORS.

<p align="right">(<a href="#readme-top">back to top</a>)</p>
