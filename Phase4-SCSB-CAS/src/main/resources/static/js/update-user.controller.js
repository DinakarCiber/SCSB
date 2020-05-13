
var updateUser = angular.module('updateUser', ['recap.app-service']);

updateUser.controller('updateUserController', ['$scope','$rootScope', '$http','recapAPIService', function($scope,$rootScope, $http,recapAPIService) {

    $scope.updateUser = updateUser;

    function updateUser() {
        console.log("called");
        $scope.dataLoading = true;
        recapAPIService.postRestCall('recapApi/updateUser',{}, $scope.user).then(function (response) {
            var message = response.data;
            console.log(message);
            if (message.status) {
                console.log("Success");
                $scope.dataLoading = false;
                $scope.done = true;
                $rootScope.flash = {
                    message: "Successfully updated user.",
                    type: 'success'
                };
            } else {
                console.log("Failed");
                $scope.dataLoading = false;
                $rootScope.flash = {
                    message: "User update failed.",
                    type: 'error'
                };
            }
        });
    }

}]);
