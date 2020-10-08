angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';
    let page = 1;
    let pageCount;
    let contentOnPage = 10;

    $scope.fillTable = function () {
        console.log('fill');
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.Products = response.data;    // $scope - хранилище (глобальная область видимости)
            });
    };

    $scope.applyFilter = function(next) {
        $http.get(contextPath + '/api/v1/products/size')
            .then(function (response) {
                pageCount = response.data/contentOnPage;
            });

        if(next === 1 && page < pageCount){
            page++;
        }
        if(next === -1 && page > 1) {
            page--;
        }

        $http({
            url: contextPath + '/api/v1/products',
            method: "GET",
            params: {title: $scope.filterTitle,
                min_price: $scope.filterPriceMax,
                max_price: $scope.filterPriceMin,
                p: page
            }
        }).then(function (response) {
            $scope.Products = response.data;
        });
    }

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function (response) {
                // $scope.Products.push(response.data);
                $scope.newProduct = null;
                $scope.fillTable();
            });
    };

    $scope.fillTable();
});