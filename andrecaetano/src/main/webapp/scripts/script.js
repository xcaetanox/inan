var tarefasAPP = angular.module('tarefasAPP', ['ngRoute', 'angularUtils.directives.dirPagination', 'ngToast']);

var hostServico = "http://localhost:8080/"; //serviço do java 


tarefasAPP.config(function($routeProvider) {
    $routeProvider


        .when('/', {
            templateUrl: 'pages/home.html',
            controller: 'mainController'
        })
        .when('/cliente', {
            templateUrl: 'pages/cliente.html',
            controller: 'clienteController'
        })
        .when('/produto', {
            templateUrl: 'pages/produto.html',
            controller: 'produtoController'
        }).when('/produtocliente', {
            templateUrl: 'pages/produtocliente.html',
            controller: 'produtoClienteController'
        });


});


tarefasAPP.controller('mainController', function($scope, $http, ngToast) {
    $scope.procurar;
    $http.get(hostServico + 'movimento').success(function(data) {
        $scope.movimentos = data._embedded.movimentoProdutoClientes;

    });

    $http.get(hostServico + 'produto').success(function(data) {
        $scope.produtosParaMovimentar = data._embedded.produtoes;

    });

    $http.get(hostServico + 'cliente').success(function(data) {
        $scope.clienteParaMovimentar = data._embedded.clientes;

    });
    $scope.clienteSelecioando = null;
    $scope.setSelectedCli = function(cliente) {

        $scope.clienteSelecioando = cliente;
        angular.element('#searchCliente').modal('hide');

    };

    $scope.produtoSelecionado = null;
    $scope.setSelectedPrd = function(produto) {
        $scope.produtoSelecionado = produto;
        angular.element('#prdModal').modal('hide');

    };




    $scope.salvarMovimento = function(movimentoSalvar, operacao) {

        if (operacao != 'remove') {
            movimentoSalvar.cliente = $scope.clienteSelecioando;
            movimentoSalvar.produto = $scope.produtoSelecionado;
            movimentoSalvar.dataMovimento = new Date();




            console.log(angular.toJson(movimentoSalvar));


            $http.post(hostServico + "movimentacao", movimentoSalvar).
            success(function(data, status, headers, config) {
                $http.get(hostServico + 'movimento').success(function(data) {
                    $scope.movimentos = data._embedded.movimentoProdutoClientes;

                });
            }).
            error(function(data, status, headers, config) {
                console.log(data);
            });
        } else {
            console.log(movimentoSalvar.id);

            $http.post(hostServico + "movimentacaoremove", movimentoSalvar).
            success(function(data, status, headers, config) {
                $http.get(hostServico + 'movimento').success(function(data) {
                    $scope.movimentos = data._embedded.movimentoProdutoClientes;

                });

                ngToast.create({
                    className: 'success',
                    content: '<a href="#" class="">Laçamento removido com sucesso</a>',
                    timeout: 4000,
                    animation: 'fade',
                    dismissButton: true,
                    horizontalPosition: 'right'

                });


            }).
            error(function(data, status, headers, config) {
                console.log(data);
            });
        }
    };
});

tarefasAPP.controller('clienteController', function($scope, $http, ngToast) {
    $scope.procurar;
    $http.get(hostServico + 'cliente').success(function(data) {
        $scope.listaClientes = data._embedded.clientes;

    });
    $scope.salvarCliente = function(tarefa) {
        $http.post(hostServico + "clientecontroller", angular.toJson(tarefa)).
        success(function(data, status, headers, config) {
            $http.get(hostServico + 'cliente').success(function(data) {
                $scope.listaClientes = data._embedded.clientes;

            });
        }).
        error(function(data, status, headers, config) {
            ngToast.create({
                className: 'warning',
                content: '<a href="#" class="">' + data + '</a>',
                timeout: 4000,
                animation: 'fade',
                dismissButton: true,
                horizontalPosition: 'right'

            });
        });
    };

    $scope.atualizarCliente = function(cliente) {


        $http.post(hostServico + 'clientecontroller', angular.toJson(cliente)).
        success(function(data, status, headers, config) {
            $http.get(hostServico + 'cliente').success(function(data) {
                $scope.listaClientes = data._embedded.clientes;

            });

            ngToast.create({
                className: 'success',
                content: '<a href="#" class="">Cliente Salvo com sucesso</a>',
                timeout: 4000,
                animation: 'fade',
                dismissButton: true,
                horizontalPosition: 'right'

            });


        }).
        error(function(data, status, headers, config) {
            ngToast.create({
                className: 'warning',
                content: '<a href="#" class="">' + data + '</a>',
                timeout: 4000,
                animation: 'fade',
                dismissButton: true,
                horizontalPosition: 'right'

            });

            $http.get(hostServico + 'cliente').success(function(data) {
                $scope.listaClientes = data._embedded.clientes;

            });


        });
    };

    $scope.edit = function(cliente) {

        $scope.clienteSelecionado = cliente;
    }

});

tarefasAPP.controller('produtoController', function($scope, $http, ngToast) {

    $scope.procurar;
    $http.get(hostServico + 'produto').success(function(data) {
        $scope.listaProdutos = data._embedded.produtoes;

    });

    $scope.salvar = function(tarefa) {
        $http.post(hostServico + "produtocontroller", angular.toJson(tarefa)).
        success(function(data, status, headers, config) {
            $http.get(hostServico + 'produto').success(function(data) {
                $scope.listaProdutos = data._embedded.produtoes;

            });
        }).
        error(function(data, status, headers, config) {
            ngToast.create({
                className: 'warning',
                content: '<a href="#" class="">' + data + '</a>',
                timeout: 4000,
                animation: 'fade',
                dismissButton: true,
                horizontalPosition: 'right'

            });

            $http.get(hostServico + 'produto').success(function(data) {
                $scope.listaProdutos = data._embedded.produtoes;

            });
        });
    };

    $scope.atualizarproduto = function(produto) {


        $http.post(hostServico + "produtocontroller", angular.toJson(produto)).
        success(function(data, status, headers, config) {
            $http.get(hostServico + 'produto').success(function(data) {
                $scope.listaProdutos = data._embedded.produtoes;

            });

            ngToast.create({
                className: 'success',
                content: '<a href="#" class="">Produto Salvo com sucesso</a>',
                timeout: 4000,
                animation: 'fade',
                dismissButton: true,
                horizontalPosition: 'right'

            });


        }).
        error(function(data, status, headers, config) {
            ngToast.create({
                className: 'warning',
                content: '<a href="#" class="">Produto não foi Salvo com sucesso</a>',
                timeout: 4000,
                animation: 'fade',
                dismissButton: true,
                horizontalPosition: 'right'

            });

        });
    };

    $scope.edit = function(produto) {
        console.log(produto);
        $scope.produtoSelecionado = produto;
    }


});


tarefasAPP.controller('produtoClienteController', function($scope, $http) {
    $scope.procurar;
    $http.get(hostServico + 'produtocliente').success(function(data) {
        $scope.listaProdutosClienteSalvo = data._embedded.produtoClientes;

    });


});