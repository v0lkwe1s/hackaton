var app = angular.module("app.up", ["firebase"]);
app.controller("projetosCtrl", function($scope, $firebaseObject) {
  var ref = new Firebase("https://project-78368809104017595.firebaseio.com/projetos");
  // create a synchronized array
  // click on `index.html` above to see it used in the DOM!
  $scope.projetos = $firebaseObject(ref);
});