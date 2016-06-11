var app = angular.module("app.up", ["firebase"]);
app.controller("cadastroProjetosCtrl", function($scope, $firebaseArray) {

   var ref = new Firebase("https://project-78368809104017595.firebaseio.com/projetos");

   $scope.titulo = '';
   $scope.descricao = '';

   var projetos = $firebaseArray(ref);

   $scope.postar = function() {
      projetos.push({ title: $scope.titulo,  description: $scope.descricao });
   };
}); 