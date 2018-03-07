# acksio YCP CS320 Joel Horne, Andrew Georgiou, Alaska Kiley, Christian Waldemar
Team Project
CS320-102: Software Engineering, Spring Semester 2018

Automated Courier Dispatch Service

Team Members:
Joel Horne
Andrew Georgiou
Alaska Kiley 
Christian Waldemar

Summary: 
Acksio is an enterprise solution web application designed to automate the process of logistics dispatching. Acksio is designed to provide an effortless portal for logistics dispatchers to schedule jobs from clients without having to spend valuable time manually searching for and contacting available contractors. Acksio can intelligently detect the closest available driver to the pick up location, and automatically alert them that a job is available, giving them a set amount of time to accept the job before Acksio moves the job to the next closest available driver. Acksio can also monitor en-route jobs in real time to alert dispatchers if drivers are behind schedule. This solution saves time and money, and provides a superior experience for clients, dispatchers, and drivers.   
Features: 
To begin, couriers and dispatchers will be able to register with the service and create an account. Passwords will be encrypted. All accounts have a type, specifying it as either a courier account or an dispatcher account. Each courier account will records the individual’s personal information, including billing information, email, and phone number, in addition to employment information, such as their dispatcher, the list of jobs they have completed, their modes of transportation, and their dependability as a courier. When a courier registers for an account, the dispatcher will need to provide their user ID so that the courier can link their account to their dispatcher’s. All this information will be recorded either when the user registers as a courier, or is later specified by the user. 
When a user logs in, they will be redirected to a type of landing page depending on their account type with different landing pages for couriers and dispatchers.  
The dispatcher page will provide access to a list of couriers, the lists of jobs completed by those couriers, and the option to create a new job. When a dispatcher selects a courier to observe, they will be shown all the information they need to properly pay that courier for their work, which includes the previously mentioned name, address, billing information, et cetera. Each job will have a boolean value associated with it that tells the dispatcher if the courier has been paid for the job yet and the amount the courier needs to be paid for each job. The site will calculate and display the amount the courier is due for their next payday. When the courier is paid, the dispatcher will be able to mark that they have been paid and on what date, which will then change the boolean values in the database so that it properly represents whether or not the courier has been paid. The dispatcher would also be able to select each job that the courier has done and be able to see all information associated with the selected job. The most complex interaction between the dispatcher and the website will be in job creation. The dispatcher will first select the type of job, then be redirected to a new page. At this point, the dispatcher enters a start address and an end address, in addition to any other vital information for the job. The server can then be called to access Google Maps to calculate the distance between these two addresses. This value is then used to calculate a price estimation for the job using the price rate for the type of courier job selected. This information is then displayed to the dispatcher. The dispatcher can then confirm the creation of the job and have that information saved to the database. Part of this information will be a unique ID for the job that is a string of the time that the job creation arrived at the server. The server then finds the best choice of courier to complete the delivery (usually the closest) and allows for the courier page to display that a new job is available. When a courier finally accepts the job, the courier’s ID is saved to the database in the job’s entry so that the system, linking the job to the courier’s account. As the delivery is completed, the courier will report the pick up time, the arrival at the drop off point, the actual drop off time, and other needed information. These pieces of information will be saved to the database. After the job is completed, the final calculation of the actual payout for the job will be made and saved to the database for the dispatcher. The payment will be calculated based on the times recorded while the delivery takes place in addition to the vehicle type and distance.
The other type of user is the courier. The courier’s view varies greatly from the dispatcher’s main view, but the dispatcher will be able to see most of what each courier can see. This is because the list of jobs associated with each courier will be displayed to both the dispatcher and the courier in the same way, with the same interaction with the database. The interactions are where the real differences start. The courier, as previously mentioned, will have access to their list of jobs and their list of vehicles. To reflect the changes they might experience in actual use, couriers will be able to add and remove vehicles so they are not contacted to use a vehicle type they no longer own. The courier will have to enter their current location into the website so that the system can find the best fit of courier, but later on in development the website should be able to check the for the courier’s location automatically. The website will know when a courier is online or offline, and where the courier is. This location will be seen by the dispatcher when they observe a courier’s location.
When a new job is created and a courier is seen as the best fit, the server will asynchronously update the courier’s page to show that the new job is available with the estimated payout for that job.  At this point, the courier will be able to accept or decline the job. The courier’s ID will be added to the database in the entry for the new job and the job will be given an acceptance time. Then, the courier will update their progress along the progress of the job, reporting when they pick of the package, arrive at the delivery point, and drop off the package successfully. After the job is completed, the system will automatically update the dispatcher’s and courier’s page to update the readout for the amount due at the courier’s next payout.
Later in development, if there is time, a companion android app will be made for courier accounts, so that users can use their smartphones to access the dispatch service. This app will also run in the background of the phone to continually update the tracking on the courier, so that the dispatcher can see and report the progress of the courier’s deliveries. Smartphones are easier to transport, and will allow couriers to report their location and availability from anywhere. Each feature of the courier site will be added over time, as to be sure the android app is properly developed in parts rather than all at once. All features of the website and courier will be identical, save one. The app will be able to let the courier gather digital signatures from customers, avoiding paperwork and automating the process of job completion. These signatures will be stored as images and will be accessible with the display of each job on the user’s version of the website. Later in development, a courier will be allowed to register for multiple dispatchers. Previously, a rating for each courier was mentioned, this will be added later in development, when dispatchers will be able to mark how well the courier did on each job, which will then be averaged into the courier’s rating, which will be saved to the database to conserve processing power and updated at the completion of each job.
If there is still time towards the end of development, the app will be updated to allow dispatchers to also log into the mobile app and complete similar functionality to their website. At this point, the dispatcher will be able to work from their smartphone exclusively. 
If there is still time left in development, the android app will be ported to Apple. If there is still time left before the end of the course, then we will most likely implement the ability for couriers to register an african or european swallow as one of their vehicles, for the purpose of transporting coconuts. 
Sketches:

Dispatch page

Courier mobile app

Courier incoming job alert



Dispatch page

Responsibilities:
Joel - Website construction (frontend,), database construction, controller construction
Christian - Website design, concept design, model design, Android development
Andrew - Server, database construction, Android development
Alaska - Server, database construction
Challenges:
As this project is, from a technical standpoint, relativity simple; the process of website and database construction should not be a great challenge. This is especially clear given the fact that the majority of the knowledge on construction of those features will primarily be gained from coursework. That being said, integrating Google Maps will probably spawn the greatest challenge, as we will be integrating an external app with our existing app. Android development, which is a feature to be implemented as a stretch goal, will also prove to be difficult as it can be fairly different to web development. However, this will not be a primary focus.
Android development
GPS integration w/ Google maps
Integration of website with Java servlet and JSPs
Asynchronous updating of courier display
Development Environment:
Given the web based nature of our project, we do not require much in terms of hardware. If we reach our stretch goal of developing an android application, then we would need android devices with GPS capabilities in order to test the app, however all of us have our own personal devices that we could use for testing. In terms of software, all we would need are the basics; Eclipse, a server, and a web domain. The languages that we plan on using include Java, JSP, HTML, CSS, and JavaScript. We will be using git for version control and SQL for our database(s). 
Java
Eclipse
JSP
HTML/CSS/JS
SQL
git
Android studio
Android devices w/ GPS capabilities (personal devices or Android emulators) 
Server/domain/web browser
