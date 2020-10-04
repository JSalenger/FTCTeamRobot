[![license](https://badgen.net/badge/license/Unlicense/blue)]()
[![jsalenger](https://circleci.com/gh/JSalenger/FTCTeamRobot.svg?style=svg)](https://app.circleci.com/pipelines/github/JSalenger/FTC13270-2020)
## Getting Started
https://jbsalenger.gitbook.io/teamrobot/

## Welcome 
This is the home of team 13270's robot code. Last year's repository was, needless to say a dissapointment. The final TeamCode folder had dozens of loose files with names that had nothing to do with what they did! So, this year we're fixing that. Instead, this year, we will have more stringent contribution standards and will build a framework for our code before writing it. 

**Everyone on the team contributing please read, remember these principles**

Here's how: 
- We're writing a high-level API to interact with the FTC SDK;
     Why is this beneficial? 
	We'll be challenged to make reproduceable, expandable code. Using best practices such as polymorphism and inheritance. We're forced to think about our code's purpose and not just write code that *works* but is flexible. 
- We're using git, correctly
     What do you mean? 
     	We're using it *correctly* we'll strictly enforce merges to master and make sure that all code is as close to flawless as we can get before it is merged. This seems like a given, but often we'd sacrifice testing to be a little bit quicker. 
	
## The API 

What is this api? 
The "API" is aptly named `TeamRobot` it didn't need a creative name because its just meant for internal use. It stops us from implementing the same functions over and over again, consequently it allows our OpModes to be only serveral lines long. If we can write a performant and simple API we can easily detect errors in our OpModes because of their size. Stepping out the robots objectives becomes much easier! 

## Deep Learning
Gone fishing. 
