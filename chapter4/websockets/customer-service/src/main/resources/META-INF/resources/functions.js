      var app = angular.module("customerManagement", []);
      angular.module('customerManagement').constant('SERVER_URL','/customers');

      //Controller Part
      app.controller("customerManagementController",  function ($scope, $http, SERVER_URL) {
        //Initialize page with default data which is blank in this example
        $scope.customers = [];
        $scope.form = {
          id: -1,
          name: "",
          surname: ""
        };
        //Now load the data from server
        _refreshPageData();
        //HTTP POST/PUT methods for add/edit customers
        $scope.update = function () {
          var method = "";
          var url = "";
          var data = {};
          if ($scope.form.id == -1) {
            //Id is absent so add customers - POST operation
            method = "POST";
            url = SERVER_URL;
            data.name = $scope.form.name;
            data.surname = $scope.form.surname;
          } else {
            //If Id is present, it's edit operation - PUT operation
            method = "PUT";
            url = SERVER_URL;

            data.id = $scope.form.id;
            data.name = $scope.form.name;
            data.surname = $scope.form.surname;
          }
          $http({
            method: method,
            url: url,
            data: angular.toJson(data),
            headers: {
              'Content-Type': 'application/json'
            }
          }).then(_success, _error);
        };
        //HTTP DELETE- delete customer by id
        $scope.remove = function (customer) {

          $http({
            method: 'DELETE',
            url: SERVER_URL+'?id='+customer.id
          }).then(_success, _error);
        };
        //In case of edit customers, populate form with customer data
        $scope.edit = function (customer) {
          $scope.form.name = customer.name;
          $scope.form.surname = customer.surname;
          $scope.form.id = customer.id;
        };
          /* Private Methods */
        //HTTP GET- get all customers collection
        function _refreshPageData() {
          $http({
            method: 'GET',
            url: SERVER_URL
          }).then(function successCallback(response) {
            $scope.customers = response.data;
          }, function errorCallback(response) {
            console.log(response.statusText);
          });
        }
        function _success(response) {
          _refreshPageData();
          _clearForm()
        }
        function _error(response) {
          alert(response.data.message || response.statusText);
        }
        //Clear the form
        function _clearForm() {
          $scope.form.name = "";
          $scope.form.surname = "";
          $scope.form.id = -1;
        }
      });
