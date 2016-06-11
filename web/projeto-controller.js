var app = angular.module("app.up", ["firebase"]);
app.controller("projetosCtrl", function($scope, $firebaseArray) {
  var ref = new Firebase("https://project-78368809104017595.firebaseio.com/projetos");
  // create a synchronized array
  // click on `index.html` above to see it used in the DOM!
  $scope.projetos = $firebaseArray(ref);
    
   $scope.votar = function($scope) {
      projetos.$add({ title: $scope.titulo,  description: $scope.descricao, favor: $scope.favor, contra : $scope.contra }).then(function(ref) {
         var id = ref.key();
         projetos.$indexFor(id);
            console.log($scope.favor);
         $scope.titulo = '';
         $scope.descricao = '';
      });
   };
});