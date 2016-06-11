
var app = angular.module("app.up", ["firebase"]);
app.controller("cadastroProjetosCtrl", function($scope, $firebaseArray) {

   var ref = new Firebase("https://project-78368809104017595.firebaseio.com/projetos");

   $scope.titulo = '';
   $scope.descricao = '';

   var projetos = $firebaseArray(ref);

   $scope.postar = function() {
      projetos.$add({ title: $scope.titulo,  description: $scope.descricao }).then(function(ref) {
         var id = ref.key();
         projetos.$indexFor(id);

         $scope.titulo = '';
         $scope.descricao = '';
      });
   };
});



                <div class="col-sm-9 col-md-10 col-md-offset-2 main" >
                    <div class="row">
                        <div class="col-xs-12 col-md-12" ng-view>
                            <div>
                                <h1>Postar notícia</h1>
                                <form>
                                    <div class="form-group">
                                        <label for="titulo">Título</label>
                                        <input ng-model="titulo" type="text" class="form-control" id="titulo" placeholder="Título" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="descricao">Descrição</label>
                                        <textarea ng-model="descricao" id="descricao" class="form-control" placeholder="Descrição" rows="6" required></textarea>
                                    </div>
                                    <button class="btn btn-default" ng-click="postar()">Postar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>