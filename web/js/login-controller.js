var app = angular.module("app.up", ["firebase"]);
app.controller("loginCtrl", function($scope, $firebaseArray) {

   var ref = new Firebase("https://project-78368809104017595.firebaseio.com/");
   ref.authWithPassword({
      email    : "bobtony@firebase.com",
      password : "correcthorsebatterystaple"
   }, function(error, authData) {
      if (error) {
         console.log("Login Failed!", error);
      } else {
         console.log("Authenticated successfully with payload:", authData);
      }
   });
});