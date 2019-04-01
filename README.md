# DietPlanners

DietPlanners is a web based tool that helps users track and plan their weight loss journey. Users can create accounts and personalize their information by entering their current weight, height, age and then set a target goal for themselves. DietPlanners will help them keep track of their progress with a calorie counter based on the foods they eat throughout the course of the day and the amount of exercise they do. Users must input their information in the form of entries either for food or exercise for each day. Food categories are broken down into Breakfast, Lunch, Dinner and Snack. Exercise is broken down into cardio and strength training. These entries are saved in a logbook where they can look back on various entries they have created over time. The logbook is essential because users can track their progression by looking through previous days within the logbook and see if they are staying on track. In addition, users can track their progress by going to the progress page and entering their current weight and date and the page will keep track of these values for users to look back on in the future. 


## Strategy and Tools

### Source Control and Review

Given the nature of large scale, multi-person projects, to best manage the various contributions of each member we will be using a combination of Git and GitHub, as well as their VCS integration into the common place IDEs used such as Eclipse and IntelliJ. This allows individuals to work on the project concurrently, each completing their own tasks and then pushing the changes to the master branch when complete. 

The tool used for code review for this project is called Codacy and is offered on the GitHub Marketplace. This web-app’s dashboard offer a thorough breakdown of the project’s existing issues such as code complexity, duplication, performance, code style, and much more. The dashboard also gives us important statistics based on the code’s quality over time. Furthermore, each commit is analyzed and can notify the reviewer if there are any problems with the code. Another feature that Codacy offers is anti-pattern detection. The admin can select known anti-patterns from a list for the automated code reviewer to detect.

* [CODACY](https://www.codacy.com)- Code Review Tool

* [INTELLIJ](https://www.jetbrains.com/idea/)- IDE 

* [GITHUB](https://github.com)- Version Control Tool



### Tools

 Travis CI is a hosted, distributed continuous integration service used to build and test software projects. Travis CI can sync easily with GitHub projects and allows you to test your code quickly, as well as at no charge for open source projects. 
We chose to use Travus CI as the major build tool for our application, besides its various benefits, it is also the tool that our team was most familiar with. 

Furthermore, since we are a relatviely large group, finding a way to keep in contact and make sure every team member is up to date with all changes, we decided to use google drive and slack. Google drive is a useful tool which we will use to share and gather all important files, and for all of the documentation aspects of the project, Google Docs will be used and the files saved on our drive. 

The Slack application will help us communicate efficiently within various channels that will represent the different denominations of the team. Each subteam will have their own Slack channel in order for the team to stay organized and on track with the various tasks. 



* [TRAVIS CI](https://travis-ci.org)- Build Tool
* [GOOGLE DOCS](https://www.google.com/docs/about/)- Documentation Tool
* [SLACK](https://slack.com/lp/three?cvosrc=ppc.google.d_ppc_google_ca_en_brand-hv&cvo_creative=257480048359&utm_medium=ppc&utm_source=google&utm_campaign=d_ppc_google_ca_en_brand-hv&utm_term=slack&ds_rl=1249094&cvosrc=ppc.google.slack&cvo_campaign=&cvo_crid=257480048359&Matchtype=e&utm_source=google&utm_medium=ppc&c3api=5523,257480048359,slack&gclid=EAIaIQobChMI2szs_LuO4QIVCWSGCh0w0gfwEAAYASAAEgIu0fD_BwE&gclsrc=aw.ds)- Communication Tool


## Contributing

Ilana Haddad

Jordan Itzkovitz

Arielle Lasry

Eden Ovadia

Rebecca Weill

Benjamin Szwimer

Ethan Itovitch

Kamy Moussavi

Lucas Bellido

Matthew Zoltak

Noam Suissa

Oliver Clark

Paul Attara

Spencer Handfield



## Final Report

### 1. Scrum Rituals

### 1.1 Backlog Grooming
After every sprint, we would make sure to eliminate or de-prioritize some of the stories that were less important and prioritize the ones that had to do with the core functionality of the application. The stories that were selected for implementation in the next sprint were broken down into standard scenarios, alternate scenarios and error flows. These detailed story breakdowns are found in the initial backlog that was submitted with Sprint 1. It was essential to filter and groom the backlog in order for the team to work effectively and efficiently on the functional elements of the application and the specific stories that were essential to the functionality of the application were added to the task tracking for the Sprints thereafter. 

### 1.2 Sprint Planning
At the start of every sprint we would make sure to hold a team-wide meeting. The goal of this meeting was to go over the stories that would be implemented during the sprint, make any revisions on the objective of the particular sprint, and create the task list with assigned tasks to every member on the team along with their estimated times. In addition, the team would discuss how we wanted to move forward with different implementations of various elements within the application (front end tools, database management, frameworks application would run on, etc) and make sure that everyone was on board with the different tools we would be using throughout the process. The intention of these meetings was to eliminate any uncertainties moving forward in the development of the application and people would have a space to express concerns, questions or confusions during these meetings which made the sprints run more smoothly. The meetings were governed by the scrum masters for each sprint.

### 1.3 Story Estimation
After the first meeting of every sprint, the scrum master would be responsible for the breakdown or transformation of the stories from the backlog into executable tasks. Once the tasks were created, the scrum master would estimate how long each task would take to complete. The scrum masters made an effort to distribute the same amount of time to every member of the team but evidently this was difficult since some tasks would inherently take longer. Additionally, when assigning tasks to members, the scrum masters ensured that every member would have a total effort of 3 hours per week. The tasks were then distributed to team members based on their strengths and capabilities to ensure an efficient completion of the tasks. The estimations of the tasks were primarily based off of previous experiences doing similar tasks in other projects and basing our estimations on how long those respective tasks took in other projects. 

### 1.4 Daily Stand Up Meetings
Our team decided that spending the time to find a time that worked for every single individual schedule (15 different ones) everyday would be a waste of time and resources. Instead, we took a different approach by having the scrum master check the task tracking list everyday and contacting team members every 2-3 days to check in with the progress of their task. In addition, the task tracking list was extremely useful for tracking the progression of the team as a whole. Team members were quite efficient in keeping the task tracking list up to date which simplified the whole process of keeping track since the scrum master could just refer to the list and see the progress of different tasks within the list. In the check in’s, the scrum master would focus on asking whether the individual has started their task, how much time they spent so far if they had started and when they would likely be finished. At the end of each sprint the hours would be tallied up and all tasks would be complete on time. Furthermore, to maintain constant communication, we had a team slack with various different channels for modeling, frontend, backend, testing, and general. That way, all conversations were managed separately and only the relevant members were in each channel, in addition to having the scrum masters in each channel to look over all communication.

### 1.5 Sprint Demo
The Sprint Demo was an important aspect of this project, it allowed the team to work collaboratively to ensure the product was ready for demoing. This included running full tests after every sprint, as well as integration tests to ensure that each component could work together properly. In order to prepare for our application to run smoothly during the demo, we made sure all of our backend unit tests were passing for all high priority user stories. Additionally, we conducted manual frontend tests by testing if each frontend component worked properly in integration with the backend. We made a checklist of all of our functionalities and went through them one by one, fixing each error we found along the way. We then finalized our manual testing by entering numerous invalid inputs and making sure all of our input validation checks were in order.

### 1.6 Sprint Retrospective
The Sprint Retrospective is an opportunity for the Scrum Team to inspect itself and create a plan for improvements to be applied to the next Sprint. At the end of the first sprint, our team would meet for a sprint retrospective. This concept of retrospective proved very helpful from the beginning as it helped sort out any misunderstandings between team members with regards to merging code and division of work. In the sprint retrospective, certain things we discussed were:

- What went well in the Sprint
- What could be improved
- What will we commit to improve in the next Sprint

In a software project, it is often hard to divide the tasks without any overlap, and even more difficult considering we were a group of 15 people. Due to this, a lot of members ended up working with different versions of others code. This resulted in a lot of overwriting and difficulty in understanding another person’s work. Additionally, during our retrospective of Sprint 1, we realised that not enough tasks were assigned and if we didn’t increase the workload of each student, we wouldn’t be able to finish in time for the end of Sprint 2. Therefore, the retrospective helped clear these doubts, set standards for code for the following sprint, and we were able to adjust our tasks to meet our goals.

### 1.7 Collaboration Between Team Members
Team Collaboration played an important role in the completion of our project. We would have not been able to collaborate properly without proper communication. Throughout the project we communicated over Facebook Messenger and Slack. These group chats allowed us to follow up on available dates and design team meetings that everyone could attend. In these group meetings we discussed our strengths and weaknesses which allowed us to properly designate roles. Lastly, since this was a difficult project, several students needed help with their tasks at some point so we used these conversation platforms to ask questions. Due to the large size in group, the conversations were very responsive and many people offered their help and expertise when a question was posed on a particular issue. 

### 2. Scrum Objects 

### 2.1 Done Checklist 
Three days before the end of each sprint, we would have group meetings led by the scrum masters and we would go through our done checklist. During our first sprint our done checklist only was designed to check if our user stories were working. However this evolved in the second sprint as we realized that it was very important to have members testing each story, this was done in order to prevent bugs from cascading into other members tasks and therefore preventing us from moving forward. 

### 2.2 Product Backlog 
After completing brainstorming for our application and coming up with our final product of developing a diet planning website, we deduced a list of stories in which we would be implementing throughout the duration of the project. Each sprint was composed of implementing a variety of stories. Our plan of action for the first sprint regarding the product backlog was focused primarily on having the project being properly set up such as setting up our RESTful API and starting to write some of our backend repository and controller methods as well as doing the domain modelling for our application. As the second sprint came underway, we started implementing the frontend components of our existing backend methods from sprint 1, and as the remaining high priority user stories for the backend were being implemented, we implemented their frontend component accordingly. In hindsight, one thing that we should’ve given ourselves more time to work on, is the integration of all features and components at the end of the second sprint. A lot of bugs and errors came about during the integration that required a lot of time to fix. 
	
### 2.3 Sprint Backlog 
The sprint backlog was an extremely helpful tool throughout the duration of our project. We designed our sprint backlog by using Githubs’ issue tracking and it stayed relatively similar throughout both sprints. Our goal was to strive for simplicity in the sprint backlog keeping the issues very concise and to the point for both sprints as to not confuse any of the individuals on the team. We had this vision because we believed that it would be very difficult to coordinate developing an application with such a large group of developers so we kept our backlog very straight forward so that it provided a structured common place that the whole team could turn to in order to ensure that all the initially set requirements we had set out to implemented have been rightfully so. 

### 2.4 Sprint Backlog Task
During the first sprint, a complete list of tasks was created, and roles were given out to members based on preference. All the tasks were completed on time, however, strengths and weaknesses of each member were noticed. For the second sprint, tasks were given out based on a preference of the members and by the additional factor of the strengths noted from the first sprint. There was a google spreadsheet that had a list of all the tasks, with the members assigned to them and the date for which the tasks were needed to be completed by. Each task had a status as well -- to do, in progress, and done -- and each row was highlighted a different color depending on its status. This made it easy to track the progress of the sprint, allowing the scrum master to keep track of the team’s progress more effectively.
	
### 2.5 Story Point Burndown Chart
Our group did not use a burndown chart. The scrum masters felt that the task lists were thorough enough to ensure that all the members would have a clear understanding of their tasks and responsibilities. In the task list there was a clear indication of when members should start their tasks and when they should be finished by. The time frames were reasonable enough to give the members enough time, and the scrum masters created a culture that made members want to contribute and finish their tasks. Therefore, although we did not use a point burndown chart, our team was still responsible enough to complete the tasks at hand. 

### 3. Agile Manifesto

### 3.1 Individuals and interactions over processes and tools
Due to the fact that this was the first time all of us had ever worked in a team this large, it was important for the team to be able to communicate well. Instead of relying on automated tools and rigid systematic processes to ensure the completeness of the code, our group relied heavily on our communication skills to ensure that tasks were being completed and properly implemented. Due to the group’s lack of experience with automated process and tools, there would have been a huge learning curve and added many unnecessary hours of work for all the team members. We felt that through effective communication and the organization of our scrum masters we would be able to complete the application more efficiently.

### 3.2 Working software over comprehensive documentation
The main objective of this project was to ensure that the stories we created and their respective flows were pertinent to what we were trying to accomplish. This was always our main priority during this project as properly formed stories and goals lead to a better outcome for the end result. Once the stories were properly formatted and ready to be distributed as tasks, we had all hands on deck for the code and software. The fluidity of the Agile process enabled us to focus more on the code we were producing allowing us to create a successful end result rather than focusing on the documentation. Although, there was documentation at the beginning of the project, we quickly transitioned our focus on implementation. 

### 3.3 Customer collaboration over contract negotiation
In this situation, the team acted as the customer since we were ultimately setting the requirements of the project. Moreover, we set our stories and backlog early on in the project to have a framework to follow so this could have been seen as the set requirements to follow. These were the goals and objectives we set at the beginning of the project and were responsible to attain by the end. In addition, there was a lot of collaboration with the Teaching Assistant to ensure that our stories and backlogs were satisfactory and we were given constructive feedback and advice on how to improve our implementation or process throughout the course of the project. The TA gave us a lot of feedback on how to advance in the project and what changes should be made. 

### 3.4 Responding to change over following a plan	
It was very clear early on that while following a very rigid plan may seem effective in principle, in reality it could not be further from the truth. The adaptability of our work method was crucial in allowing for a quality product to be delivered. The initial planning process gave an overall idea of how a sprint would go but enough wiggle room was always left between tasks and times so that we may adapt on the fly. At the onset of our project we had thought to use the online API of the national food resources, but this proved to not be an effective way of implementing our desired solution. Based on the way we had planned our sprints and tasks, this meant that any work up until that point was not lost since it was laid out in an order that ensured basic functionalities we’re all fully functioning before tackling those final level issues. On top of this, since our criteria would shift, often the constraints of the team would shift. Between tasks, sprints and other things, we often were able to shuffle members around to best accomplish tasks and optimize the time it would take to push out a deliverable. Once again this heightened the speed by putting the right people in the right places based on the shifting requirements and also allowed for higher quality products to be delivered to the client since we were playing to the team’s strength as we responded to change. 

### 4. Agile Principles

### 4.1 Our highest priority is to satisfy the customer through early and continuous delivery of valuable software
Naturally, we always built and implemented systems in a client driven manner. This meant that most key features they required were identified and prioritized. However, this does not mean that many other important subsidiary features were not also envisioned within the planning process. Naturally we always tried to optimize our system and thoroughly test them to ensure a quality product for the client. While this is always the goal and was often achieved, it is not the only form of software that may be deemed valuable. We would also push to get the most components completed, albeit maybe in a more rudimentary form, but their value then existed in the form of customer feedback and viewing their integration into the system. With this approach we were always able to maximize the value of any one component from a developer’s perspective as well as client feedback driven perspective. Often there was a balance to strike between taking more time to perfectly implement and test a feature versus getting it simply to a workable state. By skating this balance and usually settling any doubts via maintaining a focus on the client we were able to deliver software rapidly and continuously, often going back on things that still needed reworking to push them out again as early as possible so as to move on to the next portions of the project, allowing them to be delivered in a quality manner to the client. It started slower since the backend was an area of difficulty and required to be thoroughly tested and developed so as to not affect the things built on top of it, but towards the end, using the methods outlined above, we were able to non stop push out valuable software as well as getting to some of the smaller but still important elements of the project so as to improve the quality of the application overall

### 4.2 Welcome changing requirement, even late in development. Agile processes harness change for the customer’s competitive advantage
From the onset of the project, our tasks were designed to fit together as well as be accomplished in a very flexible way. While we always aimed to solve the user stories, there was always enough room left within how a feature would work and how it fit into the rest of the system to easily be modified at a moment’s notice. It was not rare to see a change be required and be immediately undertaken, towards bettering the system and satisfying the client without breaking the application. More often than not this required good team communication, to ensure any critical paths were designed properly so as to allow for that flexibility when changing or modifying something at a moment’s notice. Not only for the team members but for the features as well so they may be the best possible product for the client based on the shifting requirements. At first it was difficult since some of our tasks were overly ambitious and did not well capture our timeline and various skill sets. However, as the timeline progressed, we adjusted the tasks so as to embrace change as an opportunity to improve by well integrating that into our agile work system, rather than a hindrance.

### 4.3 Deliver working software frequently, from a couple of weeks to a couple of months, with a preference on shorter timescales.
In terms of long scale, we naturally had the final deadline which always kept a long term consistent goal in mind to work towards. The individual sprints allowed for more specification in terms of longer term planning, allowing for reasonable chunks to be dealt with at a time out of the whole, on a timeline that was pushed to be as rapid as possible. On top of that, to heighten the responsibility to develop software at a more frequent rate, the weekly submission meant that every week, more tasks were undertaken. The tasks were also developed to minimize delay between parts which relied on each other so that as soon as a component was ready, the one built on top of it was immediately scheduled to commence development (albeit with the flexibility for customer feedback and shifting requirements). This meant that on a day to day level, we had tasks laid out, which were then developed towards a weekly goal, which fit into the goal of the sprint, which were built from the final submission goal in mind.

### 4.4 Business people and developers must work together daily throughout the project 
The team did not strictly have any business people to directly work in the project, however, we often leaned on the help of the TA and the Professor who would quasi fill those roles and provide the necessary insight for us to continue our development. While the scrum masters were also involved at the technical levels, they could often take on the role of a business person and were often consulting with members of the team on a daily basis from that perspective to make sure the work was on track but also that the work we were doing was the correct work from a more business minded view. There was always a proximity maintained between the developers working daily on the project and the people who would be able to take on the role of a business mind in the context of the project, since the synergy between those facets is important in ensuring a quality product for all those involved. 

### 4.5 Build projects around motivated individuals. Give them the environment and support they need, and trust them to get the job done. 
As a team, we always tried distributing tasks fairly, evenly, and based on skill-set/interest. This would allow for a productive and efficient environment where members would be working on tasks that truly interested them, helped them develop as software engineers and exposed them to a myriad of new challenges. In addition, the team was always extremely supportive. We were a very collaborative team, where if anyone ever had trouble with their task they would simply ask for help from the group and many individuals would step up to assist the student in their task. Finally trust and respect were two essentials in the execution of this project. All members of the group trusted one another to finish their tasks on time in addition to respecting each others ideas during the meetings. This was very important because without harmony in trust and respect we would have been facing constant problems and been unable to complete the project. 

### 4.6 The most efficient and effective method of conveying information to and within a development team is face-to-face conversation.
Although it was very difficult for the entire team to meet as a whole every start of each sprint it acknowledged a lot of people's concerns and questions since it was easier to communicate being all together without any distractions. Meeting face-to-face allowed members of the team to communicate their ideas efficiently since miscommunication often occurs across different mediums (Facebook Messenger, Slack…) rather than face-to-face interaction. A lot of the time, we were more productive when meeting face-to-face and able to get more accomplished since it was easier to develop and cultivate ideas in a fluid environment without any interruptions and where conversation was consistent. 

### 4.7 Working software is the primary measure of progress. How did this apply? How did this evolve?
One of the most important things in development is testing. We made sure to include testing for each component of the application that was being developed to ensure proper functioning of the code. This was crucial since we needed to know if portions of code were working properly in order to move forward with the tasks. As time progressed, we got more accustomed to the various testing environments and methods. Additionally, when it came to integration testing at the end of the second sprint, we relied on backend unit tests alot to figure out if a certain bug was coming from frontend or backend. If the tests in the backend passed automatically, we knew the problem stemmed from an error in the frontend implementation of that feature, and we were able to right away resolve the issue, instead of spending hours figuring out where it came from. Overall, the working software stems from breaking down the stories into simple executable tasks, solidifying and making sure the high priority stories were functioning perfectly above all else and finally testing each implemented element right as it was developed to reduce errors. 

### 4.8 Agile processes promote sustainable development. The sponsors, developers, and users should be able to maintain a constant pace indefinitely
The agile process and planning methods were essential to the success of our project. We were required to split the work over a distinct time period which made us plan our Sprints precisely and accurately in order to end with a finished application. The sponsor was in constant communication with all team developers and had the role of liaison between the client and the developing team. We had some problems keeping with pace in the first sprint since it included some technologies that not everyone was familiar with and it took longer to get accustomed to these new tools for some. As time progressed and we garnered some more experience, we were able to better estimate the amount of time needed for every task. Of course, with time we became more comfortable with the tools and technologies but we still wanted to give enough time to accomplish the tasks in order to stay on track with a consistent pace. 

### 4.9 Simplicity - the art of maximizing the amount of work not done - is Essential 
The deciding factor in achieving working software quickly while minimizing the amount of work that needs to be done can be handed to simplicity. Our entire project was based on this idea, we believed it was very important to create simple tasks that were achievable in the given time frame they were assigned (a couple of days). This allowed us to properly integrate all parts of the software into one cohesively working unit. When we were first brainstorming our application, we shortly discussed the idea of developing an android or IOS application but for simplicity we decided that it would be fundamentally easier to develop a website for our diet application and this is the approach that we took.

### 4.10 The best architectures, requirements, and designs emerge from self-organizing teams
The team learned very quickly that flexibility as a group would be an important characteristic if we wanted to build a successful product. Since many parts relied on other parts to be working, it was crucial to communicate well with the other members of the team. For example, if a member was finished with their controller method, but later on discovered by another team member that adjustments needed to be made, it was important for that member to make the necessary changes. Everyone was held accountable for finishing their part, and we moved together as one. Therefore, it was not only the scrum masters that made sure that everyone was doing their part but the group as a whole. 

### 4.11 At regular intervals, the team reflects on how to become more effective, then tunes and adjusts its behaviour accordingly
Following the end-of-sprint meetings and meetings with the TA or Professor, suggestions would be made on how to become more effective in meeting both our short and long term goals. Being able to reflect on the work that was done prior to such meetings according to what was actually planned allowed us to see how effective our current strategies were while also exposing their ineffectiveness. This allowed us to adjust on the fly and fine tune how we approached solving problems throughout the project. One example arises from our meeting at the end of Sprint 1. Initially tasks were delegated more at random, where a team members skill set was taken into account however who they were working with was not according to any scheduling. While tasks were being completed, sub-group team members could not always meet to do their parts together and thus had to work remotely. Team members expressed that this was not optimal and tasks were taking longer than they should have. To fix this, a discussion on who’s schedules worked best together cross-referenced with a members skill set determined who worked on what task together. This allowed for sub-group members to work together and ultimately more effectively on their given tasks.

### 5. References
[1]"The Four Values of the Agile Manifesto", Neotys.com, 2019. [Online]. Available: https://www.neotys.com/blog/the-four-values-of-the-agile-manifesto/. [Accessed: 31- Mar- 2019].

[2]P. Landau, "Top 12 Agile Principles", ProjectManager.com, 2019. [Online]. Available: https://www.projectmanager.com/blog/agile-principles. [Accessed: 31- Mar- 2019].

[3]J. Ondiek, "The 12 Agile Manifesto Principles Simply Explained", Linkedin.com, 2019. [Online]. Available: https://www.linkedin.com/pulse/12-agile-manifesto-principles-simply-explained-jacob-aliet-ondiek/. [Accessed: 31- Mar- 2019].

[4]"A Project Sponsor Isn’t A Project Manager, Scrum Master or Product Owner!", Software Process and Measurement, 2019. [Online]. Available: https://tcagley.wordpress.com/2014/09/03/a-project-sponsor-isnt-a-project-manager-scrum-master-or-product-owner/. [Accessed: 01- Apr- 2019].
