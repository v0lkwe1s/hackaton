var app = angular.module("app.up", ["firebase"]);
app.controller("cadastroCtrl", function($scope, $firebaseArray) {

   var ref = new Firebase("https://project-78368809104017595.firebaseio.com");
   ref.createUser({
      email    : "bobtony@firebase.com",
      password : "correcthorsebatterystaple"
   }, function(error, userData) {
      if (error) {
         console.log("Error creating user:", error);
      } else {
         console.log("Successfully created user account with uid:", userData.uid);
      }
   });
});