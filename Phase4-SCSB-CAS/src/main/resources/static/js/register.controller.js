
var register = angular.module('register', ['recap.app-service']);

register.controller('registerController', ['$scope','$rootScope', '$http','recapAPIService', function($scope,$rootScope, $http,recapAPIService) {

    $scope.register = register;

    function register() {
        console.log("called");
        $scope.dataLoading = true;
        recapAPIService.postRestCall('recapApi/createNewUser',{}, $scope.user).then(function (response) {
            var message = response.data;
            console.log(response.data);
            if (message.status) {
                console.log("Success");
                $scope.dataLoading = false;
                $scope.done = true;
                $rootScope.flash = {
                    message: "Successfully saved user.",
                    type: 'success'
                };
            } else {
                console.log("Failed");
                $rootScope.flash = {
                    message: "User creation failed.",
                    type: 'error'
                };
            }
        });
    }

}]);
