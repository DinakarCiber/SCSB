'use strict';

angular.module('recap.app-service', [])
    .factory('recapAPIService', ["$rootScope", "$http", "$window", function ($rootScope, $http, $window) {
        var recapAPI = {};


        recapAPI.getRestCall = function (serviceName, config) {
            return $http.get(serviceName, config);
        };


        recapAPI.postRestCall = function (serviceName, config, param) {
            return $http.post(serviceName, param, config);
        };


        recapAPI.putRestCall = function (serviceName, param) {
            return $http({
                method : "PUT",
                url : serviceName,
                data : param,
                headers : {
                    'Content-Type' : 'application/json'
                }
            });
        };


        recapAPI.deleteRestCall = function (serviceName) {
            console.log("delete rest call service called");
            return $http({
                method : "DELETE",
                url : serviceName
            });
        };


        recapAPI.isNotEmpty = function(object) {
            if(null !== object && undefined !== object && NaN !== object) {
                return true;
            }
            return false;
        };

        return recapAPI;
    }]);