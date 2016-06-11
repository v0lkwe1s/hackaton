function criar(){
    var objeto = {
        nome : 'fulano',
        endereco : 'rua 1234',
        codigo : 123456
    };
    return objeto;
}
function testar(){
    var cliente = criar();
    recuperar(cliente);
}

function recuperar(cliente){
    alert('Nome = ' = cliente.nome + '\n'+ 
         'Endereço = ' + cliente.endereco + '\n'+
         'Código = ' + cliente.codigo);
}
