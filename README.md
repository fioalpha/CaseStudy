# Case Study

The project only test my knowledge how **Android Developer**

This project contains the screen form  to make calculate the investment, 
after on clicked the button calculate make validated fields by type.
After form validated go to screen the result calculate, this screen show the values the your investment

## Architecture

The architecture I choice was:
 - View model with state machine 
 - Clean architecture
 The project separate on the many modules: 
  - App
  - Component
  - Data
  - Domain
  - Extension
  
### App module
This module is **feature**, contains two feature *calculate* and *result*
 - *Calculate* contain a form to make calculate with data the fields de forms
 - *Result* make request to server 
 
### Component
 This module is components the view
 
### Data
 This module is responsible by data the application, how the project only make request the serve 
 then contains retrofit library
 *This module is 100% Kotlin not contains dependencies with Android Frameworks*
 
### Domain
 This module is responsible rules business
 *This module is 100% Kotlin not contains dependencies with Android Frameworks*
 
### Extensions
 This modules contains functions and constant common by the project
 
 ![Modules](/images/module.png)

## Quality 
 - Unit test 
 - Interface test
 - ktlint 
 - detekt
 
## TODO
 - Handler error
 - Improvement layout
 
 
