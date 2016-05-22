# JsonParsing

### INTRODUCTION
This project includes (mainly) a correct way to parse JSON and loading online images by universal image loader libs.
It is entirely written in Java (for Android) following CLEAN Architecture and SOLID principles.  

### ARCHITECTURE
As described in the introduction, this project is written under CLEAN architecture and SOLID principles. All classes represent an unique entity and function (following single responsability), except pure UI classes (Activities). 

Structure: 

- framework
- logic
- model
- ui

### THINGS I HAD TO DISCART BECAUSE OF TIMING
- Dagger: I am used to work with dependency injection but it's quite expensive (in time terms) to configure, so I decided to discard. 
- A beautiful and friendly interface: I would really love to add a good and beautiful design using material design but in some aspects is toilsome. 
- Unit Tests, finally I couldn't add junit tests, I haven't have enought time to do it and the design pattern chosen didn't helped. My fault. 