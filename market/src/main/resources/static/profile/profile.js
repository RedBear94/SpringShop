angular.module('app').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.submitUpdateProfile = function () {
        $http.put(contextPath + '/api/v1/profile', $scope.profile)
            .then(function (response) {
                $scope.loadProfile();
                alert('Профиль обновлен');
            });
    };

    $scope.loadProfile = function () {
        $http.get(contextPath + '/api/v1/profile')
            .then(function (response) {
                $scope.profile = response.data;
            });
    }

    $scope.loadProfile();
});