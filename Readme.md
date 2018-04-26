Clev-Todo Application requires below mandatory specification
A.Java - 1.8.x
B.Maven - 3.x.x
C. MongoDB - 3.x.x

## Steps to Setup(Prerequisites: Install MongoDB, Maven3)

1. Clone the application**

2. Build and run the backend app using maven**
In Bash shell
mvn spring-boot:run
The backend server will start at <http://localhost:8080>.

3. Run the frontend app using npm**
On bash
cd angular-frontend
npm install
npm start
ng serve
Frontend server will run on <http://localhost:4200>
