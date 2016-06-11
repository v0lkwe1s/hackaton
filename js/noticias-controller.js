//angular.module('app.up').controller('noticiasCtrl', function($scope){
  // $scope.noticias = [
    //{titulo : 'PARQUE TECNOLÓGICO DE PATO BRANCO RECEBE O “I HACKATHON PATOLIVRE”', descricao : 'A inauguração do Parque Tecnológico de Pato Branco será no dia 01 de julho. Antes mesmo de ser entregue oficialmente à comunidade, a estrutura receberá um grande evento, o “I Hackathon PatoLivre: Cidades Digitais”. Promovido pelo Grupo Pato Livre, a iniciativa, que acontece nos dias 10 e 11 de junho, conta com apoio do Município de Pato Branco, através da Secretaria de Ciência, Tecnologia e Inovação e do Sebrae/PR.      O encontro terá início às 19h de sexta-feira (10) e segue, sem intervalo, até às 21h de sábado (11).'},
    //{titulo : 'PREFEITO ZUCCHI SE REÚNE COM PRESIDENTE DA CAMPUS PARTY VISANDO PARCERIA PARA A INVENTUM', descricao : 'Pato Branco está presente na Campus Party Brasil, considerada uma das maiores maratonas internacionais de tecnologia. Nesta quinta-feira (28), o prefeito Augustinho Zucchi se reuniu com o presidente do Instituto Campus Party, Francesco Farruggia, juntamente com comitiva formada pelo deputado estadual Guto Silva, o secretário municipal de Ciência, Tecnologia e Inovação, Géri Dutra e o diretor de projetos do Instituto de Desenvolvimento Tecnológico, de Pesquisa e Inovação do Sudoeste do Paraná (Idetep), Hugo Souza Ribeiro. O encontro representou o início da parceria que projeta levar uma extensão da Campus Party para a Feira de Ciência Tecnologia e Inovação de Pato Branco, a Inventum.'}
  // ];     
// });

var app = angular.module("app.up", ["firebase"]);
app.controller("noticiasCtrl", function($scope, $firebaseArray) {
  var ref = new Firebase("https://project-78368809104017595.firebaseio.com/descricao");
  // create a synchronized array
  // click on `index.html` above to see it used in the DOM!
  $scope.noticias = $firebaseArray(ref);
});