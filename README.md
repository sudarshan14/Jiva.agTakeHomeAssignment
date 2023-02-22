<h1 align="center">Welcome to Mandi </h1>

Mandi app is a demo app which allows apple sellers to sell their product on choice of their price.  


## Application flow and how to test 

 The app requires the following data to test 
- Seller Name
- Seller Loyalty Card Identification
- Village name
- Selling price per unit based on village


 ### Test data
- Seller Names
  SellerName | Loyalty Card Id 
  | :--- | ---: 
  Ramesh Singh  | RS123
  Suresh Chand  | SC123
  Prakash Kohli  | PK123
  Vikas Sharma  | VS123  
  Aditya Singh  | 
  Harminder Singh  | 

- Selling Price for village
  Village Name | Selling Price 
  | :--- | ---:
   Ramnagar  | 120.01
   Bijnor  | 110.11
   Shimla  | 115
   Kashmir  | 180.50

#### How to test
- Type seller name in the given input field. Seller name list will populate. Select seller's name. Based on the seller, the loyalty card id will be selected and the Loyalty card index will be applied.
- Type Loyalty Card Id in the given input field, Id list will populate. Select LC Id, sellers name will be auto filled , the LC index will be applied.
- Select village from drop down Menu, based on village name, the selling price per unit will be applied.
- Enter the amount in KG which you want to sell. 
- Press "Sell My Product" button. It will navigate to thank you page with information. 

**Please note**: Any validation of Sell My Product button is not applied.

## Application is  Built using the following : 

- [Kotlin](https://kotlinlang.org/) 
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)

    - [Stateflow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) 
    - [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) 
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) 
    - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started) 
    - [RoomDatabase](https://developer.android.com/training/data-storage/room)
    - [DaggerHilt](https://developer.android.com/training/dependency-injection/hilt-android)


##  Package Structure

Please refer 
![image](https://github.com/sudarshan14/Jiva.agTakeHomeAssignment/blob/main/app_support_images/package_structure.png)
 
## Screenshots
 - app_support_images/1.png
 - app_support_images/2.png
 - app_support_images/3.png

##  The project is build using 
- Android studio Dolphin
- minSDK -> 26
- targetSDK -> 32
- kotlin version -> 1.7.20


