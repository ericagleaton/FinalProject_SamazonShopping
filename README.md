# FinalProject_SamazonShopping
Final Project - Samazon Shopping Site

Magneto Group Members:
* Erica Jenise Gleaton
* Margo Kelly
* Surendra K Bijukchhe

Project Due Date:         Monday 10/28/2019 10:30 AM
Presentation Datetime:    Monday 10/28/2019 1:00 PM
Last Change Request:      Wednesday 10/23/2019 03:00 PM
Project Accepted Date:    Monday 10/21/2019 10:00 AM

Stakeholders:
  Project Sponsor:          Jennifer Lee
  Project Manager:          Erica Jenise Gleaton
  Last Change Requested By: Jennifer Lee
  Project Enduser:          Sam Montgomery
  CIO:                      Surendra K Bijukchhe
  Dir of Database Admin:    Margo Kelly

  Magneto Project Team:
    Backend Developer:        Margo Kelly
    Frontend Developer:       Surendra K Bijukchhe
    User Interface:           Erica Jenise Gleaton


Project Summary: 
Sam has been saving all his money and now wants to create an online store. Team Magneto has been assigned to access Sam with this project. 


Magneto Vitamin Store Requirements: 

  Products:
    1. The store must have at least twenty products
    2. Products Field Requirements:
         Name
         Description
         Price 
            Note: The team can add any other information they want 
    3. The products must be store in a database

  Website:
    1. There must be a web application to serve as a shopping cart for the online store 
    2. The user should be able to view a list of products with pictures 
    3. The user should be able to select a product to add to the shopping cart
    4. The images may use cloudinary or urls
    5. Have the ability to a new product(s) and removing a product(s) from the database / website 
          Note: This change should not break your application. 
    6. The site must use Bootstrap and include a navbar which shows on all pages when the user is logged in.

  Website Pages:
    1. Product List: quick view of all products
    2. Product Details: shows everything about the product
    3. Shopping Cart: shows all the items in the shopping cart
    4. Order Confirmation: shows the total amount of the items ordered
          Trigger: Users should receive an email confirmation of their order which details items purchased and total cost
    5. User Details: shows the user information and their order history

  Website / User Functionality:
          1. user should be able to browse the list
          2. see product details and add the product to their cart. 
          3. the user can go back to the list and shop more or can go to the cart to checkout.
          4. User's name and number of items in their cart should appear on every page. 
                Note: Optionally you can list the total value of their cart.
          5. User should be able to search for products containing any or all the words entered.
                  Note: You can decide how you want to present the results. 
          6. Sites should charge shipping on every order but offer free shipping for orders > $50.00.
          7. Search form in the navbar which allows the user to search for items in the store or their order history

  
  Security(Login Role levels and access):
     1. Users
          1. can read only products
          2. can put products in cart
          3. users can place orders
          4. should be able to browse the list
          5. see product details add the product to their cart
          6. the user can go back to the list 
          7. shop more or can go to the cart to checkout.
          8. can put products in wish list (Change Request 10/23/2019 3:00 PM)
           
    2. Administrators
          1. can add and edit products and change prices.
          2. Only the Admin can add products. 
          3. Admin can change the price or put something on sale.

------------------

  Change Request (Wednesday 10/23/2019 3:00 PM, Submitted By Jennifer Lee):
      C1. Allow users to have a wishlist, from which they can add items to the cart
